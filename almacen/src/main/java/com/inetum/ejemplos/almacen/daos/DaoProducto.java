package com.inetum.ejemplos.almacen.daos;

import java.math.BigDecimal;

import com.inetum.ejemplos.almacen.entidades.Producto;

public interface DaoProducto extends Dao<Producto> {
	Iterable<Producto> obtenerPorPrecios(BigDecimal inferior, BigDecimal superior);
}
