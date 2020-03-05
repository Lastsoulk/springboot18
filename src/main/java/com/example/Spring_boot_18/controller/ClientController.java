package com.example.Spring_boot_18.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.Spring_boot_18.models.Cliente;
import com.example.Spring_boot_18.repository.ClientRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;



@RestController
@RequestMapping("/clientes")
@Api(value="Controlador de Clientes", description="Operaciones relacionadas a los Clientes")
public class ClientController{
	@Autowired
	private ClientRepository clienteRepositorio;
	
	
	@ExceptionHandler(value =MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public String validationError(MethodArgumentNotValidException ex) {
	    String m = "ERRORES ENCONTRADOS \n";
		BindingResult result = ex.getBindingResult();
	    final List<FieldError> fieldErrors = result.getFieldErrors();
    for (int i =0; i<fieldErrors.size();i++) {
    	m += fieldErrors.get(i).getDefaultMessage()+"\n";
    }
	return m;
	}
	
	@GetMapping
	@ApiOperation(value = "Lista de Clientes disponibles", response = List.class)
	public List<Cliente> retrieveAllClients() {
		return clienteRepositorio.findAll();
	}
	
	//Búsqueda por ID
	@GetMapping("/{cliente_codigo}")
	@ApiOperation(value = "Busqueda de un Cliente")
	public Cliente retrieveClient(@PathVariable String cliente_codigo) {
		Optional<Cliente> cliente = clienteRepositorio.findByIdentificacion(cliente_codigo);
		return cliente.get();
	}
	
	//Eliminar
	@ApiOperation(value = "Eliminar un Cliente")
	@DeleteMapping("/{cliente_codigo}")
	public void deleteClient(@PathVariable int cliente_codigo) {
		clienteRepositorio.deleteByIdentificacion(cliente_codigo);
	}
	
	//Crear
	@ApiOperation(value = "Agregar un Cliente")
	@PostMapping
	public ResponseEntity<Object> createClient(@RequestBody @Valid Cliente cliente) {

		if(clienteRepositorio.findByIdentificacion(cliente.getIdentificacion()).isPresent()){
			return new ResponseEntity<>("Cliente con identificación: " + cliente.getIdentificacion() + " ya existe", HttpStatus.BAD_REQUEST);
		}
		
		Cliente savedProduct = clienteRepositorio.save(cliente);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{cliente_codigo}")
				.buildAndExpand(savedProduct.getIdentificacion()).toUri();

		return ResponseEntity.created(location).build();

	}
	
	//Modificar
	@ApiOperation(value = "Modificar un Cliente")
	@PutMapping("/{cliente_codigo}")
	public ResponseEntity<Object> updateClient(@RequestBody Cliente cliente, @PathVariable String cliente_codigo) {

		Optional<Cliente> clientOptional = clienteRepositorio.findByIdentificacion(cliente_codigo);

		if (!clientOptional.isPresent())
			return ResponseEntity.notFound().build();

		cliente.setIdentificacion(cliente_codigo);
		
		clienteRepositorio.save(cliente);

		return ResponseEntity.noContent().build();
	}
}