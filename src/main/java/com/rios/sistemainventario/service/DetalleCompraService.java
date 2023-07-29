package com.rios.sistemainventario.service;

import java.util.List;

import com.rios.sistemainventario.entity.DetalleCompra;



public interface DetalleCompraService {
    List<DetalleCompra> findAll();
    DetalleCompra findById(int id);
    DetalleCompra create(DetalleCompra detalleVenta);
    DetalleCompra update(DetalleCompra detalleVenta);
    int delete(int id);
	List<DetalleCompra> findByCompraId(int ventaId);
}
