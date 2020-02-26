package strikeball;
import java.io.*;
import java.net.*;
import java.util.*;

/**
 *
 * @author Dominus
 */

public class InviaMSG implements Runnable {
    public final static int PORT = 3500;
    private DatagramSocket sock;
    private String hostname;
    
    InviaMSG (DatagramSocket s, String h) {
        sock = s;
        hostname = h;
    }
    
    private void sendMessage(String s) throws Exception {
        byte buf[] = s.getBytes();
        InetAddress address = InetAddress.getByName(hostname);
        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, PORT);
        sock.send(packet);
    }
    
    @Override
    public void run() {
        boolean connected = false;
        do {
            try {
                sendMessage("Accesso avvenuto con successo");
                connected = true;
            } catch (Exception e) {
                System.err.println("ERRORE, nessuno sa perché/n");
            }
        } while (!connected);
        
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                while (!in.ready()) {
                    Thread.sleep(100);
                }
                sendMessage(in.readLine());
            } catch(Exception e) {
                System.err.println(e);
            }
        }
    }
}
