/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgengine.entity;

import java.awt.event.KeyEvent;
import sgengine.Main;
import sgengine.inteface.SpriteRenderer;
import sgengine.model.Data2D;
import sgengine.model.Entity;
import sgengine.model.Sprite;
import sgengine.ui.WindowWrapper.KeyEventListener;

/**
 *
 * @author pi
 */
public class TestEntity extends Entity implements SpriteRenderer, KeyEventListener {

    private Data2D horizontalInput;
    private Data2D verticalInput;
    private Data2D movement;
    private float movementSpeed;
    private Sprite sprite;

    @Override
    public void start() {
        position = new Data2D();
        horizontalInput = new Data2D();
        verticalInput = new Data2D();
        movement = new Data2D();
        movementSpeed = 1;
        sprite = new Sprite("simpleguy_small.png", new Data2D(8, 8));

        getMainWindow().addKeyEventListener(this);
    }

    @Override
    public void update() {

        movement.setX(0);
        movement.setY(0);
        if (!verticalInput.equals(Data2D.ONE) && !verticalInput.equals(Data2D.ZERO)) {
            if (verticalInput.getX() == 1) {
                movement.setY(-1);
            }

            if (verticalInput.getY() == 1) {
                movement.setY(1);
            }
        }

        if (!horizontalInput.equals(Data2D.ONE) && !horizontalInput.equals(Data2D.ZERO)) {
            if (horizontalInput.getX() == 1) {
                movement.setX(-1);
            }

            if (horizontalInput.getY() == 1) {
                movement.setX(1);
            }
        }

        movement.multiply((int) movementSpeed);

        position.plus(movement);

        if (position.getX() > Main.WIDTH - 8) {
            position.setX(0);
        }

        if (position.getY() > Main.HEIGHT - 8) {
            position.setY(0);
        }

        if (position.getX() < 0) {
            position.setX(Main.WIDTH - 8);
        }

        if (position.getY() < 0) {
            position.setY(Main.HEIGHT - 8);
        }
    }

    @Override
    public Sprite renderSprite() {
        return sprite;
    }

    @Override
    public void onKeyTyped(KeyEvent e) {

    }

    @Override
    public void onKeyPressed(KeyEvent e) {
        System.out.println("Key pressed " + e.getKeyChar());
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                verticalInput.setX(1);
                break;
            case KeyEvent.VK_S:
                verticalInput.setY(1);
                break;
            case KeyEvent.VK_A:
                horizontalInput.setX(1);
                break;
            case KeyEvent.VK_D:
                horizontalInput.setY(1);
                break;
        }
    }

    @Override
    public void onKeyReleased(KeyEvent e) {
        System.out.println("Key released " + e.getKeyChar());

        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                verticalInput.setX(0);
                break;
            case KeyEvent.VK_S:
                verticalInput.setY(0);
                break;
            case KeyEvent.VK_A:
                horizontalInput.setX(0);
                break;
            case KeyEvent.VK_D:
                horizontalInput.setY(0);
                break;
        }
    }
}
