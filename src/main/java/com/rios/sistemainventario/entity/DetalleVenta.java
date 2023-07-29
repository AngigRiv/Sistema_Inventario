package com.rios.sistemainventario.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonIgnore;


import jakarta.persistence.*;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "detalle_venta")
public class DetalleVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "cantidad", nullable = false)
    private int cantidad;

    @Column(name = "total", nullable = false, precision = 10)
    private Double total;

    @ManyToOne
    @JoinColumn(name = "producto_id", referencedColumnName = "id")
    private Producto producto_id;

    @ManyToOne
    @JoinColumn(name = "venta_id", nullable = false)
    //@JsonIgnore
    private Venta venta;

}
