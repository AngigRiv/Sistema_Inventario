package com.rios.sistemainventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.rios.sistemainventario.entity.Compra;

import java.util.Date;
import java.util.List;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Integer> {
	@Query("SELECT v FROM Compra v WHERE v.fecha = ?1")
	List<Compra> findComprasByFecha(Date fecha);

}


