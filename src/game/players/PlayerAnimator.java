package game.players;

import game.Utils;
import game.bases.Vector2D;
import game.bases.renderers.Animation;
import game.bases.renderers.Renderer;

import java.awt.*;

/**
 * Created by SNOW on 7/30/2017.
 */
public class PlayerAnimator implements Renderer{

    private Animation leftAnimation;
    private Animation rightAnimation;
    private Animation straightAnimation;

    private Animation currentAnimation;

    public PlayerAnimator() {
        leftAnimation = new Animation(
                Utils.loadAssetImage("players/left/0.png"),
                Utils.loadAssetImage("players/left/1.png"),
                Utils.loadAssetImage("players/left/2.png"),
                Utils.loadAssetImage("players/left/3.png"),
                Utils.loadAssetImage("players/left/4.png"),
                Utils.loadAssetImage("players/left/5.png")
                );
        rightAnimation = new Animation(
                Utils.loadAssetImage("players/right/0.png"),
                Utils.loadAssetImage("players/right/1.png"),
                Utils.loadAssetImage("players/right/2.png"),
                Utils.loadAssetImage("players/right/3.png"),
                Utils.loadAssetImage("players/right/4.png"),
                Utils.loadAssetImage("players/right/5.png")
                );
        straightAnimation = new Animation(
                Utils.loadAssetImage("players/straight/0.png"),
                Utils.loadAssetImage("players/straight/1.png"),
                Utils.loadAssetImage("players/straight/2.png"),
                Utils.loadAssetImage("players/straight/3.png"),
                Utils.loadAssetImage("players/straight/4.png"),
                Utils.loadAssetImage("players/straight/5.png"),
                Utils.loadAssetImage("players/straight/6.png")
                );
    }

    public void run(Player player){
        if (player.velocity.x < 0){
            currentAnimation = leftAnimation;
        }else if (player.velocity.x > 0){
            currentAnimation = rightAnimation;
        }else {
            currentAnimation = straightAnimation;
        }
    }

    @Override
    public void render(Graphics g2d, Vector2D position) {
        if (currentAnimation != null){
            currentAnimation.render(g2d, position);
        }
    }
}
