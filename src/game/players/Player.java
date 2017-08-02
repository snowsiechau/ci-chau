package game.players;

import game.Utils;
import game.bases.*;
import game.bases.renderers.ImageRenderer;
import game.inputs.InputManager;

/**
 * Created by SNOW on 7/11/2017.
 */
public class Player extends GameObject{

   Contraints contraints;
   InputManager inputManager;

   FrameCounter coolDownCounter;
   boolean spellDisabled;

   Vector2D velocity;
   private PlayerAnimator playerAnimator;

   public static Player instance;


    public Player(){
        this.velocity = new Vector2D();
        this.coolDownCounter = new FrameCounter(10);  // 17 frame  = 300 millisecond
        playerAnimator = new PlayerAnimator();
        this.renderer = playerAnimator;
        instance = this;

    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        move();
        animate();
        castSpell();
    }

    private void animate() {
        playerAnimator.run(this);
    }

    private void castSpell() {
        if (!spellDisabled) {
            if (inputManager.xPressed) {
                PlayerSpell playerSpell = GameObjectPool.recycle(PlayerSpell.class);
                playerSpell.position.set(this.position.add(0, -20));
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
