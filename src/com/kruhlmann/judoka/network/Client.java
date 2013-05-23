package com.kruhlmann.judoka.network;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.kruhlmann.judoka.menu.Connecting;

public class Client implements Runnable{
	private Socket socket;
	private OutputStream output;
	private ObjectOutputStream objectOut;
	private ObjectInputStream objectIn;
	private InputStream input;
	
	public boolean connection;
	
	private String host;
	private int port;
	
	public Client(String host, int port){
		this.host = host;
		this.port = port;
		try {
			System.out.println("[INFO] CONNECTING TO " + host + ":" + port);
			Connecting.console("Connecting to to " + host + ":" + port, 0);
			socket 		= new Socket(host, port);
			System.out.println("[INFO] CONNECTED TO " + host);
			Connecting.console("Connected to host!", 0);
			connection 	= true;
			//createPlayer2();
			output 		= socket.getOutputStream();
			input  		= socket.getInputStream();
			objectOut 	= new ObjectOutputStream(output);
			objectIn 	= new ObjectInputStream(input);
		} catch (Exception e) {
			System.out.println("[Error] Connection failed to " + host + ":" + port);
			Connecting.console(e.toString(), 1);
		}
		new Thread(this, "Client").start();
	}

	public void run() {
		System.out.println("s");
	}
}
