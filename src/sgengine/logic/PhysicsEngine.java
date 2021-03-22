/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgengine.logic;

import java.util.ArrayList;
import sgengine.inteface.Collider;
import sgengine.model.Data2D;
import sgengine.model.Scene;

/**
 *
 * @author pi
 */
public class PhysicsEngine {

    public void executePhysics(Scene scene) {
        ArrayList<Collider> colliders = new ArrayList();

        if (scene != null) {
            scene.getEntityList().forEach(e -> {
                if (e instanceof Collider) {
                    colliders.add((Collider) e);
                }
            });
        }

        colliders.forEach(c1 -> {
            colliders.forEach(c2 -> {
                if (c1.hashCode() != c2.hashCode() && isColliderColliding(c1, c2)) {
                    c1.OnCollision(c2);
                }
            });
        });

    }

    public boolean isPointColliding(Data2D point, Collider collider) {

        Data2D cPosition = collider.getPosition().copy();
        Data2D cSize = collider.getSize().copy();

        cPosition.plus(collider.getPivot().negative());

        return (point.getX() >= cPosition.getX()
                && point.getY() >= cPosition.getY()
                && point.getX() <= (cPosition.getX() + cSize.getX())
                && point.getY() <= (cPosition.getY() + cSize.getY()));
    }

    public boolean isColliderColliding(Collider c1, Collider c2) {

        Data2D c1Position = c1.getPosition().copy();
        Data2D c1Size = c1.getSize().copy();

        c1Position.plus(c1.getPivot().negative());

        return (isPointColliding(c1Position, c2)
                || isPointColliding(c1Position.plusCopy(c1Size), c2)
                || isPointColliding(c1Position.plusCopy(new Data2D(c1Size.getX(), 0)), c2)
                || isPointColliding(c1Position.plusCopy(new Data2D(0, c1Size.getY())), c2));

    }
}
