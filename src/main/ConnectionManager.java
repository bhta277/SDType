package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Utility class for connection management
 */
public class ConnectionManager {
	private static Connection connection = null;
	
	public static synchronized Connection getConnection() {
		if(connection!=null)
			return connection;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Error: could not load mysql driver");
		}
        try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/vidbpedia?useUnicode=yes&characterEncoding=UTF-8&" +
                                   "user=root&password=1234567");
		} catch (SQLException e) {
			System.out.println("Error: could not initialize database");
		}
        return connection;
	}
        
        public static void main(String[] args) {
            Connection conn = ConnectionManager.getConnection();
//            Statement stmt = null;
//            try {
//                stmt = conn.createStatement();
//            } catch (SQLException e) {
//                System.out.println("Error preparing insert");
//                e.printStackTrace();
//            }
//            try {
//                String createStatement = "CREATE TABLE dbpedia_properties (subject VARCHAR(1000) NOT NULL, predicate VARCHAR(1000) NOT NULL, object VARCHAR(1000) NOT NULL)";
//                stmt.execute(createStatement);
//            } catch (SQLException e) {
//                System.out.println("Error creating table");
//                e.printStackTrace();
//            }
//            System.out.println("Table created");
//            Util.printTable("dbpedia_properties");
        }
}
