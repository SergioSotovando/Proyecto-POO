package proyecto;

import java.time.LocalDate;

public class Tarea {
	String Nombre;
	LocalDate Fecha;
	boolean Periodica;
	int Frecuencia;
	int TiempoAntelacion;
	boolean ActivarRecordatorio;
	
	public Tarea(String nombre, LocalDate fecha, boolean periodica, int frecuencia, int tiempoAntelacion,
			boolean activarRecordatorio) {
		super();
		Nombre = nombre;
		Fecha = fecha;
		Periodica = periodica;
		Frecuencia = frecuencia;
		TiempoAntelacion = tiempoAntelacion;
		ActivarRecordatorio = activarRecordatorio;
	}

	public String getNombre() {
		return Nombre;
	}

	public boolean isLimit() {
		return Fecha!=null;
	}
	
	public LocalDate getFecha() {
		return Fecha;
	}

	public boolean isPeriodica() {
		return Periodica;
	}

	public int getFrecuencia() {
		return Frecuencia;
	}

	public int getTiempoAntelacion() {
		return TiempoAntelacion;
	}

	public boolean isActivarRecordatorio() {
		return ActivarRecordatorio;
	}

	@Override
	public String toString() {
		return " Nombre: " + Nombre + ", Fecha Límite: " + Fecha + ", Periodica: " + Periodica + ", Frecuencia: "
				+ Frecuencia + ", Tiempo de Antelacion: " + TiempoAntelacion + ", Activar Recordatorio: " + ActivarRecordatorio;
	}
	
	public String tocsv() {
		return Nombre+";"+Fecha+";"+Periodica+";"+Frecuencia+";"+TiempoAntelacion+";"+ActivarRecordatorio;
	}
	
	
}
