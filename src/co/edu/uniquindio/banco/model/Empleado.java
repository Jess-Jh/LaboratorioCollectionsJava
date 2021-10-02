package co.edu.uniquindio.banco.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public abstract class Empleado extends Persona implements Serializable{

	private static final long serialVersionUID = 1L;
	private Double salario;
	private String codigo;
	private Set<Cliente> listaClientesAsociados = new HashSet<>();
	
	public Empleado() {}
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public Double getSalario() {
		return salario;
	}
	public void setSalario(Double salario) {
		this.salario = salario;
	}
	public Set<Cliente> getListaClientesAsociados() {
		return listaClientesAsociados;
	}
	public void setListaClientesAsociados(Set<Cliente> listaClientesAsociados) {
		this.listaClientesAsociados = listaClientesAsociados;
	}

}
