package edu.baylor.ecs.csi3471.seniorjacob.CalendarProject;

public class CalendarProtocol {
	
	private User me;
	private UserDAO udao;
	
	public CalendarProtocol(String myEmail) {
		//Create Calendar UI
		CalendarUI cui = new CalendarUI();
		
		//Create connection to database
		TableHelper th = new TableHelper();
		
		//Create UserDAO to get my user from database
		udao = new UserDAO();
		
		//Get my user from the database
		me = udao.getUser(myEmail);
		
		System.out.println(me.getName());
	}
}
