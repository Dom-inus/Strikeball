package strikeball;

import java.io.*;
import java.net.*;

/**
 *
 * @author Dominus
 */
public class Client_Side {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        int door = 3500;
        BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
        
        try{
            Socket connect;
            connect = new Socket("localhost", door);
            System.out.println("Connessione aperta");
            
            DatagramSocket dsocket = new DatagramSocket();
            RiceviMSG r = new RiceviMSG(dsocket);
            InviaMSG s = new InviaMSG(dsocket, "localhost");
            Thread rt = new Thread(r);
            Thread st = new Thread(s);
            rt.start();
            st.start();
            if("esci".equals(r.getReceived())){
                connect.close();
                System.out.println("connessione chiusa");   //da ricevi msg dovresti riuscire a passare un msg di chiusura
            }
        } catch (IOException ex) {
            System.err.println("Server non trovato sulla porta: " + door);
        }
        
        //Strikeball game = new Strikeball();
    }
    
    private void send(){
        
    }
}