package game.bases;

import javax.xml.bind.ValidationEvent;
import java.awt.*;
import java.util.Vector;

/**
 * Created by SNOW on 7/18/2017.
 */
public class GameObject {

    public Vector2D  position;
    public ImageRenderer renderer;

    private static Vector<GameObject> gameObjects = new Vector<>();
    private static Vector<GameObject> newGameObject = new Vector();

    public static void add(GameObject gameObject){
        newGameObject.add(gameObject);
    }

    public static void renderAll(Graphics2D g2d){
        for (GameObject gameObject: gameObjects) {
            gameObject.render(g2d);
        }

        gameObjects.addAll(newGameObject);
        newGameObject.clear();
    }

    public static void runAll(){
        for (GameObject gameObject: gameObjects) {
            gameObject.run();
        }
    }

    public GameObject(){
        this.position = new Vector2D();
    }

    public void render(Graphics2D g2d) {
        if (renderer != null) {
            renderer.render(g2d, this.position);
        }
    }

    public void run(){

    }


}
