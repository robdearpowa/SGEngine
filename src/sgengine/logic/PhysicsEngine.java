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

    public ArrayList<Collider> getCurrentColliders() {
        Scene scene = Controller.getInstance().getMainLooper().getCurrentScene();
        ArrayList<Collider> result = new ArrayList();
        if (scene != null) {
            scene.getEntityList().forEach(e -> {
                if (e instanceof Collider) {
                    result.add((Collider) e);
                }
            });
        }

        return result;
    }

    public void executePhysics(Scene scene) {
        if (scene != null) {
            scene.getCameraList().forEach(c -> {
                if (Camera.DEFAULT_TAG.equals(c.getTag())) {
                    c.addDrawListener(this);
                }
            });
        }
    }

    public boolean isPointColliding(Data2D point, Collider collider) {

        Data2D cPosition = collider.getPosition().copy();
        Data2D cSize = collider.getSize().copy();

        cPosition.plus(collider.getPivot().negative());

        return (point.getX() > cPosition.getX()
                && point.getY() > cPosition.getY()
                && point.getX() < (cPosition.getX() + cSize.getX())
                && point.getY() < (cPosition.getY() + cSize.getY()));
    }

    public boolean isColliderColliding(Collider c1, Collider c2) {

        if (c1.hashCode() == c2.hashCode() || c1.trigger() || c2.trigger()) {
            return false;
        }

        Data2D c1p1 = c1.getPosition().copy();
        c1p1.plus(c1.getPivot().negative());

        Data2D c1p2 = c1p1.plusCopy(c1.getSize());

        Data2D c2p1 = c2.getPosition().copy();
        c2p1.plus(c2.getPivot().negative());

        Data2D c2p2 = c2p1.plusCopy(c2.getSize());

        if (c1p1.getX() >= c2p2.getX() || c2p1.getX() >= c1p2.getX()) {
            return false;
        }

        if (c1p1.getY() >= c2p2.getY() || c2p1.getY() >= c1p2.getY()) {
            return false;
        }

        return true;
    }

    @Override
    public void onPostDraw(Camera camera, Graphics2D g2d) {
        getCurrentColliders().forEach(c -> {
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
