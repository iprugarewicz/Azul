package client.network;

import client.logic.NetworkPlayer;
import client.logic.Player;

import java.io.*;
import java.net.Socket;

public class Klient {
    private Socket socket;
    private NetworkPlayer player;

    /**
     * Klient do komunikacji z serwerem
     * @param host adres hosta
     * @throws IOException wyjątek w przypadku błędu połączenia
     */
    public Klient(String host) throws IOException {
        socket = new Socket(host,12300);

    }
    public Klient()throws IOException{
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/Settings/settings.txt"));
            String host = "localhost";
            int port = 12300;
            socket = new Socket(host,port);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    /**
     * Metoda incjalizuje klienta - łączy się z serwerem,
     * otrzymuje numer ID, na jego podstawie tworzy obiekt klasy
     * NetworkPlayer i wysyła go do serwera
     *
     * @throws IOException błąd w przpadku błędu komunikacji
     */
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

    /**
     * Odebranie numeru ID
     * @return int ID
     */
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

    /**
     * Klient który dołącza do gry (Można go uruchomić w celu uruchomienia gry sieciowej na jednym komputerze)
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Klient k = new Klient();
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
