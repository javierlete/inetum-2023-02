package com.inetum.ejemplos.almacen.servicios;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inetum.ejemplos.almacen.entidades.Categoria;
import com.inetum.ejemplos.almacen.entidades.Producto;
import com.inetum.ejemplos.almacen.repositorios.CategoriaRepository;
import com.inetum.ejemplos.almacen.repositorios.ProductoRepository;

@Service
public class ProductoServiceImpl implements ProductoService {
	@Autowired
	private ProductoRepository repo;
	
	@Autowired
	private CategoriaRepository repoCategorias;
	
	@Override
	public Iterable<Producto> obtenerListado() {
		return repo.findAll();
	}

	@Override
	public Producto obtenerDetalle(Long id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public Producto darDeAlta(Producto producto) {
		if(producto.getId() != null) {
			throw new ServiciosException("No se puede dar de alta un producto con un id");
		}
		
		return repo.save(producto);
	}

	@Override
	public Producto modificar(Producto producto) {
		if(producto.getId() == null) {
			throw new ServiciosException("No se puede modificar un producto sin un id");
		}
		
		return repo.save(producto);
	}

	@Override
	public void eliminar(Long id) {
		repo.deleteById(id);
	}

	@Override
	public Iterable<Producto> buscarPorPrecio(BigDecimal inferior, BigDecimal superior) {
		return repo.findByPrecioBetween(inferior, superior);
	}

	@Override
	public Iterable<Categoria> obtenerCategorias() {
		return repoCategorias.findAll();
	}
	
	

}
