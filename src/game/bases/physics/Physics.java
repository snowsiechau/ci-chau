package game.bases.physics;

import java.util.Vector;

/**
 * Created by SNOW on 7/25/2017.
 */
public class Physics {
    private static Vector<PhysicBody> bodies = new Vector<>();

    public static void add(PhysicBody body){
        bodies.add(body);
    }

    public static <T extends PhysicBody> T bodyInRed(BoxCollider boxCollider, Class<T> classz){
        for (PhysicBody body: bodies){
            if (body.isActive() && body.getBoxCollider().collideWidth(boxCollider)){
                if (body.getClass() == classz){
                    return (T) body;
                }
            }
        }
        return null;
    }
}
