package com.inetum.ejemplos.almacen.repositorios;

import java.math.BigDecimal;

import org.springframework.data.repository.CrudRepository;

import com.inetum.ejemplos.almacen.entidades.Producto;

public interface ProductoRepository extends CrudRepository<Producto, Long> {
	Iterable<Producto> findByPrecioBetween(BigDecimal inferior, BigDecimal superior);
}
