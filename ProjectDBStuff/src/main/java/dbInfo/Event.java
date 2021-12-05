package main.java.dbInfo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Event {
    List<User> userList = new ArrayList<User>();
    List<String> emails = new ArrayList<String>();
    String description, name, location;
    Date resourceStart = new Date();
    Date resourceEnd = new Date();
    int calendarID;

    DateFormat si= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);

    Event(User u, String s, String e, int cID)
    {
        userList.add(u);
        try {
            resourceStart = si.parse(s);
            resourceEnd = si.parse(e);
        } catch (ParseException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        calendarID = cID;
    }

    public boolean checkConflicts(Event e)
    {
        boolean conflicts = false;
        //checks to make sure given event starts after, and ends before this event
        if(e.resourceStart.after(this.resourceEnd) && this.resourceStart.after(e.resourceEnd))
        {
            conflicts = true;
        }
        return conflicts;
    }

    //adds a user's email to the event's mailing list
    public void addUser(String u){
        emails.add(u);
    }

    //getters for Event list of user emails, name, location, description, start and end dates, and CalendarID
    public List<String> getUsers()
    {
        return emails;
    }
    public String getName()
    {
        return this.name;
    }
    public String getLocation()
    {
        return this.location;
    }
    public String getDescription()
    {
        return this.description;
    }
    public String getStart()
    {
        return si.format(this.resourceStart);
    }
    public String getEnd()
    {
        return si.format(this.resourceEnd);
    }
    public int getCalID()
    {
        return this.calendarID;
    }

    //Setters for Event name, location, description, start and end dates, and CalendarID
    public void setName(String s)
    {
        this.description = s;
    }
    public void setLocation(String s)
    {
        this.description = s;
    }
    public void setDescription(String s)
    {
        this.description = s;
    }
    public void setStart(String s)
    {
        try {
            resourceStart = si.parse(s);
        } catch (ParseException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }
    public void setEnd(String s)
    {
        try {
            resourceEnd = si.parse(s);
        } catch (ParseException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }
    public void setCalID(int i)
    {
        this.calendarID = i;
    }

}
