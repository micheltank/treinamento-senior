package br.com.senior.treinamento.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.senior.treinamento.demo.entidades.ClienteEntity;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {

	public ClienteEntity findByNome(String nome);
}
