package main.java.dbInfo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.stream.Stream;

public class UserDAO extends DTO{
    public UserDAO(){};

    //returns the user with the input email
    @DisplayName("Should Print Results")
    @ParameterizedTest(name = "{index} => emp = {0}")
    @MethodSource("objectList")
    public User getUser(String email){
        User returning = null;
        Connection dbConnection;
        Statement statement;
        String getSQLDat = "SELECT * FROM Users WHERE EMAIL = '" + email + "'";
        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            System.out.println(getSQLDat);
            // execute the SQL stetement
            ResultSet rs = statement.executeQuery(getSQLDat);
            System.out.println("Got SQL Data");
            if (!rs.next()) {
                System.out.println("Email does not exist.");
            }
            else {
                returning = new User(rs.getString("NAME"),
                                        rs.getString("EMAIL"),
                                        rs.getString("PASSWORD"),
                                        rs.getInt("ACCESS"));
            }

            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return returning;
    }
    @SuppressWarnings("unused")
    private static Stream<Arguments> objectList() {
        return Stream.of(
                Arguments.of("BobRoss@baylor.edu"),
                Arguments.of("BobSauss@baylor.edu"),
                Arguments.of("RobBoss@baylor.edu")
        );
    }

    //Checks if the user exists in the database
    @DisplayName("Should Print Results")
    @ParameterizedTest(name = "{index} => email = {0}, password = {1}")
    @MethodSource("emailPass")
    public boolean verifyUser(String email, String password){
        boolean exists = false;
        Connection dbConnection = null;
        Statement statement = null;
        String getSQLDat = "SELECT * FROM Users WHERE EMAIL = '" + email + "' AND PASSWORD = '" + password + "'";
        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            System.out.println(getSQLDat);
            // execute the SQL stetement
            ResultSet rs = statement.executeQuery(getSQLDat);
            System.out.println("Got SQL Data");
            if (!rs.next()) {
                System.out.println("User does not exist.");
            }
            else {
                exists = true;
            }

            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return exists;
    }
    @SuppressWarnings("unused")
    private static Stream<Arguments> emailPass() {
        return Stream.of(
                Arguments.of("BobRoss@baylor.edu", "12345"),
                Arguments.of("BobSauss@baylor.edu", "12543"),
                Arguments.of("RobBoss@baylor.edu", "54321")
        );
    }

    //Checks if the user exists in the database
    @DisplayName("Should Print Results")
    @ParameterizedTest(name = "{index} => email = {0}, password = {1}")
    @MethodSource("emailPass")
    public boolean verifyUserExist(String email){
        boolean exists = false;
        Connection dbConnection = null;
        Statement statement = null;
        String getSQLDat = "SELECT * FROM Users WHERE EMAIL = '" + email + "'";
        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            System.out.println(getSQLDat);
            // execute the SQL stetement
            ResultSet rs = statement.executeQuery(getSQLDat);
            System.out.println("Got SQL Data");
            if (!rs.next()) {
                System.out.println("User does not exist.");
            }
            else {
                exists = true;
            }

            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return exists;
    }
    @SuppressWarnings("unused")
    private static Stream<Arguments> email() {
        return Stream.of(
                Arguments.of("BobRoss@baylor.edu"),
                Arguments.of("BobSauss@baylor.edu"),
                Arguments.of("RobBoss@baylor.edu")
        );
    }

    //delete user with the string
    @DisplayName("Should delete User with matching ID")
    @ParameterizedTest(name = "{index} => id = {0}")
    @MethodSource("emails")
    public void deleteUser(String email){
        Connection dbConnection = null;
        Statement statement = null;
        String deleteUserSQL = "DELETE FROM UserS WHERE EMAIL= '" + email + "'";
        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            System.out.println(deleteUserSQL);
            // execute delete SQL stetement
            statement.execute(deleteUserSQL);
            System.out.println("Record is deleted from DBUser table!");
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @SuppressWarnings("unused")
    private static Stream<Arguments> emails() {
        return Stream.of(
                Arguments.of("Bobross@baylor.edu"),
                Arguments.of("BobRoss@baylor.edu")
        );
    }

    //get all
    @Test
    public void findAll()
    {
        Connection dbConnection = null;
        Statement statement = null;
        String selectUserSQL = "SELECT * " +
                "FROM UserS " +
                "ORDER BY NAME";
        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            System.out.println(selectUserSQL);
            // execute select SQL stetement
            ResultSet rs = statement.executeQuery(selectUserSQL);
            if (!rs.next()) {
                System.out.println("ResultSet is empty in Java");
            } else {
                System.out.println("\tNAME\tEMAIL\t");
                do {
                    String Name = rs.getString("NAME");
                    String Email = rs.getString("EMAIL");
                    String Pass = rs.getString("password");
                    System.out.print(Name + "\t");
                    System.out.println(Email + "\t");
                    System.out.println("User password: " + Pass);
                } while (rs.next());
            }
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //Counts number of users in the Database
    @Test
    public void count()
    {
        Connection dbConnection = null;
        Statement statement = null;
        String selectUserSQL = "SELECT COUNT(DISTINCT EMAIL)" +
                "FROM UserS ";
        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            System.out.println(selectUserSQL);
            // execute select SQL stetement
            ResultSet rs = statement.executeQuery(selectUserSQL);
            if (!rs.next()) {
                System.out.println("ResultSet is empty in Java");
            } else {
                System.out.println("User Count: " + rs.getInt(1) );
            }
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @DisplayName("Should Print User with Conditions")
    @ParameterizedTest(name = "{index} => emp = {0}")
    @MethodSource("empsNam")
    public void find(String s){
        Connection dbConnection = null;
        Statement statement = null;
        String selectUserSQL = "SELECT NAME, EMAIL, PASSWORD " +
                "FROM UserS " +
                "WHERE " + s + " " +
                "ORDER BY NAME";

        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            System.out.println(selectUserSQL);
            // execute select SQL stetement
            ResultSet rs = statement.executeQuery(selectUserSQL);
            if (!rs.next() ) {
                System.out.println("ResultSet is empty in Java");
            } else {
                System.out.println("NAME\tEMAIL\t");
                do {
                    String Name = rs.getString("NAME");
                    String Email = rs.getString("EMAIL");
                    //String Pass = rs.getString("password");
                    System.out.print(Name + "\t");
                    System.out.println(Email + "\t");
                    //System.out.println("User password: " + Pass);
                } while (rs.next());
            }
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    @SuppressWarnings("unused")
    private static Stream<Arguments> usersNam() {
        return Stream.of(
                Arguments.of("EMAIL LIKE 'R%'"),
                Arguments.of("EMAIL LIKE 'B%'")
        );
    }


}
