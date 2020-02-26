package strikeball;
import java.io.*;
import java.net.*;
import java.util.*;

/**
 *
 * @author Dominus
 */
public class RiceviMSG implements Runnable{
    
    DatagramSocket sock;
    byte buf[];
    String received; 

    RiceviMSG(DatagramSocket s) {
        sock = s;
        buf = new byte[1024];
    }
    @Override
    public void run() {
        
        boolean flag =true;
        while (flag) {
            try {
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                sock.receive(packet);
                received = new String(packet.getData(), 0, packet.getLength());
                System.out.println(received);
                if(received == "esci"){
                    flag=false;
                }
            } catch(IOException e) {
                System.err.println(e);
            }
        }
    }  

    public String getReceived() {
        return received;
    }
    
}




