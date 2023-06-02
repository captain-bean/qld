package com.marshall.benjy.qld.core.game.render;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.math.Vector3;
import com.marshall.benjy.qld.core.game.Constants;
import com.marshall.benjy.qld.core.game.state.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class PlayerRenderer {

    private static final Logger logger = LogManager.getLogger(PlayerRenderer.class);
    private Player player;
    private String texturePath;
    private AssetManager assetManager;
    private ModelInstance playerModelInstance;
    public PlayerRenderer(Player player, String textureName, AssetManager assetManager) {
        this.player = player;
        this.texturePath = "Models/" + textureName;
        this.assetManager = assetManager;

        this.player.addMovementListener(this::updateModelInstance);

        assetManager.load(texturePath, Model.class);
    }

    public ModelInstance getPlayerModelInstance() {
        if (playerModelInstance != null) {
            return playerModelInstance;
        }

        if (assetManager.isLoaded(texturePath)) {
            updateModelInstance(player);
        }

        return playerModelInstance;
    }

    public void updateModelInstance(Player player) {
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

        playerModelInstance.transform.rotateTowardDirection(DevCamera.instance().direction,DevCamera.instance().up);
    }
}
