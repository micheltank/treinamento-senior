package br.com.senior.treinamento.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.senior.treinamento.pedido.item.PedidoItem;

@Repository
public interface PedidoItemRepository extends CrudRepository<PedidoItem, Long> {

}
