package main.java.dbInfo;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        try
        {
            if(!isTableExists("USERS",getDBConnection()))
            {
                UserTable.createDbUserTable();
            }
            else
            {
                UserTable.dropDbUserTable();
                UserTable.createDbUserTable();
            }
            if(!isTableExists("EVENTS",getDBConnection()))
            {
                EventTable.createDbEventTable();
            }
            else
            {
                EventTable.dropDbEventTable();
                EventTable.createDbEventTable();
            }
            if(!isTableExists("CALENDARS",getDBConnection()))
            {
                CalendarTable.createDbCalendarTable();
            }
            else
            {
                CalendarTable.dropDbCalendarTable();
                CalendarTable.createDbCalendarTable();
            }
        }
        catch(SQLException e)
        {
            System.out.println("SQL Exception: " + e.getMessage());
        }

        UserDAO ugetter = new UserDAO();
        UserDTO ucreator = new UserDTO();
        ucreator.save(new User("Bob", "Ross@gmail.com", "12345", 0));
        User checker = ugetter.getUser("Ross@gmail.com");
        if(checker != null)
        {
            System.out.println("User Is: " + "\n\tName: " + checker.getName()
                    + "\n\tEmail: " + checker.getEmail()
                    + "\n\tPassword: " + checker.getPassword());
        }
        else
        {
            System.out.println("User does not exist, or DB failed");
        }
        ugetter.count();

        CalendarDAO cGetter = new CalendarDAO();
        CalendarDTO cCreator = new CalendarDTO();
        cCreator.save(new CalendarApp("Ross@gmail.com", "Bob Ross Calendar"));

        EventDAO eGetter = new EventDAO();
        EventDTO eCreator = new EventDTO();
        eCreator.save(new Event(checker, "2022-12-11 09:00:00","2022-12-11 11:00:00",0, "Test Event",
                "This is a test event", "Denver, CO"));
        eCreator.save(new Event(checker, "2022-12-11 10:00:00","2022-12-11 11:00:00",0, "Test Event",
                "This is a test event", "Denver, CO"));
        Event echecker = eGetter.getEvent(0, "2022-12-11 09:00:00");
        if(echecker!=null)
        {
            System.out.println("Event Is: " + "\n\tName: " + echecker.getName()
                    + "\n\tDescription: " + echecker.getDescription()
                    + "\n\tLocation: " + echecker.getLocation()
                    + "\n\tStart Date: " + echecker.getStart()
                    + "\n\tEnd Date: " + echecker.getEnd()
                    + "\n\tCalendar ID: " + echecker.getCalID());
            Event Second = eGetter.getEvent(0,"2022-12-11 10:00:00" );
            System.out.println("Event Is: " + "\n\tName: " + Second.getName()
                    + "\n\tDescription: " + Second.getDescription()
                    + "\n\tLocation: " + Second.getLocation()
                    + "\n\tStart Date: " + Second.getStart()
                    + "\n\tEnd Date: " + Second.getEnd()
                    + "\n\tCalendar ID: " + Second.getCalID());
            if(echecker.checkConflicts(Second))
            {
                System.out.println("Events are conflicting!");
            }
        }
        else
        {
            System.out.println("Event does not exist, or DB failed");
        }

        CalendarApp cChecker = cGetter.getCalendar(0);
        if(cChecker != null)
        {
            System.out.println("Calendar Is: " + "\n\tName: " + cChecker.getName()
                    + "\n\tID: " + cChecker.getID()
                    + "\n\tUsers: ");
            for(String emails: cChecker.getUsers())
            {
                System.out.println("\t\t" + emails + " ACCESS: " + cChecker.getAccessLvl(emails));
            }
        }
        else
        {
            System.out.println("User does not exist, or DB failed");
        }

        eGetter.deleteEvent("2022-12-11 09:00:00");
    }

}
