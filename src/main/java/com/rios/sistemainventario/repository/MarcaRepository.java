package com.rios.sistemainventario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rios.sistemainventario.entity.Marca;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Integer> {
	List<Marca> findByNombreContaining(String texto);
	Marca findByNombre(String texto);
}
