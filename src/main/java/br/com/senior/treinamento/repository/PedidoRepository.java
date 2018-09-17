package br.com.senior.treinamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.senior.treinamento.pedido.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
