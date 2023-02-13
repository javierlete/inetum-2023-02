package com.inetum.ejemplos.almacen.repositorios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import com.inetum.ejemplos.almacen.entidades.Categoria;
import com.inetum.ejemplos.almacen.entidades.Producto;

@SpringBootTest
class ProductoRepositoryTest {
	@Autowired
	private ProductoRepository repo;

	@Autowired
	private JdbcTemplate jdbc;

	@BeforeEach
	void beforeEach() {
		jdbc.update("TRUNCATE productos");
		jdbc.update("DELETE FROM categorias");
		jdbc.update("ALTER TABLE categorias AUTO_INCREMENT = 1");
		
		for (int c = 1; c <= 5; c++) {
			jdbc.update("INSERT INTO categorias (codigo, nombre, descripcion) VALUES (?,?,?)", "CODI000" + c,
					"Categoria" + c, "Descripción" + c);
		}
		for (int p = 1; p <= 20; p++) {
			jdbc.update(
					"INSERT INTO productos (nombre, precio, stock, fecha_caducidad, categoria_id) VALUES (?,?,?,?,?)",
					"Producto" + p, 100 * p, 10 * p, LocalDate.of(2030 + p, p % 12 + 1, (p * 2) % 28 + 1), p % 5 + 1);
		}
	}

	@Test
	void findByIdTest() {
		assertFalse(repo.findById(0L).isPresent());
		Optional<Producto> producto = repo.findById(1L);
		
		assertTrue(producto.isPresent());
		
		Categoria categoriaEsperada = new Categoria(1L, "CODI0001", "Categoria1", "Descripción1", null);
		Producto productoEsperado = new Producto(1L, "Producto1", 10, LocalDate.of(2031, 1, 2), new BigDecimal(100), categoriaEsperada);
		
		assertEquals(productoEsperado, producto.get());
	}
}
