package main.java.dbInfo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.sql.*;
import java.util.stream.Stream;

public class UserDTO extends DTO{
    User store;

    @DisplayName("Should Print Results")
    @ParameterizedTest(name = "{index} => emp = {0}")
    @MethodSource("objectList")
    public void save(User emp){
        Connection dbConnection = null;
        Statement statement = null;
        String saveSQL = null;
        String getSQLDat = "SELECT username FROM UserS WHERE email=" + emp.getEmail();
        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            System.out.println(getSQLDat);
            // execute the SQL stetement
            ResultSet rs = statement.executeQuery(getSQLDat);
            System.out.println("Got SQL Data");
            if (rs.next() == false) {
                saveSQL = "INSERT INTO UserS(NAME, EMAIL, PASSWORD)" +
                        " values(" +
                        emp.getName() + ", " +
                        emp.getEmail() + ", " +
                        emp.getPassword() + ")";
            } else {
                saveSQL = "UPDATE UserS SET " +
                        "USERNAME = " + emp.getName() +
                        ", PASSWORD = " + emp.getPassword() +
                        "WHERE EMAIL =" + emp.getEmail() ;
            }
            //execute statement
            statement.execute(saveSQL);
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    @SuppressWarnings("unused")
    private static Stream<Arguments> objectList() {
        return Stream.of(
                Arguments.of(new User("Bob Ross", "BobRoss@baylor.edu", "deezusNut5")),
                Arguments.of(new User("Bob Ross", "BobRoss@baylor.edu", "deezusNuts")),
                Arguments.of(new User("Bob Ross", "BobSauss@baylor.edu", "deezusNut5")),
                Arguments.of(new User("Rob Boss", "RobBoss@baylor.edu", "NeezusDut5"))
        );
    }

    //returns whether user exists in the the DB or not
    @DisplayName("Should Print User with matching email")
    @ParameterizedTest(name = "{index} => nam = {0}, pass = {1}")
    @MethodSource("primaryKey")
    public boolean checkUserValid(String nam, String pass){
        boolean found = false;
        Connection dbConnection = null;
        Statement statement = null;
        String selectUserSQL = "SELECT NAME EMAIL, PASSWORD " +
                "FROM UserS " +
                "WHERE PASSWORD=" + pass + " AND USERNAME=" + nam;
        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            System.out.println(selectUserSQL);
            // execute select SQL stetement
            ResultSet rs = statement.executeQuery(selectUserSQL);
            if (rs.next() == false) {
                found = false;
            } else {
                found = true;
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
        return found;
    }
    @SuppressWarnings("unused")
    private static Stream<Arguments> primaryKey() {
        return Stream.of(
                Arguments.of("Bob Ross", "deezNut5"),
                Arguments.of("Rob Boss", "deezNut5")
        );
    }


    @DisplayName("Should delete User with matching ID")
    @ParameterizedTest(name = "{index} => id = {0}")
    @MethodSource("emails")
    public void deleteUser(String email){
        Connection dbConnection = null;
        Statement statement = null;
        String deleteUserSQL = "DELETE FROM UserS WHERE EMAIL= " + email;
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

    @Test
    public void findAll()
    {
        Connection dbConnection = null;
        Statement statement = null;
        String selectUserSQL = "SELECT NAME, EMAIL, PASSWORD " +
                "FROM UserS " +
                "ORDER BY NAME";
        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            System.out.println(selectUserSQL);
            // execute select SQL stetement
            ResultSet rs = statement.executeQuery(selectUserSQL);
            if (rs.next() == false) {
                System.out.println("ResultSet is empty in Java");
            } else {
                System.out.println("\tNAME\tEMAIL\t");
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
            if (rs.next() == false) {
                System.out.println("ResultSet is empty in Java");
            } else {
                System.out.println("User Count: COUNT");
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
            if (rs.next() == false) {
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
                Arguments.of(new String("EMAIL LIKE 'R%'")),
                Arguments.of(new String("EMAIL LIKE 'B%'"))
        );
    }
}
