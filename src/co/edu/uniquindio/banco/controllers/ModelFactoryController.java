package co.edu.uniquindio.banco.controllers;

import co.edu.uniquindio.banco.exceptions.ClienteException;
import co.edu.uniquindio.banco.exceptions.CuentaException;
import co.edu.uniquindio.banco.exceptions.EmpleadoException;
import co.edu.uniquindio.banco.model.AsesorVentas;
import co.edu.uniquindio.banco.model.Banco;
import co.edu.uniquindio.banco.model.Cliente;
import co.edu.uniquindio.banco.model.Cuenta;
import co.edu.uniquindio.banco.model.CuentaAhorro;
import co.edu.uniquindio.banco.model.CuentaCorriente;
import co.edu.uniquindio.banco.model.Empleado;
import co.edu.uniquindio.banco.model.EstadoTransaccion;
import co.edu.uniquindio.banco.model.Gerente;
import co.edu.uniquindio.banco.model.TipoTransaccion;
import co.edu.uniquindio.banco.model.Transaccion;
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
		Cuenta cuenta = new CuentaAhorro();
		Transaccion transaccion;
		
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
		
		cuenta = new CuentaCorriente();
		cuenta.setNumeroCuenta("12345678");
		cuenta.setSaldo(3000000.0);
		cuenta.setClienteAsociado(cliente);
		
		banco.getListaCuentas().put(cuenta.getNumeroCuenta(), cuenta);
		
		transaccion = new Transaccion(500000, TipoTransaccion.DEPOSITO, EstadoTransaccion.EXITOSA);
		banco.getListaTransaccionesAsociadas().put(cuenta.getNumeroCuenta(), transaccion);
				
		cliente = new Cliente();
		cliente.setNombre("juan");
		cliente.setApellido("arias");
		cliente.setCedula("1094");
		cliente.setDireccion("Armenia");
		cliente.setCorreo("uni1@");
		cliente.setFechaNacimiento("12454");
		cliente.setTelefono("125444");

		banco.getListaClientes().add(cliente);
		
		cuenta = new CuentaAhorro();
		cuenta.setNumeroCuenta("34567890");
		cuenta.setSaldo(2000000.0);
		cuenta.setClienteAsociado(cliente);
		
		banco.getListaCuentas().put(cuenta.getNumeroCuenta(), cuenta);
		
		transaccion = new Transaccion(4000000, TipoTransaccion.RETIRO, EstadoTransaccion.RECHAZADA);
		banco.getListaTransaccionesAsociadas().put(cuenta.getNumeroCuenta(), transaccion);

		cliente = new Cliente();
		cliente.setNombre("juan");
		cliente.setApellido("Perez");
		cliente.setCedula("1095");
		cliente.setDireccion("Pererira");
		cliente.setCorreo("uni2@");
		cliente.setFechaNacimiento("12454");
		cliente.setTelefono("125444");
		
		banco.getListaClientes().add(cliente);

		cuenta = new CuentaCorriente();
		cuenta.setNumeroCuenta("67890123");
		cuenta.setSaldo(2500000.0);
		cuenta.setClienteAsociado(cliente);
		
		banco.getListaCuentas().put(cuenta.getNumeroCuenta(), cuenta);
		transaccion = new Transaccion(800000, TipoTransaccion.RETIRO, EstadoTransaccion.EXITOSA);
		banco.getListaTransaccionesAsociadas().put(cuenta.getNumeroCuenta(), transaccion);
		
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
		
		empleado = new AsesorVentas();
		empleado.setNombre("Luisa");
		empleado.setApellido("Martinez");
		empleado.setCedula("145678");
		empleado.setDireccion("Armenia");
		empleado.setCorreo("lui3@gmail");
		empleado.setFechaNacimiento("13453454");
		empleado.setTelefono("3234234");
		empleado.setCodigo("em-001");
		empleado.setSalario(1000000.0);	
		
		banco.getListaEmpleados().add(empleado);
		
		cuenta = new CuentaCorriente();
		cuenta.setNumeroCuenta("9872748");
		cuenta.setSaldo(8000000.0);
		cuenta.setClienteAsociado(cliente);
		
		banco.getListaCuentas().put(cuenta.getNumeroCuenta(), cuenta);
	
	}

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	//--------------------------------------------------- CRUD Empleado ------------------------------------------------------------------------------------->>
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
	//--------------------------------------------------------------------------------------------------------------------------------------------------------||

	//--------------------------------------------------- CRUD Cliente ------------------------------------------------------------------------------------->>
	
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
	//--------------------------------------------------------------------------------------------------------------------------------------------------------||

	//--------------------------------------------------- CRUD Cuenta ------------------------------------------------------------------------------------->>
	@Override
	public Cuenta crearCuenta(String numeroCuenta, double saldoCliente, String clienteAsociado, String tipoCuenta) throws ClienteException, CuentaException {
		
		Cuenta cuenta = banco.crearCuenta(numeroCuenta, saldoCliente, clienteAsociado, tipoCuenta);
		return cuenta;
	}
	
	@Override
	public Cuenta actualizarCuenta(String numeroCuenta, double saldoCliente, String clienteAsociado, String tipoCuenta) throws ClienteException, CuentaException {
		
		Cuenta cuenta = banco.actualizarCuenta(numeroCuenta, saldoCliente, clienteAsociado, tipoCuenta); 	
		return cuenta;
	}
	
	@Override
	public Boolean eliminarCuenta(String numeroCuenta) {
		if(banco.eliminarCuenta(numeroCuenta)) {
			return true;
		}		
		return false;
	}
	//--------------------------------------------------------------------------------------------------------------------------------------------------------||

	@Override
	public Transaccion RetirarDineroCuenta(double dineroRetiro, String numeroCuentaBancaria, String numeroCedulaTransaccion) throws ClienteException, CuentaException {
		
		Transaccion transaccion = banco.RetirarDineroCuenta(dineroRetiro, numeroCuentaBancaria, numeroCedulaTransaccion);	
		return transaccion;
	}

	@Override
	public Transaccion DepositarDineroCuenta(double depositarDinero, String numeroCuentaBancaria, String numeroCedulaTransaccion) throws ClienteException {
		Transaccion transaccion = banco.DepositarDineroCuenta(depositarDinero, numeroCuentaBancaria, numeroCedulaTransaccion);	
		return transaccion;
	}



	@Override
	public Cuenta ConsultarSaldoCuenta(String numeroCuentaBancaria) throws CuentaException {
		Cuenta cuenta = banco.ConsultarSaldoCuenta(numeroCuentaBancaria);
		
		return cuenta;
	}

	@Override
	public Empleado obtenerEmpleado(String cedula) {
		return null;
	}
	
}
