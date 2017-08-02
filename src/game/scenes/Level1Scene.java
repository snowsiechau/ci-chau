package game.scenes;

import game.bases.Contraints;
import game.bases.GameObject;
import game.enemies.EnemySpawner;
import game.inputs.InputManager;
import game.players.Player;

import java.util.Set;

/**
 * Created by SNOW on 8/1/2017.
 */
public class Level1Scene extends Scene{
    Background background;

    @Override
    public void init() {
        addBackground();
        addPlayer();
        addEnemySpawned();
    }

    private void addBackground() {
        background = new Background();

        background.position.y = Setting.WINDOW_HEIGHT;
        GameObject.add(background);
    }

    private void addPlayer() {
        Player player = new Player();
        player.setContraints(new Contraints(20, Setting.WINDOW_HEIGHT, 0, background.getWidth()));
        player.setInputManager(InputManager.instance);
        player.position.set(background.getWidth() / 2, Setting.WINDOW_HEIGHT - 50);

        GameObject.add(player);
    }

    private void addEnemySpawned() {
        GameObject.add(new EnemySpawner());
    }
}
