package game.bases.renderers;

import game.bases.FrameCounter;
import game.bases.Vector2D;

import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.util.List;
import java.util.Arrays;
/**
 * Created by SNOW on 7/25/2017.
 */
public class Animation implements Renderer{
    private List<BufferedImage> images;
    private int imageIndex;
    private FrameCounter frameCounter;
    private boolean finished;
    private boolean repeat;

    public Animation( int delayFrame, boolean repeat, BufferedImage... imageArr){
        frameCounter = new FrameCounter(delayFrame);
        this.images = Arrays.asList(imageArr);
        this.repeat = repeat;
    }

    public Animation(BufferedImage... imageArr ){
        this(1, true, imageArr);
    }

    @Override
    public void render(Graphics g2d, Vector2D position) {

        if (frameCounter.run()){
            frameCounter.reset();
            changeIndex();
        }

        BufferedImage image = images.get(imageIndex);
        g2d.drawImage(image,
                (int) (position.x - image.getWidth() / 2),
                (int) (position.y - image.getHeight() / 2),
                null);
    }

    private void changeIndex() {

        if (imageIndex >= images.size() - 1){
            if (repeat) {
                imageIndex = 0;
            }
            finished = true;
        }else {
            imageIndex++;
        }
    }

    public boolean isFinished() {
        return finished;
    }

    public void reset(){
        imageIndex = 0;
        finished = false;
    }
}
