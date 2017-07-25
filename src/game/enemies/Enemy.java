package game.enemies;

import game.Utils;
import game.bases.*;
import game.bases.physics.BoxCollider;
import game.bases.physics.PhysicBody;
import game.bases.renderers.Animation;
import game.bases.renderers.ImageRenderer;
import game.players.Player;

/**
 * Created by SNOW on 7/23/2017.
 */
public class Enemy extends GameObject implements PhysicBody{

    public Vector2D velocity;
    private FrameCounter shootCounter;

    BoxCollider boxCollider;

    public Enemy(){
        super();
        this.renderer = new Animation(Utils.loadAssetImage("enemies/level0/blue/0.png"),
                Utils.loadAssetImage("enemies/level0/blue/1.png"),
                Utils.loadAssetImage("enemies/level0/blue/2.png"),
                Utils.loadAssetImage("enemies/level0/blue/3.png")
        );

        this.velocity = new Vector2D();
        this.shootCounter = new FrameCounter(5);

        this.boxCollider = new BoxCollider(20,20);
        this.children.add(boxCollider);
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        velocity.y = 2;
        this.position.addUp(velocity);

        if (shootCounter.run()){
            this.shootCounter.reset();
            shoot();
        }
    }

    private void shoot() {
            Vector2D target = Player.instance.position;
            Vector2D bulletVelocity = target.substract(position)
                    .nomalize()
                    .multiply(3);

            EnemyBullet enemyBullet = GameObjectPool.recycle(EnemyBullet.class);
            enemyBullet.velocity.set(bulletVelocity);
            enemyBullet.position.set(this.position.x , this.position.y + 15);


    }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }
}
