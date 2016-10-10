package MainPack.ClientPart;


import javax.swing.*;

public class ClientExecutor {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GameFrameV2 frameV2 = new GameFrameV2();
            }
        });
    }
}
