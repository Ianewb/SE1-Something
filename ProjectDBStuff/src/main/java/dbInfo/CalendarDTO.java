package main.java.dbInfo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.sql.*;
import java.util.stream.Stream;

public class CalendarDTO extends DTO{

    @DisplayName("Should Print Results")
    @ParameterizedTest(name = "{index} => emp = {0}")
    @MethodSource("objectList")
    public void save(Calendar emp){
        Connection dbConnection = null;
        Statement statement = null;
        String saveSQL = null;
        String getSQLDat = "SELECT name FROM CalendarS WHERE ID=" + emp.id;
        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            System.out.println(getSQLDat);
            // execute the SQL stetement
            ResultSet rs = statement.executeQuery(getSQLDat);
            System.out.println("Got SQL Data");
            if (rs.next() == false) {
                saveSQL = "INSERT INTO CalendarS(ID,NAME)" +
                        " values(" +
                        emp.id + ", " +
                        emp.name + ")";
            } else {
                saveSQL = "UPDATE UserS SET " +
                        "ID = " + emp.id +
                        ", NAME = " + emp.name;
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
                Arguments.of(new Calendar(new User("Bob Ross", "BobRoss@baylor.edu", "deezusNut5"), "Bob Ross Calendar")),
                Arguments.of(new Calendar(new User("Bob Ross", "BobRoss@baylor.edu", "deezusNut5"), "Bob Ross Calendar")),
                Arguments.of(new Calendar(new User("Bob Ross", "BobSauss@baylor.edu", "deezusNut5"), "Bob Ross Calendar")),
                Arguments.of(new Calendar(new User("Rob Boss", "RobBoss@baylor.edu", "NeezusDut5"), "Rob Soss Calendar"))
        );
    }

}
