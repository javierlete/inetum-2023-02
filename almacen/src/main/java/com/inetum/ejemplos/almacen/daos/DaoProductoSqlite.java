package com.inetum.ejemplos.almacen.daos;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.inetum.ejemplos.almacen.entidades.Producto;

@Repository
public class DaoProductoSqlite implements DaoProducto {

	@Autowired
	private JdbcTemplate jdbc;
	
	@Override
	public Iterable<Producto> obtenerTodos() {
		return jdbc.query(
				"SELECT id, nombre, precio, stock, fecha_caducidad FROM productos", 
				new BeanPropertyRowMapper<Producto>(Producto.class));
	}

	@Override
	public Producto obtenerPorId(Long id) {
		return jdbc.queryForObject(
				"SELECT id, nombre, precio, stock, fecha_caducidad FROM productos WHERE id = ?", 
				new BeanPropertyRowMapper<Producto>(Producto.class),
				id);
	}

	@Override
	public Producto insertar(Producto producto) {
		jdbc.update("INSERT INTO productos (nombre, precio, stock, fecha_caducidad) VALUES (?,?,?,?)",
				producto.getNombre(),
				producto.getPrecio(),
				producto.getStock(),
				producto.getFechaCaducidad());
		return producto;
	}

	@Override
	public Producto modificar(Producto producto) {
		jdbc.update("UPDATE productos SET nombre=?, precio=?, stock=?, fecha_caducidad=? WHERE id=?",
				producto.getNombre(),
				producto.getPrecio(),
				producto.getStock(),
				producto.getFechaCaducidad(),
				producto.getId());
		return producto;
	}

	@Override
	public void borrar(Long id) {
		jdbc.update("DELETE FROM productos WHERE id=?", id);
	}

	@Override
	public Iterable<Producto> obtenerPorPrecios(BigDecimal inferior, BigDecimal superior) {
		return jdbc.query(
				"SELECT id, nombre, precio, stock, fecha_caducidad FROM productos WHERE precio BETWEEN ? AND ?", 
				new BeanPropertyRowMapper<Producto>(Producto.class),
				inferior,
				superior);
	}

}
