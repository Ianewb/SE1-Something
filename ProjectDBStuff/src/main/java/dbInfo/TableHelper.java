package main.java.dbInfo;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import static main.java.dbInfo.CalendarTable.createDbCalendarTable;
import static main.java.dbInfo.EventTable.createDbEventTable;
import static main.java.dbInfo.UserTable.createDbUserTable;

public class TableHelper extends DTO {
    public static boolean isTableExists(String TableName, Connection c) throws SQLException
    {
        boolean exists = false;
        if(c != null)
        {
            DatabaseMetaData dbmd = c.getMetaData();
            ResultSet rs = dbmd.getTables(null,null,TableName.toUpperCase(),null);
            if(rs.next())
            {
                exists = true;
                System.out.println("Table " + TableName + " Already Exists!");

            }
            else
            {
                System.out.println("Running Create Table Functions!");
            }
        }
        return exists;
    }

    public static void main(String []args)
    {
        UserDAO ugetter = new UserDAO();
        UserDTO ucreator = new UserDTO();
        EventDAO eGetter = new EventDAO();
        EventDTO eCreator = new EventDTO();
        CalendarDAO cGetter = new CalendarDAO();
        CalendarDTO cCreator = new CalendarDTO();
        try
        {
            if(!isTableExists("USERS",getDBConnection()))
            {
                createDbUserTable();
            }
            if(!isTableExists("EVENTS",getDBConnection()))
            {
                createDbEventTable();
            }
            if(!isTableExists("CALENDARS",getDBConnection()))
            {
                createDbCalendarTable();
            }
        }
        catch(SQLException e)
        {
            System.out.println("SQL Exception: " + e.getMessage());
        }

        ucreator.save(new User("Bob", "Ross@gmail.com", "12345"));
        User checker = ugetter.getUser("Ross@gmail.com");
        ugetter.count();
        ugetter.deleteUser("Ross@gmail.com");
        if(checker != null)
        {
            System.out.println("User Is: " + "\nName: " + checker.getName()
                    + "\nEmail: " + checker.getEmail()
                    + "\nPassword: " + checker.getPassword());
        }
        else
        {
            System.out.println("User does not exist, or DB failed");
        }
        ugetter.count();

        eCreator.save(new Event(checker, "2022-12-11 09:00:00","2022-12-11 11:00:00",1));
        Event checker = eGetter.getEvent("");
        eGetter.deleteEvent("Ross@gmail.com");
        if(checker != null)
        {
            System.out.println("User Is: " + "\nName: " + checker.getName()
                    + "\nEmail: " + checker.getEmail()
                    + "\nPassword: " + checker.getPassword());
        }
        else
        {
            System.out.println("User does not exist, or DB failed");
        }
    }

}
