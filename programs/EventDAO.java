package edu.baylor.ecs.csi3471.seniorjacob.CalendarProject;

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
    public Event getEvent(int id, String Start){
        Event returning = new Event();
        Connection dbConnection = null;
        Statement statement = null;
        String getSQL = null;
        String getSQLDat = "SELECT * FROM Events WHERE C_ID=" + id + " AND DATE_START=TIMESTAMP('" + Start + "')" ;
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
                //Creates the Event that was gotten
                returning.setCalID(id);
                returning.setName(rs.getString("NAME"));
                returning.setDescription(rs.getString("DESCRIPTION"));
                returning.setLocation(rs.getString("LOCATION"));
                returning.setStart(si.format(rs.getTimestamp("DATE_START")));
                returning.setEnd(si.format(rs.getTimestamp("DATE_END")));
                System.out.println(rs.getString("NAME") + returning.getName() + rs.getString("DESCRIPTION")+ rs.getString("LOCATION") );
                //Creates SQl query to populate Calendar with Users
               /* getSQL = "SELECT * FROM USERS u, EVENTS e WHERE " +
                        "e.USER_EMAIL = u.EMAIL ";

                //execute statement
                ResultSet emailSet = statement.executeQuery(getSQL);

                if(emailSet.next()) {
                    do {//Adds Users to the Event
                        returning.addUser(emailSet.getString("EMAIL"));
                    } while (emailSet.next());
                }*/
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
                Arguments.of(new Event(new User("Bob Ross", "BobRoss@baylor.edu", "deezusNut5", 0),
                        "2022-12-11 09:00:00","2022-12-11 11:00:00",1)),
                Arguments.of(new Event(new User("Bob Ross", "BobRoss@baylor.edu", "deezusNut5", 0),
                        "2022-12-11 09:00:00","2022-12-11 11:00:00",1)),
                Arguments.of(new Event(new User("Bob Ross", "BobSauss@baylor.edu", "deezusNut5", 1),
                        "2022-12-11 09:00:00","2022-12-11 11:00:00",1)),
                Arguments.of(new Event(new User("Rob Boss", "RobBoss@baylor.edu", "NeezusDut5", 2),
                        "2022-12-11 09:00:00","2022-12-11 11:00:00",1))
        );
    }

    @DisplayName("Should delete User with matching ID")
    @ParameterizedTest(name = "{index} => id = {0}")
    @MethodSource("emails")
    public void deleteEvent(String start){
        Connection dbConnection = null;
        Statement statement = null;
        String deleteUserSQL = "DELETE FROM Events WHERE DATE_START= '" + start + "'";
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
}
