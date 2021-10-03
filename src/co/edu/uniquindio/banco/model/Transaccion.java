package co.edu.uniquindio.banco.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Transaccion implements Serializable {

	private static final long serialVersionUID = 1L;
	private double valor;
	private String hora;
	private LocalDate fecha;
	private TipoTransaccion tipoTransaccion;
	private EstadoTransaccion estado;
	
	/**
	 * Constructor de la clase
	 * @param valor, hora, fecha, tipoTransaccion, estado
	 */
	public Transaccion(double valor, TipoTransaccion tipoTransaccion, EstadoTransaccion estado) {
		this.valor = valor;
		
		LocalTime hora = LocalTime.now();
        DateTimeFormatter f = DateTimeFormatter.ofPattern("h':'mm':'ss");
        
		this.hora = hora.format(f);
		this.fecha = LocalDate.now();
		this.tipoTransaccion = tipoTransaccion;
		this.estado = estado;
	}

	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public TipoTransaccion getTipoTransaccion() {
		return tipoTransaccion;
	}
	public void setTipoTransaccion(TipoTransaccion tipoTransaccion) {
		this.tipoTransaccion = tipoTransaccion;
	}
	public EstadoTransaccion getEstado() {
		return estado;
	}
	public void setEstado(EstadoTransaccion estado) {
		this.estado = estado;
	}
	
	@Override
	public String toString() {
		return "Transaccion" + "\n" + "Valor: " + valor + "\n" + "Hora: " + hora + "\n" + "Fecha: " + fecha + "\n" + "Tipo de Transaccion: "
				+ tipoTransaccion +"\n" + "Estado: " + estado + "\n\n";
	}

}
