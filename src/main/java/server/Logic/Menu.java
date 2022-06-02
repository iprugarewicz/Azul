package server.Logic;


import client.logic.NetworkPlayer;
import client.network.Klient;
import server.Network.Serwer;
import server.ObjectsProcessing.ObjectsSerializer;
import server.ObjectsProcessing.ReadGameStatusFromFile;

import java.io.IOException;
import java.util.Scanner;

public class Menu {
    public static void start() throws IOException, ClassNotFoundException {

        System.out.println("     _     ______   _ _     ");
        System.out.println("    / \\   |__  / | | | |    ");
        System.out.println("   / _ \\    / /| | | | |    ");
        System.out.println("  / ___ \\  / /_| |_| | |___ ");
        System.out.println(" /_/   \\_\\/____|\\___/|_____|");
        System.out.println("                            ");

        System.out.println("Witaj w grze Azul");
        boolean on = true;
        while(on) {
            System.out.println("Wybierz dzałanie:");
            System.out.println("1. Gra lokalna");
            System.out.println("2. Gra sieciowa");
            System.out.println("0. Zakończ dzałanie programu");
            Scanner sc = new Scanner(System.in);
            int a = sc.nextInt();
            if(a==1) {
                System.out.println("Wybierz tryb gry:");
                System.out.println("1. Nowa gra");
                System.out.println("2. Wczytaj grę");
                System.out.println("0. Powrót do Menu");
                int b = sc.nextInt();
                if(b==1) {
                    System.out.println("Podaj liczbę graczy");
                    int l = sc.nextInt();
                    Game game = new Game(l);
                    game.letsplay();
                }
                else if(b==2){
                    System.out.println("Wprowadź ścierzkę zapisanej gry");
                    String fn = sc.nextLine();
                    Game game2= ReadGameStatusFromFile.readGameStatus(ObjectsSerializer.deserializeGameStatus(fn));
                    game2.letsplay();
                }
                else if(b==0){}
                else System.out.println("Niepoprawna wartość");
            }
            else if(a==2) {
                System.out.println("Wybierz tryb gry:");
                System.out.println("1. Hostowanie rozgrywki");
                System.out.println("2. Dołączenie do rozgrywki");
                System.out.println("0. Powrót do Menu");
                int b = sc.nextInt();
                if(b==1){
                    Serwer serwer= new Serwer();
                    serwer.runServer();
                    Klient k = new Klient();
                    k.initialize();
                    boolean waitingroom = true;
                    while(waitingroom){
                        System.out.println("Aktualnie jest " + serwer.getPlayersList().size() + "graczy");
                        System.out.println("Czy cheesz już zacząć?");
                        Scanner sc1 = new Scanner(System.in);
                        String ans = sc1.nextLine();
                        if(ans.equals("tak")){break;}
                    }
                    NetworkGame networkGame = new NetworkGame(serwer.getPlayersList().size(),serwer);
                    new Thread(()->{
                        try {
                            networkGame.letsplay();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        } catch (ClassNotFoundException e) {
                            //throw new RuntimeException(e);
                        }
                    }).start();
                    k.getPlayer().playGame(k);
                }
                else if (b==2){}
                else if(b==0){}
                else System.out.println("Niepoprawna wartość");
            }
            else {
                System.out.println("Do zobaczenia!");
                on = false;
            }


        }
    }
}
