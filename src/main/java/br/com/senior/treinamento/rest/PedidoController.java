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

import br.com.senior.treinamento.pedido.Pedido;
import br.com.senior.treinamento.service.PedidoService;

@RestController
@RequestMapping("/api")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;
	
	@PostMapping("/pedido")
	public ResponseEntity<Pedido> criar(@RequestBody Pedido pedido) throws URISyntaxException {
		pedido = pedidoService.salvar(pedido);
		return new ResponseEntity<Pedido>(pedido, HttpStatus.CREATED);
	}
	
	@PutMapping("/pedido")
	public ResponseEntity<Void> alterar(@RequestBody Pedido pedido) throws URISyntaxException {
		if (pedidoService.buscarPorId(pedido.getId()) == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		pedido = pedidoService.salvar(pedido);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/pedido/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		pedidoService.deletar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
    @GetMapping("/pedidos")
    public ResponseEntity<List<Pedido>> buscarPedidos() {
    	List<Pedido> pedidos = pedidoService.buscarPedidos();
        return new ResponseEntity<List<Pedido>>(pedidos, HttpStatus.OK);
    }

    @GetMapping("/pedido/{id}")
    public ResponseEntity<Pedido> buscarPedido(@PathVariable Long id) {
        Optional<Pedido> pedido = pedidoService.buscarPorId(id);
        if (pedido.isPresent()) {
        	return new ResponseEntity<Pedido>(pedido.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        
    }
}
