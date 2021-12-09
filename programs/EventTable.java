package edu.baylor.ecs.csi3471.seniorjacob.CalendarProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class EventTable {
    private static final String DB_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String DB_CONNECTION = "jdbc:derby:ex1connect;";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";

    public static void createDbEventTable() throws SQLException {
        Connection dbConnection = null;
        Statement statement = null;
        String createTableSQL = "CREATE TABLE EventS("
                + " C_ID BIGINT NOT NULL,"
                + " USER_EMAIL VARCHAR(255),"
                + " DATE_START TIMESTAMP NOT NULL ,"
                + " DATE_END TIMESTAMP NOT NULL,"
                + " NAME VARCHAR(255),"
                + " DESCRIPTION VARCHAR(255),"
                + " LOCATION VARCHAR(255) NOT NULL"
                + ")";
        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            System.out.println(createTableSQL);
            // execute the SQL stetement
            statement.execute(createTableSQL);
            System.out.println("Table \"Events\" is created!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
    }

    public static void dropDbEventTable() throws SQLException {
        Connection dbConnection = null;
        Statement statement = null;
        String createTableSQL = "DROP TABLE EventS";
        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            System.out.println(createTableSQL);
            // execute the SQL stetement
            statement.execute(createTableSQL);
            System.out.println("Table \"Events\" is dropped!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
    }

    private static Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }

    public static void main(String[] argv) {
        try {
            dropDbEventTable();
            createDbEventTable();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
