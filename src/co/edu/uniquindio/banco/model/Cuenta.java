package co.edu.uniquindio.banco.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import co.edu.uniquindio.banco.model.services.ICuentaService;

public abstract class Cuenta implements ICuentaService , Serializable{

	private static final long serialVersionUID = 1L;
	
	private String numeroCuenta;
	private Double saldo;
	private Cliente clienteAsociado;
	HashMap<String, Transaccion> listaTransacciones = new HashMap<>();
	
	public Cuenta() {}
	
	public String getNumeroCuenta() {
		return numeroCuenta;
	}
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
	public Double getSaldo() {
		return saldo;
	}
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	public Cliente getClienteAsociado() {
		return clienteAsociado;
	}
	public void setClienteAsociado(Cliente clienteAsociado) {
		this.clienteAsociado = clienteAsociado;
	}
	public HashMap<String, Transaccion> getListaTransacciones() {
		return listaTransacciones;
	}
	public void setListaTransacciones(HashMap<String, Transaccion> listaTransacciones) {
		this.listaTransacciones = listaTransacciones;
	}
	
	@Override
	public void retirarDinero(Double cantidad) {
		
	}
	
	public void crearTransaccion() {
		
	}

	@Override
	public void depositarDinero(Double cantidad) {
		
	}

	@Override
	public void consultarSaldo(Integer numeroCuenta) {
		
	}


	
}
