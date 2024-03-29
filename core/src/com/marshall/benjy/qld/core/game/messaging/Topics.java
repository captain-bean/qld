package com.marshall.benjy.qld.core.game.messaging;

public class Topics {

    // State updates
    public static final String PLAYER_MOVED = "player-moved";
    public static final String TILE_UPDATED = "tile-updated";
    public static final String LEVEL_CHANGED = "level-changed";

    // Intents
    public static final String MOVE_CAMERA_INTENT = "move-camera-intent";
    public static final String MOVE_PLAYER_INTENT = "move-player-intent";

    // Commands
    public static final String MOVE_CAMERA = "move-camera";
    public static final String MOVE_PLAYER = "move-player";
}
