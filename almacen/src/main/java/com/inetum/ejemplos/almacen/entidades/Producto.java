package com.inetum.ejemplos.almacen.entidades;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Producto {
	private Long id;
	private String nombre;
	private Integer stock;
	private LocalDate fechaCaducidad;
	private BigDecimal precio;
}
