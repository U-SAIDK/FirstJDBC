package LauchApp1;

import java.sql.*; // Import all JDBC classes

/// There are Two Types of SQL Operations :- Select & Non - Select Operations(INSERT,UPDATE,DELETE)
/// Select Ops -> executeQuery method is used & Non Select OPs -> executeUpdate is used
/// Common Method for executing both kinda query -> statemeant.execute(sql);


/// Ex 1 -Non-Select Operations (INSERT,UPDATE,DELETE)
public class NonSelect {
    public static void main(String[] args) {

        try {

            // STEP 1: LOAD JDBC DRIVER (Class)
            // This loads MySQL driver class into JVM memory
            // Class -> For Loading Class  , forName = for Executing Static Block ,
            // (com.mysql.cj.jdbc.Driver) -> Address of the Driver Class
            Class.forName("com.mysql.cj.jdbc.Driver");

            // STEP 2: Establish the Connection
            String url = "jdbc:mysql://localhost:3306/JDBClearning"; // URL Format: jdbc:mysql://host:port/database_name
            String user = "root"; // Database username
            String password = "U$@ID12345"; // Database password


            // DriverManager finds MySQL driver and Validates Credentials
            // All These will be Stored inside connect (object/interface) of Connection(Main Class)
            Connection connect = DriverManager.getConnection(url, user, password);
            // Live Connection Establish



            // STEP 4: CREATE STATEMENT OBJECT
            // Statement object is used to send SQL queries to database

            Statement statement = connect.createStatement();
            // Statement are of two types :- Prepared & Simple

            // STEP 5: WRITE SQL QUERY
            String sql = "INSERT INTO studentinfo(id, sname, sage, scity) VALUES (1, 'usaid', 21, 'Pune')";


            // STEP 6: EXECUTE QUERY
            // executeUpdate() is used for non-access ops:- → INSERT, UPDATE, DELETE
            int rowAffected = statement.executeUpdate(sql);


            // STEP 7: PROCESS RESULT [Option]

            if (rowAffected == 0) {
                // No row inserted → failure
                System.out.println("Unable to Insert Data");
            } else {
                // Row inserted → success
                System.out.println("Data Inserted Successfully!");
            }


            // =========================================================
            // STEP 8: CLOSE RESOURCES
            // =========================================================
            // Always close resources to avoid memory leaks

            statement.close(); // closes SQL execution object
            connect.close();   // closes DB connection


        } catch (Exception e) {

            // =========================================================
            // ERROR HANDLING
            // =========================================================
            // If any error occurs:
            // → Driver not found
            // → Wrong password
            // → SQL error
            // → Duplicate primary key

            e.printStackTrace(); // prints full error details
        }
    }
}

/// Ex- 2 NOn Select Operations -> Deleting the Data
/// using executequery method

//public class NonSelect{
//   public static void main(String[] args) throws ClassNotFoundException, SQLException {
//
//   // Load & Register the Driver
//       Class.forName("com.mysql.cj.jdbc.Driver");
//
//   // Establish the Connection
//        String url = "jdbc:mysql://localhost:3306/JDBClearning";
//        String user = "root";
//        String password = "U$@ID12345";
//   Connection connection = DriverManager.getConnection(url, user, password);
//
//   // Creating Statement
//       Statement statement = connection.createStatement();
//
//   // Write Query
//   String sql = "DELETE FROM studentinfo WHERE id = 2";
//
//   // Execute Query
//   // executeUpdate Method is used for Non Select Ops
//   int rowAffected = statement.executeUpdate(sql);
//
//   // Process the Result
//  if (rowAffected == 0){
//      System.out.println("Failed to Delete RECORD");
//  }
//  else {
//      System.out.println("Record Deleted Sucessfully!");
//  }
//
//
//
//       // Close the Resources
//       statement.close();
//       connection.close();
//
//
//   }
//}