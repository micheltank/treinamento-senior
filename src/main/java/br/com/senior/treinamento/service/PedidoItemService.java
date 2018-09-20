package br.com.senior.treinamento.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senior.treinamento.pedido.item.PedidoItem;
import br.com.senior.treinamento.repository.PedidoItemRepository;

@Service
public class PedidoItemService {

	@Autowired
	private PedidoItemRepository pedidoItemRepository;
	
	public PedidoItem salvar(PedidoItem pedidoItem) {
		return pedidoItemRepository.save(pedidoItem);
	}

	public Optional<PedidoItem> buscarPorId(Long id) {
		return pedidoItemRepository.findById(id);
	}

	public void deletar(Long id) {
		Optional<PedidoItem> pedidoItem = pedidoItemRepository.findById(id);
		if (pedidoItem.isPresent()) {
			pedidoItemRepository.delete(pedidoItem.get());
		}
	}

	public List<PedidoItem> buscarPedidoItems() {
		List<PedidoItem> pedidoItems = new ArrayList<>();
		for (PedidoItem pedidoItem : pedidoItemRepository.findAll()) {
			pedidoItems.add(pedidoItem);
		}
		return pedidoItems;
	}
}
