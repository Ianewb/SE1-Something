/*
 * Author: Jacob Senior
 */

package edu.baylor.ecs.csi3471.seniorjacob.CalendarProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Calendar;

public class LoginProtocolClient extends Thread{
	
	public static void main(String[] args) throws IOException {
    	if(args.length != 0) {
    		System.err.println(
    				"Usage: java CalendarListProtocolClient");
    		System.exit(1);
    	}
    	String hostName = "localhost";
    	int portNumber = 8080;
    	
    	//establish connection with the server
    	try (Socket kkSocket = new Socket(hostName, portNumber);
    			PrintWriter out = new PrintWriter(kkSocket.getOutputStream(),true);
    			BufferedReader in = new BufferedReader(
    					new InputStreamReader(kkSocket.getInputStream()));
    			){
    		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
    		String fromServer, fromUser;
    		
			LoginUI lui = null;
			CalendarUI cui = null;
			
			//process input from the server
    		while((fromServer = in.readLine()) != null){
    			System.out.println("Server: " + fromServer);
    			if(fromServer.equals("Bye.")) { break; }
    			
    			fromUser = "";
    			if(fromServer.equals("Establishing login UI")) {
    				//Create and verify creation of UI
    				lui = new LoginUI();
    				out.println("UI created");
    			}
    			if(fromServer.equals("Waiting for login request.")) {
    				boolean hasLogged,hasCreatedNew,isCreating;
    				lui.setRequestedLogin(false);
    				lui.setClickedCreatingNew(false);
    				lui.setCreatingNew(false);
    				lui.setClosedCreatingNew(false);
    				while(!((hasLogged = lui.isRequestedLogin()) || (hasCreatedNew = lui.isClickedCreatingNew()))) {
    					
    				}
					//blocks until user submits a request to login or create new acc
					if(hasLogged) {
						out.println("user has logged");
						//Output email and password to server
	    				out.println(lui.getEmailField().getText());
	    				out.println(lui.getPasswordField().getPassword());
	    				out.println("log me in");
					} else {
						//wait until user clicks create new account
						while(!( (isCreating = lui.isCreatingNew()) || lui.isClosedCreatingNew() ));
						if(lui.isClosedCreatingNew()) {
							out.println("user closed creation");
						} else {
    						out.println("user has created");
    						//Output email username and password to server
    						out.println(lui.getNewEmailText().getText());
    						out.println(lui.getNewUserText().getText());
    						out.println(lui.getNewPassText().getPassword());
    						out.println("create my account");
						}
					}    				
    			} else if(fromServer.equals("Login denied")) {
    				//set requested login to false
    				lui.setRequestedLogin(false);
    				
    				//Show error to login
    				lui.createLoginError();
    				
    				//Prompt server to redo protocol
    				out.println("Broke protocol");
    			} else if(fromServer.equals("Login accepted")) {
    				//Create protocol for calendar
    				lui.dispose();
    			} else if(fromServer.equals("new user email already exists")) {
    				//set request to create new user to false
    				lui.setCreatingNew(false);
    				
    				//Show error to create new account
    				lui.createExistingUserError();
    				
    				//dispose dialog
    				lui.getDialog().dispose();
    				
    				out.println("user already existed");
    			} else if(fromServer.equals("new user added")) {
    				
    				lui.setClickedCreatingNew(false);
    				
    				//display success message
    				lui.createSuccessMsg();
    				
    				//dispose dialog
    				lui.getDialog().dispose();
    				out.println("thanks for adding user");
    				
    			} else if(fromServer.equals("closed creation")) {
    				
    				lui.setClosedCreatingNew(false);
    				out.println("back to login");
    			}
    		}
    		//close connection
    		kkSocket.close();
    	} catch (UnknownHostException e) {
    		System.err.println("Dont know about host " + hostName);
    		System.exit(1);
    	} catch (IOException e) {
    		System.err.println("Countlnt get I/O for the connection to + " + hostName);
    		System.exit(1);
    	}
    }
}
