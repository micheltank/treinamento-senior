package br.com.senior.treinamento.entidades.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.com.senior.treinamento.TreinamentoApplication;
import br.com.senior.treinamento.entidades.ClienteEntity;
import br.com.senior.treinamento.repository.ClienteRepository;
import br.com.senior.treinamento.rest.ClienteController;
import br.com.senior.treinamento.service.ClienteService;
import br.com.senior.treinamento.util.TestUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TreinamentoApplication.class})
public class ClienteControllerIntTest {

	private static final String DEFAULT_NOME = "Joao";
	private static final String UPDATED_NOME = "Joao da Silva";
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;
	
	private MockMvc restClienteMockMvc;
	
	private ClienteEntity cliente;
	
	@Before
	public void setup() {
		final ClienteController clienteController = new ClienteController(clienteService);
		this.restClienteMockMvc = MockMvcBuilders.standaloneSetup(clienteController)
				.setMessageConverters(jacksonMessageConverter).build();
	}
	
	private static ClienteEntity criarEntidade() {
		ClienteEntity cliente = new ClienteEntity();
		cliente.setNome("Joao");
		return cliente;
	}
	
	@Before
	public void initTest() {
		cliente = criarEntidade();
	}
	
	@Test
	@Transactional
	public void criarCliente() throws Exception {
		long quantidadeRegistrosAntesDeCriar = clienteRepository.count();
		
		restClienteMockMvc.perform(post("/api/cliente")
				.contentType(TestUtil.APPLICATION_JSON_UTF8)
				.content(TestUtil.convertObjectToJsonBytes(cliente)))
			.andExpect(status().isCreated());
		
		long quantidadeRegistrosDepoisDeCriar = clienteRepository.count();
		
		assertThat(quantidadeRegistrosDepoisDeCriar).isEqualTo(quantidadeRegistrosAntesDeCriar + 1);
		assertThat(cliente.getNome()).isEqualTo(DEFAULT_NOME);
	}
	
	@Test
	public void buscarClientes() throws Exception {
		clienteService.salvar(cliente);
		
		restClienteMockMvc.perform(get("/api/clientes"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$.[*].id").value(hasItem(cliente.getId().intValue())))
			.andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())));
	}
	
	@Test
	public void buscarCliente() throws Exception {
		cliente = clienteService.salvar(cliente);
		
		restClienteMockMvc.perform(get("/api/cliente/{id}", cliente.getId()))
			.andExpect(status().isOk())
			.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$.id").value(cliente.getId().intValue()))
			.andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()));
	}
	
	@Test
	public void atualizarCliente() throws IOException, Exception {
		cliente = clienteService.salvar(cliente);
		cliente.setNome("Joao da Silva");
		
		restClienteMockMvc.perform(put("/api/cliente")
				.contentType(TestUtil.APPLICATION_JSON_UTF8)
				.content(TestUtil.convertObjectToJsonBytes(cliente)))
			.andExpect(status().isNoContent());
		
		Optional<ClienteEntity> clienteAlterado = clienteService.buscarPorId(cliente.getId());
		assertThat(clienteAlterado.get().getNome()).isEqualTo(UPDATED_NOME);
	}
	
	@Test
	public void deletarCliente() throws Exception {
		cliente = clienteService.salvar(cliente);
		
		long quantidadeRegistrosAntesDeCriar = clienteRepository.count();
		
		restClienteMockMvc.perform(delete("/api/cliente/{id}", cliente.getId()))
			.andExpect(status().isNoContent());

		long quantidadeRegistrosDepoisDeCriar = clienteRepository.count();
		
		assertThat(quantidadeRegistrosDepoisDeCriar).isEqualTo(quantidadeRegistrosAntesDeCriar - 1);		
	}
}
