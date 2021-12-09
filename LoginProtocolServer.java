/*
 * Author: Jacob Senior
 */

package edu.baylor.ecs.csi3471.seniorjacob.CalendarProject;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class LoginProtocolServer {
	
	public static void main(String[] args) {
		
		int portNumber = 8080;
		ServerSocket serverSocket = null;
		
		//Create a server
		try{
			serverSocket = new ServerSocket(portNumber);
		} catch (IOException e) {
			System.out.println("Exception caught when trying to listen on port "
					+ portNumber + " or listening for a connection");
			System.out.println(e.getMessage());
		}
		
		//server runs continuously
		while(true) {
			try {
				//create new client whenever there is a new connection to the server
				Socket clientSocket = serverSocket.accept();
				
				//start a thread for the new clients protocol
				(new LoginProtocol(clientSocket)).start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
