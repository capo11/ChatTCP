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
	 Socket connessione;
	 BufferedReader in;
	 PrintStream out;
                      int port=1000;
                      public static final String GREEN = "\u001B[32m";
                      public static final String RED = "\u001B[31m";
                      
	public Client() {
		BufferedReader t = new BufferedReader(new InputStreamReader(System.in));
		try {
			connessione = new Socket("127.0.0.1",  port);
			in = new BufferedReader(new InputStreamReader(connessione.getInputStream()));
			out = new PrintStream(connessione.getOutputStream());
		}
		catch(IOException e) {
			System.out.println(e);
		}
	} // Client()

	public void conversazione() {
		String messaggio = "";
		BufferedReader t = new BufferedReader(new InputStreamReader(System.in));
		try {
			while(!messaggio.equals("end")) {
				messaggio = in.readLine();
				System.out.println(messaggio);
				if(!messaggio.equals("end")) {
					messaggio = t.readLine();
					out.println("Client:  "+messaggio);
				}
			} // while
			connessione.close();
		}
		catch(IOException e) {
			System.out.println(RED + "Conversazione interrotta");
		}
	} // conversazione()
}