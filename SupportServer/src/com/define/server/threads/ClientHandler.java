package com.define.server.threads;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.ResultSet;

import com.define.server.db.DbConnectionFactory;
import com.define.server.resources.CommonResource;
import com.define.server.resources.LoggedInClient;
import com.define.server.resources.RequestCodes;
import com.define.server.resources.Student;

import static com.define.server.resources.CommonResource.handleException;
public class ClientHandler extends Thread{
	
	private Socket client;
	private int id;
	private String type;
	private Student student;
	
	
	
	public ClientHandler(Socket client) {
		// TODO Auto-generated constructor stub
		this.client = client;
		this.student = null;
		this.start();
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			// make ready to recieve data request
			while(true) {
				ObjectInputStream in =  new ObjectInputStream(this.client.getInputStream());
				RequestCodes code = (RequestCodes)in.readObject();
				
				if(code == RequestCodes.LOGIN) {
					String logid = in.readObject().toString();
					String password = in.readObject().toString();
					// String type = in.readObject().toString();
					
					// validate
					String sql = "select * from login where LoginId='" + logid + "' and Password='" + password +  "'";
					DbConnectionFactory conn = new DbConnectionFactory();
					ResultSet rs =  conn.getConnData(sql);
					ObjectOutputStream out = new ObjectOutputStream(this.client.getOutputStream());
					if(rs.next()) {
						String type = rs.getString("Type");
						
						// maintaining basic details of current client in thread
						this.type = type;
						int id = rs.getInt("Id");
						
						// maintaining details of logged in client
						LoggedInClient loggedInClient = new LoggedInClient();
						loggedInClient.setId(this.id);
						loggedInClient.setClient(this.client);
						CommonResource.loggedInClients.add(loggedInClient);
						
						rs.close();
						
						out.writeObject("Success");
						out.writeObject(this.type);
						
						// for student
						if(type.equals("Student")) {
							sql = "select * from Student_profile where StudentId=" + id;
							rs = conn.getConnData(sql);
							this.student =  new Student();
							this.student.setId(id);
							this.student.setName(rs.getString("Student_Name"));
							this.student.setPassword(password);
							this.student.setAge(rs.getInt("Age"));
							this.student.setBranch(rs.getString("Branch"));
							this.student.setSemester(rs.getInt("Semester"));
							out.writeObject(this.student);
						}
						
						
						
					}else {
						out.writeObject("Failed");
					}
					
				}
				/*if(code == RequestCodes.LOGIN) {
									
				}
				if(code == RequestCodes.LOGIN) {
					
				}
				if(code == RequestCodes.LOGIN) {
					
				}
				if(code == RequestCodes.LOGIN) {
					
				}*/
			}
			
		}catch(Exception ex) {
			handleException(ex, "Inside client handler");
		}
		
	}
}
