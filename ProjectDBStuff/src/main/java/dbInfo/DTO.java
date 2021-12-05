package main.java.dbInfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DTO {
    protected static final String DB_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    protected static final String DB_CONNECTION = "jdbc:derby:ex1connect;";
    protected static final String DB_User = "";
    protected static final String DB_PASSWORD = "";

    protected static Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_User, DB_PASSWORD);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }

}