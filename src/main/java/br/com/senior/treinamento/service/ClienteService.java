package br.com.senior.treinamento.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senior.treinamento.entidades.ClienteEntity;
import br.com.senior.treinamento.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public ClienteEntity salvar(ClienteEntity cliente) {
		return clienteRepository.save(cliente);
	}

	public Optional<ClienteEntity> buscarPorId(Long id) {
		return clienteRepository.findById(id);
	}

	public void deletar(Long id) {
		Optional<ClienteEntity> cliente = clienteRepository.findById(id);
		if (cliente.isPresent()) {
			clienteRepository.delete(cliente.get());
		}
	}

	public List<ClienteEntity> buscarClientes() {
		List<ClienteEntity> clientes = new ArrayList<>();
		for (ClienteEntity cliente : clienteRepository.findAll()) {
			clientes.add(cliente);
		}
		return clientes;
	}

}
