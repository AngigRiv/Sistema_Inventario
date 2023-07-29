package com.rios.sistemainventario.service;

import java.util.Date;
import java.util.List;

import com.rios.sistemainventario.entity.Compra;



public interface CompraService {

    public List<Compra> findAll();

    public Compra findById(int id);

    public Compra update(Compra venta);

    public int delete(int id);

	Compra create(Compra venta);

	List<Compra> findComprasByFecha(Date fecha);


}
