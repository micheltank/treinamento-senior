package br.com.senior.treinamento.repository;

import java.util.List;

import br.com.senior.treinamento.entidades.PedidoEntity;

public interface PedidoRepositoryCustom {

	public List<PedidoEntity> buscarPedidosDoCliente(Long clienteId);
}
