package com.marshall.benjy.qld.core.game.player;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Consumer;

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

        playerModelInstance.transform.translate(player.getPosition().getX() * 10, 0, player.getPosition().getY() * 10);
        playerModelInstance.transform.scale(5, 5, 5);
    }
}
