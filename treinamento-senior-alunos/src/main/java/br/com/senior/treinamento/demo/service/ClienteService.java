package br.com.senior.treinamento.demo.service;

import org.springframework.stereotype.Service;

import br.com.senior.treinamento.demo.entidades.ClienteEntity;
import br.com.senior.treinamento.demo.repository.ClienteRepository;

@Service
public class ClienteService {

	private ClienteRepository clienteRepository;

	public ClienteService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	public ClienteEntity salvar(ClienteEntity cliente) {
		return clienteRepository.save(cliente);
	}

}
