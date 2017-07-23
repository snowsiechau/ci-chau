package game.bases;


import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by SNOW on 7/16/2017.
 */
public class ImageRenderer {
    public BufferedImage image;
    public Vector2D anchor;

    public ImageRenderer(BufferedImage image){
        this.image = image;
        this.anchor = new Vector2D(0.5f, 0.5f);
    }

    public void render(Graphics g, Vector2D position){
        g.drawImage(image, (int) (position.x - image.getWidth() * anchor.x), (int) (position.y - image.getHeight() * anchor.y), null);

    }

    public float getWidth(){
        return image.getWidth();
    }

    public float getHeight(){
        return image.getHeight();
    }
}
