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

import com.example.Spring_boot_18.models.Proveedor;
import com.example.Spring_boot_18.repository.ProveedorRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/proveedores")
@Api(value="Controlador de Proveedores", description="Operaciones relacionadas a los Proveedores")
public class ProveedorController {
	@Autowired
	private ProveedorRepository proveedorRepositorio;
	
	@ExceptionHandler(value =MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public String validationError(MethodArgumentNotValidException ex) {
	    String m = "Error Found \n";
		BindingResult result = ex.getBindingResult();
	    final List<FieldError> fieldErrors = result.getFieldErrors();
    for (int i =0; i<fieldErrors.size();i++) {
    	m += fieldErrors.get(i).getDefaultMessage()+"\n";
    }
	return m;
	}
	
	@ApiOperation(value = "Lista de Proveedores disponibles", response = List.class)
	@GetMapping
	//Lista completa
	public List<Proveedor> retrieveAllProviders() {
		return proveedorRepositorio.findAll();
	}
	
	//Búsqueda por ID
	@GetMapping("/{codigo}")
	@ApiOperation(value = "Buscar un Proveedor")
	public Proveedor retrieveProvider(@PathVariable int codigo) {
		Optional<Proveedor> proveedor = proveedorRepositorio.findById(codigo);

		return proveedor.get();
	}
	
	//Eliminar
	@DeleteMapping("/{codigo}")
	@ApiOperation(value = "Eliminar un Proveedor")
	public void deleteProvider(@PathVariable int codigo) {
		proveedorRepositorio.deleteById(codigo);
	}
	
	//Crear
	@PostMapping
	@ApiOperation(value = "Agregar un Proveedor")
	public ResponseEntity<Object> createProvider(@RequestBody @Valid Proveedor proveedor) {
		Proveedor savedProvider = proveedorRepositorio.save(proveedor);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedProvider.getRuc()).toUri();

		return ResponseEntity.created(location).build();
	}
	
	//Modificar
	@PutMapping("/{codigo}")
	@ApiOperation(value = "Modificar un Proveedor")
	public ResponseEntity<Object> updateProvider(@RequestBody Proveedor proveedor, @PathVariable int codigo) {

		Optional<Proveedor> providerOptional = proveedorRepositorio.findById(codigo);

		if (!providerOptional.isPresent())
			return ResponseEntity.notFound().build();

		proveedor.setRuc(codigo);
		
		proveedorRepositorio.save(proveedor);

		return ResponseEntity.noContent().build();
	}
	
}
