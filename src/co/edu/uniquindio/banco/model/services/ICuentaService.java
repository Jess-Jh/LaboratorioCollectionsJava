package co.edu.uniquindio.banco.model.services;

public interface ICuentaService {

	public void retirarDinero (double cantidad);
	public void depositarDinero(Double cantidad);
	public void consultarSaldo(Integer numeroCuenta);
}
