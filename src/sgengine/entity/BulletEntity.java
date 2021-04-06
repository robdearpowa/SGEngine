/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgengine.entity;

import sgengine.inteface.SpriteRenderer;
import sgengine.model.Animation;
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
    private Animation<Sprite> anim;

    @Override
    public void start() {
        sprite = new Sprite("ban1.png", new Data2D(16, 16));
        sprite.setPivot(new Data2D(4, 12));

        Sprite frame1 = sprite.copy();
        Sprite frame2 = sprite.copy();
        Sprite frame3 = sprite.copy();
        Sprite frame4 = sprite.copy();
        Sprite frame5 = sprite.copy();

        frame2.setRotation(72);
        frame3.setRotation(144);
        frame4.setRotation(216);
        frame5.setRotation(288);

        anim = new Animation(100, frame1, frame2, frame3, frame4, frame5);

        speed = 2;
        life = System.currentTimeMillis();
        Audio audio = new Audio("shoot2.wav");
        audio.play();

        if (direction.getX() < 0) {
            sprite.setFlippedHorizontal(true);
        }

        if (direction.getX() == 0) {
            if (direction.getY() < 0) {
                sprite.setRotation(-90);
            }

            if (direction.getY() > 0) {
                sprite.setRotation(90);
            }
        }

    }

    @Override

    public void update() {
        Data2D movement = direction.copy();
        movement.multiply(speed);
        position.plus(movement);

        long currentLife = System.currentTimeMillis();

        sprite = anim.getFrameAtTime(currentLife);

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
