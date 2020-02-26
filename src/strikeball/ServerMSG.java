package strikeball;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 *
 * @author Dominus
 */
public class ServerMSG extends Thread {

    public final static int PORT = 3500;
    private final static int BUFFER = 1024;

    private DatagramSocket socket;
    private ArrayList<InetAddress> clientAddresses;
    private ArrayList<Integer> clientPorts;
    private HashSet<String> existingClients;

    public ServerMSG() throws IOException {
        socket = new DatagramSocket(PORT);
        clientAddresses = new ArrayList();
        clientPorts = new ArrayList();
        existingClients = new HashSet();
    }

    @Override
    public void run() {
        byte[] buf = new byte[BUFFER];
        while (true) {
            try {
                Arrays.fill(buf, (byte) 0);
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);

                String content = new String(buf, buf.length);

                InetAddress clientAddress = packet.getAddress();
                int clientPort = packet.getPort();

                String id = clientAddress.toString() + "," + clientPort;    //Quando vorrai modificare il nome di chi invia il messaggio interferisci qui
                if (!existingClients.contains(id)) {
                    existingClients.add(id);
                    clientPorts.add(clientPort);
                    clientAddresses.add(clientAddress);
                }

                System.out.println(id + " : " + content);
                byte[] data = (id + " : " + content).getBytes();
                for (int i = 0; i < clientAddresses.size(); i++) {
                    InetAddress cl = clientAddresses.get(i);
                    int cp = clientPorts.get(i);
                    packet = new DatagramPacket(data, data.length, cl, cp);
                    socket.send(packet);
                }
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }
}
