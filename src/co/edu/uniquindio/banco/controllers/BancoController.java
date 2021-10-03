package co.edu.uniquindio.banco.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.banco.application.BancoApplication;
import co.edu.uniquindio.banco.model.Banco;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class BancoController {
	
	//-------SINGLETON------------------------------------------------------>
	
	ModelFactoryController modelFactoryController;
	Banco banco;
	
	public BancoController() {
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
    private Button btnRegistrar;

    @FXML
    private RadioButton rdBtnRegistrarCliente;

    @FXML
    private ToggleGroup tipoRegistro;

    @FXML
    private RadioButton rdBtnRegistrarEmpleado;

    @FXML
    private RadioButton rdBtnRegistrarTransaccion;
    
    private BancoApplication bancoAplicacion;
    
	public void setAplicacion(BancoApplication bancoApplication) {
    	this.bancoAplicacion = bancoApplication;
    	this.banco = bancoApplication.getBanco();
    }

    @FXML
    void registrar(ActionEvent event) {
    	if(rdBtnRegistrarCliente.isSelected())
    		bancoAplicacion.mostrarClienteView();
    	if(rdBtnRegistrarEmpleado.isSelected())
    		bancoAplicacion.mostrarEmpleadoView();
    	if(rdBtnRegistrarTransaccion.isSelected())
    		bancoAplicacion.mostrarTransaccionView();
     
    }
}
