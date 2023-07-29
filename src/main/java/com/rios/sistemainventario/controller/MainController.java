package com.rios.sistemainventario.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;

import com.rios.sistemainventario.service.UsuarioService;
import com.rios.sistemainventario.service.AdministradorService;

import org.springframework.ui.Model;

@Controller
public class MainController {
	  private final AdministradorService AdministradorService;
	    
	  @Autowired
	    public MainController(AdministradorService AdministradorService) {
	        this.AdministradorService = AdministradorService;
	    }
	  @GetMapping("/administrador")
	    public String getAdministrador(Model model) {
			return "administrador";
		}
		
		@GetMapping("/login")
	    public String mostrarFormularioLogin() {
			return "login";
		}
		
		@PostMapping("/login")
	    public String procesarFormularioLogin(@RequestParam("email") String email,
	                                          @RequestParam("password") String password,
	                                          Model model) {
	    
	         try {
				if (AdministradorService.validarContraseña( email,password)) {
				     return "redirect:/categorias"; 
				 } else {
				     model.addAttribute("error", "Credenciales inválidas. Inténtalo de nuevo.");
				     return "login"; 
				 }
			} catch (Exception e) {
				e.printStackTrace();
			}

	        return "redirect:/categorias";
	    }
		
	@GetMapping("/categorias")
    public String getCategorias(Model model) {
		return "categorias";
	}
	
	@GetMapping("/marcas")
    public String getMarcas(Model model) {
		return "marcas";
	}
	
	@GetMapping("/cargos")
    public String getCargos(Model model) {
		return "cargos";
	}
	
	@GetMapping("/unidades")
    public String getUnidades(Model model) {
		return "unidades";
	}
	
	@GetMapping("/proveedores")
	public String getProveedores(Model model) {
		return "proveedores";
	}
	
	@GetMapping("/productos")
	public String getProductos(Model model) {
		return "productos";
	}
	@GetMapping("/compras")
	public String getCompras(Model model) {
		return "compras";
	}
	
	@GetMapping("/ingreso")
    public String getVentas(Model model) {
		return "ingreso";
	}
	
	@GetMapping("/usuarios")
	public String getUsuarios(Model model) {
		return "usuarios";
	}
	
	@GetMapping("/listacompras")
    public String getListaCompras(Model model) {
		return "lista_compra";
	}
	@GetMapping("/listaventas")
    public String getListaVentas(Model model) {
		return "lista_ingreso";
	}
	
}
