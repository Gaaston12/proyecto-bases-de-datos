package models.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import models.Actividad;

public class ActividadDao {
	
	
	public static final String TABLE_NAME_ACTIVIDAD = "Actividad";
	private Connection connection;
	
	public ActividadDao(Connection connection) {
		this.connection = connection;
	}
	
	
	public void insert(Actividad actividad) throws SQLException {
		String sql = "insert into "+ TABLE_NAME_ACTIVIDAD +"(descripcion,cod_materia) values(?,?);";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, actividad.getDescipcion());
		statement.setInt(2, actividad.getCod_materia());
		if (statement.executeUpdate() > 0)
			System.out.println("la actividad "+actividad.getDescipcion()+" se inserto correctamente");
		else	
			System.out.println("No se pudo insertar la actividad "+actividad.getDescipcion());
	}
	
}
