/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgengine.model;

import sgengine.logic.Controller;
import sgengine.ui.WindowWrapper;

/**
 *
 * @author pi
 */
public abstract class Entity {

    protected int drawOrder = 0;

    protected Data2D position = new Data2D(0, 0);

    public void start() {
    }

    public void update() {
    }

    public int getDrawOrder() {
        return drawOrder;
    }

    public void setDrawOrder(int drawOrder) {
        this.drawOrder = drawOrder;
    }

    public Data2D getPosition() {
        return position;
    }

    public void setPosition(Data2D position) {
        this.position = position;
    }

    protected WindowWrapper getMainWindow() {
        return Controller.getInstance().getMainWindow();
    }
}
