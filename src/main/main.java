package main;

import java.sql.SQLException;
import java.util.Scanner;

import models.Actividad;
import models.dao.DaoManager;

public class main {

	public static void main(String[] args) {
		int op = 0;
		Scanner input = new Scanner(System.in);
		Scanner input2 = new Scanner(System.in);
		DaoManager daoManager = new DaoManager();
//		daoManager.configuration();
		
		do {
			if (op==0) {
				menu();
				op = input.nextInt();
			}
										
			try {				
				switch (op) {
				case 1: daoManager.getActividadDao().insert(cargarActividad());
					break;
				case 2: System.out.println("Ingrese el codigo de la materia que quiere eliminar");
					daoManager.getMateriaDao().delete(input.nextInt());		// ver la eliminacion en cascasda		
					break;
				case 3: System.out.println("Ingrese el nombre de la materia"); 
					System.out.println(daoManager.getMateriaDao().getAlumnos(input2.nextLine()));
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
				System.out.println(" Para volver al menu presione 0 y para salir 4");
				op = input.nextInt();
				if (op == 4 ) System.out.println("Adios");
			}
				
		} while (op != 4 );
		
	}
	
	private static Actividad cargarActividad() {
		Actividad actividad = new Actividad();
		Scanner input = new Scanner(System.in);
		
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
		System.out.println(" 4- Salir ");
	}

}
