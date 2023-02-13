package com.inetum.ejemplos.almacen.repositorios;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.inetum.ejemplos.almacen.entidades.Producto;

@RepositoryRestResource(collectionResourceRel = "productos", path = "productos")
public interface ProductoRepository extends JpaRepository<Producto, Long> {
	Iterable<Producto> findByPrecioBetween(@Param("inferior") BigDecimal inferior, @Param("superior") BigDecimal superior);
}
