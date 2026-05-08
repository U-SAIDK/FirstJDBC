package LauchApp1;

import java.sql.*; // Import all JDBC classes

/// EX -1 SELECT Operation (Retreiving the Data)
public class Select {

    public static void main(String[] args)
            throws SQLException, ClassNotFoundException {

        // STEP 1: LOAD & REGISTER MYSQL JDBC DRIVER
        Class.forName("com.mysql.cj.jdbc.Driver");


        // STEP 2: ESTABLISH DATABASE CONNECTION
        String url = "jdbc:mysql://localhost:3306/JDBClearning";
        String user = "root";
        String password = "U$@ID12345";

        // STEP3:- Establish the Conneciton
        // DriverManager creates connection with DB
        Connection connection = DriverManager.getConnection(url, user, password);
        // Now Java application is connected to MySQL DB

        // STEP 3: CREATE STATEMENT OBJECT
        // Statement object is used to send SQL queries
        Statement statement = connection.createStatement();

        // STEP 4: WRITE SQL QUERY (Select -> Retreive Data from db)
        String sql = "SELECT * FROM studentinfo";


        // STEP 5: EXECUTE SQL QUERY
        // For Retrieve ResultSet is USed
        // ResultSet:→ Stores rows returned from database
        // executeQuery Method is Used for Select Ops
        ResultSet rs = statement.executeQuery(sql);



        // STEP 6: PROCESS RESULTSET
        // rs.next(); → Moves cursor one row forward
        // → Returns true if row exists
        // → Returns false when rows finish

        while (rs.next()) {

            // Retrieve values column-by-column

            // getInt(1)
            // → Reads 1st column value as integer

            // getString(2)
            // → Reads 2nd column value as String

            System.out.println(
                    rs.getInt(1) + " " +
                            rs.getString(2) + " " +
                            rs.getInt(3) + " " +
                            rs.getString(4)
            );
        }


        // STEP 7: CLOSE ALL RESOURCES

        rs.close();         // Close ResultSet
        statement.close();  // Close Statement

        connection.close(); // Close DB connection
    }
}





