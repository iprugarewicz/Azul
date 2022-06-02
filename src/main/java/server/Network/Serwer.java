package server.Network;

import client.logic.NetworkPlayer;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class Serwer{
    private ServerSocket ss;
    private final int port = 12300;
    private ArrayList<Socket> socketDatabase;
    private ArrayList<NetworkPlayer> playersDatabase;
    private ArrayList<InputStream> inputStreams;
    private ArrayList<OutputStream> outputStreams;
    private ArrayList<ObjectOutputStream> objectOutputStreams;
    private ArrayList<ObjectInputStream> objectInputStreams;
    private int numberOfClients;



    public Serwer(){
        socketDatabase = new ArrayList<Socket>();
        playersDatabase = new ArrayList<NetworkPlayer>();
        outputStreams = new ArrayList<OutputStream>();
        inputStreams = new ArrayList<InputStream>();
        objectOutputStreams = new ArrayList<ObjectOutputStream>();
        objectInputStreams = new ArrayList<ObjectInputStream>();
        numberOfClients = 0;
    }

    public ArrayList<NetworkPlayer> getPlayersDatabase() {
        return playersDatabase;
    }
    public ArrayList<Socket> getSocketDatabase() {
        return socketDatabase;
    }

    public void runServer() throws IOException, UnknownHostException {
        new Thread(()->{
            try {
                ss = new ServerSocket(port);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            InetAddress localHost = null;
            try {
                localHost = InetAddress.getLocalHost();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            System.out.println("localHost.getHostAddress() = " + localHost.getHostAddress());
            System.out.println("localHost.getHostName() = " + localHost.getHostName());
            System.out.println("Serwer na porcie " + port);
            int id = 1;
            while(true) {
                Socket socket = null;
                try {
                    socket = ss.accept();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    OutputStream os = socket.getOutputStream();
                    InputStream is = socket.getInputStream();
                    outputStreams.add(os);
                    inputStreams.add(is);
                    //
                    PrintWriter pw = new PrintWriter(os,true);
                    pw.println(id);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                try{
                    ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    objectInputStreams.add(ois);
                    objectOutputStreams.add(oos);
                    NetworkPlayer p = (NetworkPlayer) ois.readObject();
                    playersDatabase.add(p);
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                id++;
                this.socketDatabase.add(socket);
                System.out.println("Połączono nowego klienta.");
                this.numberOfClients++;
            }
        }).start();
    }

    public void sendToAll(Object obj){
        for (ObjectOutputStream objectOutputStream:objectOutputStreams) {
            try {
                objectOutputStream.writeObject(obj);
            }catch (IOException e) {
                System.out.println(e.getMessage());
            }

        }
    }
    public void sendToOne(Object obj, int index){
        Socket s = socketDatabase.get(index);
            try {
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(s.getOutputStream());
                objectOutputStream.writeObject(obj);
            }catch (IOException e) {
                System.out.println(e.getMessage());
            }

    }
    public ArrayList<NetworkPlayer> getPlayersList() {
        return this.playersDatabase;
    }

    public ArrayList<ObjectOutputStream> getObjectOutputStreams() {
        return objectOutputStreams;
    }

    public ArrayList<ObjectInputStream> getObjectInputStreams() {
        return objectInputStreams;
    }

    public ArrayList<InputStream> getInputStreams() {
        return inputStreams;
    }

    public ArrayList<OutputStream> getOutputStreams() {
        return outputStreams;
    }

    public static void main(String[] args) throws IOException{
        Serwer s = new Serwer();
        s.runServer();
    }


}
