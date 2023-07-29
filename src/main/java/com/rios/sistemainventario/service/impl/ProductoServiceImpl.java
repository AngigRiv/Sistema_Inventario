package com.rios.sistemainventario.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.rios.sistemainventario.entity.Producto;
import com.rios.sistemainventario.repository.ProductoRepository;
import com.rios.sistemainventario.service.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Producto> findAll() {
        try {
            return repository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Producto findById(int id) {
        try {
            return repository.findById(id).orElse(null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Producto findByNombre(String nombre) {
        try {
            return repository.findByNombre(nombre);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @Transactional
    public Producto create(Producto producto) {
        try {
            return repository.save(producto);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @Transactional
    public Producto update(Producto producto) {
        try {
            Producto productoDb = repository.findById(producto.getId()).orElse(null);
            if (productoDb == null) {
                return null;
            }
            productoDb.setNombre(producto.getNombre());
            productoDb.setDescripcion(producto.getDescripcion());
            productoDb.setStock(producto.getStock());
            productoDb.setPrecio(producto.getPrecio());
            productoDb.set_active(producto.is_active());
            productoDb.setMarca_id(producto.getMarca_id());
            productoDb.setCategoria_id(producto.getCategoria_id());
            productoDb.setUnidad_id(producto.getUnidad_id());
            return repository.save(productoDb);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        try {
            Producto productoDb = repository.findById(id).orElse(null);
            if (productoDb == null) {
                return false;
            } else {
                repository.delete(productoDb);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

