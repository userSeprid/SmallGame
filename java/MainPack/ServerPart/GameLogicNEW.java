package MainPack.ServerPart;

import MainPack.BattleMode;
import MainPack.HeroData;
import MainPack.RaceType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.logging.Logger;


class GameLogicNEW {

    private Logger log;
    private Hero yourHero;
    private Monster yourEnemy;

    private CreatureSuperFactory heroFactory;

    private ServerSocket serverSocket;
    private Socket incomeData;
    private ObjectInputStream in;
    private PrintWriter out;

    GameLogicNEW()
    {
        log = Logger.getGlobal();
        logging("In head of constructor");

        try {
            serverSocket = new ServerSocket(8981);
            incomeData = serverSocket.accept();
            in = new ObjectInputStream(incomeData.getInputStream());
            out = new PrintWriter(new OutputStreamWriter(incomeData.getOutputStream()));

            logging("Try cycle of constructor - ok");
        } catch (IOException e) {
            e.printStackTrace();
        }

        logging("In tail of constructor");
        heroCreationPanel();
    }

    private void heroCreationPanel() {
        logging("In head of heroCreationPanel");

        heroFactory = CreatureFactoryProducer.getFactory("hero");


        try {
            Object obj = in.readObject();
            logging(obj.toString());
            HeroData data = (HeroData) obj;
            logging("Received object of HeroData class. Obj has fields: " + data.toString());
            yourHero = heroFactory.getHero(data.getaClass(), data.getName(), data.getRace());


            sentData("restPhase");


            logging("Try cycle of heroCreationPanel - ok");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        logging("In tail of heroCreationPanel");
        setRestPhase();
    }


    private void setRestPhase()
    {
        logging("In head of setRestPhase");
        sentStats();

        try {
            boolean flag = true;

            /**
             * This function catch the key-words that client part send and depends on they invoke some methods
             * Also it must work till the receiving "battleButton"
            */
            while (flag)
            {
                String key = (String)in.readObject();
                if (key.equalsIgnoreCase("battleButton")) {
                    BattleMode heroMode = (BattleMode)in.readObject();
                    sentData("battlePhase");
                    yourHero.setCurrentPosition(heroMode);

                    flag = false;
                }
            }

            logging("Try cycle of setRestPhase - ok");
            setAttackPhase();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


    /**
     * Here monster object will be created and his stats will be sent to client
     * Before sending a data key-word must be sent to define a future actions(e.m. continue cycle or
     * return to "setRestPhase" method)
     */
    private void setAttackPhase()
    {
        logging("In head of setAttackPhase");
        CreatureSuperFactory monsterFactory = CreatureFactoryProducer.getFactory("monster");

        //TODO : Change monster creation
        Random rand = new Random();
        assert monsterFactory != null;
        yourEnemy = monsterFactory.getMonster(8 + rand.nextInt(3), 15 + rand.nextInt(4), 2 + rand.nextInt(2), "Bakemono", RaceType.Orc,
                BattleMode.attackPosition);

        logging("before while cycle");
        while (!yourHero.isDead() && !yourEnemy.isDead())

        {
            sentData("status");
            sentData(getStatus());
            logging("A status received");

            try {
                logging("Wait for pressedButton'string");
                String pressedButton = (String) in.readObject();

                logging("Comparing with available cases");
                if (pressedButton.equalsIgnoreCase("Attack")) {
                    yourEnemy.takeDamageFromAttack(yourHero.getAttack());
                    yourHero.takeDamageFromAttack(yourEnemy.getAttack());
                }else if (pressedButton.equalsIgnoreCase("Defend")){
                    yourEnemy.takeDamageFromAttack(yourHero.getAttack());
                    yourHero.takeDamageWhileDefending(yourEnemy.getAttack());
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
        logging("after while cycle");

        if (yourHero.isDead()){
            sentData("heroDie");
            heroCreationPanel();
        }
        else if (yourEnemy.isDead()){
            sentData("restPhase");
            setRestPhase();
        }

    }

    private String getStatus()
    {
        return yourHero.getName() + " have: " + yourHero.getHp() + " hp.\n" +
                yourEnemy.getName() + " have: " + yourEnemy.getHp() + " hp \n" +
                yourEnemy.getName() + " have: \n" +
                "Agi= " + yourEnemy.getAgility() + " Str= " + yourEnemy.getStrength() +
                " Con= "+ yourEnemy.getConstitution();
    }


//    private String getHeroCondition()
//    {
//        \n was deleted
//        return yourHero.getName() + " have:  \n" + yourHero.getStrength() + " Str, " +
//                yourHero.getAttack() + " Agi, " + yourHero.getConstitution() + " Con  \n" +
//                yourHero.getHp() + '/' + yourHero.getMaxHp() + " hp";
//    }

    private void logging(String message)
    {
        log.info(message);
    }

    private void sentData(String data)
    {
        out.println(data);
        out.flush();
    }

    private void sentStats()
    {
        out.println(yourHero.getName() + " have:  ");
        out.flush();
        out.println(yourHero.getStrength() + " Str, " + yourHero.getAttack() + " Agi, " +
                yourHero.getConstitution() + " Con  ");
        out.flush();
        out.println(yourHero.getHp() + "/" + yourHero.getMaxHp() + " hp");
        out.flush();
    }


}
