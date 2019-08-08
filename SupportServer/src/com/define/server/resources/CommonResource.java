package com.define.server.resources;

import java.util.ArrayList;
import java.util.List;

public class CommonResource {
	
	public static void handleException(Exception ex, String location) {
		System.out.println("Exception at :" + location + "\nDescription : \n" + ex);
	}
	
	public static List<LoggedInClient> loggedInClients;
	
	static {
		loggedInClients = new ArrayList<LoggedInClient>();
	}
}
