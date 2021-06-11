package models.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.Alumno;

public class AlumnoDao {

	public static final String TABLE_NAME_ALUMNOS = "Alumnos";
	public static final String TITLE_COLUMN_DNI = "dni";
	public static final String TITLE_COLUMN_NAME = "nombre";
	public static final String TITLE_COLUMN_LASTNAME ="apellido";
	public static final String TITLE_COLUMN_ADDRESS = "direccion";
	public static final String TITLE_COLUMN_TEL = "telefono";
		
	private Connection connection;
		
	public AlumnoDao(Connection connection) {
		this.connection = connection;
	}
	
	public ArrayList<Alumno> getAlumnos() throws SQLException {
		ArrayList<Alumno> alumnos = new ArrayList<Alumno>();
		String sql = "Select * from "+TABLE_NAME_ALUMNOS+";";
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql); 
		
		while (resultSet.next()) 
			alumnos.add(resultSetToAlumno(resultSet));
		
		return alumnos;
	}
		
	
	public static Alumno resultSetToAlumno(ResultSet resultSet) throws SQLException {
		Alumno alumno = new Alumno();
		alumno.setDni(resultSet.getInt("dni"));
		alumno.setNombre(resultSet.getString("nombre"));
		alumno.setApellido(resultSet.getString("apellido"));
		alumno.setDireccion(resultSet.getString("direccion"));
		alumno.setTelefono(resultSet.getString("telefono"));
		return alumno;
	}
	
}
