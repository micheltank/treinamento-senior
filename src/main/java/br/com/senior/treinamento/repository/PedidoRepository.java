package br.com.senior.treinamento.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.senior.treinamento.pedido.Pedido;

@Repository
public interface PedidoRepository extends CrudRepository<Pedido, Long> {

}
