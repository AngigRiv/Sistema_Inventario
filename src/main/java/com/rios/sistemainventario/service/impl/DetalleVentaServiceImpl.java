package com.rios.sistemainventario.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rios.sistemainventario.entity.DetalleVenta;
import com.rios.sistemainventario.repository.DetalleVentaRepository;
import com.rios.sistemainventario.service.DetalleVentaService;

import java.util.List;

@Service
public class DetalleVentaServiceImpl implements DetalleVentaService {
    @Autowired
    private DetalleVentaRepository detalleVentaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<DetalleVenta> findAll() {
        return detalleVentaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public DetalleVenta findById(int id) {
        return detalleVentaRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public DetalleVenta create(DetalleVenta detalleVenta) {
        return detalleVentaRepository.save(detalleVenta);
    }

    @Override
    @Transactional
    public DetalleVenta update(DetalleVenta detalleVenta) {
        DetalleVenta detalleVentaDb = detalleVentaRepository.findById(detalleVenta.getId()).orElse(null);
        if (detalleVentaDb == null) {
            return null;
        }
        // Actualizar los campos necesarios del detalle de venta
        detalleVentaDb.setCantidad(detalleVenta.getCantidad());
        detalleVentaDb.setTotal(detalleVenta.getTotal());
        detalleVentaDb.setProducto_id(detalleVenta.getProducto_id());
        detalleVentaDb.setVenta(detalleVenta.getVenta());
        return detalleVentaRepository.save(detalleVentaDb);
    }

    @Override
    @Transactional
    public int delete(int id) {
        DetalleVenta detalleVentaDb = detalleVentaRepository.findById(id).orElse(null);
        if (detalleVentaDb == null) {
            return 0;
        } else {
            detalleVentaRepository.delete(detalleVentaDb);
            return 1;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<DetalleVenta> findByVentaId(int ventaId) {
        // Consulta los detalles de venta por id de la venta usando el repository
        return detalleVentaRepository.findByVentaId(ventaId);
    }
}
