package models.dao;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class DaoManager {

	private final String FILE_CONFIG_JSON = "config_db.json";
	private String user;
	private String password;
	private String url;
	private Connection connection;
	private AlumnoDao alumnoDao;
	private MateriaDao materiaDao;
	private ActividadDao actividadDao;
		
	public DaoManager() {	
		loadJson();
		System.out.println("Conexion = Url: "+url+" User: "+user+ " password: "+password);
	}

	private Connection connectionSql() throws SQLException, ClassNotFoundException{
		String driver = "com.mysql.cj.jdbc.Driver";
		Class.forName(driver);
		if (connection == null) {
			connection = DriverManager.getConnection("jdbc:mysql://" + url + "?serverTimezone=UTC", user, password);
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
		
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getUrl() {
		return url;
	}

	public void closeConnection() throws SQLException {
		connection.close();
	}
	
	private void loadJson() {
		JSONParser parser = new JSONParser();
				
		try {
			FileReader file = new FileReader(FILE_CONFIG_JSON);
			JSONObject jsonObject = (JSONObject) parser.parse(file);
			this.url = jsonObject.get("Host")+"/"+jsonObject.get("Database"); 
			this.user = (String) jsonObject.get("User");
			this.password = (String) jsonObject.get("Password");
			file.close();
		} catch (IOException | ParseException e) {
			System.out.println("Error al leer la configuracion de la base de datos "+e.getMessage());
		}
	}
}
