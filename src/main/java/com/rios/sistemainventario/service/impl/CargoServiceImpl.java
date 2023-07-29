package com.rios.sistemainventario.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rios.sistemainventario.entity.Cargo;
import com.rios.sistemainventario.repository.CargoRepository;
import com.rios.sistemainventario.service.CargoService;

@Service
public class CargoServiceImpl implements CargoService {
	@Autowired
	private CargoRepository repository;

	@Override
	@Transactional(readOnly = true)
	public List<Cargo> findAll() {
		try {
			return repository.findAll();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Cargo findById(int id) {
		try {
			return repository.findById(id).orElse(null);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Cargo findByNombre(String nombre) {
		try {
			return repository.findByNombre(nombre);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Cargo> findByNombreContaining(String nombre) {
		try {
			return repository.findByNombreContaining(nombre);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	@Transactional
	public Cargo create(Cargo obj) {
		try {
			return repository.save(obj);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	@Transactional
	public Cargo update(Cargo obj) {
		try {
			Cargo cargoDb=repository.findById(obj.getId()).orElse(null);
			if(cargoDb==null) {
				return null;
			}
			cargoDb.setNombre(obj.getNombre());
			return repository.save(cargoDb);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public int delete(int id) {
		try {
			Cargo cargoDb=repository.findById(id).orElse(null);
			if(cargoDb==null) {
				return 0;
			}else {
				repository.delete(cargoDb);
				return 1;
			}
		} catch (Exception e) {
			return 0;
		}
	}
}
