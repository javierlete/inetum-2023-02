package com.inetum.ejemplos.almacen.rest;

import java.math.BigDecimal;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.inetum.ejemplos.almacen.daos.DaoProducto;
import com.inetum.ejemplos.almacen.entidades.Producto;

@CrossOrigin
@RestController
@RequestMapping("/productos")
public class ProductoRestController {
	@Autowired
	private DaoProducto dao;

	@GetMapping
	public Iterable<Producto> get() {
		return dao.obtenerTodos();
	}

	@GetMapping("{id}")
	public Producto getPorId(@PathVariable Long id) {
		Producto producto = dao.obtenerPorId(id);
		if (producto == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No encontrado");
		} else {
			return producto;
		}
	}
	
	@GetMapping("buscar/por-precio")
	public Iterable<Producto> getPorPrecio(BigDecimal inferior, BigDecimal superior) {
		return dao.obtenerPorPrecios(inferior, superior);
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Producto post(@RequestBody Producto producto) {
		return dao.insertar(producto);
	}

	@PutMapping("{id}")
	public Producto put(@PathVariable Long id, @RequestBody Producto producto) {
		if (Objects.equals(id, producto.getId())) {
			return dao.modificar(producto);
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El id de la URL debe coincidir con el id del producto");
		}
	}

	@DeleteMapping("{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		dao.borrar(id);
	}
}
