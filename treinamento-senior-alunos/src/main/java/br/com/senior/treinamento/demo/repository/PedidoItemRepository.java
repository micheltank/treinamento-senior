package br.com.senior.treinamento.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.senior.treinamento.demo.entidades.PedidoItemEntity;

@Repository
public interface PedidoItemRepository extends JpaRepository<PedidoItemEntity, Long> {

}
