package LauchApp1;
import java.sql.*; // Import all JDBC classes

/// statement.execute(sql) -> Common Method for Both Select & Non-Select Operations
public class common {
   public static void main(String[] args) throws ClassNotFoundException, SQLException {

   // Load & Register the Driver
       Class.forName("com.mysql.cj.jdbc.Driver");

   // Establish the Connection
        String url = "jdbc:mysql://localhost:3306/JDBClearning";
        String user = "root";
        String password = "U$@ID12345";
   Connection connection = DriverManager.getConnection(url, user, password);

   // Creating Statement
       Statement statement = connection.createStatement();

   // Write Query
      String sql = "SELECT * FROM studentinfo"; //

   // Execute Query
   // Will Return -> select = true ; non-select = false
      boolean status = statement.execute(sql);

   // Process the Result
      if(status)
      {
       // select
         ResultSet rs = statement.getResultSet();
         while (rs.next())
         {
             System.out.println(
                     rs.getInt(1) + " " +
                             rs.getString(2) + " " +
                             rs.getInt(3) + " " +
                             rs.getString(4));
         }
      }
      else
      {
       // non-select
        int rowsAffected =  statement.getUpdateCount();
          if (rowsAffected == 0) {
              System.out.println("Operation Failed!");
          }
          else
          {
              System.out.println("Operation Sucessfull");
          }

      }
// Close Connections
       connection.close();
       statement.close();
}
}
