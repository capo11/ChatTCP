package chattcp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andrea Capobianco
 */
public class Server {

    /**
     * @param args the command line arguments
     */
        // porta del server maggiore di 1024 
        int port=2000;
        //oggetto ServerSocket necessario per accettare richieste dal client
        ServerSocket sSocket = null;
        //oggetto da usare per realizzare la connessione TCP
        Socket connection;
        
        
        void connetti(){
        
            try{
                
                // il server si mette in ascolto sulla porta voluta
                sSocket = new ServerSocket(port);
                System.out.println("In attesa di connessioni!");
                //si è stabilita la connessione
                connection = sSocket.accept();
                System.out.println("Connessione stabilita!");
                System.out.println("Socket server: " + connection.getLocalSocketAddress());
                System.out.println("Socket client: " + connection.getRemoteSocketAddress());  
            }
               catch(IOException e){
                   System.err.println("Errore di I/O nell'avvio!");
            }
      }
        
        void messaggi(){
            try{
             //lettura messaggio
                DataInputStream inputStream = new DataInputStream(connection.getInputStream());
                String message = inputStream.readUTF();
                System.out.println("Messaggio: " + message);
                
                
                
            //scrittura risposta
            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
            InputStreamReader reader = new InputStreamReader (System.in);
            BufferedReader br = new BufferedReader(reader);
            System.out.println("Scrivi il messaggio da inviare in risposta:");
            message = br.readLine();
            outputStream.writeUTF(message);
            outputStream.flush();
            
            } catch(IOException ex){
                System.err.println("Errore di I/O nella lettura!");
            }
        }
        void chiudi(){
             //chiusura della connessione con il client
            try {
                if (sSocket!=null) sSocket.close();
            } catch (IOException ex) {
                System.err.println("Errore nella chiusura della connessione!");
            }
            System.out.println("Connessione chiusa!");
        }
}