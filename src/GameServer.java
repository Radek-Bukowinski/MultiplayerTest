import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {

    private ServerSocket serverSocket;
    private int numberOfPlayer;
    private Window window;

    private boolean running = false;

    private ServerSideConnection player1;
    //private ServerSideConnection player2;

    public GameServer(){
        numberOfPlayer = 0;
        try{
            serverSocket = new ServerSocket(8008);
        }catch (IOException ioException){
            ioException.printStackTrace();
        }
    }

    public void stopServer(){
        try {
            running = false;
            serverSocket.close();
            window.setOutput("server stopped");
        } catch (IOException ioException){
            ioException.printStackTrace();
        }
    }

    public void acceptConnections(){
        try{
            Socket socket = serverSocket.accept();
            //window.setOutput("accepting connections");
            Window.output.setText("accepting connetion");
            //numberOfPlayer++;
            ServerSideConnection serverSideConnection = new ServerSideConnection(socket, numberOfPlayer);

            player1 = serverSideConnection;
            Thread thread = new Thread(serverSideConnection);
            thread.start();
            /*
            while(numberOfPlayer > 2){
                Socket socket = serverSocket.accept();
                numberOfPlayer++;
                ServerSideConnection serverSideConnection = new ServerSideConnection(socket, numberOfPlayer);

                if (numberOfPlayer == 1){
                    player1 = serverSideConnection;
                }else{
                    player2 = serverSideConnection;
                }

                Thread thread = new Thread(serverSideConnection);
                thread.start();
            }
            */

        }catch (IOException ioException){
            ioException.printStackTrace();
        }
    }

    private class ServerSideConnection implements Runnable{
        private Socket socket;
        private DataInputStream dataInputStream;
        private DataOutputStream dataOutputStream;
        private int playerID;

        public ServerSideConnection(Socket inputSocket, int id){
            socket = inputSocket;
            playerID = id;

            try{
                dataInputStream = new DataInputStream(socket.getInputStream());
                dataOutputStream = new DataOutputStream(socket.getOutputStream());
            } catch (IOException ioException){
                ioException.printStackTrace();
            }
        }

        @Override
        public void run() {
            running = true;
            try{
                dataOutputStream.writeInt(playerID);
                dataOutputStream.flush();

                while (running){

                }

            } catch (IOException ioException){
                ioException.printStackTrace();
            }
        }
    }
}
