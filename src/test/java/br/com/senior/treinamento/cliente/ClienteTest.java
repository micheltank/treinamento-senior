package br.com.senior.treinamento.cliente;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import br.com.senior.treinamento.entidades.ClienteEntity;

public class ClienteTest {

	private ClienteEntity cliente;
	
	@Before
	public void setUp() {
		cliente = new ClienteEntity();
	}
	
	@Test
	public void getId() {
		Long id = 2L;
		cliente.setId(2L);
		
		assertEquals(id, cliente.getId());
	}
	
	@Test
	public void getNome() {
		String nome = "Joao";
		cliente.setNome("Joao");
		
		assertEquals(nome, cliente.getNome());
	}
}
