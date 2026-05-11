/// Take User Input and & Then Execute Query
package LauchApp1;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Userinput {
   public static void main(String[] args) {

// Step 1 - Load & Register mysql Driver
       Class.forName("com.mysql.cj.jdbc.Driver");

// STEP 2: ESTABLISH DATABASE CONNECTION
       String url = "jdbc:mysql://localhost:3306/JDBClearning";
       String user = "root";
       String password = "U$@ID12345";

// Create live connection with database
       Connection connection = DriverManager.getConnection(url, user, password);

       Scanner scanner = new Scanner(System.in);

       System.out.println("Enter Sutdent Id: ");
       int id = sc.nextInt();
       int id = sc.nextLine(); // Clears extra newline from buffer

       System.out.print("Enter Student Name: ");
       String name = sc.nextLine();

       System.out.print("Enter Student Age: ");
       int age = sc.nextInt();
       sc.nextLine();

       System.out.print("Enter Student City: ");
       String city = sc.nextLine();

       // STEP 5: WRITE PARAMETERIZED SQL QUERY
       String sql = "INSERT INTO studentinfo(id,name,age,scity) VALUES (?, ?, ?, ?)";

       // STEP 6: CREATE PREPAREDSTATEMENT OBJECT
       PreparedStatement ps  = connection.prepareStatement(sql);

       // Set Values Instead of Parameters
       ps.setInt(1, id);
       ps.setString(2, name);
       ps.setInt(3, age);
       ps.setString(4, city);

      // STEP 8: EXECUTE QUERY
      // executeUpdate() used for:
       int rowAffected = ps.executeQuery();

       // STEP 9: PROCESS RESULT
       if (rowAffected == 0) {

           System.out.println("Insertion Failed!");

       } else {

           System.out.println("Data Inserted Successfully!");
       }

       // STEP 10: CLOSE RESOURCES

       ps.close();

       connection.close();

       sc.close();
   }
}
}
