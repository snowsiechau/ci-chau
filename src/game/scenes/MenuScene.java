package game.scenes;

import game.Utils;
import game.bases.GameObject;
import game.bases.renderers.ImageRenderer;
import game.inputs.InputListener;
import game.inputs.InputManager;

/**
 * Created by SNOW on 8/1/2017.
 */
public class MenuScene extends Scene implements InputListener{


    @Override
    public void init() {
        GameObject introImage = new GameObject();
        introImage.renderer = new ImageRenderer(Utils.loadAssetImage("scenes/menu.png"));
        introImage.position.set(Setting.GAMEPLAY_WIDTH / 2, Setting.GAMEPLAY_HEIGHT / 2);
        GameObject.add(introImage);

        InputManager.instance.register(this);
    }

    @Override
    public boolean onKeyPressed(int keyCode) {
        return false;
    }

    @Override
    public boolean onKeyReleased(int keyCode) {
        SceneManager.instance.requestChangeScene(new Level1Scene());
        return true;  // request Delete
    }
}
