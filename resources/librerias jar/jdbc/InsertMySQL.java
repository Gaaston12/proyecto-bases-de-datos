import java.sql.*;
import java.io.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class InsertMySQL {

  public InsertMySQL() {
    try {
    	/*  codigo para leer desde consola
        try
        {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String sTexto = br.readLine();
        System.out.println(sTexto);
      } catch(Exception e) {
      }
  */
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  public static void main(String[] args) {

	  Connection connection = null;

    try {
    	String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/prueba?serverTimezone=UTC";
      String username = "root";
      String password = "root";

        try
        {
        	System.err.println("Presione una tecla para continuar"); 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String sTexto = br.readLine();
        System.out.println(sTexto);
      } catch(Exception e) {
      }



      // Load database driver if not already loaded.
      Class.forName(driver);
      // Establish network connection to database.
      connection =
        DriverManager.getConnection(url, username, password);
 
      // para trabajar con transacciones
      connection.setAutoCommit(false); 
      String query = "insert into persona (DNI,NOMBRE,EMAIL) values(?,?,?)";

      PreparedStatement statement = connection.prepareStatement(query);
      // Send query to database and store results.
      statement.setString(1,"444444");
      statement.setString(2,"nuevaPeesona444444");
      statement.setString(3,"correonueva persona44444");
      statement.executeUpdate();

      
      // cierro la transaccion
      connection.rollback();
    } catch(ClassNotFoundException cnfe) {
      System.err.println("Error loading driver: " + cnfe);
    } catch(SQLException sqle) {
        try	
        {
        // como se produjo una excepcion en el acceso a la base de datos se debe hacer el rollback	
 //        Thread.sleep (20*1000); 	
         System.err.println("antes rollback: " + sqle);
         connection.rollback();
         System.err.println("Error Se produjo una Excepcion accediendo a la base de datoas: " + sqle);
         sqle.printStackTrace();
        } 
        catch(Exception e)
        {
            //System.err.println("Error Ejecutando el rollback de la transaccion: " + e.getMessage());
            e.printStackTrace();
        }
    
    }


  }

}
