package com.marshall.benjy.qld.core.game;

public class QLDConfig {

    private boolean isHeadless;

    public QLDConfig(boolean isHeadless) {
        this.isHeadless = isHeadless;
    }

    public boolean isHeadless() {
        return isHeadless;
    }
}
