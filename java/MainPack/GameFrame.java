package MainPack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;


class GameFrame extends JFrame{

    GameFrame()
    {
        logger = Logger.getLogger("Condition");
        logger.setLevel(Level.INFO);

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension dim = kit.getScreenSize();
        setBounds(dim.width /4 ,dim.height / 4 ,dim.width / 2, dim.height / 2);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(true);



        cardLayout = new CardLayout();
        controlPanel = new JPanel();
        controlPanel.setLayout(cardLayout);
        add(controlPanel);



        mainMenuPanel = new JPanel();
        JButton gameStarter = new JButton("Start Journey");
        gameStarter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                heroChooser();
                cardLayout.next(controlPanel);
            }
        });
        mainMenuPanel.add(gameStarter);
        controlPanel.add(mainMenuPanel, "MainMenu");


logger.info("Constructor- no errors");

    }

    private void heroChooser()
    {
        logger.info("heroChooser start");

        mainLogic = new GameLogic();

        chooserPanel = new JPanel(new BorderLayout());

        nameField = new JTextField("Input a name");
        chooserPanel.add(nameField, BorderLayout.SOUTH);

        raceChoosePanel = new JPanel();
        JRadioButton human = new JRadioButton("Human", true);
        human.addActionListener(new RaceListener());
        raceChoosePanel.add(human);
        JRadioButton orc = new JRadioButton("Orc");
        orc.addActionListener(new RaceListener());
        raceChoosePanel.add(orc);
        JRadioButton dwarf = new JRadioButton("Dwarf");
        dwarf.addActionListener(new RaceListener());
        raceChoosePanel.add(dwarf);
        JRadioButton elf = new JRadioButton("Elf");
        elf.addActionListener(new RaceListener());
        raceChoosePanel.add(elf);
        JRadioButton fairy = new JRadioButton("Fairy");
        fairy.addActionListener(new RaceListener());
        raceChoosePanel.add(fairy);

        ButtonGroup raceButtons = new ButtonGroup();
        raceButtons.add(human);
        raceButtons.add(orc);
        raceButtons.add(dwarf);
        raceButtons.add(elf);
        raceButtons.add(fairy);

        chooserPanel.add(raceChoosePanel, BorderLayout.CENTER);





        heroChoosePanel = new JPanel();
        JButton firstHero = new JButton("Select Paladin");
        firstHero.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (nameField.getText() != null || userType != null)
                {
                    mainLogic.makeAHero(1, nameField.getText(), userType);
                    setRestPhase();
                    cardLayout.next(controlPanel);
                    logger.info("Pal selected");
                }
            }
        });
        heroChoosePanel.add(firstHero);

        JButton secondHero = new JButton("Select Dwarf");
        secondHero.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (nameField.getText() != null)
                {
                    mainLogic.makeAHero(1, nameField.getText(), userType);
                    setRestPhase();
                    cardLayout.next(controlPanel);
                    logger.info("Dwarf selected");
                }
            }
        });
        heroChoosePanel.add(secondHero);

        JButton thirdHero = new JButton("Select Barbarian");
        thirdHero.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (nameField.getText() != null)
                {
                    mainLogic.makeAHero(3, nameField.getText(), userType);
                    setRestPhase();
                    cardLayout.next(controlPanel);
                    logger.info("Barb selected");
                }
            }
        });
        heroChoosePanel.add(thirdHero);

        chooserPanel.add(heroChoosePanel, BorderLayout.NORTH);


        controlPanel.add(chooserPanel, "HeroChoseMenu");

        logger.info("Chooser end");
    }


    private void setRestPhase()
    {
        logger.info("setRestPhase starts");


        restPhase = new JPanel();
        BorderLayout BorderLO = new BorderLayout();
        restPhase.setLayout(BorderLO);
        JPanel radioButtonsPanel = new JPanel();

        ButtonGroup group = new ButtonGroup();
        JRadioButton attackPosition = new JRadioButton("Attack Position", true);
        attackPosition.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainLogic.getPlayer().transferStats();
                mainLogic.getPlayer().attackPosition();
                logger.info("Change hero position to "+mainLogic.getPlayer().getFlag());
            }
        });
        group.add(attackPosition);
        radioButtonsPanel.add(attackPosition);
        JRadioButton defencePosition = new JRadioButton("Defence Position", false);
        defencePosition.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainLogic.getPlayer().transferStats();
                mainLogic.getPlayer().defencePosition();
                logger.info("Change hero position to "+mainLogic.getPlayer().getFlag());
            }
        });
        group.add(defencePosition);
        radioButtonsPanel.add(defencePosition);

        restPhase.add(BorderLayout.NORTH, radioButtonsPanel);


        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(2,2,20,20));
        statsButton = new JButton("Check your stats");
        statsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Hero playerClone = mainLogic.getPlayer();
                logger.info("Str:"+playerClone.getStrength()+" Con:"+playerClone.getConstitution()+
                        " Agi: "+playerClone.getAgility()+" Atk:"+playerClone.getAttack()+" Def:"+
                        playerClone.getDefence()+" CurHp:"+playerClone.getHp()+" MaxHp"+playerClone.getMaxHp());
            }
        });
        buttonsPanel.add(statsButton);

        mapButton = new JButton("Open a map");
        mapButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        buttonsPanel.add(mapButton);

        inventoryButton = new JButton("Inspect an inventory");
        inventoryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        buttonsPanel.add(inventoryButton);

        advanceButton = new JButton("Advance");
        advanceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setAttackPhase();
                cardLayout.show(controlPanel, "AttackPhase");
            }
        });
        buttonsPanel.add(advanceButton);



        restPhase.add(BorderLayout.SOUTH, buttonsPanel);
        controlPanel.add(restPhase, "RestPhase");

        logger.info("RestPhase end");
    }

    private void setAttackPhase()
    {
        logger.info("AttackPhase start");

        JPanel attackPhase = new JPanel();
        BorderLayout BorderLO = new BorderLayout();
        attackPhase.setLayout(BorderLO);
        mainLogic.makeAMonster();


        JPanel battleInformationPanel= new JPanel();
        battleInformationPanel.setLayout(new GridLayout(5, 1, 10, 10));

        playerNameAndHp = new JLabel(mainLogic.getPlayer().getName()+" have " + mainLogic.getPlayer().getHp() + " hp.");
        battleInformationPanel.add(playerNameAndHp);
        enemyName = new JLabel(mainLogic.getEnemy().getName()+" have:");
        battleInformationPanel.add(enemyName);
        enemyHp = new JLabel(mainLogic.getEnemy().getHp()+" hp.");
        battleInformationPanel.add(enemyHp);
        enemyDefence = new JLabel(mainLogic.getEnemy().getDefence()+" defence points");
        battleInformationPanel.add(enemyDefence);
        enemyAttack = new JLabel(mainLogic.getEnemy().getAttack()+ " attack points");
        battleInformationPanel.add(enemyAttack);

        attackPhase.add(BorderLayout.CENTER, battleInformationPanel);


        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1, 10,10));


        attackButton = new JButton("Attack an enemy");
        attackButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainLogic.getEnemy().takeDamageFromAttack(mainLogic.getPlayer().getAttack());
                enemyHp.setText(mainLogic.getEnemy().getHp()+" hp.");
                logger.info("Attack, enemyHp:"+mainLogic.getEnemy().getHp());
                if (mainLogic.getEnemy().isDead())
                {
                    cardLayout.show(controlPanel, "RestPhase");

                }
                mainLogic.getPlayer().takeDamageFromAttack(mainLogic.getEnemy().getAttack());
                playerNameAndHp.setText(mainLogic.getPlayer().getName()+" have " + mainLogic.getPlayer().getHp() + " hp.");
                logger.info("Attack, playerHp:"+mainLogic.getPlayer().getHp());
                if (mainLogic.getPlayer().isDead()){cardLayout.show(controlPanel, "MainMenu");}


            }
        });
        buttonPanel.add(attackButton);
        defenceButton = new JButton("Defend from enemy strikes");
        defenceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainLogic.getPlayer().takeDamageWhileDefending(mainLogic.getEnemy().getAttack());
                playerNameAndHp.setText(mainLogic.getPlayer().getName()+" have " + mainLogic.getPlayer().getHp() + " hp.");
                logger.info("Attack, playerHp:"+mainLogic.getPlayer().getHp());
                if (mainLogic.getPlayer().isDead()){cardLayout.show(controlPanel, "MainMenu");}
            }
        });
        buttonPanel.add(defenceButton);

        attackPhase.add(BorderLayout.EAST, buttonPanel);





        repaint();


        controlPanel.add(attackPhase, "AttackPhase");

        logger.info("AttackPhase end");
    }

    private class RaceListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if (e.getActionCommand().equals("Human")){userType = RaceType.Human;}
            else if (e.getActionCommand().equals("Orc")){userType = RaceType.Orc;}
            else if (e.getActionCommand().equals("Dwarf")){userType = RaceType.Dwarf;}
            else if (e.getActionCommand().equals("Elf")){userType = RaceType.Elf;}
            else if (e.getActionCommand().equals("Fairy")){userType = RaceType.Fairy;}
        }
    }



    private CardLayout cardLayout;


    private JPanel controlPanel;
    private JPanel mainMenuPanel;
    private JPanel chooserPanel;
    private JPanel heroChoosePanel;
    private JPanel raceChoosePanel;
    private JPanel restPhase;

    private GameLogic mainLogic;

    private JButton statsButton;
    private JButton inventoryButton;
    private JButton mapButton;
    private JButton advanceButton;
    private JButton attackButton;
    private JButton defenceButton;

    private JLabel playerNameAndHp;
    private JLabel enemyName;
    private JLabel enemyHp;
    private JLabel enemyDefence;
    private JLabel enemyAttack;

    private JTextField nameField;

    private Logger logger;

    private RaceType userType;


}
