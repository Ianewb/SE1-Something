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

    @DisplayName("Should Print Results")
    @ParameterizedTest(name = "{index} => emp = {0}")
    @MethodSource("objectList")
    public void save(CalendarApp emp){
        Connection dbConnection = null;
        Statement statement = null;
        String saveSQL = null;
        String getSQLDat = "SELECT name FROM CalendarS WHERE ID=" + emp.getID();
        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            System.out.println(getSQLDat);
            // execute the SQL stetement
            ResultSet rs = statement.executeQuery(getSQLDat);
            System.out.println("Got SQL Data");
            if (rs.next() == false) {
                saveSQL = "INSERT INTO CalendarS(ID, NAME)" +
                        " values(" +
                        emp.getID() + ", '" +
                        emp.getName() +"')";
            } else {
                saveSQL = "UPDATE UserS SET " +
                        "ID = " + emp.getID() +
                        ", NAME = '" + emp.getName() + "'";
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
