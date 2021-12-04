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

public class EventDAO extends DTO{
    UserDAO userStore;

    public EventDAO(){};

    @DisplayName("Should Print Results")
    @ParameterizedTest(name = "{index} => emp = {0}")
    @MethodSource("objectList")
    public Event getEvent(int id){
        Event returning = null;
        Connection dbConnection = null;
        Statement statement = null;
        String getSQL = null;
        String getSQLDat = "SELECT * FROM Events WHERE ID = " + id;
        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            System.out.println(getSQLDat);
            // execute the SQL stetement
            ResultSet rs = statement.executeQuery(getSQLDat);
            System.out.println("Got SQL Data");
            if (rs.next() == false) {
                System.out.println("Event does not exist.");
            }
            else {
                returning.calendarID = rs.getInt("C_ID");
                returning.name = rs.getString("NAME");
                returning.description = rs.getString("DESCRIPTION");
                returning.location = rs.getString("LOCATION");
                returning.resourceStart= rs.getDate("START");
                returning.resourceEnd= rs.getDate("END");
                getSQL = "SELECT * FROM Users WHERE " +
                        "C_ID = " + returning.calendarID;

                //execute statement
                statement.execute(getSQL);

                if(rs.next()!= false) {
                    do {
                        returning.addUser(userStore.getUser(rs.getString("EMAIL")));
                    } while (rs.next());
                }
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
