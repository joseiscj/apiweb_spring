package br.com.project.controller;

import java.awt.PageAttributes.MediaType;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.project.model.Cliente;

@RestController
public class ClienteController {
		
		Map<Integer, Cliente> clientes = new HashMap<>();;
		Integer proximoId=1;
		
		//Negocios
		private Cliente cadastrar(Cliente cliente) {
			
			//criar id
			cliente.setId(proximoId);
			this.proximoId++;
			
			clientes.put(cliente.getId(), cliente);
			
			return cliente;
		}
		
		private Collection<Cliente> buscarTodos() {
			return clientes.values();
		}
		
		public void excluir(Cliente cliente) {
			clientes.remove(cliente.getId());
		}
		
		private Cliente buscarPorId(Integer id) {
			return clientes.get(id);
		}
		
		private Cliente alterar(Cliente cliente) {
			clientes.put(cliente.getId(), cliente);
			return cliente;
		}
		
		//End points
		@RequestMapping(method=RequestMethod.POST, value="/clientes", consumes=org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Cliente> cadastrarCliente(@RequestBody Cliente cliente) {
			Cliente clienteSalvo = cadastrar(cliente);
			return new ResponseEntity<>(clienteSalvo, HttpStatus.CREATED);
		}
		
		@RequestMapping(method=RequestMethod.GET, value="/clientes", produces=org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Collection<Cliente>> buscarTodosClientes() {
			Collection<Cliente> clientesBuscados = buscarTodos();
			return new ResponseEntity<>(clientesBuscados, HttpStatus.OK);
		}
		
		@RequestMapping(method=RequestMethod.DELETE, value="/clientes/{id}")
		public ResponseEntity<Cliente> excluirCliente(@PathVariable Integer id) {
			Cliente clienteEncontrado = buscarPorId(id);
			
			if (clienteEncontrado == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			excluir(clienteEncontrado);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		@RequestMapping(method=RequestMethod.PUT, value="/clientes", consumes=org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Cliente> alterarCliente(@RequestBody Cliente cliente) {
			Cliente clienteAlterado = alterar(cliente);
			return new ResponseEntity<>(clienteAlterado, HttpStatus.OK);
		}
		
}
