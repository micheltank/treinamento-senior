package br.com.senior.treinamento.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senior.treinamento.cliente.Cliente;
import br.com.senior.treinamento.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente salvar(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	public Optional<Cliente> buscarPorId(Long id) {
		return clienteRepository.findById(id);
	}

	public void deletar(Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		if (cliente.isPresent()) {
			clienteRepository.delete(cliente.get());
		}
	}

	public List<Cliente> buscarClientes() {
		List<Cliente> clientes = new ArrayList<>();
		for (Cliente cliente : clienteRepository.findAll()) {
			clientes.add(cliente);
		}
		return clientes;
	}

}
