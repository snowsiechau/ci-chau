package game.scenes;

import game.Utils;
import game.bases.GameObject;
import game.bases.renderers.ImageRenderer;
import game.bases.Vector2D;

/**
 * Created by SNOW on 7/23/2017.
 */
public class Background extends GameObject{
    private  ImageRenderer imageRenderer;

    public Background(){
        super();

        imageRenderer = new ImageRenderer(Utils.loadAssetImage("background/0.png"));
        imageRenderer.anchor.set(0,1);
        this.renderer = imageRenderer;
    }

    @Override
    public void run(Vector2D parentPosition) {

        if (this.position.y - imageRenderer.getHeight() < 0) {
            this.position.addUp(0, 1);
        }
    }

    public float getWidth(){
        return imageRenderer.getWidth();
    }

    public float getHeight(){
        return  imageRenderer.getHeight();
    }
}
