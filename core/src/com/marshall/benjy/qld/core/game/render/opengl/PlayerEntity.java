package com.marshall.benjy.qld.core.game.render.opengl;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.math.Vector3;
import com.marshall.benjy.qld.core.engine.render.ModelTexturer;
import com.marshall.benjy.qld.core.engine.render.ecs.component.ModelComponent;
import com.marshall.benjy.qld.core.engine.render.ecs.component.ShadowComponent;
import com.marshall.benjy.qld.core.engine.render.ecs.component.TransformComponent;
import com.marshall.benjy.qld.core.engine.render.ecs.component.UpdateComponent;
import com.marshall.benjy.qld.core.engine.render.ecs.entity.GameObject;
import com.marshall.benjy.qld.core.engine.state.Constants;
import com.marshall.benjy.qld.core.game.state.datatype.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class PlayerEntity extends GameObject {

    private static final Logger logger = LogManager.getLogger(PlayerEntity.class);

    private Player player;
    private Camera orientingCamera;
    private String texturePath;

    private boolean needsUpdate;
    public PlayerEntity(Player player, Camera camera, String textureName) {
        super();
        this.player = player;
        this.texturePath = "Models/" + textureName;
        this.orientingCamera = camera;

        getComponent(ModelComponent.class).setModel(textureName);
        addAndReturn(new ShadowComponent()).setShadowType(ShadowComponent.ShadowType.RECTANGLE);
        add(new UpdateComponent((something) -> {
            if(needsUpdate) {
                update();
            }
        }));
        ModelTexturer.addTexture(getComponent(ShadowComponent.class).getInstance(),"Textures/default.png",TextureAttribute.Diffuse);
        update();
    }

    public void markNeedsUpdate() {
        needsUpdate = true;
    }

    public void update() {
        logger.info("Updating player model");

        ModelComponent modelComponent = getComponent(ModelComponent.class);
        modelComponent.setTexturePath("Textures/walk_1.png",TextureAttribute.Diffuse);

        getComponent(TransformComponent.class).transform.setToTranslation(player.getPosition().getX() * Constants.SCALE,
                Constants.SCALE * 1f,
                player.getPosition().getZ() * Constants.SCALE);

        getComponent(TransformComponent.class).transform.scale(Constants.SCALE * 1f,
                Constants.SCALE * .8f,
                Constants.SCALE * .8f);
        getComponent(TransformComponent.class).transform.rotateTowardDirection(orientingCamera.direction.cpy(), orientingCamera.up);

        Vector3 position = new Vector3();
        getComponent(TransformComponent.class).transform.getTranslation(position);

        getComponent(ShadowComponent.class).transform.setToTranslation(position)
                .translate(0,-5.5f,0)
                .scale(3,1,5)
                .rotate(1,0,0,-90);
        needsUpdate = false;
    }
}
