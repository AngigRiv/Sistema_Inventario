package com.rios.sistemainventario.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rios.sistemainventario.entity.Marca;
import com.rios.sistemainventario.repository.MarcaRepository;
import com.rios.sistemainventario.service.MarcaService;

@Service
public class MarcaServiceImpl implements MarcaService {
	@Autowired
	private MarcaRepository repository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Marca> findAll() {
		try {
			return repository.findAll();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Marca findById(int id) {
		try {
			return repository.findById(id).orElse(null);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Marca findByNombre(String nombre) {
		try {
			return repository.findByNombre(nombre);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Marca> findByNombreContaining(String nombre) {
		try {
			return repository.findByNombreContaining(nombre);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	@Transactional
	public Marca create(Marca obj) {
		try {
			return repository.save(obj);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	@Transactional
	public Marca update(Marca obj) {
		try {
			Marca marcaDb=repository.findById(obj.getId()).orElse(null);
			if(marcaDb==null) {
				return null;
			}
			marcaDb.setNombre(obj.getNombre());
			return repository.save(marcaDb);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public int delete(int id) {
		try {
			Marca marcaDb=repository.findById(id).orElse(null);
			if(marcaDb==null) {
				return 0;
			}else {
				repository.delete(marcaDb);
				return 1;
			}
		} catch (Exception e) {
			return 0;
		}
	}

}
