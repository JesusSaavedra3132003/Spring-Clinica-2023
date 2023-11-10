package com.project.consorcio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.consorcio.entity.Categoria;
import com.project.consorcio.entity.Paciente;
import com.project.consorcio.service.CategoriaService;
import com.project.consorcio.service.PacienteService;

@Controller
@RequestMapping("/paciente")
public class PacienteController {
	
	@Autowired 
	private PacienteService servicioPac;
	
	@Autowired
	private CategoriaService servicioCat;
	
	@RequestMapping("/lista")
	public String index(Model model) {
		model.addAttribute("pacientes", servicioPac.listarTodos());
		model.addAttribute("categorias", servicioCat.listarTodos());
		return "paciente";
	}
	
	@RequestMapping("/grabar")
	public String grabar(@RequestParam("codigo") Integer cod,
			             @RequestParam("nombres") String nom,
			             @RequestParam("apellidos") String ape,
			             @RequestParam("sexo") String sex,
			             @RequestParam("categoria") int codCat,
			             RedirectAttributes redirect) {
		
		
		try {
			Paciente pac = new Paciente();
			pac.setNombres(nom);
			pac.setApellidos(ape);
			pac.setSexo(sex);
			
			
			Categoria cat = new Categoria();
			cat.setCodigo(codCat);
			
			pac.setCategoria(cat);
			
			if(cod==0) {
				servicioPac.registrar(pac);
				redirect.addFlashAttribute("MENSAJE", "Paciente registrado");
			}
			else {
				pac.setCodigo(cod);
				servicioPac.actualizar(pac);
				redirect.addFlashAttribute("MENSAJE", "Paciente actualizado");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/paciente/lista";
	}
	
	@RequestMapping("/buscar")
	@ResponseBody
	public Paciente buscar(@RequestParam("codigo") Integer cod) {
		return servicioPac.buscarPorId(cod);
	}
}
