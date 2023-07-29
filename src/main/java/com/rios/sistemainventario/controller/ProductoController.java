package com.rios.sistemainventario.controller;

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

import com.rios.sistemainventario.entity.Producto;
import com.rios.sistemainventario.service.impl.ProductoServiceImpl;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    @Autowired
    private ProductoServiceImpl service;

    // localhost:8091/api/productos (GET)
    @GetMapping()
    public ResponseEntity<List<Producto>> getAll() {
        List<Producto> productos = service.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(productos);
    }

    // localhost:8091/api/productos/1 (GET)
    @GetMapping(value = "/{id}")
    public ResponseEntity<Producto> getById(@PathVariable("id") int id) {
        Producto producto = service.findById(id);
        if (producto != null) {
            return ResponseEntity.status(HttpStatus.OK).body(producto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // localhost:8091/api/productos (POST)
    @PostMapping
    public ResponseEntity<Producto> create(@RequestBody Producto producto) {
        Producto productoDb = service.create(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoDb);
    }

    // localhost:8091/api/productos (PUT)
    @PutMapping
    public ResponseEntity<Producto> update(@RequestBody Producto producto) {
        Producto productoDb = service.update(producto);
        if (productoDb != null) {
            return ResponseEntity.status(HttpStatus.OK).body(productoDb);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // localhost:8091/api/productos/1 (DELETE)
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        boolean deleted = service.delete(id);
        if (deleted) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
