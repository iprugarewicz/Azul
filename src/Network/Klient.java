package Network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Klient {
    public static void main(String[] args) throws UnknownHostException,
            IOException, ClassNotFoundException {
        for(int i =0; i<3;i++) {
            Socket socket = new Socket("localhost", 12300);
            System.out.println("Client connected");
            ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
            System.out.println("Ok");
            socket.close();
        }
    }

}