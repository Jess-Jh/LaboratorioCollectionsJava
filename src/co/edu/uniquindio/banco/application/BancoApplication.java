package co.edu.uniquindio.banco.application;

import java.io.IOException;

import co.edu.uniquindio.banco.controllers.ClienteController;
import co.edu.uniquindio.banco.controllers.EmpleadoController;
import co.edu.uniquindio.banco.controllers.ModelFactoryController;
import co.edu.uniquindio.banco.model.Banco;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class BancoApplication extends Application {

	private Stage primaryStage;
	private Stage dialogStage;
	
	ModelFactoryController modelFactoryController;
	private Banco banco;
	
	public BancoApplication () {
		modelFactoryController = ModelFactoryController.getInstance();
		banco = modelFactoryController.getBanco();
	}

	public Banco getBanco() {
		return banco;
	}
	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		
		mostrarVentanaPrincipal();
	}

	//----------------Métodos para cargar las interfaces------------------------------------------------------------------------------->
	/**
	 * Cargar los archivos de la vista principal
	 */
	public void mostrarVentanaPrincipal() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(BancoApplication.class.getResource("/co/edu/uniquindio/banco/views/ClienteView.fxml"));
			AnchorPane anchorPane = (AnchorPane)loader.load();
			ClienteController clienteController = loader.getController();
			clienteController.setAplicacion(this);
			
			dialogStage = new Stage();
			dialogStage.setTitle("Cliente");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			
			Scene scene = new Scene(anchorPane);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void mostrarClienteView() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(BancoApplication.class.getResource("/co/edu/uniquindio/banco/views/ClienteView.fxml"));
			AnchorPane anchorPane = (AnchorPane)loader.load();
			ClienteController clienteController = loader.getController();
			clienteController.setAplicacion(this);
			
			dialogStage = new Stage();
			dialogStage.setTitle("Cliente");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			
			Scene scene = new Scene(anchorPane);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void mostrarEmpleadoView() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(BancoApplication.class.getResource("/co/edu/uniquindio/banco/views/EmpleadoView.fxml"));
			AnchorPane anchorPane = (AnchorPane)loader.load();
			EmpleadoController empleadoController = loader.getController();
			empleadoController.setAplicacion(this);
			
			dialogStage = new Stage();
			dialogStage.setTitle("Empleado");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			
			Scene scene = new Scene(anchorPane);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//-------------------------------------------------------------------------------------------------------------------------------||

	//----------Métodos Getters and Setters---------------------------------------------------------------------------------------->
	public Stage getPrimaryStage() {
		return primaryStage;
	}
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
	//----------------------------------------------------------------------------------------------------------------------------||
	
	public static void main(String[] args) {
		launch(args);
	}
	
	/**
     * Método para mostrar un mensaje al usuario
     * @param titulo
     * @param header
     * @param contenido
     * @param alertType
     */
    public void mostrarMensaje(String titulo, String header, String contenido, AlertType alertType) {
    	Alert alert = new Alert(alertType);
    	alert.setTitle(titulo);
    	alert.setHeaderText(header);
    	alert.setContentText(contenido);
    	alert.showAndWait();
    }
}


		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

