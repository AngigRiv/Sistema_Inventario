package com.rios.sistemainventario.service;

import java.util.List;

import com.rios.sistemainventario.entity.DetalleVenta;

public interface DetalleVentaService {
    List<DetalleVenta> findAll();
    DetalleVenta findById(int id);
    DetalleVenta create(DetalleVenta detalleVenta);
    DetalleVenta update(DetalleVenta detalleVenta);
    int delete(int id);
    List<DetalleVenta> findByVentaId(int ventaId);
    // Puedes agregar más métodos según tus necesidades
}
