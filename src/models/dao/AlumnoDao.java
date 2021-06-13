package models.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.Alumno;

public class AlumnoDao {

	public static final String TABLE_NAME_ALUMNOS 		= "Alumnos";
	public static final String TITLE_COLUMN_DNI 		= TABLE_NAME_ALUMNOS+".dni";
	public static final String TITLE_COLUMN_NAME 		= TABLE_NAME_ALUMNOS+".nombre";
	public static final String TITLE_COLUMN_LASTNAME 	= TABLE_NAME_ALUMNOS+".apellido";
	public static final String TITLE_COLUMN_ADDRESS 	= TABLE_NAME_ALUMNOS+".direccion";
	public static final String TITLE_COLUMN_TEL 		= TABLE_NAME_ALUMNOS+".telefono";
		
	private Connection connection;
		
	public AlumnoDao(Connection connection) {
		this.connection = connection;
	}
	
	public ArrayList<Alumno> getAlumnos() throws SQLException {
		ArrayList<Alumno> alumnos = new ArrayList<Alumno>();
		String sql = "Select * from " + TABLE_NAME_ALUMNOS + ";";
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql); 
		
		while (resultSet.next()) 
			alumnos.add(resultSetToAlumno(resultSet));
		
		resultSet.close();
		statement.close();
		connection.close();
		
		return alumnos;
	}
		
	
	public static Alumno resultSetToAlumno(ResultSet resultSet) throws SQLException {
		Alumno alumno = new Alumno();
		alumno.setDni(resultSet.getInt(TITLE_COLUMN_DNI));
		alumno.setNombre(resultSet.getString(TITLE_COLUMN_NAME));
		alumno.setApellido(resultSet.getString(TITLE_COLUMN_LASTNAME));
		alumno.setDireccion(resultSet.getString(TITLE_COLUMN_ADDRESS));
		alumno.setTelefono(resultSet.getString(TITLE_COLUMN_TEL));
		return alumno;
	}
	
}
