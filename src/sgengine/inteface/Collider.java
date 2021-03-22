/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgengine.inteface;

import sgengine.model.Data2D;

/**
 *
 * @author pi
 */
public interface Collider {

    public Data2D getPosition();

    public Data2D getSize();

    public Data2D getPivot();

    public default void OnCollision(Collider o) {
    }
}
