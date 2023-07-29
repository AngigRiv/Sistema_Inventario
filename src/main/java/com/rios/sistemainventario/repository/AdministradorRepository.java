package com.rios.sistemainventario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.rios.sistemainventario.entity.Administrador;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Integer> {
	List<Administrador> findByEmailContaining(String texto);
	Administrador findByEmail(String email);
    
}
