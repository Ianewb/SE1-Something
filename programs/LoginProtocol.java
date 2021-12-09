/*
 * Author: Jacob Senior
 */

package edu.baylor.ecs.csi3471.seniorjacob.CalendarProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LoginProtocol extends Thread {
	private static final int ESTABLISHUI = 0;
	private static final int WAITING = 1;
	private static final int WHICHBUTTON = 2;
	private static final int LOGINBUTTON = 3;
	private static final int GETPASS = 4;
	private static final int NEWUSERBUTTON = 5;
	private static final int NEWUSERNAME = 6;
	private static final int NEWPASSWORD = 7;
	private static final int VERIFYNEWUSER = 8;
	private static final int VERIFYLOGIN = 9;
	private static final int VERIFIED = 10;
	
	private int status = ESTABLISHUI;
	private Socket myClient;
	private String userEmail;
	private String userUsername;
	private String userPassword;
	private String loginEmail;
	private String loginPassword;
	private UserDAO udao;
	private UserDTO udto;
	
	public LoginProtocol(Socket s) {
		myClient = s;
	}
	
	public int getStatus() {
		return status;
	}
	
	public void run() {
		System.out.println("Got connection.");
		//create input and output streams for socket
		try (PrintWriter sockOut =
					new PrintWriter(myClient.getOutputStream(), true);
			BufferedReader sockIn = new BufferedReader(
					new InputStreamReader(myClient.getInputStream()))){
			String inputLine, outputLine;
			
			//Generate tables if they do not exist
			TableHelper th = new TableHelper();
			
			//Create a UserDAO and UserDTO to handle user functions
			udao = new UserDAO();
			udto = new UserDTO();
			
			//tell client the server is waiting for an input
			outputLine = processInput(null);
			sockOut.println(outputLine);
			
			//get login request from user and verify user
			while((inputLine = sockIn.readLine()) != null) {
				System.out.println("Client: " + inputLine);
				outputLine = processInput(inputLine);
				sockOut.println(outputLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String processInput(String theInput) {
		String theOutput = null;
		if(status == ESTABLISHUI) {
			
			theOutput = "Establishing login UI";
			status = WAITING;
			
		}else if(status == WAITING) {
			
			//Wait for user to attempt a login
			theOutput = "Waiting for login request.";
			status = WHICHBUTTON;
			
		} else if (status == WHICHBUTTON) {
			
			//Get which button the user presses
			if(theInput.equals("user has logged")) {
				status = LOGINBUTTON;
				theOutput = "getting login";
			} else if(theInput.equals("user has created")) {
				status = NEWUSERBUTTON;
				theOutput = "getting new user";
			} else if(theInput.equals("user closed creation")) {
				status = WAITING;
				theOutput = "closed creation";
			}
			
		} else if(status == NEWUSERBUTTON) {
			
			//Get email of the new user
			userEmail = theInput;
			status = NEWUSERNAME;
			theOutput = "got new email";
			
		} else if(status == NEWUSERNAME) {
			
			//Get username of the new user
			userUsername = theInput;
			status = NEWPASSWORD;
			theOutput = "got new username";
			
		} else if(status == NEWPASSWORD) {
			
			//Get password of the new user
			userPassword = theInput;
			status = VERIFYNEWUSER;
			theOutput = "got new password";
			
		} else if(status == VERIFYNEWUSER) {
			
			//Check if email already exists in database
			if(udao.verifyUserExist(userEmail)) {
				theOutput = "new user email already exists";
			} else {
				System.out.println("verifying e: "+userEmail+" u: "+userUsername+" p: "+userPassword);
				User u = new User(userUsername,userEmail,userPassword,3);
				udto.save(u);
				theOutput = "new user added";
			}
			status = WAITING;
			
		} else if(status == LOGINBUTTON) {
			
			//get inputted email
			theOutput = "got login email";
			status = GETPASS;
			loginEmail = theInput;
			
		} else if(status == GETPASS) {
			
			//get inputted password
			theOutput = "got login pass";
			status = VERIFYLOGIN;
			loginPassword = theInput;
			
		} else if(status == VERIFYLOGIN) {
			System.out.println("verifying l: " +loginEmail+" p: "+loginPassword);
			//Validate login attempt
			if(udao.verifyUser(loginEmail, loginPassword)) {
				theOutput = "Login accepted";
				status = VERIFIED;
				CalendarProtocol cp = new CalendarProtocol(loginEmail);
			} else {
				theOutput = "Login denied";
				status = WAITING;
			}
			
		} else if(status == VERIFIED) {
			
			if(theInput.equals("restart")) {
				theOutput = "restarting login protocol";
				status = ESTABLISHUI;
			}
			
		}
		
		return theOutput;
	}
	
}
