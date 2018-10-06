package br.com.senior.treinamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.senior.treinamento.entidades.PedidoItemEntity;

@Repository
public interface PedidoItemRepository extends JpaRepository<PedidoItemEntity, Long> {

}
