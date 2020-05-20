package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    // database connection parameters
    private static final String DBNAME = "U05BRu";
    private static final String URL = "jdbc:mysql://52.206.157.109/" + DBNAME;
    private static final String USER = "U05BRu";
    private static final String PASS = "53688453939";
    private static final String DRIVER = "com.mysql.jdbc.Driver"; 
    private static Connection conn;
    
    public Database() {}
    
    // Connect to Database
    public static void connect() {
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Connected to MySQL Database");
        } catch (ClassNotFoundException e) {
            System.out.println("Class Not Found " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage()); 
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }
    
    // Close Database Connection
    public static void disconnect() {
        try {
            conn.close();
            System.out.println("Disconnected From MySQL Database");
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
    }
    
    // Returns Database Connection
    public static Connection getConnection() {
        return conn;
    }
}
