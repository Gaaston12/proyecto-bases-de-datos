package models.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.Alumno;

public class MateriaDao {
	final String TABLE_NAME_MATERIAS = "Materias"; 
	Connection connection;
	
	public MateriaDao(Connection connection) {
		this.connection = connection;
	}
	
	public ArrayList<Alumno> getAlumnos(String materia_nombre) throws SQLException {
		ArrayList<Alumno> alumnos = new ArrayList<Alumno>();
		String alumnos_dni_colunm = AlumnoDao.TABLE_NAME_ALUMNOS+".dni";
		String alumnos_nombre_colunm = AlumnoDao.TABLE_NAME_ALUMNOS+".nombre";
		String materias_cod_colunm = TABLE_NAME_MATERIAS+".cod";
		String materia_nombre_column =TABLE_NAME_MATERIAS+".nombre";
		
		String sql ="select " + alumnos_dni_colunm + ", "+ alumnos_nombre_colunm
				+ " from " + TABLE_NAME_MATERIAS + " inner join "
				+ "( " + AlumnoDao.TABLE_NAME_ALUMNOS + " inner join cursa on " + alumnos_dni_colunm + " = cursa.dni_alumno) "
				+ " on Cursa.cod_materia = " + materias_cod_colunm
				+ " where "+materia_nombre_column+" = '"+materia_nombre+ "' ;";
		
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql); 
		
		
		while (resultSet.next()) {
			Alumno alumno = new Alumno();
			alumno.setDni(resultSet.getInt(alumnos_dni_colunm));
			alumno.setNombre(resultSet.getString(alumnos_nombre_colunm));
			alumnos.add(alumno);
			
		}
		return alumnos;
	}
	
	public void delete (int cod) throws SQLException {
		String sql = "delete from "+TABLE_NAME_MATERIAS+" where cod = "+cod+";";
		PreparedStatement statement = connection.prepareStatement(sql);
		
		if (statement.executeUpdate() > 0) 
			System.out.println(" Se elimino correctamente la materia "+cod);
		else
			System.out.println(" Error al eliminar la materia "+cod+" puede que no exista");
	}
	
}
