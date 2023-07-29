package com.rios.sistemainventario.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rios.sistemainventario.entity.Unidad;
import com.rios.sistemainventario.repository.UnidadRepository;
import com.rios.sistemainventario.service.UnidadService;

@Service
public class UnidadServiceImpl implements UnidadService {
	@Autowired
	private UnidadRepository repository ;


	@Override
	@Transactional(readOnly = true)
	public List<Unidad> findAll() {
		try {
			return repository.findAll();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Unidad findById(int id) {
		try {
			return repository.findById(id).orElse(null);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Unidad findByNombre(String nombre) {
		try {
			return repository.findByNombre(nombre);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Unidad> findByNombreContaining(String nombre) {
		try {
			return repository.findByNombreContaining(nombre);
			} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Unidad findByAbreviatura(String abreviatura) {
		try {
			return repository.findByAbreviatura(abreviatura);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Unidad> findByAbreviaturaContaining(String abreviatura) {
		try {
			return repository.findByAbreviaturaContaining(abreviatura);
			} catch (Exception e) {
			return null;
		}
	}
	@Override
	@Transactional
	public int delete(int id) {
		try {
			Unidad unidadDb=repository.findById(id).orElse(null);
			if(unidadDb==null) {
				return  0;
			}else {
				repository.delete(unidadDb);
			}
		} catch (Exception e) {
			return 0;
		}
		return 0;
	}

	@Override
	public Unidad create(Unidad obj) {
		try {
			return repository.save(obj);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
					
		}
	}

	@Override 
	@Transactional
	public Unidad update(Unidad obj) {
		try {
			Unidad unidadDb=repository.findById(obj.getId()).orElse(null);
			if(unidadDb==null) {
				return null;
			}		
			unidadDb.setNombre(obj.getNombre());
			unidadDb.setAbreviatura(obj.getAbreviatura());
			return repository.save(unidadDb);
			
		} catch (Exception e) {
			return null;
		}
	}


}
