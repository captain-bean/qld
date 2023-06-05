package com.marshall.benjy.qld.core.engine.render.ecs.component;

import com.badlogic.ashley.core.Component;
import com.marshall.benjy.qld.core.engine.generator.UUIDGenerator;

import java.util.UUID;

public class IDComponent implements Component {

    public UUID id;

    public IDComponent(){
        id = UUIDGenerator.generate();
    }
}
