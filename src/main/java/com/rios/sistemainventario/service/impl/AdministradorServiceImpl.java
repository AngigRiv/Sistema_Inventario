package com.rios.sistemainventario.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.rios.sistemainventario.entity.Administrador;

import com.rios.sistemainventario.repository.AdministradorRepository;
import com.rios.sistemainventario.service.AdministradorService;

@Service
public class AdministradorServiceImpl implements AdministradorService {
	@Autowired
	private AdministradorRepository repository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Administrador> findAll() {
		try {
			return repository.findAll();
		} catch (Exception e) {
			return null;
		}
	}
	

	@Override
	@Transactional(readOnly = true)
	public Administrador findById(int id) {
		try {
			return repository.findById(id).orElse(null);
		} catch (Exception e) {	
		   return null;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Administrador findByEmail(String email) {
		try {
			return repository.findByEmail(email);
		} catch (Exception e) {	
			return null;
		}
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Administrador> findByEmailContaining(String email) {
		try {
			return repository.findByEmailContaining(email);
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	@Transactional
	public Administrador create(Administrador obj) {
		try {
			return repository.save(obj);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	@Transactional
	public Administrador update(Administrador obj) {
		try {
			Administrador administradorDb=repository.findById(obj.getId()).orElse(null);
			if(administradorDb==null) {
				return null;
			}
			administradorDb.setEmail(obj.getEmail());
			return repository.save(administradorDb);
		} catch (Exception e) {
			return null;

		}
	}
	
	@Override
	@Transactional
	public int delete(int id) {
		try {
			Administrador administradorDb=repository.findById(id).orElse(null);
			if(administradorDb==null) {
				return 0;
			}else {
				repository.delete(administradorDb);
				return 1;
			}
		} catch (Exception e) {
			return 0;
		}
	}
	 @Autowired
	    public AdministradorServiceImpl(AdministradorRepository administradorDbRepository) {
	        this.repository = administradorDbRepository;
	    }

	    @Override
	    public Administrador buscarPorEmail(String email) {
	        return repository.findByEmail(email);
	    }

	    @Override
	    public boolean validarContraseña(String email, String password) {
	    	Administrador administrador = buscarPorEmail(email);
	        return administrador != null && administrador.getPassword().equals(password);
	    }
}
