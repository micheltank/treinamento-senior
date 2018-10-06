package br.com.senior.treinamento.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senior.treinamento.entidades.PedidoItemEntity;
import br.com.senior.treinamento.repository.PedidoItemRepository;

@Service
public class PedidoItemService {

	@Autowired
	private PedidoItemRepository pedidoItemRepository;
	
	public PedidoItemEntity salvar(PedidoItemEntity pedidoItem) {
		return pedidoItemRepository.save(pedidoItem);
	}

	public Optional<PedidoItemEntity> buscarPorId(Long id) {
		return pedidoItemRepository.findById(id);
	}

	public void deletar(Long id) {
		Optional<PedidoItemEntity> pedidoItem = pedidoItemRepository.findById(id);
		if (pedidoItem.isPresent()) {
			pedidoItemRepository.delete(pedidoItem.get());
		}
	}

	public List<PedidoItemEntity> buscarPedidoItems() {
		List<PedidoItemEntity> pedidoItems = new ArrayList<>();
		for (PedidoItemEntity pedidoItem : pedidoItemRepository.findAll()) {
			pedidoItems.add(pedidoItem);
		}
		return pedidoItems;
	}
}
