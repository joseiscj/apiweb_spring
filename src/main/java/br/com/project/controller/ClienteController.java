package br.com.project.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.project.model.Cliente;
import br.com.project.service.ClienteService;

@RestController
public class ClienteController {
		
		@Autowired
		ClienteService clienteService;
		
		//End points
		@RequestMapping(method=RequestMethod.POST, value="/clientes", consumes=org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Cliente> cadastrarCliente(@RequestBody Cliente cliente) {
			Cliente clienteSalvo = clienteService.cadastrar(cliente);
			return new ResponseEntity<>(clienteSalvo, HttpStatus.CREATED);
		}
		
		@RequestMapping(method=RequestMethod.GET, value="/clientes", produces=org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Collection<Cliente>> buscarTodosClientes() {
			Collection<Cliente> clientesBuscados = clienteService.buscarTodos();
			return new ResponseEntity<>(clientesBuscados, HttpStatus.OK);
		}
		
		@RequestMapping(method=RequestMethod.DELETE, value="/clientes/{id}")
		public ResponseEntity<Cliente> excluirCliente(@PathVariable Integer id) {
			Cliente clienteEncontrado = clienteService.buscarPorId(id);
			
			if (clienteEncontrado == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			clienteService.excluir(clienteEncontrado);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		@RequestMapping(method=RequestMethod.PUT, value="/clientes", consumes=org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Cliente> alterarCliente(@RequestBody Cliente cliente) {
			Cliente clienteAlterado = clienteService.alterar(cliente);
			return new ResponseEntity<>(clienteAlterado, HttpStatus.OK);
		}
		
}
