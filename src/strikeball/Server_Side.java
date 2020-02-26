package strikeball;

import java.io.*;
import java.net.*;

/**
 *
 * @author Dominus
 */
public class Server_Side {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ServerSocket sSocket;
        int door = 3500;
        boolean open = true;
        int time = 30; //Tempo di attesa in secondi
        BufferedReader riga = new BufferedReader(new InputStreamReader(System.in));
        
        
        while(open){
            try{
                sSocket = new ServerSocket(door);
                sSocket.setSoTimeout(time*1000);
                Socket connect;
                System.out.println("In attesa della connessione...");
                Countdown cd = new Countdown(time*1000);
                cd.start();
                connect = sSocket.accept();
                System.out.println("Connessione stabilita");
                cd.setConnesso(true);
                
                ServerMSG s = new ServerMSG();
                
                s.start();
                connect.close();
                System.out.println("Connessione Chiusa");
                
            }catch(SocketTimeoutException e2){
                System.err.println("Nessun client entro il timeout");
                open=false;
            }catch(Exception e3){
                System.out.println("Errore generico");
                open=false;
            }
        }
        
    }

    //Input numero intero
    private Integer intin(String richiesta) {
        System.out.print(richiesta);
        Integer num = 0;
        String stringa;
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        try {
            stringa = buffer.readLine();
            num = Integer.valueOf(stringa);
        }
        catch (Exception e) {
            System.out.println("Errore: " + e + " nella lettura da tastiera");
            System.exit(-1);
        }
        return (num);
    }
}