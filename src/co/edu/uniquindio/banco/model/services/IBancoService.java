package co.edu.uniquindio.banco.model.services;

import co.edu.uniquindio.banco.exceptions.ClienteException;
import co.edu.uniquindio.banco.exceptions.CuentaException;
import co.edu.uniquindio.banco.exceptions.EmpleadoException;
import co.edu.uniquindio.banco.model.Cliente;
import co.edu.uniquindio.banco.model.Cuenta;
import co.edu.uniquindio.banco.model.Empleado;
import co.edu.uniquindio.banco.model.Transaccion;

public interface IBancoService {

	public Empleado crearEmpleado(String nombre, String apellido, String cedula, String direccion, String telefono,
			String correo, String fechaNacimiento, String codigo, Double salario, String cargo) throws EmpleadoException;

	public Empleado actualizarEmpleado(String nombre, String apellido, String cedula, String direccion, String telefono,
			String correo, String fechaNacimiento, String codigo, Double salario);

	public Boolean eliminarEmpleado(String cedula);

	public Empleado obtenerEmpleado(String cedula);

	public Cliente crearCliente(String nombre, String apellido, String cedula, String direccion, String telefono,
			String correo, String fechaNacimiento) throws ClienteException;

	public Cliente actualizarCliente(String nombre, String apellido, String cedula, String direccion, String telefono,
			String correo, String fechaNacimiento);

	public Boolean eliminarCliente(String cedula);

	public Cliente obtenerCliente(String cedula);
	
	public Cuenta crearCuenta(String numeroCuenta, double saldo, String clienteAsociado, String tipoCuenta) throws ClienteException, CuentaException;
	
	public Cuenta actualizarCuenta(String numeroCuenta, double saldoCliente, String clienteAsociado, String tipoCuenta) throws ClienteException, CuentaException;
	
	public boolean eliminarCuenta(String numeroCuenta);

	public Transaccion RetirarDineroCuenta(double dineroRetiro, String numeroCuentaBancaria, String numeroCedulaTransaccion) throws ClienteException, CuentaException;

	public Transaccion DepositarDineroCuenta(double depositarDinero, String numeroCuentaBancaria, String numeroCedulaTransaccion) throws ClienteException;

	public Cuenta ConsultarSaldoCuenta(String numeroCuentaBancaria) throws CuentaException;
	

}
