package br.com.senior.treinamento.demo.rest;

import java.util.ArrayList;
import java.util.List;

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
		//TODO: Implementar alteração
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/clientes/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		System.out.println("Deletando cliente");
		//TODO: Implementar exclusão
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/clientes/{id}") 
	public ResponseEntity<ClienteEntity> buscarCliente(@PathVariable Long id) {
		System.out.println("Buscando cliente");
		//TODO: Implementar busca de cliente
		ClienteEntity cliente = new ClienteEntity();
		cliente.setId(id);
		cliente.setNome("Pedro");
		return new ResponseEntity<ClienteEntity>(cliente, HttpStatus.OK);
	}
	
	@GetMapping("/clientes")
	public ResponseEntity<List<ClienteEntity>> buscarClientes() {
		System.out.println("Buscando clientes");
		//TODO: Implementar busca de clientes
		List<ClienteEntity> clientes = new ArrayList<ClienteEntity>();
		ClienteEntity clientePedro = new ClienteEntity();
		clientePedro.setId(1L);
		clientePedro.setNome("Pedro");
		clientes.add(clientePedro);
		
		ClienteEntity clienteJoao = new ClienteEntity();
		clienteJoao.setId(2L);
		clienteJoao.setNome("Joao");
		clientes.add(clienteJoao);
		
		return new ResponseEntity<List<ClienteEntity>>(clientes, HttpStatus.OK);
	}
}
