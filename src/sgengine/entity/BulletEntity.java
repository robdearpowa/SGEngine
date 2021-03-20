/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgengine.entity;

import sgengine.inteface.SpriteRenderer;
import sgengine.model.Audio;
import sgengine.model.Data2D;
import sgengine.model.Entity;
import sgengine.model.Sprite;

/**
 *
 * @author pi
 */
public class BulletEntity extends Entity implements SpriteRenderer {

    private Sprite sprite;
    private Data2D direction = new Data2D(0, 0);
    private int speed;
    private long life = 0;
    private long maxLife = 3000;

    @Override
    public void start() {
        sprite = new Sprite("simple_bullet.png", new Data2D(4, 4));
        speed = 2;
        life = System.currentTimeMillis();
        Audio audio = new Audio("shoot2.wav");
        audio.play();
    }

    @Override

    public void update() {
        Data2D movement = direction.copy();
        movement.multiply(speed);
        position.plus(movement);

        long currentLife = System.currentTimeMillis();
        if (currentLife > life + maxLife) {
            kill();
        }
    }

    @Override
    public Sprite renderSprite() {
        return sprite;
    }

    public Data2D getDirection() {
        return direction;
    }

    public void setDirection(Data2D direction) {
        this.direction = direction;
    }

}
