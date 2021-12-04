package main.java.dbInfo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class EventDTO extends DTO{

    @DisplayName("Should Print Results")
    @ParameterizedTest(name = "{index} => emp = {0}")
    @MethodSource("objectList")
    public void save(Event emp){
        List<User> eL = new ArrayList<User>();

        Connection dbConnection = null;
        Statement statement = null;
        String saveSQL = null;
        String getSQLDat = "SELECT * FROM Events " +
                            "WHERE DATE_START=" + emp.resourceStart + " AND DATE_END=" + emp.resourceEnd;
        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            System.out.println(getSQLDat);
            // execute the SQL stetement
            ResultSet rs = statement.executeQuery(getSQLDat);
            System.out.println("Got SQL Data");
            if (rs.next() == false) {
                saveSQL = "";
                for(String e: emp.emails)
                {
                    saveSQL += "INSERT INTO Events(C_ID,USER_EMAIL, DATE_START, DATE_END, NAME, DESCRIPTION, LOCATION)" +
                            " values(" +
                            emp.calendarID + ", " +
                            e + ", " +
                            emp.resourceStart + ", " +
                            emp.resourceEnd + ", " +
                            emp.name + ", " +
                            emp.description + ", " +
                            emp.location + ", " +")";
                }

            } else {
                saveSQL = "";
                for(String e: emp.emails)
                {
                    saveSQL += "UPDATE Events SET" +
                            " C_ID=" + emp.calendarID +
                            ", USER_EMAILS=" + e +
                            ", DATE_START=" + emp.resourceStart +
                            ", DATE_END=" + emp.resourceEnd +
                            ", NAME=" + emp.name +
                            ", DESCRIPTION=" + emp.description +
                            ", LOCATION=" + emp.location +
                            "WHERE DATE_START=" + emp.resourceStart +
                            " AND DATE_END=" + emp.resourceEnd;
                }
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
                Arguments.of(new CalendarApp(new User("Bob Ross", "BobRoss@baylor.edu", "deezusNut5"), "Bob Ross Calendar")),
                Arguments.of(new CalendarApp(new User("Bob Ross", "BobRoss@baylor.edu", "deezusNut5"), "Bob Ross Calendar")),
                Arguments.of(new CalendarApp(new User("Bob Ross", "BobSauss@baylor.edu", "deezusNut5"), "Bob Ross Calendar")),
                Arguments.of(new CalendarApp(new User("Rob Boss", "RobBoss@baylor.edu", "NeezusDut5"), "Rob Soss Calendar"))
        );
    }

}
