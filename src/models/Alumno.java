package models;

public class Alumno extends Persona {

	public Alumno() {
		super();
	}
	
	public Alumno(int dni,String nombre, String apellido, String direccion, String telefono) {
		super(dni, nombre, apellido, direccion, telefono);
	}
	
	@Override
	public String toString() {
		
		return "\n"+super.toString();
	}
	
	
}
