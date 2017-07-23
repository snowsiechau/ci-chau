package game.bases;

import javax.xml.bind.ValidationEvent;
import java.awt.*;
import java.util.Vector;

/**
 * Created by SNOW on 7/18/2017.
 */
public class GameObject {

    public Vector2D  position;  // Relative

    public Vector2D screenPosition;

    public ImageRenderer renderer;

    public Vector<GameObject> children;

    private static Vector<GameObject> gameObjects = new Vector<>();
    private static Vector<GameObject> newGameObject = new Vector();

    public GameObject(){
        this.position = new Vector2D();
        this.screenPosition = new Vector2D();
        this.children = new Vector<>();
    }

    public static void add(GameObject gameObject){
        newGameObject.add(gameObject);
    }

    public static void renderAll(Graphics2D g2d){
        for (GameObject gameObject: gameObjects) {
            gameObject.render(g2d);
        }
    }

    public static void runAll(){
        for (GameObject gameObject: gameObjects) {
            gameObject.run(Vector2D.ZERO);
        }

        gameObjects.addAll(newGameObject);
        newGameObject.clear();

        // Kiem tra doi mot

        for (int i = 0; i < gameObjects.size() - 1; i++) {
            for (int j = i + 1; j < gameObjects.size(); j++) {

            }
        }
    }

    public void render(Graphics2D g2d) {
        if (renderer != null) {
            renderer.render(g2d, this.position);
        }
    }

    public void run(Vector2D parentPosition){
        // position == relative

        this.screenPosition = parentPosition.add(position);

        for (GameObject child: children
             ) {
            child.run(this.screenPosition);
        }
    }

}
