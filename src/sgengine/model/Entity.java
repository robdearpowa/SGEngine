/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgengine.model;

import java.awt.Graphics2D;
import sgengine.logic.Controller;
import sgengine.ui.WindowWrapper;

/**
 *
 * @author pi
 */
public abstract class Entity {

    private int drawOrder = 0;

    public void start() {
    }

    public void update() {
    }

    public void draw(Graphics2D g2d) {
    }

    public int getDrawOrder() {
        return drawOrder;
    }

    public void setDrawOrder(int drawOrder) {
        this.drawOrder = drawOrder;
    }

    public WindowWrapper getMainWindow() {
        return Controller.getInstance().getMainWindow();
    }
}
