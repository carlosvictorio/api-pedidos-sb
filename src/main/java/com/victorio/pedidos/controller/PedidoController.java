package com.victorio.pedidos.controller;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.victorio.pedidos.enums.PedidoStatus;
import com.victorio.pedidos.model.Pedido;
import com.victorio.pedidos.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
	
	@Autowired
	private PedidoService pedidoService;
	
	@PostMapping
	public ResponseEntity<Pedido> criarPedido(@RequestBody Pedido pedido) {
		Pedido novoPedido = new Pedido(pedido.getDescription(), pedido.getPrice(), LocalDateTime.now(), pedido.getStatus());
		pedidoService.save(novoPedido);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novoPedido.getId()).toUri();
		
		return ResponseEntity.created(location).body(novoPedido);
	}
		
	@GetMapping
	public ResponseEntity<List<Pedido>> listarPedidos() {
		List<Pedido> pedidos = pedidoService.findAll();
		if(pedidos.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok().body(pedidos);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Pedido> buscarPedido(@PathVariable Long id) {
		Optional<Pedido> pedido =  pedidoService.findById(id);
		if(pedido.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.ok().body(pedido.get());
	}
	
	@PatchMapping("/{id}/status")
	public ResponseEntity<Pedido> atualizarPedido(@PathVariable Long id, @RequestBody PedidoStatus status) {
		Optional<Pedido> pedidoAntigo = pedidoService.findById(id);
		if(pedidoAntigo.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		Pedido pedidoAtualizado = pedidoAntigo.get();
		pedidoAtualizado.setStatus(status);
		pedidoService.save(pedidoAtualizado);
		
		return ResponseEntity.ok().body(pedidoAtualizado);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarPedido(@PathVariable Long id) {
		Optional<Pedido> pedido = pedidoService.findById(id);
		if(pedido.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		pedidoService.delete(id);
		return ResponseEntity.ok().build();
		
	}

}
