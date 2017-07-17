package game;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import game.bases.Contraints;
import game.players.Player;
import game.players.PlayerSpell;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static sun.misc.PostVMInitHook.run;

/**
 * Created by SNOW on 7/9/2017.
 */
public class GameWindow extends JFrame{

    BufferedImage background;

    Player player = new Player();
    ArrayList<PlayerSpell> playerSpells = new ArrayList<>();

    private int backGroudY;

    private BufferedImage backBufferImage;
    private Graphics2D backBufferGraphic2D;

    private boolean rightPressed, leftPressed, upPressed, downPressed, xPressed;

    private int delaySpells;

    public GameWindow(){

        setUpWindow();
        loadImage();

        Contraints contraints = new Contraints(0, this.getHeight(), 0, background.getWidth());

        player.position.set(background.getWidth() / 2, this.getHeight() - 50);
        player.setContraints(contraints);

        backBufferImage = new BufferedImage(this.getWidth(),this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        backBufferGraphic2D = (Graphics2D) backBufferImage.getGraphics();

        backGroudY = this.getHeight() - background.getHeight();

        setupInput();
        this.setVisible(true);
    }

    private void setupInput() {
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()){
                    case KeyEvent.VK_RIGHT:
                        rightPressed = true;
                        break;

                    case KeyEvent.VK_LEFT:
                        leftPressed = true;
                        break;

                    case KeyEvent.VK_UP:
                        upPressed = true;
                        break;

                    case KeyEvent.VK_DOWN:
                        downPressed = true;
                        break;
                    case  KeyEvent.VK_X:
                        xPressed = true;
                        break;

                        default:
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()){
                    case KeyEvent.VK_RIGHT:
                        rightPressed = false;
                        break;

                    case KeyEvent.VK_LEFT:
                        leftPressed = false;
                        break;

                    case KeyEvent.VK_UP:
                        upPressed = false;
                        break;

                    case KeyEvent.VK_DOWN:
                        downPressed = false;
                        break;

                    case KeyEvent.VK_X:
                        xPressed = false;
                        break;

                    default:
                            break;
                }
            }
        });

    }

    long lastUpdateTime;

    public void loop(){
        while (true){

            long  currentTime = System.currentTimeMillis();

            if (currentTime - lastUpdateTime > 17){
                lastUpdateTime = currentTime;
                render();
                run();
            }


        }
    }

    private void run(){
        if (backGroudY <= 0 ) {
            backGroudY ++;
        }

        int dx = 0;
        int dy = 0;

        if (rightPressed){
            dx += 5;
        }
        if (leftPressed){
            dx -= 5;
        }
        if (upPressed){
            dy -= 5;
        }
        if (downPressed){
            dy += 5;
        }

        if (xPressed) {
               player.castSpell(playerSpells);
            }

        player.coolDown();

        player.move(dx, dy);

        for (PlayerSpell playerSpell : playerSpells
                    ) {
                playerSpell.move();
            }


    }

    private void render() {
        backBufferGraphic2D.setColor(Color.BLACK);
        backBufferGraphic2D.fillRect(0,0,this.getWidth(),this.getHeight());

        backBufferGraphic2D.drawImage(background, 0, backGroudY, null);
        player.render(backBufferGraphic2D);
        for (PlayerSpell playerspell : playerSpells
             ) {
            playerspell.render(backBufferGraphic2D);
        }

        Graphics2D g2d = (Graphics2D)this.getGraphics();
        g2d.drawImage(backBufferImage,0,0,null);
    }

    private void loadImage() {

            background = Utils.loadAssetImage("background/0.png");
    }

    private void setUpWindow() {
        this.setSize(800, 800);

        this.setResizable(false);
        this.setTitle("Touhou - remade by SNOW");

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

}
