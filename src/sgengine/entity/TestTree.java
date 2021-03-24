/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgengine.entity;

import sgengine.inteface.Collider;
import sgengine.inteface.SpriteRenderer;
import sgengine.model.Data2D;
import sgengine.model.Entity;
import sgengine.model.Sprite;

/**
 *
 * @author pi
 */
public class TestTree extends Entity implements SpriteRenderer, Collider {

    private Sprite sprite;
    private Data2D collideSize;
    private Data2D collidePivot;

    @Override
    public void start() {
        sprite = new Sprite("simpletree.png", new Data2D(8, 8));
        sprite.setPivot(new Data2D(4, 8));
        collideSize = new Data2D(8, 2);
        collidePivot = new Data2D(4, 2);
    }

    @Override
    public void update() {
        setDrawOrder(position.getY());
    }

    @Override
    public Sprite renderSprite() {
        return sprite;
    }

    @Override
    public Data2D getSize() {
        return collideSize;
    }

    @Override
    public Data2D getPivot() {
        return collidePivot;
    }

    @Override
    public boolean drawHitox() {
        return true; //To change body of generated methods, choose Tools | Templates.
    }

}
