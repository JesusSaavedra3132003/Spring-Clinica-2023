package com.project.consorcio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.consorcio.entity.Paciente;
import com.project.consorcio.repository.PacienteRepository;

@Service
public class PacienteService {

	@Autowired
	private PacienteRepository repo;
	
	public void registrar(Paciente pac) {
		repo.save(pac);
	}
	
	public void actualizar(Paciente pac) {
		repo.save(pac);
	}
	
	public void eliminarPorId(Integer cod) {
		repo.deleteById(cod);
	}
	
	public Paciente buscarPorId(Integer cod) {
		return repo.findById(cod).orElse(null);
		
	}
	
	public List<Paciente> listarTodos(){
		return repo.findAll();
	}
}
