package game.enemies.shoot;

import game.actions.Action;
import game.bases.GameObject;
import game.bases.GameObjectPool;
import game.bases.Vector2D;
import game.enemies.Enemy;
import game.enemies.EnemyBullet;
import game.players.Player;

/**
 * Created by SNOW on 8/1/2017.
 */
public class EnemyShootAction implements Action{

    @Override
    public boolean run(GameObject gameObject) {
        Enemy enemy = (Enemy) gameObject;
        Vector2D target = Player.instance.position;
        Vector2D bulletVelocity = target.substract(enemy.position)
                .nomalize()
                .multiply(3);

        EnemyBullet enemyBullet = GameObjectPool.recycle(EnemyBullet.class);
        enemyBullet.config(bulletVelocity);
        enemyBullet.position.set(enemy.position.x , enemy.position.y + 15);

        return true;
    }

    @Override
    public void reset() {

    }
}
