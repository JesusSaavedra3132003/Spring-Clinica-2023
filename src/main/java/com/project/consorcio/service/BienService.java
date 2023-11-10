package com.project.consorcio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.consorcio.entity.Bien;
import com.project.consorcio.repository.BienRepository;

@Service
public class BienService {
	@Autowired
	private BienRepository repo;
	
	public List<Bien> bienesPoTipo(int codTipo){
		return repo.listarBienesPoTipo(codTipo);
	}
}
