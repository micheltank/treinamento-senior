package br.com.senior.treinamento.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.querydsl.jpa.impl.JPAQuery;

import br.com.senior.treinamento.entidades.PedidoEntity;
import br.com.senior.treinamento.entidades.QPedidoEntity;

public class PedidoRepositoryCustomImpl implements PedidoRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;

	private QPedidoEntity pedido = QPedidoEntity.pedidoEntity;

	@Override
	public List<PedidoEntity> buscarPedidosDoCliente(Long clienteId) {
		JPAQuery<PedidoEntity> query = new JPAQuery<PedidoEntity>(entityManager);
		return query.select(pedido).from(pedido).where(pedido.cliente.id.eq(clienteId)).fetch();
	}
}
