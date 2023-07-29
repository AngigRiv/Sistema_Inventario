package com.rios.sistemainventario.service;

import java.util.List;

import com.rios.sistemainventario.entity.Producto;

public interface ProductoService {
    public List<Producto> findAll();

    public Producto findById(int id);
    
    public Producto findByNombre(String nombre);

    public Producto create(Producto producto);

    public Producto update(Producto producto);

    public boolean delete(int id);
}
