package co.edu.uniquindio.banco.controllers;

import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import co.edu.uniquindio.banco.application.BancoApplication;
import co.edu.uniquindio.banco.exceptions.ClienteException;
import co.edu.uniquindio.banco.exceptions.CuentaException;
import co.edu.uniquindio.banco.exceptions.DatosInvalidosException;
import co.edu.uniquindio.banco.exceptions.EmpleadoException;
import co.edu.uniquindio.banco.model.AsesorVentas;
import co.edu.uniquindio.banco.model.Banco;
import co.edu.uniquindio.banco.model.Cajero;
import co.edu.uniquindio.banco.model.Cliente;
import co.edu.uniquindio.banco.model.Cuenta;
import co.edu.uniquindio.banco.model.CuentaAhorro;
import co.edu.uniquindio.banco.model.CuentaCorriente;
import co.edu.uniquindio.banco.model.Empleado;
import co.edu.uniquindio.banco.model.Gerente;
import co.edu.uniquindio.banco.model.Transaccion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ClienteController implements Initializable {
	
	//-------SINGLETON------------------------------------------------------>
	
	ModelFactoryController modelFactoryController;
	Banco banco;
	
	public ClienteController() {
		modelFactoryController = ModelFactoryController.getInstance();
		banco = modelFactoryController.getBanco();
	}

	public Banco getGimnasio() {
		return banco;
	}
	public void setGimnasio(Banco banco) {
		this.banco = banco;
	}
	//--------------------------------------------------------------------||


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Cliente> tableViewClientes;

    @FXML
    private TableColumn<Cliente, String> columnNombre;

    @FXML
    private TableColumn<Cliente, String> columnApellidos;

    @FXML
    private TableColumn<Cliente, String> columnTelefono;

    @FXML
    private TableColumn<Cliente, String> columnCedula;

    @FXML
    private Button btnEliminarCliente;

    @FXML
    private TextField txtCedula;

    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtTelefono;

    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField txtFechaDeNacimiento;

    @FXML
    private Button btnAgregarCliente;

    @FXML
    private Button btnActualizarCliente;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtApellidos;
    
    @FXML
    private TableView<Cuenta> tableViewCuentas;
    
    @FXML
    private TableColumn<Cuenta, String> columnNumeroCuenta;

    @FXML
    private TableColumn<Cuenta, String> columnSaldo;

    @FXML
    private Button btnEliminarCuenta;

    @FXML
    private Button btnAgregarCuenta;

    @FXML
    private Button btnActualizarCuenta;

    @FXML
    private TextField txtNumeroCuenta;

    @FXML
    private TextField txtSaldo;

    @FXML
    private ComboBox<String> cmbTipoCuenta;
    ObservableList<String> listaCmbTipoCuenta = FXCollections.observableArrayList();

    @FXML
    private TextField txtClienteAsociado;
    
    private BancoApplication bancoApplication;
    
    private Cliente clienteSeleccion;

    private Cuenta cuentaSeleccion;
        
    ObservableList<Cliente> listadoClientes = FXCollections.observableArrayList();

    ObservableList<Cuenta> listadoCuentas = FXCollections.observableArrayList();    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//--------------------- Valores Columnas Cliente ---------------------------------||
		this.columnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		this.columnApellidos.setCellValueFactory(new PropertyValueFactory<>("apellido"));
		this.columnCedula.setCellValueFactory(new PropertyValueFactory<>("cedula"));
		this.columnTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
		
		//--------------------- Valores Columnas Cuenta ---------------------------------||
		this.columnNumeroCuenta.setCellValueFactory(new PropertyValueFactory<>("numeroCuenta"));
		this.columnSaldo.setCellValueFactory(new PropertyValueFactory<>("saldo"));		
		
		//Obtener selección de la tabla clientes
		tableViewClientes.getSelectionModel().selectedItemProperty().addListener((obs, oldSeletion, newSelection) -> {
			if(newSelection != null) {
				clienteSeleccion = newSelection;
				mostrarInformacionCliente();
			}
		});

		//Obtener selección de la tabla cuentas
		tableViewCuentas.getSelectionModel().selectedItemProperty().addListener((obs, oldSeletion, newSelection) -> {
			if(newSelection != null) {
				cuentaSeleccion = newSelection;
				mostrarInformacionCuenta();
			}
		});
		listaCmbTipoCuenta.add("Cuenta de Ahorro");
		listaCmbTipoCuenta.add("Cuenta Corriente");
		cmbTipoCuenta.setItems(listaCmbTipoCuenta);
		
	}
	
	private void mostrarInformacionCliente() {
		if(clienteSeleccion != null) {
			txtNombre.setText(clienteSeleccion.getNombre());
			txtApellidos.setText(clienteSeleccion.getApellido());
			txtCedula.setText(clienteSeleccion.getCedula());
			txtTelefono.setText(clienteSeleccion.getTelefono());
			txtCedula.setDisable(true);
			txtCorreo.setText(clienteSeleccion.getCorreo());
			txtFechaDeNacimiento.setText(clienteSeleccion.getFechaNacimiento());
			txtDireccion.setText(clienteSeleccion.getDireccion());
		}
	}
	
	private void mostrarInformacionCuenta() {
		if(cuentaSeleccion != null) {
			txtNumeroCuenta.setText(cuentaSeleccion.getNumeroCuenta());
			txtSaldo.setText(String.valueOf(cuentaSeleccion.getSaldo()));
			txtClienteAsociado.setText(cuentaSeleccion.getClienteAsociado().getCedula());
			
			if(cuentaSeleccion instanceof CuentaAhorro) cmbTipoCuenta.setValue("Cuenta de Ahorro");
			if(cuentaSeleccion instanceof CuentaCorriente) cmbTipoCuenta.setValue("Cuenta Corriente");	
			cmbTipoCuenta.setDisable(true);
		}
	}
	
	public void setAplicacion(BancoApplication bancoApplication) {
		this.bancoApplication = bancoApplication;
		this.banco = bancoApplication.getBanco();
		
		tableViewClientes.getItems().clear();
		tableViewClientes.setItems(getClientes());
		
		tableViewCuentas.getItems().clear();
		tableViewCuentas.setItems(getCuentas());
	}
	
    private ObservableList<Cliente> getClientes() {
		listadoClientes.addAll(banco.getListaClientes()); 
		return listadoClientes;
	}
    
    private ObservableList<Cuenta> getCuentas() {	
    	HashMap<String, Cuenta> lista = banco.getListaCuentas();
    	List<Cuenta> listaCuentas = lista.values().stream().collect(Collectors.toList());
		listadoCuentas.addAll(listaCuentas); 

		return listadoCuentas;
	}

    //--------------------------------------------------------- CRUD Cliente ------------------------------------------------------------------------------------------------->>
    
    @FXML
    void agregarCliente(ActionEvent event) { 	
    	agregarCliente(txtNombre.getText(), txtApellidos.getText(), txtCedula.getText(), txtDireccion.getText(), txtTelefono.getText(), 
    					txtCorreo.getText(), txtFechaDeNacimiento.getText());
    	
    }

    private void agregarCliente(String nombre, String apellidos, String cedula, String direccion, String telefono, String correo,
			String fechaDeNacimiento) {
		try { 
			verificarDatos(nombre, apellidos, cedula, direccion, telefono, correo, fechaDeNacimiento); 
				
			Cliente cliente = modelFactoryController.crearCliente(nombre, apellidos, cedula, direccion, telefono, correo, fechaDeNacimiento);
								
			bancoApplication.mostrarMensaje("Registro Cliente", "Registro Cliente", "El cliente " + cliente.getNombre() + " " + cliente.getApellido() + "  ha sido registrado con éxito", AlertType.INFORMATION); 					
			
			if(cliente != null) listadoClientes.add(0, cliente);
			tableViewClientes.refresh();
					
		} catch(DatosInvalidosException | ClienteException e) {
			bancoApplication.mostrarMensaje("Notificación Registro de Cliente", "Información registro cliente inválida", e.getMessage(), AlertType.WARNING);			
		}
		nuevoCliente();
	}

	private boolean verificarDatos(String nombre, String apellidos, String cedula, String direccion, String telefono, String correo, String fechaDeNacimiento ) throws DatosInvalidosException {
		
		String notificacion = "";	
		
		if(nombre == null || nombre.equals("")) notificacion += "Ingrese el nombre\n";
		
		if(apellidos == null || apellidos.equals("")) notificacion += "Ingrese los apellidos\n";
		
		if(cedula == null || cedula.equals("") || cedula.isEmpty()) notificacion += "Ingrese la Cédula\n";
		
		if(direccion == null || direccion.equals("")) notificacion += "Seleccione el género\n";
		
		if(telefono == null || telefono.equals("")) notificacion += "Ingrese el número de teléfono\n";
		
		if(correo == null || correo.equals("")) notificacion += "Seleccione el correo electrónico\n";
		
		if(fechaDeNacimiento == null || fechaDeNacimiento.equals("")) notificacion += "Ingrese la fecha de nacimiento\n";
		
		if(notificacion.equals("")) return true;
		
		throw new DatosInvalidosException(notificacion); 
	}
	
	@FXML
    void actualizarCliente(ActionEvent event) {
		if(clienteSeleccion != null) {
			actualizarCliente(txtNombre.getText(), txtApellidos.getText(), txtCedula.getText(), txtDireccion.getText(), txtTelefono.getText(), txtCorreo.getText(), txtFechaDeNacimiento.getText());			
		} else {
			bancoApplication.mostrarMensaje("Actualización Cliente", "Actualización Cliente", "No se ha seleccionado ningún cliente", AlertType.WARNING);
		}
    }

    private void actualizarCliente(String nombre, String apellidos, String cedula, String direccion, String telefono, String correo,
			String fechaDeNacimiento) {
    	try {
			verificarDatos(nombre, apellidos, cedula, direccion, telefono, correo, fechaDeNacimiento);
			Cliente cliente = modelFactoryController.actualizarCliente(nombre, apellidos, cedula, direccion, telefono, correo, fechaDeNacimiento);

			clienteSeleccion.setNombre(nombre);
			clienteSeleccion.setApellido(apellidos);
			clienteSeleccion.setCedula(cedula);
			clienteSeleccion.setDireccion(direccion);
			clienteSeleccion.setTelefono(telefono);
			clienteSeleccion.setCorreo(correo);
			clienteSeleccion.setFechaNacimiento(fechaDeNacimiento);
	
			tableViewClientes.refresh();	
			bancoApplication.mostrarMensaje("Notificación Actualización Cliente", "Actualización Cliente", "El cliente " + cliente.getNombre() + " " + cliente.getApellido() 
											   + " se ha actualizado con éxito", AlertType.CONFIRMATION);	
		} catch (DatosInvalidosException e) {
			bancoApplication.mostrarMensaje("Notificación Actualización Cliente", "Actualización Cliente", e.getMessage(), AlertType.WARNING);
		}
    	nuevoCliente();
	}

	@FXML
    void eliminarCliente(ActionEvent event) {
		if(clienteSeleccion != null) {
			
			//Confirmar que el usuario si quiere eliminar el cliente
			int mensajeDeConfirmacion = JOptionPane.showConfirmDialog(null, "¿Desea eliminar el cliente " + clienteSeleccion.getNombre() + " ?");
			
			if(mensajeDeConfirmacion == 0) {
				modelFactoryController.eliminarCliente(clienteSeleccion.getCedula());
	    		bancoApplication.mostrarMensaje("Notificación eliminación cliente", "Eliminación Cliente", "Se ha eliminado el cliente", AlertType.CONFIRMATION);
	    		
	    		listadoClientes.remove(clienteSeleccion);
	    		tableViewClientes.refresh();
			} else {
				bancoApplication.mostrarMensaje("Notificación eliminación cliente", "Eliminación Cliente", "No se ha eliminado el cliente", AlertType.WARNING);    			
			}
		} else {
			bancoApplication.mostrarMensaje("Notificación eliminación cliente", "Eliminación Cliente", "No se ha seleccionado ninguna cliente", AlertType.WARNING);
		}
		nuevoCliente();		
    }
	
	/**
	 * Limpiar campos para ingresar un nuevo cliente
	 */
	private void nuevoCliente() {
		txtNombre.setText("");
		txtApellidos.setText("");
		txtCedula.setText("");
		txtCedula.setDisable(false);
		txtDireccion.setText("");
		txtTelefono.setText("");
		txtCorreo.setText("");
		txtFechaDeNacimiento.setText("");		
	}
	//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------||
	
    //--------------------------------------------------------- CRUD Cuenta ------------------------------------------------------------------------------------------------->>
	@FXML
	void agregarCuenta(ActionEvent event) {
		agregarCuenta(txtNumeroCuenta.getText(), txtSaldo.getText(), txtClienteAsociado.getText(), cmbTipoCuenta.getValue());
	}

	private void agregarCuenta(String numeroCuenta, String saldo, String clienteAsociado, String tipoCuenta) {
		
		try {
			verificarDatos(numeroCuenta, saldo, clienteAsociado, tipoCuenta);
			validarNumero(saldo, clienteAsociado);
			double saldoCliente = Double.valueOf(saldo);
	
			Cuenta cuenta = modelFactoryController.crearCuenta(numeroCuenta, saldoCliente, clienteAsociado, tipoCuenta);				
			
			if(cuenta != null) listadoCuentas.add(0, cuenta);
			tableViewCuentas.refresh();
			
			bancoApplication.mostrarMensaje("Registro Cuenta", "Registro Cuenta", "La cuenta " + cuenta.getNumeroCuenta() + "  ha sido registrada con éxito", AlertType.INFORMATION); 					
			
		
		} catch (DatosInvalidosException | ClienteException | CuentaException e) {
			bancoApplication.mostrarMensaje("Notificación Registro Cuenta", "Información registro cuenta inválida", e.getMessage(), AlertType.WARNING);			
		}
		nuevaCuenta();
	}

	private void nuevaCuenta() {
		txtNumeroCuenta.setText("");
		txtSaldo.setText("");
		txtClienteAsociado.setText("");
		cmbTipoCuenta.setValue(null);
		cmbTipoCuenta.setDisable(false);
	}

	private boolean verificarDatos(String numeroCuenta, String saldo, String clienteAsociado, String tipoCuenta) throws DatosInvalidosException {
		
		String notificacion = "";	
		
		if(numeroCuenta == null || numeroCuenta.equals("")) notificacion += "Ingrese el número de cuenta\n";
		
		if(saldo == null || saldo.equals("")) notificacion += "Ingrese el saldo\n";
		
		if(clienteAsociado == null || clienteAsociado.equals("")) notificacion += "Ingrese la cédula del cliente asociado\n";
		
		if(tipoCuenta == null || tipoCuenta.equals("")) notificacion += "Seleccione el tipo de la cuenta\n";
		
		if(notificacion.equals("")) return true;
		
		throw new DatosInvalidosException(notificacion); 
	}
	
	private boolean validarNumero(String saldo, String clienteAsociado) throws DatosInvalidosException {	
		String notificacionNumero = "";
				
		if(!(saldo.matches("[0-9.]*"))) notificacionNumero += "El salario debe ser un valor numérico\n";

		if(!(clienteAsociado.matches("[0-9.]*"))) notificacionNumero += "El número de la cédula del cliente asociado debe ser un valor numérico\n";

		if(notificacionNumero.equals("")) return true;

		throw new DatosInvalidosException(notificacionNumero);
	}
	@FXML
    void actualizarCuenta(ActionEvent event) {
		if(cuentaSeleccion != null) {
			actualizarCuenta(txtNumeroCuenta.getText(), txtSaldo.getText(), txtClienteAsociado.getText(), cmbTipoCuenta.getValue());
		} else {
			bancoApplication.mostrarMensaje("Actualización Cuenta", "Actualización Cuenta", "No se ha seleccionado ninguna cuenta", AlertType.WARNING);
		}
    }
    
	/**
	 * Actualizar una cuenta
	 * @param numeroCuenta, saldo, clienteAsociado, tipoCuenta
	 */
    private void actualizarCuenta(String numeroCuenta, String saldo, String clienteAsociado, String tipoCuenta) {
		try {
			verificarDatos(numeroCuenta, saldo, clienteAsociado, tipoCuenta);
			validarNumero(saldo, clienteAsociado);
			double saldoCliente = Double.valueOf(saldo);
			
			Cuenta cuenta = modelFactoryController.actualizarCuenta(numeroCuenta, saldoCliente, clienteAsociado, tipoCuenta);				
			
			cuentaSeleccion.setNumeroCuenta(numeroCuenta);
			cuentaSeleccion.setSaldo(saldoCliente);
			Cliente cliente = banco.obtenerCliente(clienteAsociado);
			cuentaSeleccion.setClienteAsociado(cliente);	
			
			tableViewCuentas.refresh();
			
			bancoApplication.mostrarMensaje("Notificación Actualización Cuenta", "Actualización Cuenta", "La cuenta " + cuenta.getNumeroCuenta() + " se ha actualizado con éxito", AlertType.CONFIRMATION);	

		} catch (DatosInvalidosException | ClienteException | CuentaException e) {
			bancoApplication.mostrarMensaje("Notificación Actualización Cuenta", "Información actualización cuenta inválida", e.getMessage(), AlertType.WARNING);			
		}
		nuevaCuenta();
	}

	@FXML
    void eliminarCuenta(ActionEvent event) {
		if(cuentaSeleccion != null) {
			
			//Confirmar que el usuario si quiere eliminar el cliente
			int mensajeDeConfirmacion = JOptionPane.showConfirmDialog(null, "¿Desea eliminar la cuenta " + cuentaSeleccion.getNumeroCuenta() + " ?");
			
			if(mensajeDeConfirmacion == 0) {
				modelFactoryController.eliminarCuenta(cuentaSeleccion.getNumeroCuenta());
	    		bancoApplication.mostrarMensaje("Notificación eliminación cuenta", "Eliminación Cuenta", "Se ha eliminado la cuenta", AlertType.CONFIRMATION);
	    		
	    		listadoCuentas.remove(cuentaSeleccion);
	    		tableViewCuentas.refresh();
			} else {
				bancoApplication.mostrarMensaje("Notificación eliminación cuenta", "Eliminación Cuenta", "No se ha eliminado la cuenta", AlertType.WARNING);    			
			}
		} else {
			bancoApplication.mostrarMensaje("Notificación eliminación cuenta", "Eliminación Cuenta", "No se ha seleccionado ninguna cuenta", AlertType.WARNING);
		}
		nuevaCuenta();
    }
	//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------||

}
