package game.bases.physics;

/**
 * Created by SNOW on 7/25/2017.
 */
public interface PhysicBody {

    BoxCollider getBoxCollider();

    boolean isActive();
}
