package com.rios.sistemainventario.entity;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "productos")
@EntityListeners(AuditingEntityListener.class)
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nombre", unique = true, nullable = false, length = 50)
    private String nombre;
    @Column(name = "descripcion", length = 100)
    private String descripcion;
    @Column(name = "stock", nullable = false)
    private Integer stock;
    @Column(name = "precio", nullable = false, precision = 11, scale = 2)
    private BigDecimal precio;
    @Column(name = "is_active", nullable = false)
    private boolean is_active;
    @ManyToOne
    @JoinColumn(name = "marca_id", nullable = false)
    private Marca marca_id;
    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria_id;
    @ManyToOne
    @JoinColumn(name = "unidad_id", nullable = false)
    private Unidad unidad_id;
}
