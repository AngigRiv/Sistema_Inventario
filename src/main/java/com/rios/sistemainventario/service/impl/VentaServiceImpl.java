package com.rios.sistemainventario.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rios.sistemainventario.entity.Venta;
import com.rios.sistemainventario.repository.VentaRepository;
import com.rios.sistemainventario.service.VentaService;

import java.util.Date;
import java.util.List;

@Service
public class VentaServiceImpl implements VentaService {
    @Autowired
    private VentaRepository ventaRepository;
    @Override
    @Transactional(readOnly = true)
    public List<Venta> findAll() {
        return ventaRepository.findAll();
    }
    @Override
    @Transactional(readOnly = true)
    public Venta findById(int id) {
        return ventaRepository.findById(id).orElse(null);
    }
    @Override
    @Transactional
    public Venta create(Venta venta) {
        return ventaRepository.save(venta);
    }
    @Override
    @Transactional
    public Venta update(Venta venta) {
        Venta ventaDb = ventaRepository.findById(venta.getId()).orElse(null);
        if (ventaDb == null) {
            return null;
        }
        ventaDb.setFecha(venta.getFecha());
        ventaDb.setTotal(venta.getTotal());
        ventaDb.setEstado(venta.getEstado());
        ventaDb.setDetalles(venta.getDetalles());
        return ventaRepository.save(ventaDb);
    }
    @Override
    @Transactional
    public int delete(int id) {
        Venta ventaDb = ventaRepository.findById(id).orElse(null);
        if (ventaDb == null) {
            return 0;
        } else {
            ventaRepository.delete(ventaDb);
            return 1;
        }
    }

   

    @Override
    @Transactional(readOnly = true)
    public List<Venta> findVentasByFecha(Date fecha) {
        return ventaRepository.findVentasByFecha(fecha);
    }


    
    
}
