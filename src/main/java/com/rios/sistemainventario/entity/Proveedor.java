package com.rios.sistemainventario.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="proveedores")
public class Proveedor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "nombre", nullable = false,length = 100)
	private String nombre;
	@Column(name = "ruc", nullable = false,length = 100)
	private String ruc;
	@Column(name = "direccion", nullable = false,length = 100)
	private String direccion;
	@Column(name = "telefono", nullable = false,length = 100)
	private Integer telefono;
	@Column(name = "correo", nullable = false,length = 100)
	private String correo;
	
}
