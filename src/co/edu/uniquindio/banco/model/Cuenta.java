package co.edu.uniquindio.banco.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import co.edu.uniquindio.banco.model.services.ICuentaService;

public class Cuenta implements ICuentaService , Serializable{

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
	
	
	public Transaccion validarTransaccion(Double cantidad, String numeroCuentaBancaria) {
		
		Transaccion transaccion = null;
		
		if(cantidad < saldo) {
			transaccion = new Transaccion(cantidad, TipoTransaccion.RETIRO, EstadoTransaccion.EXITOSA);
		} else if(cantidad > saldo) {
			transaccion = new Transaccion(cantidad, TipoTransaccion.RETIRO, EstadoTransaccion.SIN_FONDOS);
		} else {
			transaccion = new Transaccion(cantidad, TipoTransaccion.RETIRO, EstadoTransaccion.RECHAZADA);
		}
		listaTransacciones.put(numeroCuentaBancaria, transaccion);
			
		return transaccion;
	}
	
	@Override
	public void retirarDinero(double dineroRetiro) {
		saldo -= dineroRetiro;
	}

	@Override
	public void depositarDinero(Double cantidad) {
		saldo += cantidad;
	}
	
	public Transaccion validarDeposito(double depositarDinero, String numeroCuentaBancaria) {
		Transaccion transaccion = null;
		
		if(numeroCuenta.equalsIgnoreCase(numeroCuentaBancaria)) {
			transaccion = new Transaccion(depositarDinero, TipoTransaccion.DEPOSITO, EstadoTransaccion.EXITOSA);
		} else {
			transaccion = new Transaccion(depositarDinero, TipoTransaccion.DEPOSITO, EstadoTransaccion.RECHAZADA);
		}
		listaTransacciones.put(numeroCuentaBancaria, transaccion);
		
		return transaccion;
	}

	@Override
	public void consultarSaldo(Integer numeroCuenta) {
		
	}

	public Transaccion validarSaldo(double dineroRetiro, String numeroCuentaBancaria) {
		// TODO Auto-generated method stub
		return null;
	}




	
}
