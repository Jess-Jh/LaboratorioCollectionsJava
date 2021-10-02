package co.edu.uniquindio.banco.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import co.edu.uniquindio.banco.application.BancoApplication;
import co.edu.uniquindio.banco.exceptions.ClienteException;
import co.edu.uniquindio.banco.exceptions.DatosInvalidosException;
import co.edu.uniquindio.banco.model.Banco;
import co.edu.uniquindio.banco.model.Cliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
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
    
    private BancoApplication bancoApplication;
    
    private Cliente clienteSeleccion;
    
    ObservableList<Cliente> listadoClientes = FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.columnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		this.columnApellidos.setCellValueFactory(new PropertyValueFactory<>("apellido"));
		this.columnCedula.setCellValueFactory(new PropertyValueFactory<>("cedula"));
		this.columnTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
		
		//Obtener selección de la tabla
		tableViewClientes.getSelectionModel().selectedItemProperty().addListener((obs, oldSeletion, newSelection) -> {
			if(newSelection != null) {
				clienteSeleccion = newSelection;
				mostrarInformacion();
			}
		});
	}
	
	private void mostrarInformacion() {
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
	
	public void setAplicacion(BancoApplication bancoApplication) {
		this.bancoApplication = bancoApplication;
		this.banco = bancoApplication.getBanco();
		tableViewClientes.getItems().clear();
		tableViewClientes.setItems(getClientes());
	}
	
    private ObservableList<Cliente> getClientes() {
		listadoClientes.addAll(banco.getListaClientes()); 
		return listadoClientes;
	}

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
	}

	private boolean verificarDatos(String nombre, String apellidos, String cedula, String direccion, String telefono, String correo, String fechaDeNacimiento ) throws DatosInvalidosException {
		
		String notificacion = "";	
		
		if(nombre == null || nombre.equals("")) {
			notificacion += "Ingrese el nombre\n";
		}
		if(apellidos == null || apellidos.equals("")) {
			notificacion += "Ingrese los apellidos\n";
		}
		if(cedula == null || cedula.equals("") || cedula.isEmpty()) {
			notificacion += "Ingrese la Cédula\n";
		}
		if(direccion == null || direccion.equals("")) {
			notificacion += "Seleccione el género\n";
		}
		if(telefono == null || telefono.equals("")) {
			notificacion += "Ingrese el número de teléfono\n";
		}
		if(correo == null || correo.equals("")) {
			notificacion += "Seleccione el correo electrónico\n";
		}
		if(fechaDeNacimiento == null || fechaDeNacimiento.equals("")) {
			notificacion += "Ingrese la fecha de nacimiento\n";
		}
		if(notificacion.equals("")) {
			return true;
		}
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

    }



}
