package game.enemies;

import game.Utils;
import game.actions.MoveByAction;
import game.actions.SequenceAction;
import game.actions.WaitAction;
import game.bases.GameObject;
import game.bases.renderers.ImageRenderer;
import game.bases.Vector2D;

/**
 * Created by SNOW on 7/23/2017.
 */
public class EnemyBullet extends GameObject{

    public EnemyBullet() {
        super();
        this.renderer = new ImageRenderer(Utils.loadAssetImage("enemies/bullets/blue.png"));

    }

    public void config(Vector2D velocity){
        this.addAction(
                new SequenceAction(
                        new MoveByAction(velocity.multiply(2),10),
                        new WaitAction(3),
                        new MoveByAction(velocity,9999)
                )
        );
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        if (this.position.y > 800){
            this.isActive = false;
        }
    }
}
