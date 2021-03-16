/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgengine.model;

import java.util.ArrayList;
import java.util.Collections;
import sgengine.entity.Camera;

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

    public ArrayList<Entity> getEntityListInDrawingOrder() {
        //Copio l'array delle entità della scena per poi ordiarlo per drawingOrder
        ArrayList<Entity> orderedEntities = new ArrayList<Entity>(entityList);
        Collections.sort(orderedEntities, (Object o1, Object o2) -> {
            Entity e1 = (Entity) o1;
            Entity e2 = (Entity) o2;

            return e1.getDrawOrder() - e2.getDrawOrder();
        });

        return orderedEntities;
    }

    public ArrayList<Camera> getCameraList() {
        ArrayList<Entity> eList = getEntityListInDrawingOrder();
        ArrayList<Camera> cList = new ArrayList();

        eList.forEach(e -> {
            if (e instanceof Camera) {
                cList.add((Camera) e);
            }
        });

        return cList;
    }
}
