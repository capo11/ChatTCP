/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chattcp;

/**
 *
 * @author Andrea
 */
public class MainClient {
     public static void main(String[] args){
        Client client = new Client();
        System.out.println("Avvio Connessione da client");
        client.connetti();
        System.out.println("Invio Messaggi da client:");
        client.invio();
        client.leggi();
        System.out.println("Chiusura connessione da client");
        client.chiudi();
     }
}
