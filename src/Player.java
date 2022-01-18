import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Player extends GameObject{
    private ClientSideConnection clientSideConnection;
    private int playerID;
    private int otherPlayer;
    private Window window;

    public Player(float x , float y){
        super(x, y);
    }

    public void connectToServer(){
        clientSideConnection = new ClientSideConnection();
        //window.setOutput("completed client side connection");
        Window.output.setText("completed client side connection");
    }

    public void playerDisconnect(){
        clientSideConnection.disconnect();
    }

    private class ClientSideConnection{

        private Socket socket;
        private DataInputStream dataInputStream;
        private DataOutputStream dataOutputStream;

        public void disconnect() {
            try {
                socket.close();
                window.setOutput("player disconnected");
            } catch (IOException ioException){
                ioException.printStackTrace();
            }
        }

        public ClientSideConnection(){
            try{
                socket = new Socket("127.0.0.1", 8008);
                System.out.println("Trying socket");
                //window.setOutput("Player trying socket");
                Window.output.setText("player trying socket");
                dataInputStream = new DataInputStream(socket.getInputStream());
                dataOutputStream = new DataOutputStream(socket.getOutputStream());
                playerID = dataInputStream.readInt();
            } catch (IOException ioException){
                ioException.printStackTrace();
            }
        }
    }

    /*
    public static void main(String[] args){
        Player player = new Player();
        player.connectToServer();
    }

     */
}
