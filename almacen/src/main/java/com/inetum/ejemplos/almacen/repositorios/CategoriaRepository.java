package com.inetum.ejemplos.almacen.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.inetum.ejemplos.almacen.entidades.Categoria;

@RepositoryRestResource(collectionResourceRel = "categorias", path = "categorias")
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
