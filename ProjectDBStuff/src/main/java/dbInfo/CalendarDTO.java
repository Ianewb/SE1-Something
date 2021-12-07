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

public class CalendarDTO extends DTO{

    //
    @DisplayName("Should Print Results")
    @ParameterizedTest(name = "{index} => emp = {0}")
    @MethodSource("objectList")
    public void save(CalendarApp emp){
        Connection dbConnection;
        Statement statement;
        String saveSQL = "";
        String getSQLDat = "SELECT name FROM CalendarS WHERE ID=" + emp.getID();
        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            System.out.println(getSQLDat);
            // execute the SQL stetement
            ResultSet rs = statement.executeQuery(getSQLDat);
            System.out.println("Got SQL Data");
            if (!rs.next()) {
                for(String uEmail: emp.getUsers())
                {
                    saveSQL += "INSERT INTO CalendarS(NAME)" +
                            " values( '" +
                            emp.getName() + "')";
                }
            } else {
                saveSQL = "UPDATE Calendars SET " +
                        "NAME = '" + emp.getName() + "' " +
                        "WHERE ID=" + emp.getID();
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
                Arguments.of(new CalendarApp("BobRoss@baylor.edu", "Bob Ross Calendar")),
                Arguments.of(new CalendarApp("BobRoss@baylor.edu", "Bob Ross Calendar")),
                Arguments.of(new CalendarApp("BobSauss@baylor.edu", "Bob Ross Calendar")),
                Arguments.of(new CalendarApp("RobBoss@baylor.edu", "Rob Soss Calendar"))
        );
    }

}
