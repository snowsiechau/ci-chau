package game.players;

import game.Utils;
import game.bases.*;
import game.inputs.InputManager;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Vector;

/**
 * Created by SNOW on 7/11/2017.
 */
public class Player extends GameObject{

   Contraints contraints;
   InputManager inputManager;

   FrameCounter coolDownCounter;
   boolean spellDisabled;

   Vector2D velocity;

   public static Player instance;

    public Player(){
        this.velocity = new Vector2D();
        this.coolDownCounter = new FrameCounter(10);  // 17 frame  = 300 millisecond
        this.renderer = new ImageRenderer(Utils.loadAssetImage("players/straight/0.png"));
        instance = this;
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);

        move();

        castSpell();
    }

    private void castSpell() {
        if (!spellDisabled) {
            if (inputManager.xPressed) {
                PlayerSpell playerSpell = new PlayerSpell();
                playerSpell.position.set(this.position.add(0, -20));
                GameObject.add(playerSpell);
            }
            spellDisabled = true;
        }

        coolDown();
    }


    private void move() {
        this.velocity.set(0, 0);
        if (inputManager.leftPressed) this.velocity.x -= 10;
        if (inputManager.rightPressed) this.velocity.x += 10;
        if (inputManager.upPressed) this.velocity.y -= 10;
        if (inputManager.downPressed) this.velocity.y += 10;

        this.position.addUp(velocity);

        this.contraints.make(this.position);

    }

    public void coolDown() {
        if (spellDisabled) {
            boolean status = coolDownCounter.run();
            if (status) {
                spellDisabled = false;
                coolDownCounter.reset();
            }
        }
    }

   public void setContraints(Contraints contraints){
       this.contraints = contraints;
   }

   public void setInputManager(InputManager inputManager){
       this.inputManager = inputManager;
   }
}
