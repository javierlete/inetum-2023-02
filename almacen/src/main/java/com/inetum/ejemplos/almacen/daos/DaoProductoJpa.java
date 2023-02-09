package com.inetum.ejemplos.almacen.daos;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.inetum.ejemplos.almacen.entidades.Producto;
import com.inetum.ejemplos.almacen.repositorios.ProductoRepository;

@Repository
public class DaoProductoJpa implements DaoProducto {

	@Autowired
	private ProductoRepository repo;
	
	@Override
	public Iterable<Producto> obtenerTodos() {
		return repo.findAll();
	}

	@Override
	public Producto obtenerPorId(Long id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public Producto insertar(Producto producto) {
		return repo.save(producto);
	}

	@Override
	public Producto modificar(Producto producto) {
		return repo.save(producto);
	}

	@Override
	public void borrar(Long id) {
		repo.deleteById(id);
	}

	@Override
	public Iterable<Producto> obtenerPorPrecios(BigDecimal inferior, BigDecimal superior) {
		return repo.findByPrecioBetween(inferior, superior);
	}

}
