package edu.baylor.ecs.csi3471.seniorjacob.CalendarProject;

import java.util.ArrayList;
import java.util.List;

public class User {
    String username;
    String email;
    String password;
    int access = 0;

    List<Integer> calendarIDs = new ArrayList<Integer>();

    public User(){}

    public User(String u, String e, String p, int a){
        username = u;
        email = e;
        password = p;
        access = a;
    }

    //getters for Event list of user emails, name, location, description, start and end dates, and CalendarID
    public String getName()
    {
        return  this.username ;
    }
    public String getEmail()
    {
        return  this.email;
    }
    public String getPassword()
    {
        return this.password ;
    }
    public int getAccess()
    {
        return this.access ;
    }

    //Setters for Event name, location, description, start and end dates, and CalendarID
    public void setName(String s)
    {
        this.username = s;
    }
    public void setEmail(String s)
    {
        this.email = s;
    }
    public void setPassword(String s)
    {
        this.password = s;
    }
    public void setAccess(int a) { this.access = a;}

}
