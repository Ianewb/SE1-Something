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
    String description = "N/A", name = "N/A", location = "N/A";
    Date resourceStart = new Date();
    Date resourceEnd = new Date();
    int calendarID = -1;

    DateFormat si= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);

    Event(){}

    Event(User u, String start, String end, int cID)
    {
        this.userList.add(u);
        this.emails.add(u.getEmail());
        try {
            this.resourceStart = si.parse(start);
            this.resourceEnd = si.parse(end);
        } catch (ParseException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        this.calendarID = cID;
    }

    Event(User u, String start, String end, int cID, String n, String des, String loc)
    {
        this.userList.add(u);
        this.emails.add(u.getEmail());
        try {
            this.resourceStart = si.parse(start);
            this.resourceEnd = si.parse(end);
        } catch (ParseException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        this.calendarID = cID;
        this.name = n;
        this.description = des;
        this.location = loc;
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
        this.emails.add(u);
    }

    //getters for Event list of user emails, name, location, description, start and end dates, formatter, and CalendarID
    public List<String> getUsers()
    {
        return this.emails;
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
    public DateFormat getFormatter(){return this.si;}
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
            this.resourceStart = si.parse(s);
        } catch (ParseException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }
    public void setEnd(String s)
    {
        try {
            this.resourceEnd = si.parse(s);
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
