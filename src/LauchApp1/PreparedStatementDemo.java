//PreparedStatement is a special JDBC interface used to execute: dynamic SQL queries, precompiled queries, secure queries
package LauchApp1;
import java.sql.*;

public class PreparedStatementDemo {
       public static void main(String[] args) throws SQLException, ClassNotFoundException {

       // Load and Register the Connection
       Class.forName("com.mysql.cj.jdbc.Driver");

       // Establish the Connection
           String url = "jdbc:mysql://localhost:3306/JDBClearning";
           String user = "root";
           String password = "U$@ID12345";

          Connection connection = DriverManager.getConnection(url, user, password);

       // STEP 3: WRITE PARAMETERIZED SQL QUERY
       // Instead of directly putting values inside SQL:Statement statement = connection.createStatement(); we write
           String sql = "SELECT * FROM studentinfo WHERE id = ?";
       // '?' is placeholder ; SQL structure remains fixed -> Only values change dynamically later


       // STEP 4: CREATE PREPAREDSTATEMENT OBJECT
       // SQL query is precompiled by DB
           PreparedStatement ps = connection.prepareStatement(sql);


       // STEP 5: SET VALUES INSIDE PLACEHOLDERS
       // setInt(parameter_position, value)
           ps.setInt(1, 1); // Here: 1 = first '?',1 = actual value

       // STEP 6: EXECUTE QUERY
           ResultSet rs = ps.executeQuery();


       // STEP 7: PROCESS RESULTSET
           while (rs.next()) {

               System.out.println(

                       rs.getInt("id") + " " +

                               rs.getString("sname") + " " +

                               rs.getInt("sage") + " " +

                               rs.getString("scity")
               );
           }
        // STEP 8: CLOSE RESOURCES
           rs.close();

           ps.close();

           connection.close();

}
}

