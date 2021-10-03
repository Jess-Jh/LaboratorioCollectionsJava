package co.edu.uniquindio.banco.model;

public enum TipoTransaccion {
	
	DEPOSITO(0), RETIRO(1), CONSULTA_SALDO(2);
		
	private int numTipoTransaccion;

	/**
	 * Constructor
	 * @param numTipoTransaccion
	 */
	private TipoTransaccion(int numTipoTransaccion) {
		this.numTipoTransaccion = numTipoTransaccion;
	}
	
	//----------Métodos Getters and Setters-------------------------------->
	public int getNumTipoTransaccion() {
		return numTipoTransaccion;
	}
	public void setNumTipoTransaccion(int numTipoTransaccion) {
		this.numTipoTransaccion = numTipoTransaccion;
	}
	//---------------------------------------------------------------------|
	
	
	public TipoTransaccion getTipoTransaccion(int index) {
		
		switch (index) {
		
		case 0: return TipoTransaccion.DEPOSITO;
		
		case 1: return TipoTransaccion.RETIRO;
		
		case 2: return TipoTransaccion.CONSULTA_SALDO;
		
		default: return null;
		}
	}
}
