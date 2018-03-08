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
	 ServerSocket server;
	 Socket connessione;
	 BufferedReader in;
	 PrintStream out;
                      public static final String GREEN = "\u001B[32m";
                      public static final String RED = "\u001B[31m";
                      int port=1000;

	public Server() {
		try {
			server = new ServerSocket(port, 5);
			System.out.println(GREEN + "Server attivo");
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
			out.println(GREEN + "Connessione effettuata! Digita end per effetuare la disconnessione.");
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
			System.out.println(RED + "Conversazione interrotta");
		}
	} // conversazione()
}