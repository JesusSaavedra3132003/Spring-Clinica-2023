package com.project.consorcio.entity;

import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable //anotacion porque la clase va formar parte de un atributo de otra clase
public class RolEnlacePK{

	//los atributos tienen que ser el mismo nombre de la llave primaria compuesta de la bd
	private int idrol;
	private int idenlace;
	// hash code --genera un numero unico de la combinacion de los atributos
	@Override
	public int hashCode() {
		return Objects.hash(idenlace, idrol);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RolEnlacePK other = (RolEnlacePK) obj;
		return idenlace == other.idenlace && idrol == other.idrol;
	}
	public int getIdrol() {
		return idrol;
	}
	public void setIdrol(int idrol) {
		this.idrol = idrol;
	}
	public int getIdenlace() {
		return idenlace;
	}
	public void setIdenlace(int idenlace) {
		this.idenlace = idenlace;
	}	
}
