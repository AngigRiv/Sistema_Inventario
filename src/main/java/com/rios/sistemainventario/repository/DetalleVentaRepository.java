package com.rios.sistemainventario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rios.sistemainventario.entity.DetalleVenta;



@Repository
public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Integer> {
	 List<DetalleVenta> findByVentaId(int ventaId);
}
