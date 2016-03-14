package DanmakuCrawler.DouyuCrawler.Crawler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ListeningThread extends Thread {
	private SocketServer socketServer;
    private ServerSocket serverSocket;
    private volatile boolean isRunning;

    public ListeningThread(SocketServer socketServer, ServerSocket serverSocket) {
        this.socketServer = socketServer;
        this.serverSocket = serverSocket;
        isRunning = true;
    }

    @Override
    public void run() {
        while(isRunning) {
            if (serverSocket.isClosed()) {
                isRunning = false;
                break;
            }
            
            try {
                Socket socket;
                socket = serverSocket.accept();
                new ConnectionThread(socket, socketServer).start();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    
    public void stopRunning() {
        isRunning = false;
    }
}
