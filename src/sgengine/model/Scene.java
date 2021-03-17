/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgengine.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import sgengine.entity.Camera;
import sgengine.logic.Controller;

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

    public void addToEntityList(Entity e) {
        entityList.add(e);
        if (Controller.getInstance().getMainLooper().isIsRunning() && Controller.getInstance().getMainLooper().getCurrentScene() == this) {
            e.start();
        }
    }

    public void addAllToEntityList(Collection<Entity> list) {
        entityList.addAll(list);
        if (Controller.getInstance().getMainLooper().isIsRunning() && Controller.getInstance().getMainLooper().getCurrentScene() == this) {
            list.forEach(e -> {
                e.start();
            });
        }
    }

    public ArrayList<Entity> getEntityList() {
        return new ArrayList(entityList);
    }

    public ArrayList<Entity> getEntityListInDrawingOrder() {
        //Copio l'array delle entità della scena per poi ordiarlo per drawingOrder
        ArrayList<Entity> orderedEntities = new ArrayList(entityList);
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

    public Entity getEntityByTag(String tag) {
        ArrayList<Entity> eList = getEntityList();

        for (int i = 0; i < eList.size(); i++) {
            if (eList.get(i).getTag().equals(tag)) {
                return eList.get(i);
            }
        }

        return null;
    }
}
