package sierra.dbi;

import java.sql.*;
import java.util.*;

class AppMain {
	
	public static void Main (String[] args) {
		executeQuery("888665555");
		System.exit(1);
	}
	
	public void executeQuery (String ssn) {
		String query = "SELECT E.Fname, E.Lname FROM EMPLOYEE E WHERE E.Ssn = ?";
		
		try {
			Connection connect = DatabaseConfigurator.getConnection();
			PreparedStatement sqlStatement = connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			
			sqlStatement.setString(1,ssn);
			
			sqlStatement.executeUpdate();
		}
		catch (ClassNotFoundException e) {
			System.out.println("Operation failed due to missing database connector.\n");
			e.printStackTrace();
			System.exit(-1);
		}
		catch (SQLException e) {
			System.out.println("Operation failed due to SQL Exception.\n");
			DatabaseConfigurator.displayException(e);
			System.exit(-1);
		}
		System.out.println("Operation successful.");
		return;
	}
	
		private class DatabaseConfigurator {

		private static final String USERNAME = "root";
		private static final String PASSWORD = "";
		private static final String CONN_STRING = "jdbc:mysql://localhost:3306/company";

		/**
		 * Establishes a connection to the database to transmit SQL syntax.
		 * @return Connection
		 * @throws SQLException
		 */
			public static Connection getConnection() throws SQLException {
			//  System.out.println("User logged in Successfully!");
			return DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
		}

		/**
		 * Displays details about errors when SQL exceptions occur.
		 * @param ex SQLException
		 */
		public static void displayException(SQLException ex){
			System.err.println("Error Message: " + ex.getMessage());
			System.err.println("Error Code: " + ex.getErrorCode());
			System.err.println("SQL Status: " + ex.getSQLState());
		}
	}
}
