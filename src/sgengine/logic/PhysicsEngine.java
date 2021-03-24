/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgengine.logic;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import sgengine.entity.Camera;
import sgengine.inteface.Collider;
import sgengine.inteface.SpriteRenderer;
import sgengine.model.Data2D;
import sgengine.model.Scene;
import sgengine.model.Sprite;

/**
 *
 * @author pi
 */
public class PhysicsEngine implements Camera.DrawListener {

    private ArrayList<Collider> currentColliders;

    public void executePhysics(Scene scene) {
        currentColliders = new ArrayList();

        if (scene != null) {
            scene.getEntityList().forEach(e -> {
                if (e instanceof Collider) {
                    currentColliders.add((Collider) e);
                }
            });

            scene.getCameraList().forEach(c -> {
                if (Camera.DEFAULT_TAG.equals(c.getTag())) {
                    c.addDrawListener(this);
                }
            });
        }

        currentColliders.forEach(c1 -> {
            currentColliders.forEach(c2 -> {
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

    @Override
    public void onPostDraw(Camera camera, Graphics2D g2d) {
        currentColliders.forEach(c -> {
            if (c.drawHitox()) {
                Data2D cPosition = c.getPosition().copy();
                Data2D cSize = c.getSize().copy();
                Data2D cPivot = c.getPivot().copy();

                BufferedImage bi = new BufferedImage(cSize.getX(), cSize.getY(), BufferedImage.TYPE_INT_ARGB_PRE);

                Graphics2D bg2d = (Graphics2D) bi.getGraphics();

                //bg2d.setColor(Color.green);
                bg2d.setColor(new Color(0, 255, 0, 128));
                bg2d.fillRect(0, 0, cSize.getX(), cSize.getY());

                Sprite cSprite = new Sprite(bi);
                cSprite.setPivot(cPivot);

                SpriteRenderer sRenderer = new SpriteRenderer() {
                    @Override
                    public Sprite renderSprite() {
                        return cSprite;
                    }
                };

                camera.draw(g2d, sRenderer, cPosition);
            }
        });
    }
}
