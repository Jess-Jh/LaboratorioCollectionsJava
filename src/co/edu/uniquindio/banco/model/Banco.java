package co.edu.uniquindio.banco.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import co.edu.uniquindio.banco.exceptions.EmpleadoException;
import co.edu.uniquindio.banco.model.services.IBancoService;

public class Banco implements IBancoService, Serializable {

	private static final long serialVersionUID = 1L;

	Set<Cliente> listaClientes = new HashSet<>();
	TreeSet<Empleado> listaEmpleados = new TreeSet<>();
	HashMap<String, Cuenta> listaCuentas = new HashMap<>();
	HashMap<String, Transaccion> listaTransaccionesAsociadas = new HashMap<>();
		
	public Banco() {}
	
	public Set<Cliente> getListaClientes() {
		return listaClientes;
	}
	public void setListaClientes(Set<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}
	public TreeSet<Empleado> getListaEmpleados() {
		return listaEmpleados;
	}
	public void setListaEmpleados(TreeSet<Empleado> listaEmpleados) {
		this.listaEmpleados = listaEmpleados;
	}
	public HashMap<String, Cuenta> getListaCuentas() {
		return listaCuentas;
	}
	public void setListaCuentas(HashMap<String, Cuenta> listaCuentas) {
		this.listaCuentas = listaCuentas;
	}
	public HashMap<String, Transaccion> getListaTransaccionesAsociadas() {
		return listaTransaccionesAsociadas;
	}
	public void setListaTransaccionesAsociadas(HashMap<String, Transaccion> listaTransaccionesAsociadas) {
		this.listaTransaccionesAsociadas = listaTransaccionesAsociadas;
	}

	@Override
	public Cliente crearCliente(String nombre, String apellido, String cedula, String direccion, String telefono,
			String correo, String fechaNacimiento) {
		Cliente nuevoCliente = new Cliente();
		nuevoCliente.setNombre(nombre);
		nuevoCliente.setApellido(apellido);
		nuevoCliente.setCedula(cedula);
		nuevoCliente.setDireccion(direccion);
		nuevoCliente.setTelefono(telefono);
		nuevoCliente.setCorreo(correo);
		nuevoCliente.setFechaNacimiento(fechaNacimiento);

		getListaClientes().add(nuevoCliente);
		return nuevoCliente;
	}

	@Override
	public void actualizarCliente(String nombre, String apellido, String cedula, String direccion, String telefono,
			String correo, String fechaNacimiento) {
		Cliente cliente = obtenerCliente(cedula);

		if (cliente != null) {
			cliente.setNombre(nombre);
			cliente.setApellido(apellido);
			cliente.setCedula(cedula);
			cliente.setDireccion(direccion);
			cliente.setTelefono(telefono);
			cliente.setCorreo(correo);
			cliente.setFechaNacimiento(fechaNacimiento);
		}
	}

	@Override
	public Boolean eliminarCliente(String cedula) {

		Boolean flagEliminado = false;
		Cliente cliente = obtenerCliente(cedula);

		if (cliente != null) {
			getListaClientes().remove(cliente);
			flagEliminado = true;
		}
		return flagEliminado;
	}

	@Override
	public Cliente obtenerCliente(String cedulaCliente) {

		Cliente clienteEncontrado = null;

		for (Cliente cliente : getListaClientes()) {
			if (cliente.getCedula().equalsIgnoreCase(cedulaCliente)) {
				clienteEncontrado = cliente;
				break;
			}
		}
		return clienteEncontrado;
	}

	@Override
	public void crearEmpleado(String nombre, String apellido, String cedula, String direccion, String telefono,
			String correo, String fechaNacimiento, String codigo, Double salario) throws EmpleadoException{

		Empleado nuevoEmpleado =  null;
		Empleado empleadoExistente = obtenerEmpleado(cedula);
		
		if(empleadoExistente != null)
			throw new EmpleadoException("El empleado con c�dula "+cedula+" no se puede crear. Ya existe");
		else {
			nuevoEmpleado = new Empleado();
			nuevoEmpleado.setNombre(nombre);
			nuevoEmpleado.setApellido(apellido);
			nuevoEmpleado.setCedula(cedula);
			nuevoEmpleado.setDireccion(direccion);
			nuevoEmpleado.setTelefono(telefono);
			nuevoEmpleado.setCorreo(correo);
			nuevoEmpleado.setFechaNacimiento(fechaNacimiento);
			nuevoEmpleado.setCodigo(codigo);
			nuevoEmpleado.setSalario(salario);

			getListaEmpleados().add(nuevoEmpleado);
		}
	}

	@Override
	public void actualizarEmpleado(String nombre, String apellido, String cedula, String direccion, String telefono,
			String correo, String fechaNacimiento, String codigo, Double salario) {

		Empleado empleado = obtenerEmpleado(cedula);

		if (empleado != null) {
			empleado.setNombre(nombre);
			empleado.setApellido(apellido);
			empleado.setCedula(cedula);
			empleado.setDireccion(direccion);
			empleado.setTelefono(telefono);
			empleado.setCorreo(correo);
			empleado.setFechaNacimiento(fechaNacimiento);
			empleado.setCodigo(codigo);
			empleado.setSalario(salario);
		}
	}

	@Override
	public Boolean eliminarEmpleado(String cedula) {

		Boolean flagEliminado = false;
		Empleado empleado = obtenerEmpleado(cedula);

		if (empleado != null) {
			getListaEmpleados().remove(empleado);
			flagEliminado = true;
		}
		return flagEliminado;
	}

	@Override
	public Empleado obtenerEmpleado(String cedula) {

		Empleado empleadoEncontrado = null;

		for (Empleado empleado : getListaEmpleados()) {
			if (empleado.getCedula().equalsIgnoreCase(cedula)) {
				empleadoEncontrado = empleado;
				break;
			}
		}
		return empleadoEncontrado;
	}

	@Override
	public void DepositarDineroCuenta(Integer numeroCuenta) {

	}

	@Override
	public void RetirarDineroCuenta(Integer cedula, Integer numeroCuenta) {

	}

	@Override
	public void ConsultarSaldoCuenta(Integer numeroCuenta) {

	}

	class Comparacion implements Comparator{

		@Override
		public int compare(Object arg0, Object arg1) {
			
			Cliente c1 = (Cliente) arg0;
			Cliente c2 = (Cliente) arg1;
			int resultado = c1.getCedula().compareTo(c2.getCedula());
			return resultado;
		}
	}
	
	class Comparacion2 implements Comparator{

		@Override
		public int compare(Object arg0, Object arg1) {
			
			Cliente c1 = (Cliente) arg0;
			Cliente c2 = (Cliente) arg1;
			int resultado = c1.getTelefono().compareTo(c2.getTelefono());
			return resultado;
		}
	}
	
	

}
