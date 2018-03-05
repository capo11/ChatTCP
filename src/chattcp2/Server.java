/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chattcp2;

/**
 *
 * @author Andrea
 */
import java.net.*;
import java.io.*;

public class Server {
	private ServerSocket server;
	private Socket connessione;
	private BufferedReader dalClient;
	private PrintStream alClient;
	private String name;

	public Server(String name) {
		this.name = name;
		try {
			server = new ServerSocket(1000, 5);
			System.out.println("Server attivo");
			connessione = server.accept();
			dalClient = new BufferedReader(new InputStreamReader(connessione.getInputStream()));
			alClient = new PrintStream(connessione.getOutputStream());
		}
		catch(IOException e) {
			System.out.println(e);
		}
	} // Server()

	public void conversazione() {
		String messaggio = "";
		BufferedReader t = new BufferedReader(new InputStreamReader(System.in));
		try {
			alClient.println("Simple Hack Chat - Sei connesso al server! Digita /logout per effetuare la disconnessione.");
			while(!messaggio.equals("/logout")) {
				messaggio = dalClient.readLine();
				System.out.println(messaggio);
				if(!messaggio.equals("/logout")) {
					messaggio = t.readLine();
					alClient.println(name+" scrive: "+messaggio);
				}
			} // while
		}
		catch(IOException e) {
			System.out.println("Conversazione interrotta");
		}
	} // conversazione()
}