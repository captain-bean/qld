package com.marshall.benjy.qld.core.engine.ecs.component;

import com.badlogic.ashley.core.Component;
import com.marshall.benjy.qld.core.engine.datatype.UUIDGenerator;

import java.util.UUID;

public class IDComponent implements Component {

    public UUID id;

    public IDComponent(){
        id = UUIDGenerator.generate();
    }
}
