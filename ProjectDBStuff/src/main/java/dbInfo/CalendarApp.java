package main.java.dbInfo;

import java.util.ArrayList;
import java.util.List;

public class CalendarApp {
    static int HOST = 3;
    static int ADMIN = 2;
    static int MEMBER = 1;
    static int VIEWER = 0;

    List<String> emails = new ArrayList<String>();
    List<Integer> accessLvls = new ArrayList<Integer>();
    List<Event> eventsList = new ArrayList<Event>();

    String userHost;
    String name;
    int id;

    CalendarApp(String h, String n)
    {
        name = n;
        userHost = h;
        emails.add(h);
        accessLvls.add(HOST);
    }

    public Integer getAccessLvl(String u)
    {
        int i = -1;
        if(emails.indexOf(u) != -1)
        {
            i = accessLvls.get(emails.indexOf(u));
        }
        return i;
    }

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

    public void addEvent(Event e)
    {
        eventsList.add(e);
    }

    public void addUser(String u){
        emails.add(u);
    }

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

    public int getCalID()
    {
        return this.id;
    }

}
