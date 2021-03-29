/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgengine.model;

/**
 *
 * @author pi
 */
public class CollisionData {

    public boolean colliding;
    public Data2D offset;

    public CollisionData(boolean colliding, Data2D offset) {
        this.colliding = colliding;
        this.offset = offset;
    }

}
