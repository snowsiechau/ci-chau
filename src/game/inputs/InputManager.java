package game.inputs;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 * Created by SNOW on 7/18/2017.
 */
public class InputManager {
    public boolean leftPressed;
    public boolean rightPressed;
    public boolean upPressed;
    public boolean downPressed;
    public boolean xPressed;

    public static final InputManager instance = new InputManager();

    private List<InputListener> inputListeners;

    public void register(InputListener inputListener) {
        inputListeners.add(inputListener);
    }

    public void unRegister(InputListener inputListener){
        inputListeners.remove(inputListener);
    }

    private InputManager(){
        inputListeners = new ArrayList<>();
    }

    public void keyPressed(KeyEvent keyEvent){
        switch (keyEvent.getKeyCode()){
            case KeyEvent.VK_LEFT:
                leftPressed = true;
                break;

            case KeyEvent.VK_RIGHT:
                rightPressed = true;
                break;

            case KeyEvent.VK_UP:
                upPressed = true;
                break;

            case KeyEvent.VK_DOWN:
                downPressed = true;
                break;

            case KeyEvent.VK_X:
                xPressed = true;
                break;

            default:
                break;
        }
    }

    public void keyReleased(KeyEvent keyEvent){
        switch (keyEvent.getKeyCode()){
            case KeyEvent.VK_RIGHT:
                rightPressed = false;
                break;

            case KeyEvent.VK_LEFT:
                leftPressed = false;
                break;

            case KeyEvent.VK_UP:
                upPressed = false;
                break;

            case KeyEvent.VK_DOWN:
                downPressed = false;
                break;

            case KeyEvent.VK_X:
                xPressed = false;
                break;

            default:
                break;
        }

        Iterator<InputListener> inputListenerIterator = inputListeners.iterator();
        while (inputListenerIterator.hasNext()){
            InputListener inputListener = inputListenerIterator.next();
            if (inputListener.onKeyReleased(keyEvent.getKeyCode())){
                inputListenerIterator.remove();
            }
        }
    }
}
