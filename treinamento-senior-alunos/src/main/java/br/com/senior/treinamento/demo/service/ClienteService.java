package br.com.senior.treinamento.demo.service;

import java.util.List;
import java.util.Optional;

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

	public Optional<ClienteEntity> buscarPorId(Long id) {
		return clienteRepository.findById(id);
	}

	public void deletar(Long id) {
		clienteRepository.deleteById(id);
	}

	public List<ClienteEntity> buscarClientes() {
		return clienteRepository.findAll();
	}

	public List<ClienteEntity> buscarPorNome(String nome) {
		return clienteRepository.findByNomeIgnoreCase(nome);
	}

}
