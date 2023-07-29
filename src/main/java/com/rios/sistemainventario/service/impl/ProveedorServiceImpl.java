package com.rios.sistemainventario.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rios.sistemainventario.entity.Proveedor;
import com.rios.sistemainventario.repository.ProveedorRepository;
import com.rios.sistemainventario.service.ProveedorService;


@Service
public class ProveedorServiceImpl implements ProveedorService{
	@Autowired
	private ProveedorRepository repository ;

	@Override
	@Transactional(readOnly = true)
	public List<Proveedor> findAll() {
		try {
			return repository.findAll();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Proveedor findById(int id) {
		try {
			return repository.findById(id).orElse(null);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	
	public Proveedor findByNombre(String nombre) {
		try {
			return repository.findByNombre(nombre);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Proveedor> findByNombreContaining(String nombre) {
		try {
			return repository.findByNombreContaining(nombre);
			} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Proveedor findByRuc(String ruc) {
		try {
			return repository.findByRuc(ruc);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Proveedor> findByRucContaining(String ruc) {
		try {
			return repository.findByRucContaining(ruc);
			} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Proveedor findByDireccion(String direccion) {
		try {
			return repository.findByDireccion(direccion);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Proveedor> findByDireccionContaining(String direccion) {
		try {
			return repository.findByDireccionContaining(direccion);
			} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Proveedor findByTelefono(Integer telefono) {
		try {
			return repository.findByTelefono(telefono);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Proveedor> findByTelefonoContaining(Integer telefono) {
		try {
			return repository.findByTelefonoContaining(telefono);
			} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Proveedor findByCorreo(String correo) {
		try {
			return repository.findByCorreo(correo);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Proveedor> findByCorreoContaining(String correo) {
		try {
			return repository.findByCorreoContaining(correo);
			} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Proveedor create(Proveedor obj) {
		try {
			return repository.save(obj);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
					
		}
	}

	@Override
	public Proveedor update(Proveedor obj) {
		try {
			Proveedor proveedorDb=repository.findById(obj.getId()).orElse(null);
			if(proveedorDb==null) {
				return null;
			}		
			proveedorDb.setNombre(obj.getNombre());
			proveedorDb.setRuc(obj.getRuc());
			proveedorDb.setDireccion(obj.getDireccion());
			proveedorDb.setTelefono(obj.getTelefono());
			proveedorDb.setCorreo(obj.getCorreo());
			return repository.save(proveedorDb);
			
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public int delete(int id) {
		try {
			Proveedor proveedorDb=repository.findById(id).orElse(null);
			if(proveedorDb==null) {
				return  0;
			}else {
				repository.delete(proveedorDb);
			}
		} catch (Exception e) {
			return 0;
		}
		return 0;
	}

}
