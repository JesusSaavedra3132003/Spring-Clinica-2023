package com.project.consorcio.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.consorcio.entity.Distrito;
import com.project.consorcio.entity.Especialidad;
import com.project.consorcio.entity.Medico;
import com.project.consorcio.entity.Sede;
import com.project.consorcio.service.DistritoService;
import com.project.consorcio.service.EspecialidadService;
import com.project.consorcio.service.MedicoService;
import com.project.consorcio.service.SedeService;

@Controller //anotacion para que se pueda comunicar con la vista
@RequestMapping("/medico")
public class MedicoController {

	@Autowired
	private MedicoService servicioMed;
	
	@Autowired
	private DistritoService servicioDistrito;
	
	@Autowired
	private SedeService servicioSede;
	
	@Autowired
	private EspecialidadService servicioEspecialidad;
	
	@RequestMapping("/lista")
	public String index(Model model) {
		model.addAttribute("medicos",servicioMed.listarTodos());
		model.addAttribute("distritos", servicioDistrito.listarTodos());
		model.addAttribute("sedes", servicioSede.listarTodos());
		model.addAttribute("especialidades", servicioEspecialidad.listarTodos());
		return "medico";
	}
	
		@RequestMapping("/grabar")
		public String grabar(@RequestParam("codigo") Integer cod,
				             @RequestParam("nombre") String nom, 
				             @RequestParam("apellido") String ape,
				             @RequestParam("fecha") String fec,
				             @RequestParam("sexo") String sex,
				             @RequestParam("estadoCivil") String ec,
				             @RequestParam("dni") String dni,
				             @RequestParam("sueldo") double sue,
				             @RequestParam("especialidad") int codEspe,
				             @RequestParam("sede") int codSede,
				             @RequestParam("direccion") String dir,
				             @RequestParam("distrito") int codDis,
				             RedirectAttributes redirect) {
			try {
				//crear objeto de la entidad Medicamento
				Medico med = new Medico();
				//setear atributos del objeto "med" con los parametros
				med.setNombre(nom);
				med.setApellido(ape);
				med.setFechaNacimiento(LocalDate.parse(fec));
				med.setSexo(sex);
				med.setEstadoCivil(ec);
				med.setDni(dni);
				med.setSueldo(sue);
				med.setDireccion(dir);
				
			
				Especialidad es=new Especialidad();
				Sede s=new Sede();
				Distrito d=new Distrito();
				
				es.setCodigo(codEspe);
				s.setCodigo(codSede);
				d.setCodigo(codDis);
			
				med.setEspecialidad(es);
				med.setSede(s);
				med.setDistrito(d);
				
				
				
				//validar paràmetro cod
				if(cod ==0){
				
				//invocar mètodo registrar
				servicioMed.registrar(med);
				//crear atributo de tipo flash
				redirect.addFlashAttribute("MENSAJE", "Mèdico registrado");
				}
				else {
					//setear atributo còdigo
					med.setCodigo(cod);
					servicioMed.actualizar(med);
					redirect.addFlashAttribute("MENSAJE", "Mèdico actualizado");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "redirect:/medico/lista";
		}
		
		//Crear ruta o direccion URL para buscar medicp segùn còdigo
		@RequestMapping("/buscar")
		@ResponseBody //anotacion convierte en JSON el valor de retorno del metodo que se encuentra ..
		public Medico buscar(@RequestParam("codigo") Integer cod)
		{
			return servicioMed.buscarPorID(cod);
		}
	
}
