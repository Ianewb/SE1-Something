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

public class CalendarDAO extends DTO {
    @DisplayName("Should Print Results")
    @ParameterizedTest(name = "{index} => emp = {0}")
    @MethodSource("objectList")
    public CalendarApp getCalendar(int id){
        CalendarApp returning = null;
        Connection dbConnection = null;
        Statement statement = null;
        String getSQL = null;
        String getSQLDat = "SELECT name FROM CalendarS WHERE ID=" + id;
        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            System.out.println(getSQLDat);
            // execute the SQL stetement
            ResultSet rs = statement.executeQuery(getSQLDat);
            System.out.println("Got SQL Data");
            if (rs.next() == false) {
                System.out.println("Calendar does not exist.");
            }
            else {
                returning.id = rs.getInt("ID");
                returning.name = rs.getString("NAME");
                getSQL = "SELECT * FROM Events WHERE " +
                        "C_ID = " + returning.id;
                //execute statement
                statement.execute(getSQL);
                if(rs.next()!= false) {
                    do {
                        Event populate = null;
                        populate.calendarID = returning.id;
                        populate.id = rs.getInt("ID");
                        populate.name = rs.getString("NAME");
                        populate.description = rs.getString("DESCRIPTION");
                        populate.location = rs.getString("LOCATION");
                        populate.resourceStart= rs.getDate("START");
                        populate.resourceStart= rs.getDate("END");

                        getSQL = "SELECT * FROM Users WHERE " +
                                "C_ID = " + returning.id;
                        ResultSet userSet = statement.executeQuery(getSQL);
                        while(userSet.next() != false)
                        {
                            User members = null;
                            members.username = userSet.getString("NAME");
                            members.email = userSet.getString("EMAIL");
                            populate.addUser(members);
                        }
                        returning.addEvent(populate);
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
