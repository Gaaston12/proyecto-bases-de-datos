package models;

public class Actividad {
	private String descipcion;
	private int cod_materia;
	
	public Actividad() {	
		this.descipcion = "";
		this.cod_materia = Integer.MIN_VALUE;
	}
	
	public Actividad(String descripcion,int cod_materia) {
		this.descipcion = descripcion;
		this.cod_materia = cod_materia;
	}

	public String getDescipcion() {
		return descipcion;
	}

	public void setDescipcion(String descipcion) {
		this.descipcion = descipcion;
	}

	public int getCod_materia() {
		return cod_materia;
	}

	public void setCod_materia(int cod_materia) {
		this.cod_materia = cod_materia;
	}
	
	@Override
	public String toString() {
		return "Actividad: "+descipcion+" Codigo de la materia "+cod_materia;
	}
}
