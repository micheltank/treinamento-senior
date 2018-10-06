package br.com.senior.treinamento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.senior.treinamento.entidades.ClienteEntity;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {

	public List<ClienteEntity> findByNomeIgnoreCase(String nome);
} 
