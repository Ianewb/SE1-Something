package main.java.dbInfo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CalendarApp {
    static int HOST = 3;
    static int ADMIN = 2;
    static int MEMBER = 1;
    static int VIEWER = 0;

    List<User> userList = new ArrayList<User>();
    List<Integer> accessLvls = new ArrayList<Integer>();
    List<Event> eventsList = new ArrayList<Event>();

    User Host;
    String name;
    int id;
    Calendar currentDate;

    CalendarApp(User h, String n)
    {
        name = n;
        Host = h;
        userList.add(Host);
        accessLvls.add(HOST);
    }

    public Integer getAccessLvl(User u)
    {
        int i = -1;
        if(userList.indexOf(u) != -1)
        {
            i = accessLvls.get(userList.indexOf(u));
        }
        return i;
    }

    public void addEvent(Event e)
    {
        eventsList.add(e);
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
}
