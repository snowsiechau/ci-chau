package game;

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

/**
 * Created by SNOW on 7/9/2017.
 */
public class GameWindow extends JFrame{

    BufferedImage background;
    BufferedImage corePlayer;
    private int playerX;


    BufferedImage backBufferImage;
    Graphics2D backBufferGraphic2D;


    public GameWindow(){

        setUpWindow();
        loadImage();


        playerX = background.getWidth() / 2;

        backBufferImage = new BufferedImage(this.getWidth(),this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        backBufferGraphic2D = (Graphics2D) backBufferImage.getGraphics();



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
                        playerX += 5;

                        break;


                    default:
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

    }

    public void run(){
        while (true){
            try {
                Thread.sleep(17);

                backBufferGraphic2D.setColor(Color.BLACK);
                backBufferGraphic2D.fillRect(0,0,this.getWidth(),this.getHeight());

                int backgroudHeight = background.getHeight();

                backBufferGraphic2D.drawImage(background, 0, this.getHeight() - backgroudHeight, null);
                backBufferGraphic2D.drawImage(corePlayer, playerX, this.getHeight() - 100, null);

                Graphics2D g2d = (Graphics2D)this.getGraphics();
                g2d.drawImage(backBufferImage,0,0,null);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadImage() {
        try {
            background = ImageIO.read(new File("assets/images/background/0.png"));
            corePlayer = ImageIO.read(new File("assets/images/players/straight/0.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setUpWindow() {
        this.setSize(800, 800);

        this.setResizable(false);
        this.setTitle("Touhou - remade by SNOW");

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //
                System.exit(0);
            }
        });
    }


}
