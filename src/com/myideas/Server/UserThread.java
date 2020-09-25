package com.myideas.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class UserThread extends Thread {
	private Socket socket;
	private Server chatServer;
	private PrintWriter writer;
	private String userName;

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public Server getChatServer() {
		return chatServer;
	}

	public void setChatServer(Server chatServer) {
		this.chatServer = chatServer;
	}

	public PrintWriter getWriter() {
		return writer;
	}

	public void setWriter(PrintWriter writer) {
		this.writer = writer;
	}

	public UserThread(Socket socket, Server chatServer) {
		super();
		this.socket = socket;
		this.chatServer = chatServer;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public void run() {
		try {
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			
			this.writer = new PrintWriter(this.socket.getOutputStream(), true);
			getCurrentUser();
			userName = reader.readLine();
			this.chatServer.addUserName(userName);
			this.chatServer.broadcastToUser("New User connected! " + userName, this);

			String messageUser;
			do {
				messageUser = reader.readLine();
				String messageServer = "[" + userName + "]: " + messageUser;
				if (messageUser.equalsIgnoreCase("ls")) {
					getCurrentUser();
				}
				this.chatServer.broadcastToUser(messageServer, this);

				writer.flush();
			} while (!messageUser.equalsIgnoreCase("bye"));

			chatServer.broadcastToUser("The user " + userName + " was out! ", this);
			chatServer.removeUser(userName, this);
			this.socket.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public void sendMessage(String message) {
		this.writer.println(message);
	}

	public void getCurrentUser() {
		if (chatServer.existedUser()) {
			writer.println("Current User(s): " + chatServer.getUserNameSet().toString());
		} else {
			writer.println("No one on the Server!");
		}
	}
}
