package com.inetum.ejemplos.almacen.rest;

import java.math.BigDecimal;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.inetum.ejemplos.almacen.entidades.Producto;
import com.inetum.ejemplos.almacen.repositorios.ProductoRepository;

import jakarta.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/productos")
public class ProductoRestController {
	@Autowired
	private ProductoRepository repo;

	@GetMapping
	public Iterable<Producto> get() {
		return repo.findAll();
	}

	@GetMapping("{id}")
	public Producto getPorId(@PathVariable Long id) {
		Producto producto = repo.findById(id).orElse(null);
		
		if (producto == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No encontrado");
		} else {
			return producto;
		}
	}
	
	@GetMapping("buscar/por-precio")
	public Iterable<Producto> getPorPrecio(BigDecimal inferior, BigDecimal superior) {
		return repo.findByPrecioBetween(inferior, superior);
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Producto post(@RequestBody @Valid Producto producto, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se admite el producto");
		}
		
		return repo.save(producto);
	}

	@PutMapping("{id}")
	public Producto put(@PathVariable Long id, @Valid @RequestBody Producto producto, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se admite el producto");
		}
		
		if (Objects.equals(id, producto.getId())) {
			return repo.save(producto);
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El id de la URL debe coincidir con el id del producto");
		}
	}

	@DeleteMapping("{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		repo.deleteById(id);
	}
}
