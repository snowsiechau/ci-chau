package game.actions;

import game.bases.GameObject;

/**
 * Created by SNOW on 8/1/2017.
 */
public interface Action {
    boolean run(GameObject gameObject);
    void reset();
}
