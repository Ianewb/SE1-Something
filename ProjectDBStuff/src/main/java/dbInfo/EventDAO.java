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
            //Checks if sql query gets anything
            if (rs.next() == false) {
                System.out.println("Event does not exist.");
            }
            else {
                //Creates the Calendar that was gotten
                returning.setCalID(rs.getInt("C_ID"));
                returning.setName(rs.getString("NAME"));
                returning.setDescription(("DESCRIPTION"));
                returning.setLocation(rs.getString("LOCATION"));
                returning.setStart(rs.getString("START"));
                returning.setEnd(rs.getString("END"));
                //Creates SQl query to populate Calendar with Users
                getSQL = "SELECT * FROM Users WHERE " +
                        "C_ID = " + returning.calendarID;

                //execute statement
                statement.execute(getSQL);

                if(rs.next()!= false) {
                    do {//Adds Users to the Calendar
                        returning.addUser(rs.getString("EMAIL"));
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
                Arguments.of(new Event(new User("Bob Ross", "BobRoss@baylor.edu", "deezusNut5"), "2022-12-11 09:00:00","2022-12-11 11:00:00",1)),
                Arguments.of(new Event(new User("Bob Ross", "BobRoss@baylor.edu", "deezusNut5"), "2022-12-11 09:00:00","2022-12-11 11:00:00",1)),
                Arguments.of(new Event(new User("Bob Ross", "BobSauss@baylor.edu", "deezusNut5"), "2022-12-11 09:00:00","2022-12-11 11:00:00",1)),
                Arguments.of(new Event(new User("Rob Boss", "RobBoss@baylor.edu", "NeezusDut5"), "2022-12-11 09:00:00","2022-12-11 11:00:00",1))
        );
    }
}
