package edu.baylor.ecs.csi3471.seniorjacob.CalendarProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CalendarTable {
    private static final String DB_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String DB_CONNECTION = "jdbc:derby:ex1connect;";
    private static final String DB_Calendar = "";
    private static final String DB_PASSWORD = "";

    public static void createDbCalendarTable() throws SQLException {
        Connection dbConnection = null;
        Statement statement = null;
        String createTableSQL = "CREATE TABLE CALENDARs("
                + " ID BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 0, INCREMENT BY 1),"
                + " NAME VARCHAR(255) NOT NULL,"
                + " DATE TIMESTAMP,"
                + " PRIMARY KEY (ID)"
                + ")";
        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            System.out.println(createTableSQL);
            // execute the SQL stetement
            statement.execute(createTableSQL);
            System.out.println("Table \"Calendars\" is created!");
        } catch (SQLException e) {
            System.out.println(e.getMessage() + " " + e.getSQLState());
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
    }

    public static void dropDbCalendarTable() throws SQLException {
        Connection dbConnection = null;
        Statement statement = null;
        String createTableSQL = "DROP TABLE Calendars";
        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            System.out.println(createTableSQL);
            // execute the SQL stetement
            statement.execute(createTableSQL);
            System.out.println("Table \"Calendars\" is dropped!");
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
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_Calendar, DB_PASSWORD);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }

    public static void main(String[] argv) {
        try {
            dropDbCalendarTable();
            createDbCalendarTable();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
