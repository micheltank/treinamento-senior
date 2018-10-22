package br.com.senior.treinamento.demo.entidades;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.senior.treinamento.demo.DemoApplication;
import br.com.senior.treinamento.demo.repository.PedidoRepository;
import br.com.senior.treinamento.demo.service.ClienteService;
import br.com.senior.treinamento.demo.service.PedidoService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { DemoApplication.class })
public class PedidoTest {

	@Autowired
	private PedidoService pedidoService;

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Test
	public void criarPedidoTest() {
		PedidoEntity pedido = criarPedido();
		assertNotNull(pedido.getId());
	}

	private PedidoEntity criarPedido() {
		PedidoEntity pedido = new PedidoEntity();
		pedido.setData(LocalDateTime.now());
		pedido.setCliente(criarCliente());
		pedidoService.salvar(pedido);
		return pedido;
	}

	private ClienteEntity criarCliente() {
		ClienteEntity clienteCriado = new ClienteEntity();
		clienteCriado.setNome("Joao");
		clienteService.salvar(clienteCriado);
		return clienteCriado;
	}

	@Test
	public void buscarPedido() {
		criarPedido();
		criarPedido();
		long quantidadePedidoDepoisDeCriar = pedidoRepository.count();

		assertEquals(quantidadePedidoDepoisDeCriar, 2L);
	}
}
