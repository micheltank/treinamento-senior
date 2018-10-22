package br.com.senior.treinamento.demo.entidades;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.junit.Before;
import org.junit.Test;

public class ClienteEntityTest {

	private ClienteEntity cliente;

	@Before
	public void setUp() {
		cliente = new ClienteEntity();
	}

	@Test
	public void getId() {
		Long id = 2L;
		cliente.setId(id);

		assertEquals(id, cliente.getId());
	}

	@Test
	public void getNome() {
		String nome = "Joao";
		cliente.setNome(nome);

		assertSame(nome, cliente.getNome());
	}

}
