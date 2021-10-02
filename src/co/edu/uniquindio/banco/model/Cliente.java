package co.edu.uniquindio.banco.model;

import java.io.Serializable;
import java.util.HashMap;

public class Cliente extends Persona implements Serializable, Comparable<Cliente> {

	private static final long serialVersionUID = 1L;
	private Empleado empleadoAsociado;
	private HashMap<String, Cuenta> listaCuentasCliente = new HashMap<>();

	public Cliente() {}
	
	
	



	public Empleado getEmpleadoAsociado() {
		return empleadoAsociado;
	}
	public void setEmpleadoAsociado(Empleado empleadoAsociado) {
		this.empleadoAsociado = empleadoAsociado;
	}
	public HashMap<String, Cuenta> getListaCuentasCliente() {
		return listaCuentasCliente;
	}
	public void setListaCuentasCliente(HashMap<String, Cuenta> listaCuentasCliente) {
		this.listaCuentasCliente = listaCuentasCliente;
	}

	@Override
	public int compareTo(Cliente c) {
		return this.getCedula().compareTo(c.getCedula());
	}
}
