package game.enemies;

import game.bases.GameObject;
import game.bases.Vector2D;

/**
 * Created by SNOW on 7/18/2017.
 */
public class EnemySpawner extends GameObject {

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);

        Enemy enemy = new Enemy();
        GameObject.add(enemy);
    }
}
