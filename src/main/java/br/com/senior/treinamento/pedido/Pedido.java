package br.com.senior.treinamento.pedido;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.senior.treinamento.cliente.Cliente;
import br.com.senior.treinamento.pedido.item.PedidoItem;

@Entity
@Table(name = "pedido")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Cliente cliente;
	
	@Column
	private LocalDateTime data;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="pedido")
	private List<PedidoItem> itens;
}
