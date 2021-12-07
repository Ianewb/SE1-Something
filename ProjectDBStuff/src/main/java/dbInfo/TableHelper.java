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


    }

}
