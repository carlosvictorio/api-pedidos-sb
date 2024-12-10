package com.victorio.pedidos.model;

import java.time.LocalDateTime;

import com.victorio.pedidos.enums.PedidoStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pedido", schema = "public")
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String description;
	private Double price;
	private LocalDateTime creationDate;
	
	@Enumerated(EnumType.STRING)
	private PedidoStatus status;
	
	public Pedido() {
	}
	
	public Pedido(String description, Double price, LocalDateTime creationDate, PedidoStatus status) {
		this.description = description;
		this.price = price;
		this.creationDate = creationDate;
		this.status = status;
	}
	
	public Long getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public LocalDateTime getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}
	public PedidoStatus getStatus() {
		return status;
	}
	public void setStatus(PedidoStatus status) {
		this.status = status;
	}

}
