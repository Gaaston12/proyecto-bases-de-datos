package models;

public class Materia {
	private String nombre;
	private int dni_responsable;
	
	public Materia() {	
		this.nombre = "";
		this.dni_responsable = Integer.MIN_VALUE;
	}
	
	public Materia(String nombre,int dni_responsable) {
		this.nombre = nombre;
		this.dni_responsable = dni_responsable;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getDni_responsable() {
		return dni_responsable;
	}

	public void setDni_responsable(int dni_responsable) {
		this.dni_responsable = dni_responsable;
	}
	
	@Override
	public String toString() {
		return "Materia: "+this.nombre+" Docente responsable: "+this.dni_responsable;
	}
	
}
