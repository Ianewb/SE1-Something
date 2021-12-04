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

public class UserDAO extends DTO{
    public UserDAO(){};

    @DisplayName("Should Print Results")
    @ParameterizedTest(name = "{index} => emp = {0}")
    @MethodSource("objectList")
    public User getUser(String email){
        User returning = null;
        Connection dbConnection = null;
        Statement statement = null;
        String getSQL = null;
        String getSQLDat = "SELECT * FROM Users WHERE EMAIL = " + email;
        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            System.out.println(getSQLDat);
            // execute the SQL stetement
            ResultSet rs = statement.executeQuery(getSQLDat);
            System.out.println("Got SQL Data");
            if (rs.next() == false) {
                System.out.println("Email does not exist.");
            }
            else {
                returning.username = rs.getString("NAME");
                returning.email = rs.getString("EMAIL");
                returning.password = rs.getString("PASSWORD");
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
                Arguments.of(new CalendarApp(new User("Bob Ross", "BobRoss@baylor.edu", "deezusNut5"), "Bob Ross Calendar")),
                Arguments.of(new CalendarApp(new User("Bob Ross", "BobRoss@baylor.edu", "deezusNut5"), "Bob Ross Calendar")),
                Arguments.of(new CalendarApp(new User("Bob Ross", "BobSauss@baylor.edu", "deezusNut5"), "Bob Ross Calendar")),
                Arguments.of(new CalendarApp(new User("Rob Boss", "RobBoss@baylor.edu", "NeezusDut5"), "Rob Soss Calendar"))
        );
    }
}
