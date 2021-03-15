/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raycasterj.entity;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import raycasterj.model.Data2D;
import raycasterj.model.Entity;
import raycasterj.model.Sprite;
import raycasterj.ui.WindowWrapper.KeyEventListener;

/**
 *
 * @author pi
 */
public class TestEntity extends Entity implements KeyEventListener {

    private Data2D position;
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

        if (verticalInput != Data2D.ONE && verticalInput != Data2D.ZERO) {
            movement.setY(0);
            if (verticalInput.getX() == 1) {
                movement.setY(-1);
            }

            if (verticalInput.getY() == 1) {
                movement.setY(1);
            }
        }

        if (horizontalInput != Data2D.ONE && horizontalInput != Data2D.ZERO) {
            movement.setX(0);
            if (horizontalInput.getX() == 1) {
                movement.setX(-1);
            }

            if (horizontalInput.getY() == 1) {
                movement.setX(1);
            }
        }

        movement.multiply((int) movementSpeed);

        position.plus(movement);

        if (position.getX() > getMainWindow().getScaledDimension().width - 8) {
            position.setX(0);
        }

        if (position.getY() > getMainWindow().getScaledDimension().height - 8) {
            position.setY(0);
        }

        if (position.getX() < 0) {
            position.setX(getMainWindow().getScaledDimension().width - 8);
        }

        if (position.getY() < 0) {
            position.setY(getMainWindow().getScaledDimension().height - 8);
        }
    }

    @Override
    public void draw(Graphics2D g2d) {
        sprite.draw(g2d, position);
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
