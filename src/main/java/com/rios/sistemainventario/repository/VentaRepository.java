package com.rios.sistemainventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rios.sistemainventario.entity.Venta;

import java.util.Date;
import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer> {
	@Query("SELECT v FROM Venta v WHERE v.fecha = ?1")
	List<Venta> findVentasByFecha(Date fecha);

}


