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
	private BufferedReader in;
	private PrintStream out;

	public Server() {
		try {
			server = new ServerSocket(1000, 5);
			System.out.println("Server attivo");
			connessione = server.accept();
			in = new BufferedReader(new InputStreamReader(connessione.getInputStream()));
			out = new PrintStream(connessione.getOutputStream());
		}
		catch(IOException e) {
			System.out.println(e);
		}
	} // Server()

	public void conversazione() {
		String messaggio = "";
		BufferedReader t = new BufferedReader(new InputStreamReader(System.in));
		try {
			out.println("Connessione effettuata! Digita end per effetuare la disconnessione.");
			while(!messaggio.equals("end")) {
				messaggio = in.readLine();
				System.out.println(messaggio);
				if(!messaggio.equals("end")) {
					messaggio = t.readLine();
					out.println("Server:  "+messaggio);
				}
			} // while
		}
		catch(IOException e) {
			System.out.println("Conversazione interrotta");
		}
	} // conversazione()
}