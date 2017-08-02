package game.enemies;

import game.actions.SequenceAction;
import game.actions.WaitAction;
import game.bases.GameObject;
import game.bases.Vector2D;
import game.enemies.spawn.EnemySpawnAction;

/**
 * Created by SNOW on 7/18/2017.
 */
public class EnemySpawner extends GameObject {

    public EnemySpawner() {
        super();
        this.addAction(new SequenceAction(
                new EnemySpawnAction(new Vector2D(10,10)),
                new EnemySpawnAction(new Vector2D(374,10)),
                new WaitAction(1000 / 17),
                        new EnemySpawnAction(new Vector2D(10,10)),
                        new EnemySpawnAction(new Vector2D(374,10))
        )
        );
    }
}
