package game.players;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Properties;

/**
 * Created by SNOW on 7/11/2017.
 */
public class Player {

    //Properties : Thuoc tinh
    public int x;
    public int y;
    public BufferedImage image;

    // Methods: Phuong thuc
    public void move(int dx, int dy){
        x += dx;
        y += dy;
    }

    public void render(Graphics2D g2d){
        g2d.drawImage(image, x, y,null);

    }
}
