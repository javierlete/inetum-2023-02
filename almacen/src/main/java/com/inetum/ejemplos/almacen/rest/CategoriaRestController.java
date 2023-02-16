package com.inetum.ejemplos.almacen.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inetum.ejemplos.almacen.entidades.Categoria;
import com.inetum.ejemplos.almacen.servicios.ProductoService;

@CrossOrigin
@RestController
@RequestMapping({"/categorias", "/categorias/"})
public class CategoriaRestController {
	@Autowired
	private ProductoService servicio;
	@GetMapping
	public Iterable<Categoria> get() {
		return servicio.obtenerCategorias();
	}
}
