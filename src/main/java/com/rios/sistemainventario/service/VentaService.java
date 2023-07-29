package com.rios.sistemainventario.service;

import java.util.Date;
import java.util.List;

import com.rios.sistemainventario.entity.Venta;

public interface VentaService {

    public List<Venta> findAll();

    public Venta findById(int id);

    public Venta update(Venta venta);

    public int delete(int id);

	Venta create(Venta venta);

	List<Venta> findVentasByFecha(Date fecha);


}
