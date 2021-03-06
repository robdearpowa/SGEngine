/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgengine.entity;

import java.awt.event.KeyEvent;
import sgengine.inteface.Collider;
import sgengine.inteface.SpriteRenderer;
import sgengine.logic.Controller;
import sgengine.model.Data2D;
import sgengine.model.Entity;
import sgengine.model.Sprite;
import sgengine.ui.WindowWrapper.KeyEventListener;

/**
 *
 * @author pi
 */
public class TestEntity extends Entity implements SpriteRenderer, KeyEventListener, Collider {

    private Data2D horizontalInput;
    private Data2D verticalInput;
    private Data2D movement;
    private Data2D lastDirection;
    private float movementSpeed;
    private Sprite sprite;
    private Camera camera;
    private boolean shooting;
    private int shots = 0;
    private int maxShots = 1;
    private Data2D collideSize;
    private Data2D collidePivot;

    @Override
    public void start() {
        position = new Data2D();
        horizontalInput = new Data2D();
        verticalInput = new Data2D();
        movement = new Data2D();
        movement = new Data2D(1, 0);
        movementSpeed = 1;
        sprite = new Sprite("monk1.png", new Data2D(16, 16));
        sprite.setPivot(new Data2D(8, 16));

        shooting = false;
        collideSize = new Data2D(16, 4);
        collidePivot = new Data2D(8, 4);

        getMainWindow().addKeyEventListener(this);

        camera = (Camera) getCurrentScene().getEntityByTag("mainCamera");
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
                sprite.setFlippedHorizontal(true);
            }

            if (horizontalInput.getY() == 1) {
                sprite.setFlippedHorizontal(false);
                movement.setX(1);
            }
        }

        lastDirection = movement.equals(Data2D.ZERO) ? lastDirection : movement.copy();

        movement.multiply((int) movementSpeed);

        Data2D movementH = new Data2D(movement.getX(), 0);
        Data2D movementV = new Data2D(0, movement.getY());

        position.plus(movementH);

        for (Collider c : Controller.getInstance().getPhysicsEngine().getCurrentColliders()) {
            if (getPhysicsEngine().isColliderColliding(this, c)) {
                position.plus(movementH.negative());
            }
        }

        position.plus(movementV);

        for (Collider c : Controller.getInstance().getPhysicsEngine().getCurrentColliders()) {
            if (getPhysicsEngine().isColliderColliding(this, c)) {
                position.plus(movementV.negative());
            }
        }

        if (shooting) {
            if (shots < maxShots) {
                spawnBullet();
                shots++;
            }
        } else {
            shots = 0;
        }

        setDrawOrder(position.getY());
    }

    @Override
    public void postPhysics() {
        if (camera != null) {
            Data2D cameraPos = position.copy();
            Data2D cameraRes = camera.getRenderingResolution();
            cameraPos.plus(new Data2D(-cameraRes.getX() / 2, -cameraRes.getY() / 2));

            camera.setPosition(cameraPos);
        }
    }

    @Override
    public Sprite renderSprite() {
        return sprite;
    }

    @Override
    public void onKeyTyped(KeyEvent e
    ) {

    }

    @Override
    public void onKeyPressed(KeyEvent e
    ) {
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
            case KeyEvent.VK_SPACE:
                shooting = true;
                break;
        }
    }

    @Override
    public void onKeyReleased(KeyEvent e
    ) {
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
            case KeyEvent.VK_SPACE:
                shooting = false;
                break;
        }
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
    public void OnCollision(Collider o
    ) {
        System.out.println("Sto collidendo");
    }

    @Override
    public boolean drawHitox() {
        return true;
    }

    private void spawnBullet() {
        BulletEntity b = new BulletEntity();

        b.setPosition(position.plusCopy(new Data2D(0, -4)));
        b.setDirection(lastDirection.copy());

        getCurrentScene().addToEntityList(b);
    }
}
