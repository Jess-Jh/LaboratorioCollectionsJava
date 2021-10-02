package co.edu.uniquindio.banco.controllers;

import co.edu.uniquindio.banco.exceptions.ClienteException;
import co.edu.uniquindio.banco.exceptions.EmpleadoException;
import co.edu.uniquindio.banco.model.Banco;
import co.edu.uniquindio.banco.model.Cliente;
import co.edu.uniquindio.banco.model.Empleado;
import co.edu.uniquindio.banco.model.Gerente;
import co.edu.uniquindio.banco.model.services.IModelFactoryService;

public class ModelFactoryController implements IModelFactoryService{

	Banco banco;
	
	//------------------------------  Singleton ------------------------------------------------
	// Clase estatica oculta. Tan solo se instanciara el singleton una vez
	private static class SingletonHolder { 
		// El constructor de Singleton puede ser llamado desde aquí al ser protected
		private final static ModelFactoryController eINSTANCE = new ModelFactoryController();
	}

	// Método para obtener la instancia de nuestra clase
	public static ModelFactoryController getInstance() {
		return SingletonHolder.eINSTANCE;
	}
	
	public ModelFactoryController() {
		inicializarDatos();
	}

	private void inicializarDatos() {

		banco = new Banco();
		
		
		Cliente cliente = new Cliente();
		cliente.setNombre("juan");
		cliente.setApellido("arias");
		cliente.setCedula("125454");
		cliente.setDireccion("Armenia");
		cliente.setCorreo("uni1@");
		cliente.setFechaNacimiento("12454");
		cliente.setTelefono("125444");

		banco.getListaClientes().add(cliente);

		cliente = new Cliente();
		cliente.setNombre("juan");
		cliente.setApellido("Perez");
		cliente.setCedula("77787");
		cliente.setDireccion("Pererira");
		cliente.setCorreo("uni2@");
		cliente.setFechaNacimiento("12454");
		cliente.setTelefono("125444");

		banco.getListaClientes().add(cliente);
		
		cliente = new Cliente();
		cliente.setNombre("Alberto");
		cliente.setApellido("Arias");
		cliente.setCedula("12555");
		cliente.setDireccion("Pererira");
		cliente.setCorreo("uni3@");
		cliente.setFechaNacimiento("12454");
		cliente.setTelefono("125444");

		banco.getListaClientes().add(cliente);
		
		cliente = new Cliente();
		cliente.setNombre("juan");
		cliente.setApellido("arias");
		cliente.setCedula("1094");
		cliente.setDireccion("Armenia");
		cliente.setCorreo("uni1@");
		cliente.setFechaNacimiento("12454");
		cliente.setTelefono("125444");

		banco.getListaClientes().add(cliente);

		cliente = new Cliente();
		cliente.setNombre("juan");
		cliente.setApellido("Perez");
		cliente.setCedula("1095");
		cliente.setDireccion("Pererira");
		cliente.setCorreo("uni2@");
		cliente.setFechaNacimiento("12454");
		cliente.setTelefono("125444");

		banco.getListaClientes().add(cliente);
		
		cliente = new Cliente();
		cliente.setNombre("Alberto");
		cliente.setApellido("Arias");
		cliente.setCedula("1094");
		cliente.setDireccion("Pererira");
		cliente.setCorreo("uni3@");
		cliente.setFechaNacimiento("12454");
		cliente.setTelefono("125444");

		banco.getListaClientes().add(cliente);
		
		Empleado empleado = new Gerente();
		empleado.setNombre("Jaime");
		empleado.setApellido("Arias");
		empleado.setCedula("12555");
		empleado.setDireccion("Pererira");
		empleado.setCorreo("uni3@");
		empleado.setFechaNacimiento("12454");
		empleado.setTelefono("125444");
		empleado.setCodigo("em-001");
		empleado.setSalario(1000000.0);	
		
		banco.getListaEmpleados().add(empleado);

		
	}

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	@Override
	public Empleado crearEmpleado(String nombre, String apellido, String cedula, String direccion, String telefono,
			String correo, String fechaNacimiento, String codigo, double salarioEmpleado, String cargo) throws EmpleadoException {
		
		Empleado empleado = getBanco().crearEmpleado(nombre, apellido, cedula, direccion, telefono, correo, fechaNacimiento, codigo, salarioEmpleado, cargo);
		
		return empleado;
	}

	@Override
	public Boolean eliminarEmpleado(String cedula) {
		return getBanco().eliminarEmpleado(cedula);
	}


	@Override
	public Empleado actualizarEmpleado(String nombre, String apellido, String cedula, String direccion, String telefono,
			String correo, String fechaNacimiento, String codigo, Double salario) {
		
		Empleado empleado = banco.actualizarEmpleado(nombre, apellido, cedula, direccion, telefono, correo, fechaNacimiento, codigo, salario);
		return empleado;
	}

	@Override
	public Cliente crearCliente(String nombre, String apellido, String cedula, String direccion, String telefono,
			String correo, String fechaNacimiento) throws ClienteException {
		Cliente cliente = banco.crearCliente(nombre, apellido, cedula, direccion, telefono, correo, fechaNacimiento);
		
		return cliente;
	}

	@Override
	public Cliente actualizarCliente(String nombre, String apellido, String cedula, String direccion, String telefono,
			String correo, String fechaNacimiento) {
		
		Cliente cliente = banco.actualizarCliente(nombre, apellido, cedula, direccion, telefono, correo, fechaNacimiento);
		
		return cliente;
	}

	@Override
	public Boolean eliminarCliente(String cedula) {
		if(banco.eliminarCliente(cedula)) {
			return true;
		}		
		return false;	
	}

	@Override
	public Cliente obtenerCliente(String cedula) {
		return null;
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

	@Override
	public Empleado obtenerEmpleado(String cedula) {
		return null;
	}
	
	
	
	
	
}
