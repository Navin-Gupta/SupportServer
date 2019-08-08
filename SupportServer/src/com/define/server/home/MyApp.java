package com.define.server.home;

import static com.define.server.resources.CommonResource.handleException;

import java.net.ServerSocket;
import java.net.Socket;

import com.define.server.threads.ClientHandler;

public class MyApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			// create server socket
			ServerSocket server = new ServerSocket(2244);
			
			// activate and show the Server Window
			
			
			// start waiting for client request
			while(true){
				Socket client = server.accept();
				
				//initiate a thread
				new ClientHandler(client);
			}
			
			
		}catch(Exception ex) {
			handleException(ex, "Inside main app");
		}
	}

}
