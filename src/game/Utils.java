package game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by SNOW on 7/11/2017.
 */
public class Utils {
    public static BufferedImage loadImage(String url){
        try {
            return ImageIO.read(new File(url));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static BufferedImage loadAssetImage(String url){
        return loadImage("assets/images/" + url);
    }
}
