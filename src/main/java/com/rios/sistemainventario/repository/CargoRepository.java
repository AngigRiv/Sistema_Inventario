package com.rios.sistemainventario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rios.sistemainventario.entity.Cargo;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Integer> {
	List<Cargo> findByNombreContaining(String texto);
	Cargo findByNombre(String texto);
}
