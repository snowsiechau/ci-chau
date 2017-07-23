package game.scenes;

import game.Utils;
import game.bases.GameObject;
import game.bases.ImageRenderer;
import game.bases.Vector2D;

/**
 * Created by SNOW on 7/23/2017.
 */
public class Background extends GameObject{

    public Background(){
        super();
        this.renderer = new ImageRenderer(Utils.loadAssetImage("background/0.png"));
        this.renderer.anchor.set(0, 1);
    }

    @Override
    public void run(Vector2D parentPosition) {

        if (this.position.y - 800  < 0){
        }
        this.position.addUp(0, 1);
    }
}
