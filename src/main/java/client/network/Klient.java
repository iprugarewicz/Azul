package client.network;

import client.logic.NetworkPlayer;
import client.logic.Player;

import java.io.*;
import java.net.Socket;

public class Klient{
    private Socket socket;
    private NetworkPlayer player;

    public Klient() throws IOException {
        socket = new Socket("localhost",12300);
    }

    public void initialize(){
        int id = recieveID();
        player = new NetworkPlayer(id,this);
        OutputStream os = this.socket.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(this.player);

    }

    public Socket getSocket() {
        return socket;
    }

    public int recieveID(){
        try {
        OutputStream os = socket.getOutputStream();
        InputStream is = socket.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String s = br.readLine();
        System.out.println(s);
        return Integer.parseInt(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }


    public NetworkPlayer getPlayer() {
        return player;
    }

    public static void main(String[] args) throws IOException {
        Klient k = new Klient();
    }


}
