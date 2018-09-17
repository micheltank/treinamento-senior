package br.com.senior.treinamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.senior.treinamento.cliente.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
