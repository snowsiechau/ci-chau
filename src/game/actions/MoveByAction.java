package game.actions;

import game.bases.FrameCounter;
import game.bases.GameObject;
import game.bases.Vector2D;

/**
 * Created by SNOW on 8/1/2017.
 */
public class MoveByAction implements Action {
    private Vector2D velocity;
    private FrameCounter frameCounter;

    public MoveByAction(Vector2D velocity, int countMax) {
        this.velocity = velocity;
        this.frameCounter = new FrameCounter(countMax);
    }

    @Override
    public boolean run(GameObject gameObject) {
        if (frameCounter.run()){
            return true;
        }
        gameObject.position.addUp(velocity);

        return false;
    }

    @Override
    public void reset() {
        frameCounter.reset();
    }
}
