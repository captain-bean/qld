package com.marshall.benjy.qld.core.level;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.marshall.benjy.qld.core.tile.Tile;
import com.marshall.benjy.qld.core.tile.TileTypes;
import com.marshall.benjy.qld.core.tile.renderer.TileRenderer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LevelRenderer {

    private float SCALE = 3f;

    private Level level;

    public List<ModelInstance> instances = new ArrayList<>();
    private TileRenderer tileRenderer;

    public LevelRenderer(Level level, AssetManager assetManager) {
        this.level = level;
        this.tileRenderer = new TileRenderer(assetManager);
    }

    public void updateInstances() {
        instances.clear();
        for (int x = 0; x < level.getTiles().length; x++ ) {
            for (int z = 0; z < level.getTiles()[x].length; z++) {
                Tile workingTile = level.getTiles()[x][z];
                Model tileModel = tileRenderer.getTileModel(workingTile);
                if(tileModel == null) {
                    System.out.println("Tile model not loaded, aborting draw");
                    return;
                }
                ModelInstance tileInstance = new ModelInstance(tileModel);
                tileInstance.transform.setToTranslation(x * SCALE, 0, z * SCALE);
                instances.add(tileInstance);
            }
        }
    }

    public List<ModelInstance> getInstances() {
        if (!instances.isEmpty()) {
            return instances;
        }

        try {
            updateInstances();
            return instances;
        } catch (Exception ex) {
            System.out.println(ex);
            instances.clear();
            return Collections.emptyList();
        }
    }

}
