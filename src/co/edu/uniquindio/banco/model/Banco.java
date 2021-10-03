package co.edu.uniquindio.banco.model;

import java.io.Serializable;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import co.edu.uniquindio.banco.exceptions.ClienteException;
import co.edu.uniquindio.banco.exceptions.CuentaException;
import co.edu.uniquindio.banco.exceptions.EmpleadoException;
import co.edu.uniquindio.banco.model.services.IBancoService;

public class Banco implements IBancoService, Serializable {

	private static final long serialVersionUID = 1L;

	Set<Cliente> listaClientes = new HashSet<>();
	Set<Empleado> listaEmpleados = new TreeSet<>();
	HashMap<String, Cuenta> listaCuentas = new HashMap<>();
	HashMap<String, Transaccion> listaTransaccionesAsociadas = new HashMap<>();
		
	public Banco() {}
	
	public Set<Cliente> getListaClientes() {
		return listaClientes;
	}
	public void setListaClientes(Set<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}
	public Set<Empleado> getListaEmpleados() {
		return listaEmpleados;
	}
	public void setListaEmpleados(Set<Empleado> listaEmpleados) {
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

	//--------------------------------------------------- CRUD Cliente ------------------------------------------------------------------------------------->>
	@Override
	public Cliente crearCliente(String nombre, String apellido, String cedula, String direccion, String telefono,
			String correo, String fechaNacimiento) throws ClienteException {
		
		if(existeCliente(cedula)) {
			throw new ClienteException("El cliente con la cédula " + cedula + " ya se encuentra registrado");
		} else {			
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
	}

	private boolean existeCliente(String cedula) {
		Cliente cliente;
	     		
		for( Iterator<Cliente> it = listaClientes.iterator(); it.hasNext();) { 
			cliente = it.next();
			
			if (cliente.getCedula().equalsIgnoreCase(cedula)) {
				return true;
			}			
		}
		return false;
	}

	@Override
	public Cliente actualizarCliente(String nombre, String apellido, String cedula, String direccion, String telefono,
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
			
			return cliente;
		} 
		return cliente;
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
		
		Cliente clienteEncontrado = null, cliente;
				
		for( Iterator<Cliente> it = listaClientes.iterator(); it.hasNext();) { 
			cliente = it.next();
			
			if (cliente.getCedula().equalsIgnoreCase(cedulaCliente)) {
				clienteEncontrado = cliente;
				break;
			}			
		}
		return clienteEncontrado;
	}
	//--------------------------------------------------------------------------------------------------------------------------------------------------------||

	//--------------------------------------------------- CRUD Empleado ------------------------------------------------------------------------------------->>
	@Override
	public Empleado crearEmpleado(String nombre, String apellido, String cedula, String direccion, String telefono,
			String correo, String fechaNacimiento, String codigo, Double salario, String cargo) throws EmpleadoException{

		Empleado nuevoEmpleado = null;
		
		if(cargo.equalsIgnoreCase("Gerente")) nuevoEmpleado = new Gerente();
		if(cargo.equalsIgnoreCase("Cajero")) nuevoEmpleado = new Cajero();
		if(cargo.equalsIgnoreCase("AsesorVentas")) nuevoEmpleado = new AsesorVentas();
		
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
	
		return nuevoEmpleado;
	}

	@Override
	public Empleado actualizarEmpleado(String nombre, String apellido, String cedula, String direccion, String telefono, String correo, 
											String fechaNacimiento, String codigo, Double salario) {

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
		return empleado;
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

		Empleado empleadoEncontrado = null, empleado;
		
		for( Iterator<Empleado> it = listaEmpleados.iterator(); it.hasNext();) { 
			empleado = it.next();
			
			if (empleado.getCedula().equalsIgnoreCase(cedula)) {
				empleadoEncontrado = empleado;
				break;
			}			
		}
		return empleadoEncontrado;
	}
	//--------------------------------------------------------------------------------------------------------------------------------------------------------||

	//--------------------------------------------------- CRUD Cuenta ------------------------------------------------------------------------------------->>
	@Override
	public Cuenta crearCuenta(String numeroCuenta, double saldoCliente, String clienteAsociado, String tipoCuenta) throws ClienteException, CuentaException {
		Cliente cliente = obtenerCliente(clienteAsociado);
		Cuenta nuevaCuenta = null;
		
		if(cliente == null)
			throw new ClienteException("El cliente con la cédula " + clienteAsociado + " no se encuentra registrado");
		else if(validarNumeroCuenta(numeroCuenta)) {
			if(tipoCuenta.equalsIgnoreCase("Cuenta de Ahorro")) nuevaCuenta = new CuentaAhorro();
			if(tipoCuenta.equalsIgnoreCase("Cuenta Corriente")) nuevaCuenta = new CuentaCorriente();
			
			nuevaCuenta.setNumeroCuenta(numeroCuenta);
			nuevaCuenta.setSaldo(saldoCliente);
			nuevaCuenta.setClienteAsociado(cliente);
			
			getListaCuentas().put(numeroCuenta, nuevaCuenta);	
		}
		
		return nuevaCuenta;
	}
	
	private boolean validarNumeroCuenta(String numeroCuenta) throws CuentaException {
		for( Iterator it = listaCuentas.keySet().iterator(); it.hasNext();) { 
			String clave = (String)it.next();
			
			if(clave.equalsIgnoreCase(numeroCuenta)) throw new CuentaException("El número de la cuenta " + numeroCuenta + " ya se encuentra registrada");
		}
		return true;
	}
	
	@Override
	public Cuenta actualizarCuenta(String numeroCuenta, double saldoCliente, String clienteAsociado, String tipoCuenta) throws ClienteException, CuentaException {
		
		Cliente cliente = obtenerCliente(clienteAsociado);
		Cuenta cuenta = obtenerCuenta(numeroCuenta);
		
		if(cliente == null)
			throw new ClienteException("El cliente con la cédula " + clienteAsociado + " no se encuentra registrado");
		else if(cuenta == null)
			throw new CuentaException("La cuenta con el número " + numeroCuenta + " no se encuentra registrada");
		else {
			cuenta.setNumeroCuenta(numeroCuenta);
			cuenta.setClienteAsociado(cliente);
			cuenta.setSaldo(saldoCliente);
		}
		return cuenta;
	}

	private Cuenta obtenerCuenta(String numeroCuenta) {
		Cuenta cuenta = null;
		
		for( Iterator it = listaCuentas.keySet().iterator(); it.hasNext();) { 
			String clave = (String)it.next();
			cuenta = listaCuentas.get(clave);
			if(cuenta.getNumeroCuenta().equalsIgnoreCase(numeroCuenta)) return cuenta;
		}
		return cuenta;
	}
	
	@Override
	public boolean eliminarCuenta(String numeroCuenta) {
		Boolean flagEliminado = false;
		Cuenta cuenta = obtenerCuenta(numeroCuenta);

		if (cuenta != null) {
			getListaCuentas().remove(cuenta);
			flagEliminado = true;
		}
		return flagEliminado;
	}

	@Override
	public Transaccion DepositarDineroCuenta(double depositarDinero, String numeroCuentaBancaria, String numeroCedulaTransaccion) throws ClienteException {
		Transaccion depositoTransaccion = null;
		Cuenta cuenta = obtenerCuenta(numeroCuentaBancaria);
		Cliente cliente = obtenerCliente(numeroCedulaTransaccion);

		if(cliente == null) throw new ClienteException("El cliente con la cédula " + numeroCedulaTransaccion + " no se encuentra registrado");
		
		if(!cuenta.getClienteAsociado().getCedula().equalsIgnoreCase(numeroCedulaTransaccion)) 
			throw new ClienteException("El cliente con la cédula " + numeroCedulaTransaccion + " no se encuentra asociado a la cuenta");
		
		depositoTransaccion = cuenta.validarDeposito(depositarDinero, numeroCuentaBancaria);	
		
		if(depositoTransaccion.getEstado().equals(EstadoTransaccion.EXITOSA)) cuenta.depositarDinero(depositarDinero);
		
		listaTransaccionesAsociadas.put(numeroCuentaBancaria, depositoTransaccion);
	
		return depositoTransaccion;
	}

	@Override
	public Transaccion RetirarDineroCuenta(double dineroRetiro, String numeroCuentaBancaria, String numeroCedulaTransaccion) throws ClienteException, CuentaException {
		Transaccion retiroTransaccion = null;
		
		Cuenta cuenta = obtenerCuenta(numeroCuentaBancaria);
		
		Cliente cliente = obtenerCliente(numeroCedulaTransaccion);
		
		if(cliente == null)
			throw new ClienteException("El cliente con la cédula " + numeroCedulaTransaccion + " no se encuentra registrado");
		else if(cuenta == null)
			throw new CuentaException("La cuenta con el número " + numeroCuentaBancaria + " no se encuentra registrada");
		else if(!cuenta.getClienteAsociado().getCedula().equalsIgnoreCase(numeroCedulaTransaccion)) 
			throw new ClienteException("El cliente con la cédula " + numeroCedulaTransaccion + " no se encuentra asociado a la cuenta");
		else {
			retiroTransaccion = cuenta.validarTransaccion(dineroRetiro, numeroCuentaBancaria);	
			
			listaTransaccionesAsociadas.put(numeroCedulaTransaccion, retiroTransaccion);
			
			if(retiroTransaccion.getEstado().equals(EstadoTransaccion.EXITOSA)) cuenta.retirarDinero(dineroRetiro);									
		}			
		return retiroTransaccion;
	}

	@Override
	public Cuenta ConsultarSaldoCuenta(String numeroCuentaBancaria) throws CuentaException {
		
		Cuenta cuentaCliente = obtenerCuenta(numeroCuentaBancaria);
		
		if(cuentaCliente == null)
			throw new CuentaException("La cuenta con el número " + numeroCuentaBancaria + " no se encuentra registrada");
		
		return cuentaCliente;
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
