package game.enemies;

import game.Utils;
import game.actions.Action;
import game.actions.RepeatForeverAction;
import game.actions.SequenceAction;
import game.actions.WaitAction;
import game.bases.*;
import game.bases.physics.BoxCollider;
import game.bases.physics.PhysicBody;
import game.bases.renderers.Animation;
import game.bases.renderers.ImageRenderer;
import game.enemies.shoot.EnemyShootAction;
import game.players.Player;

/**
 * Created by SNOW on 7/23/2017.
 */
public class Enemy extends GameObject implements PhysicBody{

    public Vector2D velocity;
    BoxCollider boxCollider;

    public Enemy(){
        super();
        this.renderer = new Animation(Utils.loadAssetImage("enemies/level0/blue/0.png"),
                Utils.loadAssetImage("enemies/level0/blue/1.png"),
                Utils.loadAssetImage("enemies/level0/blue/2.png"),
                Utils.loadAssetImage("enemies/level0/blue/3.png")
        );

        this.velocity = new Vector2D();

        this.boxCollider = new BoxCollider(20,20);
        this.children.add(boxCollider);
    }

    public void config(){
        Action shootSequence = new SequenceAction(
                new WaitAction(20),
                new EnemyShootAction()
        );

        this.addAction(new RepeatForeverAction(shootSequence));
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        velocity.y = 2;
        this.position.addUp(velocity);
    }


    public void getHit(int damage){
        this.isActive = false;
        EnemyExplosion enemyExplosion = GameObjectPool.recycle(EnemyExplosion.class);
        enemyExplosion.position.set(this.position);
    }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }
}
