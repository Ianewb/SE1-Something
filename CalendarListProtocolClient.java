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

public class CalendarListProtocolClient extends Thread{

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
    		
    		//process input from the server
    		while((fromServer = in.readLine()) != null){
    			System.out.println("Server: " + fromServer);
    			if(fromServer.equals("Bye.")) { break; }
    			
    			fromUser = "";
    			if(!(fromServer.startsWith("Here $") 
    					|| fromServer.startsWith("Great you have")
    					|| fromServer.startsWith("Not a valid")
    					|| fromServer.startsWith("Invalid doll")
    					|| fromServer.startsWith("Broke pro")
    					|| fromServer.startsWith("Not an option"))) {
    				fromUser = stdIn.readLine();
    			}
    			if(fromUser != null) {
    				if(fromUser.length() > 0) {
    					System.out.println("Client: " + fromUser);
    				}
    				out.println(fromUser);
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
