package co.edu.uniquindio.banco.controllers;

import java.awt.HeadlessException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import co.edu.uniquindio.banco.application.BancoApplication;
import co.edu.uniquindio.banco.exceptions.ClienteException;
import co.edu.uniquindio.banco.exceptions.CuentaException;
import co.edu.uniquindio.banco.exceptions.DatosInvalidosException;
import co.edu.uniquindio.banco.exceptions.SaldoInsuficienteException;
import co.edu.uniquindio.banco.model.Banco;
import co.edu.uniquindio.banco.model.Cuenta;
import co.edu.uniquindio.banco.model.Transaccion;

public class TransaccionController implements Initializable {
	
	//-------SINGLETON------------------------------------------------------>
	
	ModelFactoryController modelFactoryController;
	Banco banco;
	
	public TransaccionController() {
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
    private TextField txtDepositarDinero;

    @FXML
    private TextField txtRetirarDinero;

    @FXML
    private RadioButton rdBtnRetirarDinero;

    @FXML
    private RadioButton rdBtnDepositarDinero;

    @FXML
    private RadioButton rdBtnSolicitarSaldo;

    @FXML
    private TextField txtNumeroCuentaBancaria;
    
    @FXML
    private TextField txtCedulaTransaccion;

    @FXML
    private Button btnRegistrarTransaccion;
    
    @FXML
    private TableView<Transaccion> tableViewTransacciones;

    @FXML
    private TableColumn<Transaccion, String> columnValor;

    @FXML
    private TableColumn<Transaccion, String> columnHora;

    @FXML
    private TableColumn<Transaccion, String> columnFecha;

    @FXML
    private TableColumn<Transaccion, String> columnTipoTransaccion;

    @FXML
    private TableColumn<Transaccion, String> columnEstadoTransaccion;

    @FXML
    private Button btnCerrar;
    
    private BancoApplication bancoAplicacion;
    
    private Transaccion transaccionSeleccion;
    
    /**
     * Listado de transacciones que se muestra en la interfaz
     */
    ObservableList<Transaccion> listadoTransacciones = FXCollections.observableArrayList();
    

    @Override
	public void initialize(URL location, ResourceBundle resource) {
		
    	this.columnValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
    	this.columnHora.setCellValueFactory(new PropertyValueFactory<>("hora"));
    	this.columnFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
    	this.columnTipoTransaccion.setCellValueFactory(new PropertyValueFactory<>("tipoTransaccion"));
    	this.columnEstadoTransaccion.setCellValueFactory(new PropertyValueFactory<>("estado"));
    	
    	//Obtener selección de la tabla y agregar evento
    	tableViewTransacciones.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
    		if(newSelection != null) {
    			transaccionSeleccion = newSelection;
    			mostrarInformacion();
    		}
    	});
	}
    
    private void mostrarInformacion() {
		if(transaccionSeleccion != null) {
			transaccionSeleccion.setValor(transaccionSeleccion.getValor());
			transaccionSeleccion.setHora(transaccionSeleccion.getHora());
			transaccionSeleccion.setFecha(transaccionSeleccion.getFecha());
			transaccionSeleccion.setTipoTransaccion(transaccionSeleccion.getTipoTransaccion());
			transaccionSeleccion.setEstado(transaccionSeleccion.getEstado());
		}
	}

	public void setAplicacion(BancoApplication bancoApplication) {
    	this.bancoAplicacion = bancoApplication;
    	this.banco = bancoApplication.getBanco();
    	tableViewTransacciones.getItems().clear();
    	tableViewTransacciones.setItems(getTransacciones());
    }

    private ObservableList<Transaccion> getTransacciones() {
    	HashMap<String, Transaccion> lista = banco.getListaTransaccionesAsociadas();
    	List<Transaccion> listaTransacciones = lista.values().stream().collect(Collectors.toList());
		listadoTransacciones.addAll(listaTransacciones); 

		return listadoTransacciones;
	}

    @FXML
    void registrarTransaccion(ActionEvent event) {
    	String numeroCuentaBancaria = txtNumeroCuentaBancaria.getText();
    	String numeroCedulaTransaccion = txtCedulaTransaccion.getText();
    	Transaccion transaccionRetiro, transaccionDeposito;
    	
    	//------------------------------------------------ TRANSACCION RETIRO --------------------------------------------------------------------------------->
    	try {
			if(validarNumeroCuentaBancaria(numeroCuentaBancaria, numeroCedulaTransaccion)) {
				
				String dineroUsuarioRetiro = txtRetirarDinero.getText().trim();
				
				//RadioButton para Retirar Dinero
				if(rdBtnRetirarDinero.isSelected()) {
					
					if(validarNumero(dineroUsuarioRetiro, numeroCedulaTransaccion)) { 
						double dineroRetiro = Double.valueOf(txtRetirarDinero.getText());
						
						int mensajeDeConfirmacion = JOptionPane.showConfirmDialog(null, "¿Desea realizar el retiro con valor de " + dineroRetiro + "?");
						
						if(mensajeDeConfirmacion == 0) {
							transaccionRetiro = modelFactoryController.RetirarDineroCuenta(dineroRetiro, numeroCuentaBancaria, numeroCedulaTransaccion);
							listadoTransacciones.add(0, transaccionRetiro);   
							limpiarCampos();
							bancoAplicacion.mostrarMensaje("Notificación Transacción", "Retiro de Dinero", "Se ha realizado su transacción con éxito", AlertType.INFORMATION);						
						} else {
							bancoAplicacion.mostrarMensaje("Notificación Transacción", "Retiro de Dinero", "No se ha realizado el retiro del dinero", AlertType.INFORMATION);						
						}
					}
				}
			//-----------------------------------------------------------------------------------------------------------------------------------------------------------------||
				
			//---------------------------------------------- TRANSACCION DEPOSITO ------------------------------------------------------------------------------------------->
				String dineroUsuarioDeposito = txtDepositarDinero.getText().trim();
					
				//RadioButton para Depositar Dinero
				if(rdBtnDepositarDinero.isSelected()) {
					if(validarNumero(dineroUsuarioDeposito, numeroCedulaTransaccion)) {
						double depositarDinero = Double.valueOf(txtDepositarDinero.getText());
						
						int mensajeDeConfirmacion = JOptionPane.showConfirmDialog(null, "¿Desea realizar el deposito de valor " + depositarDinero + "?");
						
						if(mensajeDeConfirmacion == 0) {
							transaccionDeposito = modelFactoryController.DepositarDineroCuenta(depositarDinero, numeroCuentaBancaria, numeroCedulaTransaccion);
							listadoTransacciones.add(0, transaccionDeposito);
							limpiarCampos();
							bancoAplicacion.mostrarMensaje("Notificación Transacción", "Deposito de Dinero", "Se ha realizado el deposito del dinero con éxito", AlertType.INFORMATION);						
						} else {
							bancoAplicacion.mostrarMensaje("Notificación Transacción", "Deposito de Dinero", "No se ha realizado el deposito del dinero", AlertType.INFORMATION);						
						}
					}
				}
			//--------------------------------------------------------------------------------------------------------------------------------------------------------------------||
				
			//----------------------------------------------- TRANSACCION SOLICITAR_SALDO ------------------------------------------------------------------------------------------->
				//RadioButton para solicitar el saldo
				if(rdBtnSolicitarSaldo.isSelected()) {
					Cuenta cuenta = modelFactoryController.ConsultarSaldoCuenta(numeroCuentaBancaria);
					
					 bancoAplicacion.mostrarMensaje("Notificación Transacción", "Solicitud Saldo Cuenta", "Número de cuenta: " + cuenta.getNumeroCuenta() + " \nCliente " 
							 						+ cuenta.getClienteAsociado().getNombre() + " " + cuenta.getClienteAsociado().getApellido() + "\nSaldo: " + cuenta.getSaldo() 
							 						+ "\nTransacciones Realizadas:\n " + cuenta.getListaTransacciones(), AlertType.INFORMATION);
					 limpiarCampos();
				}
			}
		} catch (NumberFormatException | HeadlessException | DatosInvalidosException | ClienteException | CuentaException e) {
			bancoAplicacion.mostrarMensaje("Notificación Registro Transacción", "Información registro transacción inválida", e.getMessage(), AlertType.WARNING);			

		}
    }
    
    /**
     * Limpiar campos para una nueva solicitud
     */
    private void limpiarCampos() {
    	txtCedulaTransaccion.setText("");
    	txtDepositarDinero.setText("");
    	txtNumeroCuentaBancaria.setText("");
    	txtRetirarDinero.setText("");
	}

	/**
     * Método para validar que el usuario haya ingresado números
     * @param dineroUsuario
     * @return 
     * @throws DatosInvalidosException 
     */
    private boolean validarNumero(String dineroUsuario, String numeroCedulaTransaccion) throws DatosInvalidosException {
    	
		String notificacionNumero = "";
				
		if(!(dineroUsuario.matches("[0-9.]*"))) notificacionNumero += "El dinero debe ser un valor numérico\n";

		if(!(numeroCedulaTransaccion.matches("[0-9.]*"))) notificacionNumero += "El número de la cédula del cliente asociado debe ser un valor numérico\n";

		if(notificacionNumero.equals("")) return true;

		throw new DatosInvalidosException(notificacionNumero);
    	
	}

	/**
     * Método para validar que una cuenta bancaria se encuentra registrada
     * @param numeroCuentaBancaria
     * @return
	 * @throws DatosInvalidosException 
     */
	private boolean validarNumeroCuentaBancaria(String numeroCuentaBancaria, String cedulaTransaccion) throws DatosInvalidosException {
		
		String notificacion = "";	
		
		if(numeroCuentaBancaria == null || numeroCuentaBancaria.equals("")) notificacion += "Ingrese el número de la cuenta bancaria\n";
		
		if(cedulaTransaccion == null || cedulaTransaccion.equals("")) notificacion += "Ingrese el número de la cédula\n";
		
	
		if(notificacion.equals("")) return true;
		
		throw new DatosInvalidosException(notificacion); 
	}

    @FXML
    void cerrarTransaccion(ActionEvent event) {
    	System.exit(0);
    }

}
