package proyecto;

import java.time.LocalDate;
import java.util.ArrayList;

public class Usuario {
	String nombre;
	ArrayList<Tarea> Tareas = new ArrayList<Tarea>();
	
	public Usuario(){
	}
	
	public void setTareas(ArrayList<Tarea> tareas) {
		Tareas = tareas;
	}

	public ArrayList<Tarea> getTareas() {
		return Tareas;
	}

	public Usuario(String nombre) {
	this.nombre = nombre;
	}
	
	public String getNombre() {
	return nombre;
	}
	
	public void setNombre(String nombre) {
	this.nombre = nombre;
	}
	
	public void addTarea(Tarea Ta) {
		Tareas.add(Ta);
	}
	
	public ArrayList<Tarea> TareasDia(LocalDate Hoy){
		ArrayList<Tarea> TDia=new ArrayList<Tarea>();
		for(int i=0;i<Tareas.size();i++) {
			if(Tareas.get(i).isLimit()) {   // La tarea tiene tempo limite
				if(Tareas.get(i).getFecha().isEqual(Hoy)) { // El tiempo limite es el dia de hoy
					TDia.add(Tareas.get(i));
				}		
				
				if(Tareas.get(i).getFecha().minusDays(Tareas.get(i).getTiempoAntelacion()).isEqual(Hoy)) {
					TDia.add(Tareas.get(i));
				}
			}
		}
		return TDia;
	}
	

	public ArrayList<Tarea> TareasPend(LocalDate Hoy){
		ArrayList<Tarea> TDia=new ArrayList<Tarea>();
		for(int i=0;i<Tareas.size();i++) {
			if(Tareas.get(i).isLimit()) {  // Si la tarea tiene limite verificar que sea para hoy o un dia proximi
				if(Tareas.get(i).getFecha().isEqual(Hoy) || Tareas.get(i).getFecha().isAfter(Hoy)) {
					TDia.add(Tareas.get(i));
				}		
			}else { // si no tiene fecha limite agregar la tarea
				TDia.add(Tareas.get(i));
			}
		}
		return TDia;
	}
	

	public ArrayList<Tarea> TareasSemana(LocalDate Hoy){
		LocalDate DSem = Hoy.plusWeeks(1);
		ArrayList<Tarea> TSem=new ArrayList<Tarea>();
		for(int i=0;i<Tareas.size();i++) {
			if(Tareas.get(i).isLimit()) {  // Si la tarea tiene limite verificar que sea para hoy o un dia proximi
				if((Tareas.get(i).getFecha().isEqual(Hoy) || Tareas.get(i).getFecha().isAfter(Hoy))&&Tareas.get(i).getFecha().isBefore(DSem)) {
					TSem.add(Tareas.get(i));
				}		
			}
		}
		return TSem;
	}

	public ArrayList<Tarea> TareasMes(LocalDate Hoy){
		LocalDate DMes = Hoy.plusMonths(1);
		ArrayList<Tarea> TMes=new ArrayList<Tarea>();
		for(int i=0;i<Tareas.size();i++) {
			if(Tareas.get(i).isLimit()) {  // Si la tarea tiene limite verificar que sea para hoy o un dia proximi
				if((Tareas.get(i).getFecha().isEqual(Hoy) || Tareas.get(i).getFecha().isAfter(Hoy))&&Tareas.get(i).getFecha().isBefore(DMes)) {
					TMes.add(Tareas.get(i));
				}		
			}
		}
		return TMes;
	}
}
