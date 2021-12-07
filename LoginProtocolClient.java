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
    				while(!lui.isRequestedLogin()) {
    					//blocks until user submits a request to login
    				}
    				//Output email and password to server
    				out.println(lui.getEmailField().getText());
    				out.println(lui.getPasswordField().getPassword());
    			} else if(fromServer.equals("Login denied")) {
    				//set requested login to false
    				lui.setRequestedLogin(false);
    				
    				//Show error to login
    				lui.createLoginError();
    				
    				//Prompt server to redo protocol
    				out.println("Broke protocol");
    			} else if(fromServer.equals("Login accepted")) {
    				lui.dispose();
    				CalendarUI cui = new CalendarUI();
    				while(!cui.logout) {
    					//block until user wants to logout
    				}
    				out.println("restart");
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
