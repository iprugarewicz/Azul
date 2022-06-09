package client.network;

import client.logic.NetworkPlayer;
import client.logic.Player;

import java.io.*;
import java.net.Socket;

public class Klient {
    private Socket socket;
    private NetworkPlayer player;

    public Klient(String host) throws IOException {
        socket = new Socket(host,12301);

    }



    public void initialize() throws IOException {

        //odebranie ID i stworzenie NetworkPlayera
        int id = recieveID();
        player = new NetworkPlayer(id);

        OutputStream os = this.socket.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(os);


        //Wysłanie stworzonego NetworkPlayera do serwera
        oos.flush();
        oos.reset();
        oos.writeObject(this.player);

    }

    public int recieveID(){
        //Klient odbiera swoje id i zwraca jako wynik metody
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

    //Klient który dołącza do gry
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Klient k = new Klient("localhost");
        System.out.println("Nowy obiekt");
        k.initialize();
        System.out.println("Inicjalizacja");
        k.getPlayer().playGame(k);
    }



    public NetworkPlayer getPlayer() {
        return player;
    }

    public Socket getSocket() {
        return socket;
    }



}
