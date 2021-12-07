/*
 * Author: Jacob Senior
 */

package edu.baylor.ecs.csi3471.seniorjacob.CalendarProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class CalendarListProtocol extends Thread {
	private static final int WAITING = 0;
	private static final int SENTLOGIN = 1;
	
	private int status = WAITING;
	private Socket myClient;
	
	public CalendarListProtocol(Socket s) {
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
			
			//wait for user to input a login
			outputLine = processUser(null);
			sockOut.println(outputLine);
			System.out.println(outputLine);
			
			//get login request from user and verify user
			while((inputLine = sockIn.readLine()) != null) {
				outputLine = processUser(inputLine);
				sockOut.println(outputLine);
				System.out.println(outputLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String processUser(String theUser) {
		String theOutput = null;
		
		if(status == WAITING) {
			//Wait for user to attempt a login
			theOutput = "Waiting for login request.";
			status = SENTLOGIN;
		} else if (status == SENTLOGIN) {
			//TODO
			//Verify user login with database
			theOutput = "searching for user";
			status = WAITING;
		}
		
		return theOutput;
	}
	
}
