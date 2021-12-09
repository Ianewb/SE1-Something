package edu.baylor.ecs.csi3471.seniorjacob.CalendarProject;

import java.util.ArrayList;
import java.util.List;

public class CalendarApp {
    static int HOST = 3;
    static int ADMIN = 2;
    static int MEMBER = 1;
    static int VIEWER = 0;

    //Might need to incorporate accessLevels into the database
    List<String> emails = new ArrayList<String>();
    List<Integer> accessLvls = new ArrayList<Integer>();
    List<Event> eventsList = new ArrayList<Event>();

    String userHost;
    String name;
    int id;

    CalendarApp()
    {
        this.name = "";
        this.userHost = "";
        this.id = -1;
    }

    //constructor with host and Calendar name
    CalendarApp(String h, String n)
    {
        this.name = n;
        this.userHost = h;
        this.emails.add(h);
        this.accessLvls.add(HOST);
    }

    //books resources in the calendar
    public void bookResources(Event e)
    {
        boolean conflicted = false;
        for(Event checker: eventsList)
        {
            if(checker.checkConflicts(e))
            {
                conflicted = true;
                break;
            }
        }
        if(!conflicted)
        {
            eventsList.add(e);
        }
    }

    //add event and user
    public void addEvent(Event e)
    {
        eventsList.add(e);
    }

    public void addUser(String u){
        emails.add(u);
        if(emails.size() < 2)
        {
            accessLvls.add(HOST);
        }
        else
        {
            accessLvls.add(VIEWER);
        }
    }

    //getters for Event list of user emails, name, host email, CalendarID, and access level of specific user
    public List<String> getUsers()
    {
        return emails;
    }

    public String getName()
    {
        return this.name;
    }

    public String getHost()
    {
        return this.userHost;
    }

    public int getID()
    {
        return this.id;
    }

    public Integer getAccessLvl(String u)
    {
        int i = -1;
        if(emails.contains(u))
        {
            i = accessLvls.get(emails.indexOf(u));
        }
        return i;
    }

    //Setters for name, access levels, and ID
    public void setName(String s)
    {
        this.name = s;
    }

    public void setHost(String e)
    {
        if(emails.contains(e))
        {
            accessLvls.remove((emails.indexOf(e)));
            accessLvls.add((emails.indexOf(e)), HOST);
        }
    }

    public void setAdmin(String e)
    {
        if(emails.contains(e))
        {
            accessLvls.remove((emails.indexOf(e)));
            accessLvls.add((emails.indexOf(e)), ADMIN);
        }
    }

    public void setMember(String e)
    {
        if(emails.contains(e))
        {
            accessLvls.remove((emails.indexOf(e)));
            accessLvls.add((emails.indexOf(e)), MEMBER);
        }
    }

    public void setViewer(String e)
    {
        if(emails.contains(e))
        {
            accessLvls.remove((emails.indexOf(e)));
            accessLvls.add((emails.indexOf(e)), VIEWER);
        }
    }

    public void setID(int id)
    {
        this.id = id;
    }
}
