package com.rios.sistemainventario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rios.sistemainventario.entity.DetalleVenta;
import com.rios.sistemainventario.entity.Venta;
import com.rios.sistemainventario.service.DetalleVentaService;
import com.rios.sistemainventario.service.VentaService;

import java.util.List;

@RestController
@RequestMapping("/api/detalleventas")
public class DetalleVentaController {
    @Autowired
    private DetalleVentaService detalleVentaService;
    @Autowired
    private VentaService ventaService;
    @GetMapping
    public ResponseEntity<List<DetalleVenta>> getAllDetalleVentas() {
        List<DetalleVenta> detalleVentas = detalleVentaService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(detalleVentas);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<DetalleVenta> getDetalleVentaById(@PathVariable("id") int id) {
        DetalleVenta detalleVenta = detalleVentaService.findById(id);
        if (detalleVenta != null) {
            return ResponseEntity.status(HttpStatus.OK).body(detalleVenta);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<DetalleVenta> createDetalleVenta(@RequestBody DetalleVenta detalleVenta) {
        DetalleVenta detalleVentaDb = detalleVentaService.create(detalleVenta);
        return ResponseEntity.status(HttpStatus.CREATED).body(detalleVentaDb);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleVenta> updateDetalleVenta(@PathVariable("id") int id, @RequestBody DetalleVenta detalleVenta) {
        if (detalleVenta.getId() != id) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        DetalleVenta detalleVentaDb = detalleVentaService.update(detalleVenta);
        if (detalleVentaDb != null) {
            return ResponseEntity.status(HttpStatus.OK).body(detalleVentaDb);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDetalleVenta(@PathVariable("id") int id) {
        int result = detalleVentaService.delete(id);
        if (result == 1) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
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


    
    // Puedes agregar más métodos según tus necesidades
}
