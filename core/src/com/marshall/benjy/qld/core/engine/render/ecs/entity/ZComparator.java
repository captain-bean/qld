package com.marshall.benjy.qld.core.engine.render.ecs.entity;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector3;
import com.marshall.benjy.qld.core.engine.render.ecs.component.TransformComponent;

import java.util.Comparator;

public class ZComparator implements Comparator<Entity> {
    private ComponentMapper<TransformComponent> cmTrans;

    public ZComparator(){
        cmTrans= ComponentMapper.getFor(TransformComponent.class);
    }

    @Override
    public int compare(Entity entityA, Entity entityB) { //Compares Z position
        Vector3 temp = new Vector3();
        cmTrans.get(entityA).transform.getTranslation(temp);

        float az = temp.z;
                cmTrans.get(entityB).transform.getTranslation(temp);
        float bz = temp.z;
        int res = 0;
        if(az > bz){
            res = 1;
        }else if(az < bz){
            res = -1;
        }
        return res;
    }
}
