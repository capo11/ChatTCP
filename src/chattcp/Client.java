package chattcp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.net.ConnectException;

/**
 *
 * @author Andrea Capobianco
 */
public class Client {
    /**
     * @param args the command line arguments
     */
        //oggetto da usare per realizzare la connessione TCP
        Socket connection = null;
        //nome o IP del server
        String serverAddress = "localhost";
        //porta del server in ascolto
        int port = 2000;
        
        
        void connetti(){
            //apertura della connessione al server sulla porta specificata
            try{
                connection = new Socket(serverAddress, port);
                System.out.println("Connessione aperta");
            }
            catch(ConnectException e){
                System.err.println("Server non disponibile!");
            }
            catch(UnknownHostException e1){
                System.err.println("Errore DNS!");
            }

            catch(IOException e2){//
                System.err.println(e2);
                e2.printStackTrace();
            }

           
        }
        
        
        void invio(){
            try{
            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
            InputStreamReader reader = new InputStreamReader (System.in);
            BufferedReader br = new BufferedReader(reader);
            System.out.println("Scrivi il messaggio da inviare:");
            String message = br.readLine();
            outputStream.writeUTF(message);
            outputStream.flush();
            }
            catch(IOException ex){
                
            }
        }
        
        void chiudi(){
             //chiusura della connnessione
             try {
                if (connection!=null)
                    {
                        connection.close();
                        System.out.println("Connessione chiusa!");
                    }
                }
                catch(IOException e){
                    System.err.println("Errore nella chiusura della connessione!");
                }
            }
        
        void leggi(){
            try{
            //lettura messaggio
                DataInputStream inputStream = new DataInputStream(connection.getInputStream());
                String message = inputStream.readUTF();
                System.out.println("Messaggio: " + message);
            } catch(IOException ex){
                System.err.println("Errore I/O");
            }
        }
}
