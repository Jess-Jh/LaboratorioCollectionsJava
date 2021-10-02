package co.edu.uniquindio.banco.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import co.edu.uniquindio.banco.application.BancoApplication;
import co.edu.uniquindio.banco.exceptions.ClienteException;
import co.edu.uniquindio.banco.exceptions.DatosInvalidosException;
import co.edu.uniquindio.banco.exceptions.EmpleadoException;
import co.edu.uniquindio.banco.model.AsesorVentas;
import co.edu.uniquindio.banco.model.Banco;
import co.edu.uniquindio.banco.model.Cajero;
import co.edu.uniquindio.banco.model.Cliente;
import co.edu.uniquindio.banco.model.Empleado;
import co.edu.uniquindio.banco.model.Gerente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class EmpleadoController implements Initializable {
	
	//-------SINGLETON------------------------------------------------------>
	
	ModelFactoryController modelFactoryController;
	Banco banco;
	
	public EmpleadoController() {
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
    private TableView<Empleado> tableViewEmpleados;

    @FXML
    private TableColumn<Empleado, String> columnNombre;

    @FXML
    private TableColumn<Empleado, String> columnApellidos;

    @FXML
    private TableColumn<Empleado, String> columnTelefono;

    @FXML
    private TableColumn<Empleado, String> columnCedula;

    @FXML
    private Button btnEliminarEmpleado;

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
    private Button btnAgregarEmpleado;

    @FXML
    private Button btnActualizarEmpleado;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtApellidos;

    @FXML
    private ComboBox<String> cmbCargo;
    ObservableList<String> listaCmbCargo = FXCollections.observableArrayList();


    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtSalario;
    
    private BancoApplication bancoApplication;
    
    private Empleado empleadoSeleccion;
    
    ObservableList<Empleado> listadoEmpleados = FXCollections.observableArrayList();
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.columnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		this.columnApellidos.setCellValueFactory(new PropertyValueFactory<>("apellido"));
		this.columnCedula.setCellValueFactory(new PropertyValueFactory<>("cedula"));
		this.columnTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
		
		//Obtener selección de la tabla
		tableViewEmpleados.getSelectionModel().selectedItemProperty().addListener((obs, oldSeletion, newSelection) -> {
			if(newSelection != null) {
				empleadoSeleccion = newSelection;
				mostrarInformacion();
			}
		});
		//Agregar datos a la lista Observable del combo box cargo
		listaCmbCargo.add("Gerente");
		listaCmbCargo.add("Cajero");
		listaCmbCargo.add("AsesorVentas");
		
		cmbCargo.setItems(listaCmbCargo);
	}
	
	private void mostrarInformacion() {
		if(empleadoSeleccion != null) {
		txtNombre.setText(empleadoSeleccion.getNombre());
		txtApellidos.setText(empleadoSeleccion.getApellido());
		txtCedula.setText(empleadoSeleccion.getCedula());
		txtTelefono.setText(empleadoSeleccion.getTelefono());
		txtCedula.setDisable(true);
		txtCorreo.setText(empleadoSeleccion.getCorreo());
		txtFechaDeNacimiento.setText(empleadoSeleccion.getFechaNacimiento());
		txtDireccion.setText(empleadoSeleccion.getDireccion());
		txtCodigo.setText(empleadoSeleccion.getCodigo());
		txtSalario.setText(String.valueOf(empleadoSeleccion.getSalario()));
		
		if(empleadoSeleccion instanceof Gerente) cmbCargo.setValue("Gerente");
		if(empleadoSeleccion instanceof Cajero) cmbCargo.setValue("Cajero");
		if(empleadoSeleccion instanceof AsesorVentas) cmbCargo.setValue("Asesor Ventas");
		cmbCargo.setDisable(true);
		
		}
	}
	
	public void setAplicacion(BancoApplication bancoApplication) {
		this.bancoApplication = bancoApplication;
		this.banco = bancoApplication.getBanco();
		tableViewEmpleados.getItems().clear();
		tableViewEmpleados.setItems(getEmpleados());
	}
	
    private ObservableList<Empleado> getEmpleados() {
		listadoEmpleados.addAll(banco.getListaEmpleados()); 
		return listadoEmpleados;
	}

    @FXML
    void agregarEmpleado(ActionEvent event) {
    	agregarEmpleado(txtNombre.getText(), txtApellidos.getText(), txtCedula.getText(), txtDireccion.getText(), txtTelefono.getText(), 
				txtCorreo.getText(), txtFechaDeNacimiento.getText(), txtCodigo.getText(), txtSalario.getText(), cmbCargo.getValue());

    }
    
    private void agregarEmpleado(String nombre, String apellidos, String cedula, String direccion, String telefono, String correo,
			String fechaDeNacimiento, String codigo, String salario, String cargo) {
		try { 
			verificarDatos(nombre, apellidos, cedula, direccion, telefono, correo, fechaDeNacimiento, codigo, salario, cargo);
			validarNumero(salario);
			double salarioEmpleado = Double.valueOf(salario);
			int tamañoListaEmpleados = banco.getListaEmpleados().size();

			Empleado empleado = modelFactoryController.crearEmpleado(nombre, apellidos, cedula, direccion, telefono, correo, fechaDeNacimiento, codigo, salarioEmpleado, cargo);				
			
			if(empleado != null) {
				listadoEmpleados.removeAll(listadoEmpleados);
				listadoEmpleados.addAll(banco.getListaEmpleados());
			}
			if(tamañoListaEmpleados < listadoEmpleados.size()) {
				bancoApplication.mostrarMensaje("Registro Empleado", "Registro Empleado", "El empleado " + empleado.getNombre() + " " + empleado.getApellido() + "  ha sido registrado con éxito", AlertType.INFORMATION); 					
				tableViewEmpleados.refresh();				
			} else
				bancoApplication.mostrarMensaje("Registro Empleado", "Registro Empleado", "El empleado " + empleado.getNombre() + " " + empleado.getApellido() + "  ya se encuentra registrado", AlertType.INFORMATION); 					
			
			nuevoEmpleado();
			
		} catch(DatosInvalidosException | EmpleadoException e) {
			bancoApplication.mostrarMensaje("Notificación Registro de Empleado", "Información registro empleado inválida", e.getMessage(), AlertType.WARNING);			
		}
	}

	private void nuevoEmpleado() {
		txtNombre.setText("");
		txtApellidos.setText("");
		txtCedula.setText("");
		txtTelefono.setText("");
		txtCedula.setDisable(false);
		txtCorreo.setText("");
		txtFechaDeNacimiento.setText("");
		txtDireccion.setText("");
		txtCodigo.setText("");
		txtSalario.setText("");
		cmbCargo.setValue(null);
		cmbCargo.setDisable(false);
	}

	private boolean verificarDatos(String nombre, String apellidos, String cedula, String direccion, String telefono, String correo, String fechaDeNacimiento, 
										String codigo, String salario, String cargo ) throws DatosInvalidosException {
		
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
			notificacion += "Seleccione el dirección\n";
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
		if(codigo == null || codigo.equals("")) {
			notificacion += "Ingrese código\n";
		}
		if(salario == null || salario.equals("")) {
			notificacion += "Ingrese el salario\n";
		}
		if(cargo == null || cargo.equals("")) {
			notificacion += "Seleccione el cargo\n";
		}
		if(notificacion.equals("")) {
			return true;
		}
		throw new DatosInvalidosException(notificacion); 
	}
	
	private boolean validarNumero(String salario) throws DatosInvalidosException {	
		String notificacionNumero = "";
				
		if(!(salario.matches("[0-9.]*"))) notificacionNumero += "El salario debe ser un valor numérico\n";

		if(notificacionNumero.equals("")) return true;

		throw new DatosInvalidosException(notificacionNumero);
	}
	
    @FXML
    void actualizarEmpleado(ActionEvent event) {
		if(empleadoSeleccion != null) {
			actualizarEmpleado(txtNombre.getText(), txtApellidos.getText(), txtCedula.getText(), txtDireccion.getText(), txtTelefono.getText(), txtCorreo.getText(), txtFechaDeNacimiento.getText(), txtCodigo.getText(), txtSalario.getText(), cmbCargo.getValue());			
		} else {
			bancoApplication.mostrarMensaje("Actualización Cliente", "Actualización Cliente", "No se ha seleccionado ningún cliente", AlertType.WARNING);
		}
    }

    private void actualizarEmpleado(String nombre, String apellidos, String cedula, String direccion, String telefono, String correo,
			String fechaDeNacimiento, String codigo, String salario, String cargo) {
    	try {
			verificarDatos(nombre, apellidos, cedula, direccion, telefono, correo, fechaDeNacimiento, codigo, salario, cargo);
			validarNumero(salario);
			double salarioEmpleado = Double.valueOf(salario);
			Empleado empleado = modelFactoryController.actualizarEmpleado(nombre, apellidos, cedula, direccion, telefono, correo, fechaDeNacimiento, codigo, salarioEmpleado);

			empleadoSeleccion.setNombre(nombre);
			empleadoSeleccion.setApellido(apellidos);
			empleadoSeleccion.setCedula(cedula);
			empleadoSeleccion.setDireccion(direccion);
			empleadoSeleccion.setTelefono(telefono);
			empleadoSeleccion.setCorreo(correo);
			empleadoSeleccion.setFechaNacimiento(fechaDeNacimiento);
	
			tableViewEmpleados.refresh();		
			bancoApplication.mostrarMensaje("Notificación Actualización Empleado", "Actualización Empleado", "El empleado " + empleado.getNombre() + " " + empleado.getApellido() 
											   + " se ha actualizado con éxito", AlertType.CONFIRMATION);	
		} catch (DatosInvalidosException e) {
			bancoApplication.mostrarMensaje("Notificación Actualización Empleado", "Actualización Empleado", e.getMessage(), AlertType.WARNING);
		}
	}

	@FXML
    void eliminarEmpleado(ActionEvent event) {
		if(empleadoSeleccion != null) {
			
			//Confirmar que el usuario si quiere eliminar el cliente
			int mensajeDeConfirmacion = JOptionPane.showConfirmDialog(null, "¿Desea eliminar el empleado " + empleadoSeleccion.getNombre() + " ?");
			
			if(mensajeDeConfirmacion == 0) {
				modelFactoryController.eliminarEmpleado(empleadoSeleccion.getCedula());
	    		bancoApplication.mostrarMensaje("Notificación eliminación empleado", "Eliminación Empleado", "Se ha eliminado el empleado", AlertType.CONFIRMATION);
	    		
	    		listadoEmpleados.remove(empleadoSeleccion);
	    		tableViewEmpleados.refresh();
			} else {
				bancoApplication.mostrarMensaje("Notificación eliminación empleado", "Eliminación Empleado", "No se ha eliminado el empleado", AlertType.WARNING);    			
			}
		} else {
			bancoApplication.mostrarMensaje("Notificación eliminación empleado", "Eliminación Empleado", "No se ha seleccionado ninguna empleado", AlertType.WARNING);
		}
    }



}
