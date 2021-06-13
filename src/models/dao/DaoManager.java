package models.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class DaoManager {

	private String host = "localhost";
	private String port = "3636";
	private String database = "Proyecto";
	private String user = "root";
	private String password = "root";
	
	private Connection connection;
	private AlumnoDao alumnoDao;
	private MateriaDao materiaDao;
	private ActividadDao actividadDao;
		
	public DaoManager() {	
		this.host = "localhost";
		this.port = "3306";
		this.database = "Proyecto";
		this.user = "root";
		this.password = "root";
	}

	private Connection connectionSql() throws SQLException, ClassNotFoundException{
		String driver = "com.mysql.cj.jdbc.Driver";
		Class.forName(driver);
		if (connection == null) {
			connection = DriverManager.getConnection("jdbc:mysql://" + host+":"+port+ "/" + database + "?serverTimezone=UTC", user, password);
			
		}
		
		return connection;
	}
	
	public AlumnoDao getAlumnoDao() throws SQLException, ClassNotFoundException {
		if(alumnoDao == null)
			alumnoDao = new AlumnoDao(connectionSql());
		return alumnoDao;
	}
	
	public MateriaDao getMateriaDao() throws ClassNotFoundException, SQLException {
		if(materiaDao == null)
			materiaDao = new MateriaDao(connectionSql());
		return materiaDao;
	}
	
	public ActividadDao getActividadDao() throws ClassNotFoundException, SQLException {
		if(actividadDao == null)
			actividadDao = new ActividadDao(connectionSql());
		return actividadDao;
	}
	
	
	public void setHost(String host) {
		this.host = host;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setPassword(String password) {
		this.password = password;
	}
		
	public void setPort(String port) {
		this.port = port;
	}
	
	public void closeConnection() throws SQLException {
		connection.close();
	}
	
}
