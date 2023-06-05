package com.marshall.benjy.qld.core.game.render;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.marshall.benjy.qld.core.engine.state.Constants;
import com.marshall.benjy.qld.core.engine.render.ModelTexturer;
import com.marshall.benjy.qld.core.game.state.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class PlayerRenderer {

    private static final Logger logger = LogManager.getLogger(PlayerRenderer.class);
    private Player player;
    private Camera orientingCamera;
    private String texturePath;
    private AssetManager assetManager;
    private ModelInstance playerModelInstance;
    public PlayerRenderer(Player player, Camera camera, String textureName, AssetManager assetManager) {
        this.player = player;
        this.texturePath = "Models/" + textureName;
        this.assetManager = assetManager;
        this.orientingCamera = camera;

        this.player.addMovementListener((p) -> {
            updateModelInstance();
        });

        assetManager.load(texturePath, Model.class);
    }

    public ModelInstance getPlayerModelInstance() {
        if (playerModelInstance != null) {
            return playerModelInstance;
        }

        if (assetManager.isLoaded(texturePath)) {
            updateModelInstance();
        }

        return playerModelInstance;
    }

    public void updateModelInstance() {
        logger.info("Updating player model");
        playerModelInstance = new ModelInstance(
                 assetManager.get(texturePath, Model.class));
        ModelTexturer.addTexture(playerModelInstance,"Textures/walk_1.png",TextureAttribute.Diffuse);

        playerModelInstance.transform.translate(player.getPosition().getX() * Constants.SCALE,
                Constants.SCALE * 1f,
                player.getPosition().getZ() * Constants.SCALE);

        playerModelInstance.transform.scale(Constants.SCALE * 1f,
                Constants.SCALE * 1f,
                Constants.SCALE * 1f);

        playerModelInstance.transform.rotateTowardDirection(orientingCamera.direction, orientingCamera.up);
    }
}
