package MainPack.ClientPart;

import java.io.IOException;
import java.net.Socket;

public class SocketMaker {
    private static SocketMaker ourInstance = new SocketMaker();

    public static SocketMaker getInstance() {
        return ourInstance;
    }

    private SocketMaker() {
        try {
            dataSocket = new Socket("127.0.0.1", 8981);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Socket getDataSocket() {
        return dataSocket;
    }

    private Socket dataSocket;
}
