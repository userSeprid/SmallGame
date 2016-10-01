package MainPack.ClientPart;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GameFrameV2 extends JFrame{

    private JPanel controlRanel;

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
    private JRadioButton bersekerRadioButton;
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




    GameFrameV2()
    {
        setTitle("Beta Game");

        cardLO = new CardLayout();
        controlRanel.setLayout(cardLO);
        controlRanel.add(mainMenu, "main");
        controlRanel.add(heroCreationMenu, "hero");
        controlRanel.add(gameGeneralMenu, "gameGeneral");
        controlRanel.add(battlePhaseMenu, "battle");
        controlRanel.add(mapMenu, "map");
        cardLO.first(controlRanel);


        startGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLO.show(controlRanel, "hero");
            }
        });
    }

}
