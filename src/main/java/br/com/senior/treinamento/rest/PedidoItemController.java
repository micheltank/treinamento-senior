package br.com.senior.treinamento.rest;

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

import br.com.senior.treinamento.pedido.item.PedidoItem;
import br.com.senior.treinamento.service.PedidoItemService;

@RestController
@RequestMapping("/api")
public class PedidoItemController {
	@Autowired
	private PedidoItemService pedidoItemService;
	
	@PostMapping("/pedidoItem")
	public ResponseEntity<PedidoItem> criar(@RequestBody PedidoItem pedidoItem) throws URISyntaxException {
		pedidoItem = pedidoItemService.salvar(pedidoItem);
		return new ResponseEntity<PedidoItem>(pedidoItem, HttpStatus.CREATED);
	}
	
	@PutMapping("/pedidoItem")
	public ResponseEntity<Void> alterar(@RequestBody PedidoItem pedidoItem) throws URISyntaxException {
		if (pedidoItemService.buscarPorId(pedidoItem.getId()) == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		pedidoItem = pedidoItemService.salvar(pedidoItem);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/pedidoItem/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		pedidoItemService.deletar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
    @GetMapping("/pedidoItens")
    public ResponseEntity<List<PedidoItem>> buscarPedidoItems() {
    	List<PedidoItem> pedidoItems = pedidoItemService.buscarPedidoItems();
        return new ResponseEntity<List<PedidoItem>>(pedidoItems, HttpStatus.OK);
    }

    @GetMapping("/pedidoItem/{id}")
    public ResponseEntity<PedidoItem> buscarPedidoItem(@PathVariable Long id) {
        Optional<PedidoItem> pedidoItem = pedidoItemService.buscarPorId(id);
        if (pedidoItem.isPresent()) {
        	return new ResponseEntity<PedidoItem>(pedidoItem.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
