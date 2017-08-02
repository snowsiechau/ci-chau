package game;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import game.bases.Contraints;
import game.bases.GameObject;
import game.enemies.Enemy;
import game.enemies.EnemySpawner;
import game.inputs.InputManager;
import game.players.Player;
import game.players.PlayerSpell;
import game.scenes.*;

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

    private BufferedImage backBufferImage;
    private Graphics2D backBufferGraphic2D;
    InputManager inputManager = InputManager.instance;

    Scene startupScene;

    public GameWindow(){
        setUpWindow();
        setupBackBuffer();
        setupInput();
        setupStartupScene();
        this.setVisible(true);
    }

    private void setupStartupScene() {
        startupScene = new MenuScene();
        startupScene.init();
    }

    private void setupBackBuffer() {
        backBufferImage = new BufferedImage(this.getWidth(),this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        backBufferGraphic2D = (Graphics2D) backBufferImage.getGraphics();
    }

    private void setupInput() {
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyPressed(KeyEvent e) {
                inputManager.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                inputManager.keyReleased(e);
            }
        });
    }

    long lastUpdateTime;

    public void loop(){
        while (true){

            long  currentTime = System.currentTimeMillis();

            if (currentTime - lastUpdateTime > 17) {
                lastUpdateTime = currentTime;
                render();
                run();
            }
        }
    }

    private void run(){
        GameObject.runAll();
        GameObject.runAllAction();
        // change scene
        SceneManager.instance.changeSceneIfNeeded();
    }

    private void render() {
        backBufferGraphic2D.setColor(Color.BLACK);
        backBufferGraphic2D.fillRect(0,0,this.getWidth(),this.getHeight());

        GameObject.renderAll(backBufferGraphic2D);

        Graphics2D g2d = (Graphics2D)this.getGraphics();
        g2d.drawImage(backBufferImage,0,0,null);
    }


    private void setUpWindow() {
        this.setSize(Setting.WINDOW_WIDTH, Setting.WINDOW_HEIGHT);

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
