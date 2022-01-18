import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends Canvas {

    private int width = 200;
    private int height = 150;

    private Game game;
    private Player player;
    private GameServer gameServer;

    static JLabel output = new JLabel();

    public void setOutput(String text){
        output.setText(text);
    }

    public Window(String title, Game game){
        JFrame jframe = new JFrame(title);

        output.setBounds(0, 70, 100, 30);
        output.setText("test");



        JButton connectButton = new JButton("Connect");
        connectButton.setBounds(0, 0, 100, 30);
        connectButton.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                if(game.getSessionType() == SessionType.Server){
                                                    game.inititaliseServer();
                                                }else{
                                                    game.playerConnect();
                                                }
                                            }
                                        });

                JButton disconnectButton = new JButton("Disconnect");
        disconnectButton.setBounds(90, 0, 100, 30);
        disconnectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(game.getSessionType() == SessionType.Server){
                    gameServer.stopServer();
                }else{
                    player.playerDisconnect();
                }
            }
        });

        jframe.setPreferredSize(new Dimension(width, height));
        jframe.setMaximumSize(new Dimension(width, height));
        jframe.setMinimumSize(new Dimension(width, height));

        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setResizable(false);
        jframe.setVisible(true);
        jframe.add(output);
        jframe.add(connectButton);
        jframe.add(disconnectButton);
        jframe.add(game);
        game.start();
    }

}
