package co.edu.uniquindio.banco.model.services;

import co.edu.uniquindio.banco.exceptions.ClienteException;
import co.edu.uniquindio.banco.exceptions.EmpleadoException;
import co.edu.uniquindio.banco.model.Cliente;
import co.edu.uniquindio.banco.model.Empleado;

public interface IModelFactoryService {

	public Empleado crearEmpleado(String nombre, String apellido, String cedula, String direccion, String telefono,
			String correo, String fechaNacimiento, String codigo, double salario, String cargo) throws EmpleadoException;

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

	public void DepositarDineroCuenta(Integer numeroCuenta);

	public void RetirarDineroCuenta(Integer cedula, Integer numeroCuenta);

	public void ConsultarSaldoCuenta(Integer numeroCuenta);


	


	
}
