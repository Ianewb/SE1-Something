/*
 * Author: Jacob Senior
 */

package edu.baylor.ecs.csi3471.seniorjacob.CalendarProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class LoginProtocol extends Thread {
	private static final int ESTABLISHUI = 0;
	private static final int WAITING = 1;
	private static final int GETEMAIL = 2;
	private static final int GETPASSWORD = 3;
	private static final int VERIFYLOGIN = 4;
	private static final int VERIFIED = 5;
	
	private int status = ESTABLISHUI;
	private Socket myClient;
	private String userEmail;
	private String userPassword;
	
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
			
			//tell client the server is waiting for an input
			outputLine = processInput(null);
			sockOut.println(outputLine);
			
			//get login request from user and verify user
			while((inputLine = sockIn.readLine()) != null) {
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
			status = GETEMAIL;
		} else if (status == GETEMAIL) {
			//Get username of login attempt
			userEmail = theInput;
			theOutput = "Got a login request";
			status = GETPASSWORD;
		} else if(status == GETPASSWORD) {
			//Get password of login attempt
			userPassword = theInput;
			
			//Validate login attempt
			UserDAO udao = new UserDAO();
			if(udao.verifyUser(userEmail, userPassword)) {
				theOutput = "Login denied";
				status = VERIFIED;
			} else {
				theOutput = "Login accepted";
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
