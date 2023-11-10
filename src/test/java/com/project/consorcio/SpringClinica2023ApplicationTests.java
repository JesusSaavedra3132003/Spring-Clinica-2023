package com.project.consorcio;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.project.consorcio.entity.Enlace;
import com.project.consorcio.entity.Usuario;
import com.project.consorcio.service.UsuarioService;

@SpringBootTest
class SpringClinica2023ApplicationTests {

	//prueba unitaria
	@Autowired
	private UsuarioService servicio;
	
	@Autowired
	private BCryptPasswordEncoder encriptar;
	
	@Test
	void contextLoads() {
	
		System.out.println("CLAVE :"+encriptar.encode("123"));
		/*Usuario bean=servicio.sesionUsuario("maria");
		if(bean==null)
			System.out.println("No existe usuario");
		else {
			List<Enlace> lista=servicio.enlaceDelUsuario(bean.getRol().getCodigo());
			lista.forEach(e->{
				System.out.println(e.getCodigo()+" "+e.getDescripcion()+" "+e.getRuta());
			});
		}*/
	}

}
