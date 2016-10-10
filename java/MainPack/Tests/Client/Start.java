package MainPack.Tests.Client;


import MainPack.HeroData;
import MainPack.Profession;
import MainPack.RaceType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Start {
    public static void main(String[] args) {
        HeroData data = new HeroData();

        data.setAClass(Profession.Berserk);
        data.setRace(RaceType.Fairy);
        data.setName("nmae");

        System.out.println(Profession.Berserk);

        try {
            Socket soc = new Socket("127.0.0.1",8090);

            BufferedReader in = new BufferedReader
                    (new InputStreamReader(soc.getInputStream()));
            ObjectOutputStream out = new ObjectOutputStream(soc.getOutputStream());

            System.out.println(in.readLine());

            out.writeObject(data);

            soc.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
