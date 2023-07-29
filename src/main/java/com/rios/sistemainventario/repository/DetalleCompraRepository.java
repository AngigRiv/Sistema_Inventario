package com.rios.sistemainventario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rios.sistemainventario.entity.DetalleCompra;



@Repository
public interface DetalleCompraRepository extends JpaRepository<DetalleCompra, Integer> {
	 List<DetalleCompra> findByCompraId(int ventaId);
}
