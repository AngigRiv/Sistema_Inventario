package com.rios.sistemainventario.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.rios.sistemainventario.entity.DetalleVenta;
import com.rios.sistemainventario.entity.Venta;
import com.rios.sistemainventario.service.VentaService;


@RestController
@RequestMapping("/api/ventas")
public class VentaController {
    @Autowired
    private VentaService ventaService;


    @GetMapping
    public ResponseEntity<List<Venta>> getAllVentas() {
        List<Venta> ventas = ventaService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(ventas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venta> getVentaById(@PathVariable("id") int id) {
        Venta venta = ventaService.findById(id);
        if (venta != null) {
            return ResponseEntity.status(HttpStatus.OK).body(venta);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<Venta> createVenta(@RequestBody Venta venta) {
        Venta ventaDb = ventaService.create(venta);
        return ResponseEntity.status(HttpStatus.CREATED).body(ventaDb);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Venta> updateVenta(@PathVariable("id") int id, @RequestBody Venta venta) {
        if (venta.getId() != id) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Venta ventaDb = ventaService.update(venta);
        if (ventaDb != null) {
            return ResponseEntity.status(HttpStatus.OK).body(ventaDb);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVenta(@PathVariable("id") int id) {
        int result = ventaService.delete(id);
        if (result == 1) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

  

    @GetMapping("/fecha/{fecha}")
    public ResponseEntity<List<Venta>> getVentasByFecha(@PathVariable("fecha") Date fecha) {
        List<Venta> ventas = ventaService.findVentasByFecha(fecha);
        return ResponseEntity.status(HttpStatus.OK).body(ventas);
    }
    
    @GetMapping("/{id}/detalles")
    public ResponseEntity<List<DetalleVenta>> getDetallesByVentaId(@PathVariable("id") int ventaId) {
        Venta venta = ventaService.findById(ventaId);
        if (venta == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        List<DetalleVenta> detalles = venta.getDetalles();
        return ResponseEntity.status(HttpStatus.OK).body(detalles);
    }
  
}
