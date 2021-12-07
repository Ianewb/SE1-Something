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

public class EventDTO extends DTO{

    //Save Event
    @DisplayName("Should Print Results")
    @ParameterizedTest(name = "{index} => emp = {0}")
    @MethodSource("objectList")
    public void save(Event emp){
        Connection dbConnection;
        Statement statement;
        String saveSQL = "";
        String getSQLDat = "SELECT * FROM Events " +
                            "WHERE DATE_START='" + emp.getStart() + "' AND DATE_END='" + emp.getEnd() + "'";
        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            System.out.println(getSQLDat);
            // execute the SQL stetement
            ResultSet rs = statement.executeQuery(getSQLDat);
            System.out.println("Got SQL Data");
            if (!rs.next())
            {
                saveSQL += "INSERT INTO Events(C_ID,USER_EMAIL, DATE_START, DATE_END, NAME, DESCRIPTION, LOCATION)" +
                        " values";
                for(String e: emp.getUsers())
                {
                    saveSQL += "(" +
                            emp.getCalID() + ", '" +
                            e + "', '" +
                            emp.getStart() + "', '" +
                            emp.getEnd() + "', '" +
                            emp.getName()+ "', '" +
                            emp.getDescription() + "', '" +
                            emp.getLocation()+ "') ";
                }
            } else {
               for(String e: emp.getUsers())
               {
                    saveSQL += "UPDATE Events SET" +
                            " C_ID=" + emp.getCalID() +
                            ", USER_EMAILS='" + e +
                            "', NAME='" + emp.getName() +
                            "', DESCRIPTION='" + emp.getDescription() +
                            "', LOCATION='" + emp.getLocation() +
                            "' WHERE DATE_START='" + emp.getStart() + "'" +
                            " AND DATE_END='" + emp.getEnd() + "' ";
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

}
