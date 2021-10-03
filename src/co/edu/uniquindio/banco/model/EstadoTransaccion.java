package co.edu.uniquindio.banco.model;

public enum EstadoTransaccion {

	EXITOSA(0), RECHAZADA(1), SIN_FONDOS(2);
	
	private int numEstadoTransaccion;

	/**
	 * Constructor
	 * @param numEstadoTransaccion
	 */
	private EstadoTransaccion(int numEstadoTransaccion) {
		this.numEstadoTransaccion = numEstadoTransaccion;
	}
	
	//----------Métodos Getters and Setters-------------------------------->
	public int getNumEstadoTransaccion() {
		return numEstadoTransaccion;
	}
	public void setNumEstadoTransaccion(int numEstadoTransaccion) {
		this.numEstadoTransaccion = numEstadoTransaccion;
	}
	//---------------------------------------------------------------------|
	
	
	public EstadoTransaccion getEstadoTransaccion(int index) {
		
		switch (index) {
		
		case 0: return EstadoTransaccion.EXITOSA;
		
		case 1: return EstadoTransaccion.RECHAZADA;
		
		case 2: return EstadoTransaccion.SIN_FONDOS;
		
		default: return null;
		}
	}
}
