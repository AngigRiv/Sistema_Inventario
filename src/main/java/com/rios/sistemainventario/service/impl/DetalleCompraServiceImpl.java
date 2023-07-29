package com.rios.sistemainventario.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rios.sistemainventario.entity.DetalleCompra;
import com.rios.sistemainventario.repository.DetalleCompraRepository;
import com.rios.sistemainventario.service.DetalleCompraService;

import java.util.List;

@Service
public class DetalleCompraServiceImpl implements DetalleCompraService {
    @Autowired
    private DetalleCompraRepository detalleCompraRepository;

    @Override
    @Transactional(readOnly = true)
    public List<DetalleCompra> findAll() {
        return detalleCompraRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public DetalleCompra findById(int id) {
        return detalleCompraRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public DetalleCompra create(DetalleCompra detalleCompra) {
        return detalleCompraRepository.save(detalleCompra);
    }

    @Override
    @Transactional
    public DetalleCompra update(DetalleCompra detalleCompra) {
        DetalleCompra detalleCompraDb = detalleCompraRepository.findById(detalleCompra.getId()).orElse(null);
        if (detalleCompraDb == null) {
            return null;
        }
        detalleCompraDb.setCantidad(detalleCompra.getCantidad());
        detalleCompraDb.setTotal(detalleCompra.getTotal());
        detalleCompraDb.setProducto_id(detalleCompra.getProducto_id());
        detalleCompraDb.setCompra(detalleCompra.getCompra());
        return detalleCompraRepository.save(detalleCompraDb);
    }

    @Override
    @Transactional
    public int delete(int id) {
        DetalleCompra detalleCompraDb = detalleCompraRepository.findById(id).orElse(null);
        if (detalleCompraDb == null) {
            return 0;
        } else {
            detalleCompraRepository.delete(detalleCompraDb);
            return 1;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<DetalleCompra> findByCompraId(int ventaId) {
        // Consulta los detalles de venta por id de la venta usando el repository
        return detalleCompraRepository.findByCompraId(ventaId);
    }
}
