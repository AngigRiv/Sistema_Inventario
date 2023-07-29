package com.rios.sistemainventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rios.sistemainventario.entity.Producto;


@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    Producto findByNombre(String nombre);

}
