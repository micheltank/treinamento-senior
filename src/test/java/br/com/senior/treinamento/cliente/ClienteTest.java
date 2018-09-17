package br.com.senior.treinamento.cliente;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ClienteTest {

	private Cliente cliente;
	
	@Before
	public void setUp() {
		cliente = new Cliente();
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
