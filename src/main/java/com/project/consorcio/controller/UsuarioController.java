package com.project.consorcio.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.project.consorcio.entity.Enlace;
import com.project.consorcio.entity.Usuario;
import com.project.consorcio.service.UsuarioService;

//atributos de tipo sesion
@SessionAttributes({"ENLACES","CODIGOUSUARIO"})

@Controller
@RequestMapping("/session")
public class UsuarioController {
	
	@Autowired
	private UsuarioService servicioUsu;
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/intranet")
	public String intranet(Authentication auth,Model model) {
		//obtener nombre del rol del usuario que inicio sesiòn
		String nomRol=auth.getAuthorities().stream()
			      .map(GrantedAuthority::getAuthority)
			      .collect(Collectors.joining(","));
		
		//
		List<Enlace> lista=servicioUsu.enlaceDelUsuario(nomRol);
		Usuario u = servicioUsu.sesionUsuario(auth.getName());
		model.addAttribute("CODIGOUSUARIO", u.getCodigo());
		model.addAttribute("ENLACES", lista);
		return "intranet";
	}
}
