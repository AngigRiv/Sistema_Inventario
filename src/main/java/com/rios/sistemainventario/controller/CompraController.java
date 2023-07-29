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

import com.rios.sistemainventario.entity.Compra;
import com.rios.sistemainventario.entity.DetalleCompra;
import com.rios.sistemainventario.service.CompraService;



@RestController
@RequestMapping("/api/compras")
public class CompraController {
    @Autowired
    private CompraService compraService;


    @GetMapping
    public ResponseEntity<List<Compra>> getAllCompras() {
        List<Compra> compras = compraService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(compras);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Compra> getCompraById(@PathVariable("id") int id) {
        Compra compras = compraService.findById(id);
        if (compras != null) {
            return ResponseEntity.status(HttpStatus.OK).body(compras);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<Compra> createCompra(@RequestBody Compra compras) {
        Compra compraDb = compraService.create(compras);
        return ResponseEntity.status(HttpStatus.CREATED).body(compraDb);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Compra> updateCompra(@PathVariable("id") int id, @RequestBody Compra compras) {
        if (compras.getId() != id) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Compra compraDb = compraService.update(compras);
        if (compraDb != null) {
            return ResponseEntity.status(HttpStatus.OK).body(compraDb);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompra(@PathVariable("id") int id) {
        int result = compraService.delete(id);
        if (result == 1) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

  

    @GetMapping("/fecha/{fecha}")
    public ResponseEntity<List<Compra>> getComprasByFecha(@PathVariable("fecha") Date fecha) {
        List<Compra> compras = compraService.findComprasByFecha(fecha);
        return ResponseEntity.status(HttpStatus.OK).body(compras);
    }
    
    @GetMapping("/{id}/detalles")
    public ResponseEntity<List<DetalleCompra>> getDetallesByCompraId(@PathVariable("id") int compraId) {
        Compra compras = compraService.findById(compraId);
        if (compras == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        List<DetalleCompra> detalles = compras.getDetalles();
        return ResponseEntity.status(HttpStatus.OK).body(detalles);
    }
  
}
