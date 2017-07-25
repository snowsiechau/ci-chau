package game.bases;

import game.bases.physics.PhysicBody;
import game.bases.physics.Physics;
import game.bases.renderers.Renderer;

import javax.xml.bind.ValidationEvent;
import java.awt.*;
import java.util.Vector;

/**
 * Created by SNOW on 7/18/2017.
 */
public class GameObject {

    public Vector2D  position;  // Relative
    public Vector2D screenPosition;

    public Renderer renderer;

    public boolean isActive;

    public  Vector<GameObject> children;
    private static Vector<GameObject> gameObjects = new Vector<>();
    private static Vector<GameObject> newGameObject = new Vector();

    public GameObject(){
        this.position = new Vector2D();
        this.screenPosition = new Vector2D();
        this.children = new Vector<>();
        this.isActive = true;
    }

    public boolean isActive() {
        return isActive;
    }

    public static void add(GameObject gameObject){
        newGameObject.add(gameObject);
        if (gameObject instanceof PhysicBody){
            Physics.add((PhysicBody) gameObject);
        }
    }



    public static void renderAll(Graphics2D g2d){
        for (GameObject gameObject: gameObjects) {
            if (gameObject.isActive) {
                gameObject.render(g2d);
            }
        }
    }

    public static void runAll(){
        for (GameObject gameObject: gameObjects) {
            if (gameObject.isActive) {
                gameObject.run(Vector2D.ZERO);
            }
        }
        gameObjects.addAll(newGameObject);
        newGameObject.clear();
        System.out.println(gameObjects.size());
        // Kiem tra doi mot

    }

    public void render(Graphics2D g2d) {
        if (renderer != null) {
            renderer.render(g2d, this.position);
        }
    }

    public void run(Vector2D parentPosition){
        // position == relative

        this.screenPosition = parentPosition.add(position);

        for (GameObject child: children) {
            child.run(this.screenPosition);
        }
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }
}
