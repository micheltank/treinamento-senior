package br.com.senior.treinamento.demo.rest;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.senior.treinamento.demo.entidades.PedidoItemEntity;
import br.com.senior.treinamento.demo.service.PedidoItemService;

@RestController
@RequestMapping("/api")
public class PedidoItemController {
	@Autowired
	private PedidoItemService pedidoItemService;
	
	@PostMapping("/pedidoItens")
	public ResponseEntity<PedidoItemEntity> criar(@RequestBody PedidoItemEntity pedidoItem) throws URISyntaxException {
		pedidoItem = pedidoItemService.salvar(pedidoItem);
		return new ResponseEntity<PedidoItemEntity>(pedidoItem, HttpStatus.CREATED);
	}
	
	@PutMapping("/pedidoItens")
	public ResponseEntity<Void> alterar(@RequestBody PedidoItemEntity pedidoItem) throws URISyntaxException {
		if (pedidoItemService.buscarPorId(pedidoItem.getId()) == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		pedidoItem = pedidoItemService.salvar(pedidoItem);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/pedidoItens/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		pedidoItemService.deletar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
    @GetMapping("/pedidoItens")
    public ResponseEntity<List<PedidoItemEntity>> buscarPedidoItems() {
    	List<PedidoItemEntity> pedidoItems = pedidoItemService.buscarPedidoItems();
        return new ResponseEntity<List<PedidoItemEntity>>(pedidoItems, HttpStatus.OK);
    }

    @GetMapping("/pedidoItens/{id}")
    public ResponseEntity<PedidoItemEntity> buscarPedidoItem(@PathVariable Long id) {
        Optional<PedidoItemEntity> pedidoItem = pedidoItemService.buscarPorId(id);
        if (pedidoItem.isPresent()) {
        	return new ResponseEntity<PedidoItemEntity>(pedidoItem.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
