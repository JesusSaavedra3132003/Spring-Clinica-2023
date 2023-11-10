package com.project.consorcio.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.consorcio.dto.Detalle;
import com.project.consorcio.entity.Bien;
import com.project.consorcio.entity.BienRequerimiento;
import com.project.consorcio.entity.Requerimiento;
import com.project.consorcio.entity.Usuario;
import com.project.consorcio.service.BienService;
import com.project.consorcio.service.RequerimientoService;
import com.project.consorcio.service.TipoBienService;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/requerimiento")
public class RequerimientoController {

	@Autowired
	private TipoBienService tipoServices;
	@Autowired
	private BienService bienServices;
	@Autowired
	private RequerimientoService requeService;

	@RequestMapping("/lista")
	public String lista(Model model) {
		model.addAttribute("tipos", tipoServices.listarTodos());
		return "requerimiento";
	}

	@RequestMapping("/consultaBienesPorTipo")
	@ResponseBody
	public List<Bien> consultaBienesPorTipo(@RequestParam("codigo") int cod) {
		return bienServices.bienesPoTipo(cod);
	}

	@RequestMapping("/adicionar")
	@ResponseBody
	public List<Detalle> adicionar(@RequestParam("codigo") int cod, @RequestParam("nombre") String nom,
			@RequestParam("cantidad") int can, HttpServletRequest request) {

		// declarar arreglo de objetos de la clase detalle
		List<Detalle> lista = null;
		// validar si existe el atributo de tipo sesion "datos"
		if (request.getSession().getAttribute("datos") == null)// no exite
			// crear arreglo lista
			lista = new ArrayList<Detalle>();
		else//si existe atributo session "datos" recuperar el valor de "datos" y guardar en lista

			lista = (List<Detalle>) request.getSession().getAttribute("datos");
		
		//crear objeto de la clase detalle
		Detalle det=new Detalle();
		//setear atributos
		det.setCodigo(cod);
		det.setNombre(nom);
		det.setCantidad(can);
		//adicionar objeto "det" dentro del arreglo "lista"
		lista.add(det);
		
		//crear vatributo de tipo sesion
		request.getSession().setAttribute("datos", lista);
		return lista;
	}
	
	@RequestMapping("/grabar")
	public String grabar(@RequestParam("numero") String num,
			@RequestParam("descripcion") String des,
			@RequestParam("tipo") String estado,
			@RequestParam("fecha") String fec, HttpServletRequest request) {
		try {
			//CREAR objeto de requerimiento
			Requerimiento bean=new Requerimiento();
			//setear
			bean.setNumero(num);
			bean.setNombre(des);
			bean.setEstado(estado);
			bean.setFecha(LocalDate.parse(fec));
			
			//crear objeto de usuario
			Usuario usu=new Usuario();
			//recuperar atributo de tipo Session CODIGOUSUARIO
			int cod=(int) request.getSession().getAttribute("CODIGOUSUARIO");
			usu.setCodigo(cod);
			//enviar usu a bean
			bean.setUsuario(usu);
			//detalle
			//recuperar atributo de tipo session de tipo "datos"
			List<Detalle> lista = (List<Detalle>) request.getSession().getAttribute("datos");
			//crear arreglo de BienRequerimiento
			List<BienRequerimiento> detalles= new ArrayList<BienRequerimiento>();
			//bucle
			for(Detalle det : lista) {
				//crear objeto de la entidad BienRequerimiento
				BienRequerimiento br =new BienRequerimiento();
				br.setBien(new Bien(det.getCodigo()));
				br.setCantidad(det.getCantidad());
				//adicionar objeto dr dentro de detalles
				detalles.add(br);
			}	
			//enviar detalles dentro de bean
			bean.setListaRequerimeinto(detalles);
			//grabar
			requeService.saveRequerimiento(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/requerimiento/lista";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}