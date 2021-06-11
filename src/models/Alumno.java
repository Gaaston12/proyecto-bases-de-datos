package models;

public class Alumno {

	private int dni;
	private String nombre;
	private String apellido;
	private String direccion;
	private String telefono;
	
	public Alumno() {	
		this.nombre = "";
		this.apellido = "";
		this.telefono = "";
		this.direccion = "";
	}
	
	public Alumno(int dni,String nombre, String apellido, String direccion, String telefono) {	
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		
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
		return this.telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	@Override
	public String toString() {
		String msg = infoValueInt("Dni", this.dni)
				+ infoValueString("Nombre", this.nombre)
				+ infoValueString("Apellido", this.apellido)
				+ infoValueString("Direccion", this.direccion)
				+ infoValueString("Telefono", this.telefono);
		return "\n "+msg;
	}
	
	private String infoValueString(String msg,String value) {
		return value.isEmpty() ? "": msg+": "+value+" ";
	}
	
	private String infoValueInt(String msg,int value) {
		return value == Integer.MIN_VALUE ? "" : msg+": "+String.valueOf(value)+" ";
	}
}
