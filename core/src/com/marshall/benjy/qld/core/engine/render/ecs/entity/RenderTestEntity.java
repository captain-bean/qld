package com.marshall.benjy.qld.core.engine.render.ecs.entity;

import com.marshall.benjy.qld.core.engine.render.ModelLoader;
import com.marshall.benjy.qld.core.engine.render.ecs.component.ModelComponent;
import com.marshall.benjy.qld.core.engine.render.ecs.system.RenderQueueSystem;

public class RenderTestEntity extends GameObject{

    public RenderTestEntity()
    {
        super();
        setModel("Models/block.g3db");
        ModelLoader.staticLoader.finishLoading();
    }


}
