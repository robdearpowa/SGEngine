/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgengine.model;

import java.util.ArrayList;

/**
 *
 * @author pi
 */
public class Scene {

    private String tag;
    private ArrayList<Entity> entityList;

    private Scene() {

    }

    public Scene(String tag) {
        this.tag = tag;
        this.entityList = new ArrayList();
    }

    public ArrayList<Entity> getEntityList() {
        return entityList;
    }

}
