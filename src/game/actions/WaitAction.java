package game.actions;

import game.bases.FrameCounter;
import game.bases.GameObject;

/**
 * Created by SNOW on 8/1/2017.
 */
public class WaitAction implements Action{

    private FrameCounter frameCounter;

    public WaitAction(int countMax) {
        frameCounter = new FrameCounter(countMax);
    }

    @Override
    public boolean run(GameObject gameObject) {
        return frameCounter.run();
    }

    @Override
    public void reset() {
        frameCounter.reset();
    }
}
