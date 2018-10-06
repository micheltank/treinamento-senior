package br.com.senior.treinamento.entidades;

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
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "pedido")
public class PedidoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@NotNull
	private ClienteEntity cliente;
	
	@Column
	@NotNull
	private LocalDateTime data;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="pedido")
	@JsonIgnore
	private List<PedidoItemEntity> itens;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ClienteEntity getCliente() {
		return cliente;
	}

	public void setCliente(ClienteEntity cliente) {
		this.cliente = cliente;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public List<PedidoItemEntity> getItens() {
		return itens;
	}

	public void setItens(List<PedidoItemEntity> itens) {
		this.itens = itens;
	}
	
}
