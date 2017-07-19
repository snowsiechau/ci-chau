package game.players;

import game.Utils;
import game.bases.GameObject;
import game.bases.ImageRenderer;
import game.bases.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by SNOW on 7/11/2017.
 */
public class PlayerSpell extends GameObject {

    public PlayerSpell() {

        this.renderer = new ImageRenderer(Utils.loadAssetImage("player-spells/a/1.png"));
    }

    public void run() {
        this.position.addUp(0, -10);
    }

}