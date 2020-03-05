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

import com.example.Spring_boot_18.models.Venta;
import com.example.Spring_boot_18.repository.ClientRepository;
import com.example.Spring_boot_18.repository.VentaRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/ventas")
@Api(value="Controlador de Ventas", description="Operaciones relacionadas a las Ventas")
public class VentaController {
	@Autowired
	private VentaRepository ventaRepository;

	@Autowired
	private ClientRepository clienteRepositorio;

	
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
	
	@ApiOperation(value = "Lista de Ventas realizadas", response = List.class)
	@GetMapping
	public List<Venta> retrieveAllSales() {
		return ventaRepository.findAll();
	}

	// Búsqueda por ID
	@ApiOperation(value = "Busqueda de una Venta")
	@GetMapping("/{venta_codigo}")
	public Venta retrieveSales(@PathVariable int venta_codigo) {
		Optional<Venta> venta = ventaRepository.findById(venta_codigo);

		return venta.get();
	}

	// Eliminar
	@DeleteMapping("/{venta_codigo}")
	@ApiOperation(value = "Eliminar una Venta")
	public void deleteSales(@PathVariable int venta_codigo) {
		ventaRepository.deleteById(venta_codigo);
	}

	// Crear
	@PostMapping
	@ApiOperation(value = "Agregar una Venta")
	public ResponseEntity<Object> createSales(@RequestBody @Valid Venta venta) {

		Venta savedSale = null;
		try {
			if (!clienteRepositorio.existsById(venta.getClienteCodigo())) {
				
				return new ResponseEntity<>("Error el cliente no existe", HttpStatus.BAD_REQUEST);
			}
			if (venta.getVentaTotal()<=0) {
				return new ResponseEntity<>("Error: la venta no puede ser 0 o nulo", HttpStatus.BAD_REQUEST);
			}
			savedSale = ventaRepository.save(venta);
		} catch (Exception e) {
			return new ResponseEntity<>("Error no controlado", HttpStatus.BAD_REQUEST);
		}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{venta_codigo}")
				.buildAndExpand(savedSale.getIdVenta()).toUri();
		return ResponseEntity.created(location).build();
	}

	// Modificar
	@ApiOperation(value = "Modificar de una Venta")
	@PutMapping("/{venta_codigo}")
	public ResponseEntity<Object> updateSales(@RequestBody Venta venta, @PathVariable int venta_codigo) {

		Optional<Venta> saleOptional = ventaRepository.findById(venta_codigo);
		if (!saleOptional.isPresent())
			return ResponseEntity.notFound().build();
		venta.setIdVenta(venta_codigo);
		ventaRepository.save(venta);

		return ResponseEntity.noContent().build();
	}

}
