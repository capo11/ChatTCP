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

public class Client {
	private Socket connessione;
	private BufferedReader dalServer;
	private PrintStream alServer;
	private String name;

	public Client(String name) {
		this.name = name;
		BufferedReader t = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.println("Inserire indirizzo server: (127.0.0.1) ");
			String indirizzo = t.readLine();
			connessione = new Socket(indirizzo, 1000);
			dalServer = new BufferedReader(new InputStreamReader(connessione.getInputStream()));
			alServer = new PrintStream(connessione.getOutputStream());
		}
		catch(IOException e) {
			System.out.println(e);
		}
	} // Client()

	public void conversazione() {
		String messaggio = "";
		BufferedReader t = new BufferedReader(new InputStreamReader(System.in));
		try {
			while(!messaggio.equals("/logout")) {
				messaggio = dalServer.readLine();
				System.out.println(messaggio);
				if(!messaggio.equals("/logout")) {
					messaggio = t.readLine();
					alServer.println(name+" scrive: "+messaggio);
				}
			} // while
			connessione.close();
		}
		catch(IOException e) {
			System.out.println("Conversazione interrotta");
		}
	} // conversazione()
}