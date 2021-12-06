package main.java.dbInfo;

import java.util.ArrayList;
import java.util.List;

public class User {
    UserDTO toData;
    String username;
    String email;
    String password;

    List<Integer> calendarIDs = new ArrayList<Integer>();

    public User(){}

    public User(String u, String e, String p){
        username = u;
        email = e;
        password = p;
    }

    public void createNewLogin()
    {
        toData.save(this);
    }

    //getters for Event list of user emails, name, location, description, start and end dates, and CalendarID
    public String getName()
    {
        return this.username;
    }
    public String getEmail()
    {
        return this.email;
    }
    public String getPassword()
    {
        return this.password + this.getClass().hashCode();
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

}
