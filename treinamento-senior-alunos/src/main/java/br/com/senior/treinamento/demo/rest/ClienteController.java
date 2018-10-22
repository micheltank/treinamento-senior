package br.com.senior.treinamento.demo.rest;

import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

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

import br.com.senior.treinamento.demo.entidades.ClienteEntity;
import br.com.senior.treinamento.demo.service.ClienteService;

@RestController
@RequestMapping("/api")
public class ClienteController {

	private ClienteService clienteService;
	
	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}
	
	@PostMapping("/clientes")
	public ResponseEntity<ClienteEntity> criar(@RequestBody ClienteEntity cliente) {
		System.out.println("Criando um cliente");
		cliente = clienteService.salvar(cliente);
		return new ResponseEntity<ClienteEntity>(cliente, HttpStatus.CREATED);
	}
	
	@PutMapping("/clientes")
	public ResponseEntity<Void> alterar(@RequestBody ClienteEntity cliente) {
		System.out.println("Alterando cliente");
		if (!clienteService.buscarPorId(cliente.getId()).isPresent()) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		clienteService.salvar(cliente);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/clientes/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		System.out.println("Deletando cliente");
		if (!clienteService.buscarPorId(id).isPresent()) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		clienteService.deletar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/clientes/{id}") 
	public ResponseEntity<ClienteEntity> buscarCliente(@PathVariable Long id) {
		System.out.println("Buscando cliente");
		Optional<ClienteEntity> cliente = clienteService.buscarPorId(id);
		if (cliente.isPresent()) {
			return new ResponseEntity<ClienteEntity>(cliente.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/clientes")
	public ResponseEntity<List<ClienteEntity>> buscarClientes() {
		System.out.println("Buscando clientes");
		List<ClienteEntity> clientes = clienteService.buscarClientes();
		return new ResponseEntity<List<ClienteEntity>>(clientes, HttpStatus.OK);
	}
	
	@GetMapping("/clientes/buscar")
	public ResponseEntity<List<ClienteEntity>> buscarCliente(@PathParam(value = "nome") String nome) {
		List<ClienteEntity> clientes = clienteService.buscarPorNome(nome);
		return new ResponseEntity<List<ClienteEntity>>(clientes, HttpStatus.OK);
	}
}
