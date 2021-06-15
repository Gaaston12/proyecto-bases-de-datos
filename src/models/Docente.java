package models;

public class Docente extends Persona{

	private String dedicacion;
	
	public Docente() {
		super();
		this.dedicacion = "";
	}
	
	public Docente(int dni, String nombre, String apellido, String direccion, String telefono, String dedicacion) {
		
		super(dni, nombre, apellido, direccion, telefono);
		this.dedicacion = dedicacion;
		
	}
		
	public String getDedicacion() {
		return dedicacion;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "\n"+ super.toString() + Persona.infoValueString("Dedicacion", this.dedicacion);
	}
	
	
	
	
}
