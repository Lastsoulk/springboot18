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

import com.example.Spring_boot_18.models.Producto;
import com.example.Spring_boot_18.repository.ProductoRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/productos")
@Api(value="Controlador de Productos", description="Operaciones relacionadas a los Productos")
public class ProductoController {
	@Autowired
	private ProductoRepository productRepository;
	
	@ExceptionHandler(value =MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public String validationError(MethodArgumentNotValidException ex) {
	    String m = "Error found\n";
		BindingResult result = ex.getBindingResult();
	    final List<FieldError> fieldErrors = result.getFieldErrors();
    for (int i =0; i<fieldErrors.size();i++) {
    	m += fieldErrors.get(i).getDefaultMessage()+"\n";
    }
	return m;
	}
	
	@ApiOperation(value = "Lista de Productos disponibles", response = List.class)
	@GetMapping
	public List<Producto> retrieveAllProducts() {
		return productRepository.findAll();
	}
	
	//Búsqueda por ID
	@ApiOperation(value = "Buscar un Producto")
	@GetMapping("/{codigo}")
	public Producto retrieveProduct(@PathVariable int codigo) {
		Optional<Producto> product = productRepository.findById(codigo);
		return product.get();
	}
	
	//Eliminar
	@ApiOperation(value = "Eliminar un Producto")
	@DeleteMapping("/{codigo}")
	public void deleteProducto(@PathVariable int codigo) {
		productRepository.deleteById(codigo);
	}
	
	//Crear
	@PostMapping
	@ApiOperation(value = "Agregar un Producto")
	public ResponseEntity<Object> createProduct(@Valid @RequestBody Producto producto) {
		
		Producto savedProduct = null;

	    savedProduct = productRepository.save(producto);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{producto_codigo}")
				.buildAndExpand(savedProduct.getCodigo()).toUri();

		return ResponseEntity.created(location).build();
	}
	
	//Modificar
	@PutMapping("/{codigo}")
	@ApiOperation(value = "Modificar un Producto")
	public ResponseEntity<Object> updateProduct(@Valid @RequestBody Producto producto, @PathVariable int codigo) {
		
		Optional<Producto> productOptional = productRepository.findById(codigo);

		if (!productOptional.isPresent())
			return ResponseEntity.notFound().build();

		producto.setCodigo(codigo);
		
		productRepository.save(producto);

		return ResponseEntity.noContent().build();
	}
	
}
