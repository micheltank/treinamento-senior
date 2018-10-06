package br.com.senior.treinamento.entidades.pedido;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.senior.treinamento.TreinamentoApplication;
import br.com.senior.treinamento.entidades.ClienteEntity;
import br.com.senior.treinamento.entidades.PedidoEntity;
import br.com.senior.treinamento.repository.PedidoRepository;
import br.com.senior.treinamento.service.ClienteService;
import br.com.senior.treinamento.service.PedidoService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TreinamentoApplication.class})
public class PedidoTest {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private PedidoService pedidoService;
	
	private ClienteEntity cliente;
	private PedidoEntity pedido;
	
	private static ClienteEntity criarCliente() {
		ClienteEntity clienteCriado = new ClienteEntity();
		clienteCriado.setNome("Joao");
		return clienteCriado;
	}
	
	private static PedidoEntity criarPedido(ClienteEntity cliente) {
		PedidoEntity pedidoCriado = new PedidoEntity();
		pedidoCriado.setCliente(cliente);
		pedidoCriado.setData(LocalDateTime.now());
		return pedidoCriado;
	}
	
	@Before
	public void initTest() {
		cliente = criarCliente();
		pedido = criarPedido(cliente);
	}
	
	@Test
	public void buscarClientes() {
		cliente = clienteService.salvar(cliente);
		pedidoService.salvar(pedido);
		
		long quantidadeRegistrosAntesDeCriar = pedidoRepository.count();
		
		pedidoService.buscarPedidos(cliente.getId());
		
		long quantidadeRegistrosDepoisDeCriar = pedidoRepository.count();
		
		assertThat(quantidadeRegistrosDepoisDeCriar).isEqualTo(quantidadeRegistrosAntesDeCriar);
	}
}
