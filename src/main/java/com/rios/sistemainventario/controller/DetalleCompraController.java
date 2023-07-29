package com.rios.sistemainventario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rios.sistemainventario.entity.DetalleCompra;
import com.rios.sistemainventario.entity.Compra;
import com.rios.sistemainventario.service.DetalleCompraService;
import com.rios.sistemainventario.service.CompraService;

import java.util.List;

@RestController
@RequestMapping("/api/detallecompras")
public class DetalleCompraController {
    @Autowired
    private DetalleCompraService detalleCompraService;
    @Autowired
    private CompraService compraService;
    
    
    
    @GetMapping
    public ResponseEntity<List<DetalleCompra>> getAllDetalleCompras() {
        List<DetalleCompra> detalleCompras = detalleCompraService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(detalleCompras);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<DetalleCompra> getDetalleCompraById(@PathVariable("id") int id) {
        DetalleCompra detalleCompra = detalleCompraService.findById(id);
        if (detalleCompra != null) {
            return ResponseEntity.status(HttpStatus.OK).body(detalleCompra);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<DetalleCompra> createDetalleCompra(@RequestBody DetalleCompra detalleCompra) {
        DetalleCompra detalleCompraDb = detalleCompraService.create(detalleCompra);
        return ResponseEntity.status(HttpStatus.CREATED).body(detalleCompraDb);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleCompra> updateDetalleCompra(@PathVariable("id") int id, @RequestBody DetalleCompra detalleCompra) {
        if (detalleCompra.getId() != id) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        DetalleCompra detalleCompraDb = detalleCompraService.update(detalleCompra);
        if (detalleCompraDb != null) {
            return ResponseEntity.status(HttpStatus.OK).body(detalleCompraDb);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDetalleCompra(@PathVariable("id") int id) {
        int result = detalleCompraService.delete(id);
        if (result == 1) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

 
        @GetMapping("/{id}/detalles")
        public ResponseEntity<List<DetalleCompra>> getDetallesByCompraId(@PathVariable("id") int compraId) {
            Compra compra = compraService.findById(compraId);
            if (compra == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            List<DetalleCompra> detalles = compra.getDetalles();
            return ResponseEntity.status(HttpStatus.OK).body(detalles);
        }


    
    // Puedes agregar más métodos según tus necesidades
}
