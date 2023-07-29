package com.rios.sistemainventario.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rios.sistemainventario.entity.Cargo;
import com.rios.sistemainventario.entity.Usuario;
import com.rios.sistemainventario.repository.CargoRepository;
import com.rios.sistemainventario.repository.UsuarioRepository;
import com.rios.sistemainventario.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	@Autowired
	private UsuarioRepository repository;
	@Autowired
	private CargoRepository cargoRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findAll() {
		try {
			return repository.findAll();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findById(int id) {
		try {
			return repository.findById(id).orElse(null);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findByNombre(String nombre) {
		try {
			return repository.findByNombre(nombre);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findByNombreContaining(String nombre) {
		try {
			return repository.findByNombreContaining(nombre);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	@Transactional
	public Usuario create(Usuario obj) {
		try {
			return repository.save(obj);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	@Transactional
	public Usuario update(Usuario obj) {
		try {
			Usuario usuarioDb=repository.findById(obj.getId()).orElse(null);
			if (usuarioDb==null) {
				return null;
			}
			usuarioDb.setNombre(obj.getNombre());
			usuarioDb.setApellido(obj.getApellido());
			usuarioDb.setDni(obj.getDni());
			usuarioDb.setTelefono(obj.getTelefono());
			usuarioDb.setFechaNac(obj.getFechaNac());
			usuarioDb.setCorreo(obj.getCorreo());
			usuarioDb.setClave(obj.getClave());
			usuarioDb.setCargo(obj.getCargo());
			return repository.save(usuarioDb);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public int delete(int id) {
		try {
			Usuario usuarioDb=repository.findById(id).orElse(null);
			if (usuarioDb==null) {
				return 0;
			}else {
				repository.delete(usuarioDb);
				return 1;
			}
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Cargo> selectCargo() {
		try {
			return cargoRepository.findAll();
		} catch (Exception e) {
			return null;
		}
	}

}
