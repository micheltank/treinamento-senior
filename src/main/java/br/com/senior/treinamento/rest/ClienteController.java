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

import br.com.senior.treinamento.cliente.Cliente;
import br.com.senior.treinamento.service.ClienteService;

@RestController
@RequestMapping("/api")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	@PostMapping("/cliente")
	public ResponseEntity<Cliente> criar(@RequestBody Cliente cliente) throws URISyntaxException {
		cliente = clienteService.salvar(cliente);
		return new ResponseEntity<Cliente>(cliente, HttpStatus.CREATED);
	}
	
	@PutMapping("/cliente")
	public ResponseEntity<Void> alterar(@RequestBody Cliente cliente) throws URISyntaxException {
		if (clienteService.buscarPorId(cliente.getId()) == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		cliente = clienteService.salvar(cliente);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/cliente/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		clienteService.deletar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
    @GetMapping("/clientes")
    public ResponseEntity<List<Cliente>> buscarClientes() {
    	List<Cliente> clientes = clienteService.buscarClientes();
        return new ResponseEntity<List<Cliente>>(clientes, HttpStatus.OK);
    }

    @GetMapping("/cliente/{id}")
    public ResponseEntity<Cliente> buscarCliente(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteService.buscarPorId(id);
        if (cliente.isPresent()) {
        	return new ResponseEntity<Cliente>(cliente.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
}
