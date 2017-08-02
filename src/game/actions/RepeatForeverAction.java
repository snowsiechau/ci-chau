package game.actions;

import game.bases.GameObject;

/**
 * Created by SNOW on 8/1/2017.
 */
public class RepeatForeverAction implements Action{
    private Action action;

    public RepeatForeverAction(Action action) {
        this.action = action;


    }

    @Override
    public boolean run(GameObject gameObject) {
        if (action.run(gameObject)){
            action.reset();
        }
        return false;
    }

    @Override
    public void reset() {
        action.reset();
    }
}
