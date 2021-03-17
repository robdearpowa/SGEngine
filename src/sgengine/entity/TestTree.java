/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgengine.entity;

import sgengine.inteface.SpriteRenderer;
import sgengine.model.Data2D;
import sgengine.model.Entity;
import sgengine.model.Sprite;

/**
 *
 * @author pi
 */
public class TestTree extends Entity implements SpriteRenderer {

    private Sprite sprite;

    @Override
    public void start() {
        sprite = new Sprite("simpletree.png", new Data2D(8, 8));
    }

    @Override
    public void update() {

    }

    @Override
    public Sprite renderSprite() {
        return sprite;
    }

}
