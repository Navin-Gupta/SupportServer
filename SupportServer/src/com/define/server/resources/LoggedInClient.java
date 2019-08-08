package com.define.server.resources;

import java.net.Socket;

public class LoggedInClient {

	private int id;
	private Socket client;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Socket getClient() {
		return client;
	}
	public void setClient(Socket client) {
		this.client = client;
	}
	
	
}
