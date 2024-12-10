package com.victorio.pedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.victorio.pedidos.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
