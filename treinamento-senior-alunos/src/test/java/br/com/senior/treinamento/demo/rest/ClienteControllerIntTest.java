package br.com.senior.treinamento.demo.rest;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.com.senior.treinamento.demo.DemoApplication;
import br.com.senior.treinamento.demo.entidades.ClienteEntity;
import br.com.senior.treinamento.demo.service.ClienteService;
import br.com.senior.treinamento.demo.util.TestUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { DemoApplication.class })
public class ClienteControllerIntTest {

	@Autowired
	private ClienteService clienteService;

	private MockMvc restClienteMockMvc;

	@Autowired
	private MappingJackson2HttpMessageConverter jacksonMessageConverter;

	private ClienteEntity cliente;

	@Before
	public void setup() {
		final ClienteController clienteController = new ClienteController(clienteService);
		this.restClienteMockMvc = MockMvcBuilders.standaloneSetup(clienteController)
				.setMessageConverters(jacksonMessageConverter).build();
	}

	@Before
	public void initTest() {
		cliente = criarEntidade();
	}

	private static ClienteEntity criarEntidade() {
		ClienteEntity cliente = new ClienteEntity();
		cliente.setNome("Maria");
		return cliente;
	}

	@Test
	public void criarCliente() throws IOException, Exception {
		restClienteMockMvc.perform(MockMvcRequestBuilders.post("/api/clientes")
				.contentType(TestUtil.APPLICATION_JSON_UTF8)
				.content(TestUtil.convertObjectToJsonBytes(cliente)))
		.andExpect(MockMvcResultMatchers.status().isCreated());
	}

	@Test
	public void buscarCliente() throws Exception {
		cliente = clienteService.salvar(cliente);

		restClienteMockMvc.perform(MockMvcRequestBuilders.get("/api/clientes/{id}", cliente.getId()))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().contentType(TestUtil.APPLICATION_JSON_UTF8))
			.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(cliente.getId().intValue()))
			.andExpect(MockMvcResultMatchers.jsonPath("$.nome").value(cliente.getNome()));
	}




}
