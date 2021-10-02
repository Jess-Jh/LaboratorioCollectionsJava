package co.edu.uniquindio.banco.exceptions;

public class DatosInvalidosException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public DatosInvalidosException(String mensaje) {
		super(mensaje);
	}

}
