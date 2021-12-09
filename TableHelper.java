package edu.baylor.ecs.csi3471.seniorjacob.CalendarProject;

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

    public TableHelper()
    {
        try
        {
            if(!isTableExists("USERS",getDBConnection()))
            {
                UserTable.createDbUserTable();
            }
            if(!isTableExists("EVENTS",getDBConnection()))
            {
                EventTable.createDbEventTable();
            }
            if(!isTableExists("CALENDARS",getDBConnection()))
            {
                CalendarTable.createDbCalendarTable();
            }
        }
        catch(SQLException e)
        {
            System.out.println("SQL Exception: " + e.getMessage());
        }

    }

}
