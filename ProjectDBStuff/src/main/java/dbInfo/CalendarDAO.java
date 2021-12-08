package main.java.dbInfo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.stream.Stream;

public class CalendarDAO extends DTO {
    private EventDAO eventStore = new EventDAO();
    private UserDAO userStore = new UserDAO();
    DateFormat si= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);

    //base constructor
    public CalendarDAO(){}

    //get calendar
    @DisplayName("Should Print Results")
    @ParameterizedTest(name = "{index} => emp = {0}")
    @MethodSource("objectList")
    public CalendarApp getCalendar(int id, Connection dbConnection) throws SQLException{
        CalendarApp returning = new CalendarApp();
        Statement statement;
        String getSQL;
        String getSQLDat = "SELECT * FROM CalendarS WHERE ID=" + id;
            statement = dbConnection.createStatement();
            System.out.println(getSQLDat);
            // execute the SQL stetement
            ResultSet rs = statement.executeQuery(getSQLDat);
            System.out.println("Got SQL Data");
            if (!rs.next()) {
                System.out.println("Calendar does not exist.");
            }
            else {
                returning.setID(rs.getInt("ID")) ;
                returning.setName(rs.getString("NAME"));
                getSQL = "SELECT * FROM Events WHERE " +
                        "C_ID = " + returning.getID();
                //execute statement
                rs = statement.executeQuery(getSQL);
                if(rs.next()) {
                    do {//adds users to the calendar
                        Event event = eventStore.getEvent(rs.getInt("C_ID"),
                                                            si.format(rs.getTimestamp("DATE_START")),
                                                            dbConnection);
                        returning.addEvent(event);
                        for(String u: event.getUsers())
                        {
                            User user = userStore.getUser(u,dbConnection);
                            if(!returning.getUsers().contains(user.getEmail()))
                            {
                                returning.addUser(u);
                                switch(user.getAccess())
                                {
                                    case 3:
                                        returning.setHost(u);
                                    case 2:
                                        returning.setAdmin(u);
                                    case 1:
                                        returning.setMember(u);
                                    default:
                                        returning.setViewer(u);
                                }
                            }
                        }
                    } while (rs.next());
                }
            }

            if (statement != null) {
                statement.close();
            }
        return returning;
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
