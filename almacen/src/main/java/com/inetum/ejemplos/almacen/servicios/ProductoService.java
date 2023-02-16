package com.inetum.ejemplos.almacen.servicios;

import java.math.BigDecimal;

import com.inetum.ejemplos.almacen.entidades.Categoria;
import com.inetum.ejemplos.almacen.entidades.Producto;

public interface ProductoService {
	Iterable<Producto> obtenerListado();
	Producto obtenerDetalle(Long id);

	Iterable<Producto> buscarPorPrecio(BigDecimal inferior, BigDecimal superior);
	
	Producto darDeAlta(Producto producto);
	Producto modificar(Producto producto);
	void eliminar(Long id);
	
	Iterable<Categoria> obtenerCategorias();
}
