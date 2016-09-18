package MainPack;


import javax.swing.*;

public class GameStart {

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                GameFrame frame = new GameFrame();
                frame.setVisible(true);
            }
        });


    }

}
