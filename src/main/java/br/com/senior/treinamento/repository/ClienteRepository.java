package br.com.senior.treinamento.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.senior.treinamento.cliente.Cliente;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {

}
