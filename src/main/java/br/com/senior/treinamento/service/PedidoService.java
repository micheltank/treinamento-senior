package br.com.senior.treinamento.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senior.treinamento.pedido.Pedido;
import br.com.senior.treinamento.repository.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	public Pedido salvar(Pedido pedido) {
		return pedidoRepository.save(pedido);
	}

	public Optional<Pedido> buscarPorId(Long id) {
		return pedidoRepository.findById(id);
	}

	public void deletar(Long id) {
		Optional<Pedido> pedido = pedidoRepository.findById(id);
		if (pedido.isPresent()) {
			pedidoRepository.delete(pedido.get());
		}
	}

	public List<Pedido> buscarPedidos() {
		List<Pedido> pedidos = new ArrayList<>();
		for (Pedido pedido : pedidoRepository.findAll()) {
			pedidos.add(pedido);
		}
		return pedidos;
	}
}
