package models.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.Alumno;

public class MateriaDao {
	
	final String TABLE_NAME_MATERIAS 		= "Materias"; 
	final String TITLE_COLUMN_COD 			= TABLE_NAME_MATERIAS+".cod";
	final String TITLE_COLUMN_NAME 			= TABLE_NAME_MATERIAS+".nombre";
	final String TITLE_COLUMN_DNI_TEACHER 	= TABLE_NAME_MATERIAS+".dni_responsable";
	
	private Connection connection;
	
	public MateriaDao(Connection connection) {
		this.connection = connection;
	}
	
	public ArrayList<Alumno> getAlumnos(String materia_nombre) throws SQLException {
		ArrayList<Alumno> alumnos = new ArrayList<Alumno>();
		String sql = "SELECT "+ AlumnoDao.TITLE_COLUMN_DNI + ", " + AlumnoDao.TITLE_COLUMN_NAME + ", "+AlumnoDao.TITLE_COLUMN_LASTNAME 
						+ " from " + TABLE_NAME_MATERIAS 
						+ " inner join ( " + AlumnoDao.TABLE_NAME_ALUMNOS + " inner join cursa on "
						+ AlumnoDao.TITLE_COLUMN_DNI + " = cursa.dni_alumno )"
						+ " on Cursa.cod_materia = " + TITLE_COLUMN_COD 
						+ " where " + TITLE_COLUMN_NAME + " = '"+materia_nombre+"' ;" ;  
				
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql); 
		
		
		while (resultSet.next()) {
			Alumno alumno = new Alumno();
			alumno.setDni(resultSet.getInt(AlumnoDao.TITLE_COLUMN_DNI));
			alumno.setNombre(resultSet.getString(AlumnoDao.TITLE_COLUMN_NAME));
			alumno.setApellido(resultSet.getString(AlumnoDao.TITLE_COLUMN_LASTNAME));
			alumnos.add(alumno);
			
		}
		resultSet.close();
		statement.close();
				
		return alumnos;
	}
	
	public void delete (int cod) throws SQLException {
		String sql = "delete from " + TABLE_NAME_MATERIAS
					+ " where " + TITLE_COLUMN_COD + " = "+ cod +";";
		PreparedStatement statement = connection.prepareStatement(sql);
		
		if (statement.executeUpdate() > 0) 
			System.out.println(" Se elimino correctamente la materia "+cod);
		else
			System.out.println(" Error al eliminar la materia "+cod+" puede que no exista");
		
		statement.close();
		
	}
	
}
