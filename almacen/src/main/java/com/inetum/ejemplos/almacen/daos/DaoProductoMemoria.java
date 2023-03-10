package com.inetum.ejemplos.almacen.daos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.TreeMap;

import com.inetum.ejemplos.almacen.entidades.Producto;

public class DaoProductoMemoria implements DaoProducto {
	private TreeMap<Long, Producto> productos = new TreeMap<>();

	public DaoProductoMemoria() {
		for (Long l = 1L; l <= 10L; l++) {
			productos.put(l, Producto.builder().id(l).nombre("Producto" + l).precio(new BigDecimal(l * 1000))
					.stock(l.intValue() * 100).fechaCaducidad(LocalDate.of(2000 + l.intValue(), l.intValue(), l.intValue() + 1)).build());
		}
	}

	@Override
	public Iterable<Producto> obtenerTodos() {
		return productos.values();
	}

	@Override
	public Producto obtenerPorId(Long id) {
		return productos.get(id);
	}

	@Override
	public Producto insertar(Producto producto) {
		Long id = productos.size() > 0 ? productos.lastKey() + 1L : 1L;
		producto.setId(id);
		productos.put(id, producto);

		return producto;
	}

	@Override
	public Producto modificar(Producto producto) {
		productos.put(producto.getId(), producto);

		return producto;
	}

	@Override
	public void borrar(Long id) {
		productos.remove(id);
	}

	@Override
	public Iterable<Producto> obtenerPorPrecios(BigDecimal inferior, BigDecimal superior) {
		return productos.values().stream().filter(
				producto -> 
					producto.getPrecio().compareTo(inferior) >= 0 && 
					producto.getPrecio().compareTo(superior) <= 0).toList();
	}

}
