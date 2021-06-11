package models;

public class Docente {

	private int dni;
	private String nombre;
	private String apellido;
	private String direccion;
	private String telefono;
	private String dedicacion;
	
	public Docente() {
		this.nombre = "";
		this.apellido = "";
		this.direccion = "";
		this.telefono = "";
		this.dedicacion = "";
	}
	
	public Docente(String nombre, String apellido, String direccion, String telefono, String dedicacion) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.direccion = direccion;
		this.telefono = telefono;
		this.dedicacion = dedicacion;
		
	}
	
	public int getDni() {
		return dni;
	}
	public void setDni(int dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getDedicacion() {
		return dedicacion;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Nombre: " + this.nombre+" apellido: " + this.apellido 
				+" direccion "+this.direccion +" telefono "+this.telefono+" Dedicacion "+this.dedicacion;
	}
	
	
	
	
}
