package models.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class DaoManager {

	private String host = "localhost";
	private String database = "Proyecto";
	private String user = "root";
	private String password = "root";
	
	private Connection connection;
	private AlumnoDao alumnoDao = null;
	private MateriaDao materiaDao = null;
	private ActividadDao actividadDao = null;
	
	
	public DaoManager() {	
		
	}
	
//	public DaoManager(String host, String user, String password, String database) throws SQLException {
//		connection = DriverManager.getConnection("jdbc:mysql://" + host + "/" + database,user,password); 
//	}
//			
	private Connection connectionSql() throws SQLException, ClassNotFoundException{
		String driver = "com.mysql.cj.jdbc.Driver";
		Class.forName(driver);
		if (connection == null) {
			connection = DriverManager.getConnection("jdbc:mysql://" + host + "/" + database + "?serverTimezone=UTC", user, password);
			
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
	
	public void configuration() {
		Scanner input = new Scanner(System.in);
		System.out.println("Cofiguracion de la coneccion");
		System.out.println("Ingrese el puerto: ");
		this.host = input.next();
		System.out.println("Nombre de la base de datos: ");
		this.database = input.next();
		System.out.println("Usuario: ");
		this.user = input.next();
		System.out.println("Contaseña: ");
		this.password = input.next();
	}
	
}
