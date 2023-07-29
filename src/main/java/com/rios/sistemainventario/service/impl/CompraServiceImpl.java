package com.rios.sistemainventario.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rios.sistemainventario.entity.Compra;
import com.rios.sistemainventario.repository.CompraRepository;
import com.rios.sistemainventario.service.CompraService;

import java.util.Date;
import java.util.List;

@Service
public class CompraServiceImpl implements CompraService {
    @Autowired
    private CompraRepository compraRepository;
    @Override
    @Transactional(readOnly = true)
    public List<Compra> findAll() {
        return compraRepository.findAll();
    }
    @Override
    @Transactional(readOnly = true)
    public Compra findById(int id) {
        return compraRepository.findById(id).orElse(null);
    }
    @Override
    @Transactional
    public Compra create(Compra compras) {
        return compraRepository.save(compras);
    }
    @Override
    @Transactional
    public Compra update(Compra compras) {
        Compra compraDb = compraRepository.findById(compras.getId()).orElse(null);
        if (compraDb == null) {
            return null;
        }
        compraDb.setFecha(compras.getFecha());
        compraDb.setTotal(compras.getTotal());
        compraDb.setEstado(compras.getEstado());
        compraDb.setDetalles(compras.getDetalles());
        return compraRepository.save(compraDb);
    }
    @Override
    @Transactional
    public int delete(int id) {
        Compra compraDb = compraRepository.findById(id).orElse(null);
        if (compraDb == null) {
            return 0;
        } else {
            compraRepository.delete(compraDb);
            return 1;
        }
    }

   

    @Override
    @Transactional(readOnly = true)
    public List<Compra> findComprasByFecha(Date fecha) {
        return compraRepository.findComprasByFecha(fecha);
    }


    
    
}
