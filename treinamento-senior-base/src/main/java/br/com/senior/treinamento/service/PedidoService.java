package br.com.senior.treinamento.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senior.treinamento.entidades.PedidoEntity;
import br.com.senior.treinamento.repository.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	public PedidoEntity salvar(PedidoEntity pedido) {
		return pedidoRepository.save(pedido);
	}

	public Optional<PedidoEntity> buscarPorId(Long id) {
		return pedidoRepository.findById(id);
	}

	public void deletar(Long id) {
		Optional<PedidoEntity> pedido = pedidoRepository.findById(id);
		if (pedido.isPresent()) {
			pedidoRepository.delete(pedido.get());
		}
	}

	public List<PedidoEntity> buscarPedidos() {
		List<PedidoEntity> pedidos = new ArrayList<>();
		for (PedidoEntity pedido : pedidoRepository.findAll()) {
			pedidos.add(pedido);
		}
		return pedidos;
	}

	public List<PedidoEntity> buscarPedidos(Long clienteId) {
		List<PedidoEntity> pedidos = new ArrayList<>();
		for (PedidoEntity pedido : pedidoRepository.buscarPedidosDoCliente(clienteId)) {
			pedidos.add(pedido);
		}
		return pedidos;
	}
}
