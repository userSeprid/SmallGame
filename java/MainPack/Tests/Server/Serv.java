package MainPack.Tests.Server;


import MainPack.HeroData;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Serv {
    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(8090);
            Socket mysoc = serverSocket.accept();

            ObjectInputStream in = new ObjectInputStream(mysoc.getInputStream());
            PrintWriter out = new PrintWriter(mysoc.getOutputStream(), true);

            out.println("Shake");

            HeroData hero = (HeroData) in.readObject();
            System.out.println(hero);

            mysoc.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
