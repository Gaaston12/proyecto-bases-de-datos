import java.sql.*;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class EjemploResultSetPostgres {


  public static void main(String[] args) {

    try {
      String driver = "org.postgresql.Driver";
      String url = "jdbc:postgresql://localhost:5433/prueba";
      String username = "postgres";
      String password = "root";

      // Load database driver if not already loaded.
      Class.forName(driver);
      // Establish network connection to database.
      Connection connection =
        DriverManager.getConnection(url, username, password);

/*      Statement statement = connection.createStatement();
      String query = "SELECT * FROM persona ";
      ResultSet resultSet = statement.executeQuery(query);
*/


      String query = "SELECT * FROM persona ";
      PreparedStatement statement = connection.prepareStatement(query);
//      statement.setString(1, "2");
      ResultSet resultSet = statement.executeQuery();

      
      // Send query to database and store results.



      // Print results.
      while(resultSet.next()) {
        // Quarter

        System.out.print(" DNI: " + resultSet.getString(1));
        System.out.print("; Nombre: " + resultSet.getString(2));
        System.out.print("; Email: " + resultSet.getString(3)) ;
	System.out.print("\n   ");
	System.out.print("\n   ");
   }
    } catch(ClassNotFoundException cnfe) {
      System.err.println("Error loading driver: " + cnfe);
    } catch(SQLException sqle) {
    	sqle.printStackTrace();
      System.err.println("Error connecting: " + sqle);
    }


  }

}