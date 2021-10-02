package co.edu.uniquindio.banco.model;

import java.io.Serializable;

public class Gerente extends Empleado implements Comparable<Empleado>, Serializable{

	private static final long serialVersionUID = 1L;

	public Gerente() {}
	
	@Override
	public int compareTo(Empleado empleado) {
		return this.getCedula().compareTo(empleado.getCedula());
	}
}
