package com.project.consorcio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.consorcio.entity.BienRequerimiento;
import com.project.consorcio.entity.BienRequerimientoPK;
import com.project.consorcio.entity.Requerimiento;
import com.project.consorcio.repository.BienRequerimientoRepository;
import com.project.consorcio.repository.RequerimientoRepository;

@Service
public class RequerimientoService {

	@Autowired
	private RequerimientoRepository repoReque;
	
	@Autowired
	private BienRequerimientoRepository repoDet;
	
	@Transactional
	public void saveRequerimiento(Requerimiento bean) {
		try {
			
			//guardar cabecera"requerimiento"
			repoReque.save(bean);
			//bucle
			for(BienRequerimiento det:bean.getListaRequerimeinto()) {
				//crear objeto llave
				BienRequerimientoPK llave=new BienRequerimientoPK();
				//setear
				llave.setCod_reque(bean.getCodigo());
				llave.setCod_bien(det.getBien().getCodigo());
				
				//enviar objeto llave al objeto det
				det.setPk(llave);
				//grabar detalle "BienRequerimiento"
				repoDet.save(det);				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}