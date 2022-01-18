import java.awt.*;

public class Game extends Canvas implements Runnable {
    /*
    two buttons
    one button for start server
    one button for player join server
    handle exception of no server or multiple server
    handle leave server
    more than 2 players? array?
    label for output for confirmation of connection
    try different sockets
     */

    private Window window;

    public SessionType getSessionType() {
        return sessionType;
    }

    public void setSessionType(SessionType sessionType) {
        this.sessionType = sessionType;
    }

    private SessionType sessionType = SessionType.Client;

    private Thread thread;
    private boolean running = false;

    private KeyInput keyInput;

    private Player player;


    public Game(){
        new Window("Multiplayer Test", this);
        this.addKeyListener(keyInput);

        /*
        if(sessionType ==  SessionType.Server){
            inititaliseServer();
        }else{
            playerConnect();
        }

         */

    }

    public void inititaliseServer(){
        GameServer gameServer = new GameServer();
        gameServer.acceptConnections();
        //window.setOutput("server initialised");
        Window.output.setText("server initialised");
        //System.out.println("server connected");
    }

    public void playerConnect(){
        player = new Player(100, 100);
        player.connectToServer();
        //window.setOutput("player connected");
        Window.output.setText("player connected");
        //System.out.println("player connected");
    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        }catch(Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void run() {
        this.requestFocus();
        running = true;
        if(!running){
            stop();
        }
    }

    public static void main(String[] args){
        new Game();
    }


}
