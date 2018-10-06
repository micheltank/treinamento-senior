package br.com.senior.treinamento.rest;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.ValidationException;
import javax.websocket.server.PathParam;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import br.com.senior.treinamento.entidades.ClienteEntity;
import br.com.senior.treinamento.service.ClienteService;

@RestController
@RequestMapping("/api")
public class ClienteController {

	private ClienteService clienteService;
	
	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}
	
	@PostMapping("/clientes")
	public ResponseEntity<ClienteEntity> criar(@RequestBody ClienteEntity cliente) throws URISyntaxException {
		cliente = clienteService.salvar(cliente);
		return new ResponseEntity<ClienteEntity>(cliente, HttpStatus.CREATED);
	}

	@ExceptionHandler(ValidationException.class)
	public final ResponseEntity<Error> handleValidationException(ValidationException ex, WebRequest request) {
		return new ResponseEntity<>(new Error(ex.getMessage()), HttpStatus.BAD_REQUEST);
	}

	@PutMapping("/clientes")
	public ResponseEntity<Void> alterar(@RequestBody ClienteEntity cliente) throws URISyntaxException {
		if (clienteService.buscarPorId(cliente.getId()) == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		cliente = clienteService.salvar(cliente);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/clientes/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		clienteService.deletar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/clientes")
	public ResponseEntity<List<ClienteEntity>> buscarClientes() {
		List<ClienteEntity> clientes = clienteService.buscarClientes();
		return new ResponseEntity<List<ClienteEntity>>(clientes, HttpStatus.OK);
	}

	@GetMapping("/clientes/{id}")
	public ResponseEntity<ClienteEntity> buscarCliente(@PathVariable Long id) {
		Optional<ClienteEntity> cliente = clienteService.buscarPorId(id);
		if (cliente.isPresent()) {
			return new ResponseEntity<ClienteEntity>(cliente.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/clientes/buscar")
	public ResponseEntity<List<ClienteEntity>> buscarCliente(@PathParam(value = "nome") String nome) {
		List<ClienteEntity> clientes = clienteService.buscarPeloNome(nome);
		return new ResponseEntity<List<ClienteEntity>>(clientes, HttpStatus.OK);
	}

}
