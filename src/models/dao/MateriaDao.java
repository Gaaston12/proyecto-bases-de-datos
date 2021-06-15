package models.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.Alumno;
import models.Materia;

public class MateriaDao {
	
	public static final String TABLE_NAME 					= "Materias"; 
	public static final String TITLE_COLUMN_COD 			= TABLE_NAME+".cod";
	public static final String TITLE_COLUMN_NAME 			= TABLE_NAME+".nombre";
	public static final String TITLE_COLUMN_DNI_TEACHER 	= TABLE_NAME+".dni_responsable";
	
	private Connection connection;
	
	public MateriaDao(Connection connection) {
		this.connection = connection;
	}
		
	
	public ArrayList<Alumno> getAlumnos(String materia_nombre) throws SQLException {
		ArrayList<Alumno> alumnos = new ArrayList<Alumno>();
		String sql = "SELECT "+ AlumnoDao.TITLE_COLUMN_DNI + ", " + AlumnoDao.TITLE_COLUMN_NAME + ", "+AlumnoDao.TITLE_COLUMN_LASTNAME 
						+ " from " + TABLE_NAME 
						+ " inner join ( " + AlumnoDao.TABLE_NAME + " inner join " + CursaDao.TABLE_NAME +" on "
						+ AlumnoDao.TITLE_COLUMN_DNI + " = "  + CursaDao.TITLE_COLUMN_DNI_ALUMNO  +" )"
						+ " on "+ CursaDao.TITLE_COLUMN_COD_MATERIA +" = " + TITLE_COLUMN_COD 
						+ " where " + TITLE_COLUMN_NAME + " = '"+materia_nombre+"' ;" ;  
				
		Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		ResultSet resultSet = statement.executeQuery(sql); 
				
		if (!resultSet.isBeforeFirst()) 
			throw new SQLException("La materia \""+ materia_nombre +"\" no tiene alumnos o no existe");
				
		resultSet.beforeFirst();
		
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
	
	
	public void delete (String materia) throws SQLException {
		String sql = "";
		if(isNumeric(materia)) {
			
			int cod = Integer.parseInt(materia);
			sql = "delete from " + TABLE_NAME
					+ " where " + TITLE_COLUMN_COD + " = "+ cod +";";
		}else 
			 sql = "delete from " + TABLE_NAME
					+ " where " + TITLE_COLUMN_NAME + " = '"+ materia +"' ;";
		
		
		PreparedStatement statement = connection.prepareStatement(sql);
		
		if (statement.executeUpdate() > 0) 
			System.out.println(" Se elimino correctamente la materia " +materia);
		else
			System.out.println(" Error al eliminar la materia "+materia+" puede que no exista");
		
		statement.close();
		
	}
	
	public static Materia resultSetToMateria(ResultSet resultSet) throws SQLException {
		Materia materia = new Materia();
		materia.setDni_responsable(resultSet.getInt(TITLE_COLUMN_DNI_TEACHER));
		materia.setNombre(resultSet.getString(TITLE_COLUMN_NAME));
		return materia;
	}
	
	private boolean isNumeric(String cadena){
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException e){		
			return false;
		}
	}
}
