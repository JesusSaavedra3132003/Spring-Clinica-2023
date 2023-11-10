package com.project.consorcio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.consorcio.entity.Enlace;
import com.project.consorcio.entity.Usuario;
import com.project.consorcio.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repo;
	
	public Usuario sesionUsuario(String vLogin) {
		return repo.iniciarSesion(vLogin);
	}
	
	public List<Enlace> enlaceDelUsuario(String desRol){
		return repo.traerEnlacesDelUsuario(desRol);
	}
}
