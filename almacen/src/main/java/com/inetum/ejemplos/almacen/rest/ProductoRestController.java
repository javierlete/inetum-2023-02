package com.inetum.ejemplos.almacen.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inetum.ejemplos.almacen.daos.Dao;
import com.inetum.ejemplos.almacen.entidades.Producto;

@RestController
@RequestMapping("/productos")
public class ProductoRestController {
	@Autowired
	private Dao<Producto> dao;
	
	@GetMapping
	public Iterable<Producto> get() {
		return dao.obtenerTodos();
	}
	
	@GetMapping("{id}")
	public Producto getPorId(@PathVariable Long id) {
		return dao.obtenerPorId(id);
	}
	
	@PostMapping
	public Producto post(@RequestBody Producto producto) {
		return dao.insertar(producto);
	}
	
	 @PutMapping("{id}")
	 public Producto put(@RequestBody Producto producto) {
		 return dao.modificar(producto);
	 }
	 
	 @DeleteMapping("{id}")
	 public void delete(@PathVariable Long id) {
		 dao.borrar(id);
	 }
}
