package proyecto;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String ruta = "D:/Users/Martin/Desktop/test.txt";
		
		
		String nombre;
		Usuario User = null;
		
		try{
		
		System.out.print("Ingrese su nombre: ");
		nombre = scan.next();
		
		User = new Usuario(nombre);
		
		}
		catch(InputMismatchException e) {scan.next();}
		
		LocalDate Hoy = LocalDate.now();
		if(User!=null) {
			boolean bmenu=true;
			while(bmenu) {
				System.out.println("Menu\n"
						+ " 1. Ingresar actividad\n"
						+ " 2. Ver horario del dia\n"
						+ " 3. Ver lista de tareas pendientes\n"
						+ " 4. Ver tracker semanal\n"
						+ " 5. Ver calendario mensual\n"
						+ " 6. Guardar en un csv\n"
						+ " 7. Leer un csv\n"
						+ " 8. Salir del programa\n"
						+ "Ingrese la opcion que desea seleccionar: ");
				int sel=-1;
				try {sel = scan.nextInt();}
				catch(InputMismatchException e) {scan.next();}
				
				switch(sel) {
				case 1:
					//ingresar actividad
					Tarea Ta = genTarea(scan);
					if(Ta!=null) {
						User.addTarea(Ta);
					}
					break;
				case 2: 
					//Horario del dia
					ArrayList<Tarea> TDia=User.TareasDia(Hoy);
					
					System.out.println("Tareas para hoy, "+Hoy.toString());
					for(int i=0;i<TDia.size();i++) {
						System.out.println(TDia.get(i).toString());
					}
					break;
				case 3:
					//lista de tareas pendientes
					ArrayList<Tarea> TPend=User.TareasPend(Hoy);
					
					System.out.println("Tareas pendientes: ");
					for(int i=0;i<TPend.size();i++) {
						System.out.println(TPend.get(i).toString());
					}
					break;
					
				case 4: 
					//tracker semanal
					ArrayList<Tarea> TSem=User.TareasPend(Hoy);
					
					System.out.println("Tareas para esta semana: ");
					for(int i=0;i<TSem.size();i++) {
						System.out.println(TSem.get(i).toString());
					}
					break;
				case 5: 
					//calendario mensual
					ArrayList<Tarea> TMes=User.TareasPend(Hoy);
					
					System.out.println("Tareas para este mes: ");
					for(int i=0;i<TMes.size();i++) {
						System.out.println(TMes.get(i).toString());
					}
					break;
				case 6:
					// guardar
					try {
						Guardar(User.getTareas(),ruta);
						System.out.println("Se ha guardado exisosamente");
						
					}catch(IOException e) {
						System.out.println("Error al guardar, por favor revisar la ruta de guardado en el principio del código");
					}
					break;
				case 7: 
					//leer
					try {
						User.setTareas(Lectura(ruta));
						System.out.println("Lectura exitosa");
						
					}catch(IOException e) {
						System.out.println("Error al leer, por favor revisar la ruta de guardado en el principio del código");
					}
					break;
					
				case 8:
					System.out.println("Saliendo del programa...");
					bmenu=false;
				}
			}
		}
		
		scan.close();
	}
	
	public static Tarea genTarea(Scanner scan) {
		try {
			Tarea Ta = null;
			Character c=null;
			
	// Nombre
			String Nombre;
			System.out.println("Ingrese el nombre de la actividad: ");
			Nombre=scan.next();
	
	// Fecha limite
			LocalDate Fecha;
			int anio;
			int mes;
			int dia;
			

			System.out.println("La actividad tiene fecha limite? (Y/N)");
			
			c = scan.next().charAt(0);
			
			if(c.toString().equalsIgnoreCase("y")) {
				
				System.out.println("Ingrese la fecha limite:");
				System.out.println("Dia: ");
				dia = scan.nextInt();
				System.out.println("Mes: ");
				mes = scan.nextInt();
				System.out.println("Anio: ");
				anio = scan.nextInt();
				
				Fecha = LocalDate.of(anio, mes, dia);
				
			}else if(c.toString().equalsIgnoreCase("n")) {

				Fecha=null;
				
			}else {
				throw new Exception("Caracter no reconocido");
			}
			
	// si se repite la actividad y cada cuanto 
			boolean Periodica;
			int Frecuencia;
			
			System.out.println("La actividad se repite? (Y/N)");
			
			c = scan.next().charAt(0);
			
			if(c.toString().equalsIgnoreCase("y")) {

				Periodica = true;
				System.out.println("Con cuantos dias se repite la actividad? ");
				Frecuencia=scan.nextInt();
				
			}else if(c.toString().equalsIgnoreCase("n")) {

				Periodica = false;
				Frecuencia = -1;
				
			}else {
				//System.out.println();
				throw new Exception("Caracter no reconocido");
			}
			
			int TiempoAntelacion;
			boolean ActivarRecordatorio;
			
			System.out.println("Desea activar un recordatorio (Y/N)");
			
			Character c2 = scan.next().charAt(0);
			
			if(c2.toString().equalsIgnoreCase("y")) {

				ActivarRecordatorio = true;
				System.out.println("Con cuantos dias de antelación desea tener el recordatorio? ");
				TiempoAntelacion=scan.nextInt();
				
			}else if(c2.toString().equalsIgnoreCase("n")) {

				ActivarRecordatorio = false;
				TiempoAntelacion = -1;
				
			}else {
				//System.out.println();
				throw new Exception("Caracter no reconocido");
			}
			
		Ta = new Tarea(Nombre, Fecha, Periodica, Frecuencia, TiempoAntelacion, ActivarRecordatorio);
		return Ta;
		}
		
		catch(InputMismatchException e) {scan.next();}
		catch(DateTimeException e) {System.out.println("Fecha inválida");}
		catch(Exception e) {System.out.println(e);}
		System.out.println("Dato invalido");	
		return null;
	}

	public static ArrayList<Tarea> Lectura(String ruta) throws IOException {
		//int palabrasL = 0;
		ArrayList<Tarea> LectList = new ArrayList<Tarea>();
		
		FileReader fileName = new
		FileReader(ruta);
		BufferedReader lectura = new BufferedReader(fileName);
		String linea;
		while ((linea = lectura.readLine()) != null) {
		String[] palabras = linea.split(";");

		System.out.println(linea);
		LectList.add(new Tarea(
				palabras[0],
				(palabras[1]!="null") ? null : LocalDate.parse(palabras[1]),
				Boolean.parseBoolean(palabras[2]),
				Integer.parseInt(palabras[3]),
				Integer.parseInt(palabras[4]),
				Boolean.parseBoolean(palabras[5])
				));

		
		}
		lectura.close();
		return LectList;
		}
	
/**
 * Guardar los datos en un archivo
 * @param Lista lista de los datos
 * @param ruta ruta donde se guarda el archivo
 * @throws IOException
 */
	public static void Guardar(ArrayList<Tarea> Lista,String ruta) throws IOException {
		FileWriter fileName = new
		FileWriter(ruta);
		BufferedWriter escritura = new BufferedWriter(fileName);
		for (int i=0; i < Lista.size(); i++) {
			escritura.write(Lista.get(i).tocsv());
			escritura.newLine();
			}
		escritura.close();
		}
	
	
}
