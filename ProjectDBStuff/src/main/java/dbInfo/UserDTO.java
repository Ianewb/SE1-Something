package main.java.dbInfo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.stream.Stream;

public class UserDTO extends DTO{

    @DisplayName("Should Print Results")
    @ParameterizedTest(name = "{index} => emp = {0}")
    @MethodSource("objectList")
    public void save(User emp){
        Connection dbConnection = null;
        Statement statement = null;
        String saveSQL = null;
        String getSQLDat = "SELECT NAME FROM UserS WHERE email='" + emp.getEmail() +"'" ;
        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            System.out.println(getSQLDat);
            // execute the SQL stetement
            ResultSet rs = statement.executeQuery(getSQLDat);
            System.out.println("Got SQL Data");
            if (!rs.next()) {
                saveSQL = "INSERT INTO UserS(NAME, EMAIL, PASSWORD, ACCESS)" +
                        " values('" +
                        emp.getName() + "', '" +
                        emp.getEmail() + "', '" +
                        emp.getPassword() + "', " +
                        emp.getAccess() + ")";
            } else {
                saveSQL = "UPDATE UserS SET " +
                        "NAME = '" + emp.getName() +
                        "', PASSWORD = '" + emp.getPassword() +
                        "', ACCESS = " + emp.getAccess() +
                        " WHERE EMAIL ='" + emp.getEmail() + "'";
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
                Arguments.of(new User("Bob Ross", "BobRoss@baylor.edu", "deezusNut5",0)),
                Arguments.of(new User("Bob Ross", "BobRoss@baylor.edu", "deezusNuts",0)),
                Arguments.of(new User("Bob Ross", "BobSauss@baylor.edu", "deezusNut5",1)),
                Arguments.of(new User("Rob Boss", "RobBoss@baylor.edu", "NeezusDut5",2))
        );
    }
}
