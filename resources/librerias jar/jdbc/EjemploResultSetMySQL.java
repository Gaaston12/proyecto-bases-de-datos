import java.sql.*;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class EjemploResultSetMySQL {


  public static void main(String[] args) {

    try {
    //  String driver = "org.gjt.mm.mysql.Driver";
    	String driver = "com.mysql.cj.jdbc.Driver";
      String url = "jdbc:mysql://localhost:3306/prueba?serverTimezone=UTC";
      String username = "root";
      String password = "root";

      // Load database driver if not already loaded.
      Class.forName(driver);
      // Establish network connection to database.
      Connection connection =
        DriverManager.getConnection(url, username, password);

 

      String query = "SELECT * FROM persona ";
      PreparedStatement statement = connection.prepareStatement(query);
//      statement.setString(1, "2");
      ResultSet resultSet = statement.executeQuery();

      
      // Send query to database and store results.



      // Print results.
      while(resultSet.next()) 
      {
       System.out.print(" DNI: " + resultSet.getString("DNI"));
       System.out.print("; Nombre: "+resultSet.getString("nombre"));
       System.out.print("; Email: " + resultSet.getString("email")) ;
       System.out.print("\n   ");
       System.out.print("\n   ");
      } 
      
    } catch(ClassNotFoundException cnfe) {
      System.err.println("Error loading driver: " + cnfe);
      cnfe.printStackTrace();
    } catch(SQLException sqle) {
    	sqle.printStackTrace();
      System.err.println("Error connecting: " + sqle);
    } catch(Exception sqle) {
  	sqle.printStackTrace();
    System.err.println("Error connecting: " + sqle);
  }


  }

}