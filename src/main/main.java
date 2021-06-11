package main;

import java.sql.SQLException;
import java.util.Scanner;
import models.Actividad;
import models.dao.DaoManager;

public class main {

	public static void main(String[] args) {
		int op = 0;
		Scanner input = new Scanner(System.in);
		DaoManager daoManager = configurarConecxion(input);
				
		do {
			if (op==0) {
				menu();
				op = input.nextInt();
			}
			input.nextLine();
			
			try {				
				switch (op) {
				case 1: daoManager.getActividadDao().insert(cargarActividad(input));
					break;
				case 2: System.out.println("Ingrese el codigo de la materia que quiere eliminar");
					daoManager.getMateriaDao().delete(input.nextInt());		// ver la eliminacion en cascasda	
					input.nextLine();	
					break;
				case 3: System.out.println("Ingrese el nombre de la materia"); 
					System.out.println(daoManager.getMateriaDao().getAlumnos(input.nextLine()));
					break;
				case 4: System.out.println("Adios");
					break;
				default: System.out.println("Ingrese una opcion valida");
						 op = 0;
					break;
				}
				
			} catch (SQLException | ClassNotFoundException e) {
				System.out.println("Error sql \n"+e.getMessage());
			}
			
			while ( op > 0 && op < 4 ) {
				System.out.println(" \n Para volver al menu presione 0 y para salir 4");
				op = input.nextInt();
				if (op == 4 ) System.out.println("Adios");
			}
			
			
		} while (op != 4 );
		
	}
	
	private static Actividad cargarActividad(Scanner input) {
		Actividad actividad = new Actividad();

		System.out.println("Ingrese la descripcion de la actividad");
		actividad.setDescipcion(input.next());
		System.out.println("Ingrese el codigo de la materia");
		actividad.setCod_materia(input.nextInt());
		
		return actividad;
	}
	
	private static void menu() {
		
		System.out.println(" Ingreder una opcion ");
		System.out.println(" 1- Insertar una actividad ");
		System.out.println(" 2- Eliminar una materia ");
		System.out.println(" 3- Listar alumnos de una materia ");
		System.out.println(" 4- Salir \n");
				
	}

	private static  DaoManager configurarConecxion(Scanner input) {
		DaoManager daoManager = new DaoManager();
		String op = "";
		do {
			System.out.println("Si quieres cambiar los datos de conexion ? y/n");
			 op = input.nextLine();
		} while (!op.equals("y") && !op.equals("n") );
		
		if (op.equals("y") ) {
			System.out.println("Cofigure su coneccion a la base de datos \n");
			System.out.println("Ingrese el host: ");
			daoManager.setHost(input.next());
			System.out.println("Ingrese el puerto: ");
			daoManager.setPort(input.next());
			System.out.println("Nombre de la base de datos: ");
			daoManager.setDatabase(input.next());
			System.out.println("Usuario: ");
			daoManager.setUser(input.next());
			System.out.println("Contaseña: ");
			daoManager.setPassword(input.next());
		}
		return daoManager;
	}
	
}
