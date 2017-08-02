package game.players;

import game.Utils;
import game.bases.physics.BoxCollider;
import game.bases.GameObject;
import game.bases.renderers.ImageRenderer;
import game.bases.Vector2D;
import game.bases.physics.PhysicBody;
import game.bases.physics.Physics;
import game.enemies.Enemy;


/**
 * Created by SNOW on 7/11/2017.
 */
public class PlayerSpell extends GameObject implements PhysicBody{

    private BoxCollider boxCollider;

    public PlayerSpell() {
        super();
        this.renderer = new ImageRenderer(Utils.loadAssetImage("player-spells/a/1.png"));
        boxCollider = new BoxCollider(20,20);
        children.add(boxCollider);
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        this.position.addUp(0, -10);

        hitEnemy();
        if (this.position.y < 0){
            this.isActive = false;
        }
    }

    private void hitEnemy() {
        Enemy hitEnemy = Physics.bodyInRed(this.boxCollider, Enemy.class);
        if (hitEnemy != null) {
            hitEnemy.getHit(1);
            this.isActive = false;
        }
    }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }
}