package game.bases;

import game.actions.Action;
import game.bases.physics.PhysicBody;
import game.bases.physics.Physics;
import game.bases.renderers.Renderer;

import javax.xml.bind.ValidationEvent;
import java.awt.*;
import java.util.Iterator;
import java.util.Vector;
import java.util.List;
/**
 * Created by SNOW on 7/18/2017.
 */
public class GameObject {

    public Vector2D  position;  // Relative
    public Vector2D screenPosition;

    public Renderer renderer;

    public boolean isActive;

    protected    Vector<GameObject> children;
    private static Vector<GameObject> gameObjects = new Vector<>();
    private static Vector<GameObject> newGameObject = new Vector();

    private List<Action> newAction;

    private Vector<Action> actions;

    public GameObject(){
        this.position = new Vector2D();
        this.screenPosition = new Vector2D();
        this.children = new Vector<>();
        this.actions = new Vector<>();
        this.newAction = new Vector<>();
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
    }

    public static void runAllAction(){
        for (GameObject gameObject: gameObjects){
            if (gameObject.isActive){
                gameObject.runAction();
            }
        }
    }

    public void render(Graphics2D g2d) {
        if (renderer != null) {
            renderer.render(g2d, this.position);
        }
    }

    public void run(Vector2D parentPosition){
        this.screenPosition = parentPosition.add(position);
        for (GameObject child: children) {
            child.run(this.screenPosition);
        }
    }

    public void refresh(){
        isActive = true;
        this.actions.clear();
        this.newAction.clear();
    }

    public void runAction(){
        Iterator<Action> iterator = actions.iterator();

        while (iterator.hasNext()){
            Action action = iterator.next();
            boolean actionDone = action.run(this);

            if (actionDone){
                iterator.remove();
            }
        }

        actions.addAll(newAction);
        newAction.clear();
    }

    public void addAction(Action action){
        newAction.add(action);
    }

    public static void clear(){
        gameObjects.clear();
        GameObjectPool.clear();
        Physics.clear();
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }
}
