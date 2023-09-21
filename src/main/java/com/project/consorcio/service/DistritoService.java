package com.project.consorcio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.consorcio.entity.Distrito;
import com.project.consorcio.repository.DistritoRepository;

@Service
public class DistritoService {
	@Autowired
	private DistritoRepository repo;
	
	public List<Distrito> listarTodos(){
		return repo.findAll();
	}

}
