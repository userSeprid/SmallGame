package MainPack.ClientPart;

import MainPack.BattleMode;
import MainPack.HeroData;
import MainPack.Profession;
import MainPack.RaceType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.util.logging.Logger;


public class GameFrameV2 extends JFrame{

    private JPanel controlPanel;

    private JPanel mainMenu;
    private JButton startGameButton;
    private JLabel initialPicture;

    private JPanel heroCreationMenu;
    private JPanel raceChoose;
    private JPanel classChoose;
    private JRadioButton human;
    private JRadioButton orc;
    private JRadioButton dwarf;
    private JRadioButton elf;
    private JRadioButton fairy;
    private JRadioButton dampir;
    private JRadioButton paladin;
    private JRadioButton berseker;
    private JPanel nameChooser;
    private JTextField nameField;
    private JButton creationFinishButton;
    private JLabel nameLabel;

    private JPanel gameGeneralMenu;
    private JRadioButton attackPosition;
    private JRadioButton defencePosition;
    private JButton rushIntoBattleButton;
    private JButton openAMapButton;
    private JButton characterWindowButton;
    private JTextArea conditionStats;
    private JLabel conditionName;
    private JPanel informationPanel;
    private JPanel statePanel;
    private JPanel actionsPanel;
    private JButton restAndHealButton;

    private JPanel battlePhaseMenu;
    private JButton defendButton;
    private JButton attackButton;
    private JTextArea monsterStats;
    private JLabel heroNameAndHp;
    private JLabel monsterNameAndHp;
    private JPanel battleInformation;
    private JPanel battleActions;

    private JPanel mapMenu;
    private JButton closeMapButton;
    private JPanel imageHolder;

    private CardLayout cardLO;

    private SocketMaker mySocket;
    private BufferedReader in;
    private ObjectOutputStream out;
    private Logger log;

    private HeroData heroData;
    private BattleMode heroMode;





    GameFrameV2()
    {
        log = Logger.getGlobal();
        logging("In head of constructor");

        JFrame mainFrame = new JFrame("Game Client");
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.add(controlPanel);
        mainFrame.setSize(400, 300);
        mainFrame.setVisible(true);

        setTitle("Beta Game");

        cardLO = new CardLayout();
        controlPanel.setLayout(cardLO);
        controlPanel.add(mainMenu, "main");
        controlPanel.add(heroCreationMenu, "hero");
        controlPanel.add(gameGeneralMenu, "gameGeneral");
        controlPanel.add(battlePhaseMenu, "battle");
        controlPanel.add(mapMenu, "map");
        cardLO.first(controlPanel);

        logging("Control panel now have " + controlPanel.getLayout() + " layout");


        startGameButton.addActionListener(e -> {
            cardLO.show(controlPanel, "hero");
            heroCreationPanel();
        });

        logging("In tail of constructor");
    }


    /**
     * This method represent a heroCreationMenu panel
     * Here socket and streams are initialized.
     * Different data for heroFactory also collected here
     */
    private void heroCreationPanel()
    {
        try {
            mySocket = SocketMaker.getInstance();
            in = new BufferedReader(new InputStreamReader(mySocket.getDataSocket().getInputStream()));
            out =  new ObjectOutputStream(mySocket.getDataSocket().getOutputStream());

        } catch (IOException e) {e.printStackTrace();}


        logging("In head of heroCreationPanel");

        heroData = new HeroData();
        heroData.setRace(RaceType.Human);
        heroData.setAClass(Profession.Paladin);
        heroData.setName("Adventurer");

        logging("Object heroData was created");

        RaceListener raceListener = new RaceListener();
        AClassListener classListener = new AClassListener();
        human.addActionListener(raceListener);
        orc.addActionListener(raceListener);
        dwarf.addActionListener(raceListener);
        elf.addActionListener(raceListener);
        fairy.addActionListener(raceListener);
        dampir.addActionListener(classListener);
        paladin.addActionListener(classListener);
        berseker.addActionListener(classListener);


        logging("Listeners for JRadioButtons was added");

        creationFinishButton.addActionListener(e -> {
            if (nameField.getText() != null){heroData.setName(nameField.getText());}

                logging("In head of creationFinishButton listener");
                /**
                 * Here client send collected data and wait for income key-word for "setRestPhase()" method invocation
                 */

            try {
                out.writeObject(heroData);

                boolean flag = true;
                while (flag) {

                    String s = in.readLine();
                    logging(s);

                    if (s.equalsIgnoreCase("restPhase")){
                        flag = false;
                    }
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }

                logging("In tail of creationFinishButton listener");
                setRestPhase();


        });

    }


    /**
     * This method represent a gameGeneralMenu panel.
     * Here all collected data must be send to server by proper streams
     * Before sending stream must send "key-word" first
     */
    private void setRestPhase()
    {
        logging("In head of setRestPhase");
        cardLO.show(controlPanel, "gameGeneral");


        String keyWordForBattleButton = "battleButton";

        try {
            setStats();

            heroMode = BattleMode.attackPosition;
            ABattleModeListener modeListener = new ABattleModeListener();
            attackPosition.addActionListener(modeListener);
            defencePosition.addActionListener(modeListener);

            rushIntoBattleButton.addActionListener(e -> {
            try {
                out.writeObject(keyWordForBattleButton);
                out.writeObject(heroMode);
                boolean flag = true;
                while (flag)
                {
                    String s = in.readLine();
                    logging(s);
                    if (s.equalsIgnoreCase("battlePhase")){

                        flag = false;
                    }
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
                setBattlePhase();
                cardLO.show(controlPanel, "battle");
        });

        } catch (IOException e) {e.printStackTrace();}


        logging("In tail of setRestPhase");
    }


    /**
     * Here method receive key-word that define the behavior of method
     */
    private void setBattlePhase()
    {
        logging("In head of setBattlePhase");

        // cardLO.show(controlPanel, "battle"); replaced to 224 line
        AButtonsListener buttonsListener = new AButtonsListener();
        attackButton.addActionListener(buttonsListener);
        defendButton.addActionListener(buttonsListener);

        try {
            logging("In head of try cycle");

             String s = in.readLine();
             logging(s);
             if (s.equalsIgnoreCase("status"))
             {
                 setComboInfo();
                 logging(monsterStats.getText());
             }
        } catch (IOException e) {e.printStackTrace();}
        finally {logging("In tail of setBattlePhase");}

    }



    private void logging(String message)
    {
        log.info(message);
    }


    private class RaceListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getActionCommand().equalsIgnoreCase("Human")){heroData.setRace(RaceType.Human);}
            else if (e.getActionCommand().equalsIgnoreCase("Orc")){heroData.setRace(RaceType.Orc);}
            else if (e.getActionCommand().equalsIgnoreCase("Dwarf")){heroData.setRace(RaceType.Dwarf);}
            else if (e.getActionCommand().equalsIgnoreCase("Elf")){heroData.setRace(RaceType.Elf);}
            else if (e.getActionCommand().equalsIgnoreCase("Fairy")){heroData.setRace(RaceType.Fairy);}
        }
    }


    private class AClassListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getActionCommand().equalsIgnoreCase("Dampir")){heroData.setAClass(Profession.Dampir);}
            else if (e.getActionCommand().equalsIgnoreCase("Paladin")){heroData.setAClass(Profession.Paladin);}
            else if (e.getActionCommand().equalsIgnoreCase("Berserk")){heroData.setAClass(Profession.Berserk);}
        }
    }

    private class ABattleModeListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equalsIgnoreCase("Attack position")){heroMode = BattleMode.attackPosition;}
            else if (e.getActionCommand().equalsIgnoreCase("Defence position")){heroMode = BattleMode.defencePosition;}
        }
    }

    private class AButtonsListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            String s = e.getActionCommand();
            try {
                logging("In head of try cycle of listener body");

                out.writeObject(s);
                logging(s);

                String s1 = in.readLine();

                if (s1.equalsIgnoreCase("status"))
                {
                    setComboInfo();
                    logging(monsterStats.getText());
                }else {
                    refreshBattlePanel(s1);
                }



            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }
    }

    private void refreshBattlePanel(String data)
    {
        if (data.equalsIgnoreCase("restPhase")){

            setRestPhase();
        }
        else if (data.equalsIgnoreCase("heroDie")){

            cardLO.show(controlPanel, "main");
        }
    }

    private void setStats() throws IOException {
        conditionStats.setText(in.readLine());
        conditionStats.append("\n" + in.readLine());
        conditionStats.append("\n" + in.readLine());
    }

    private void setComboInfo() throws IOException
    {
        monsterStats.setText(in.readLine());
        monsterStats.append("\n" + in.readLine());
        monsterStats.append("\n" + in.readLine());
        monsterStats.append("\n" + in.readLine());

    }
}
