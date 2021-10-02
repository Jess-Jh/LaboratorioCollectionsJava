package co.edu.uniquindio.banco.model;

import java.io.Serializable;

public class Cajero extends Empleado implements Comparable<Empleado>, Serializable {

	private static final long serialVersionUID = 1L;

	public Cajero() {}
	
	@Override
	public int compareTo(Empleado empleado) {
		return this.getCedula().compareTo(empleado.getCedula());
	}
}
