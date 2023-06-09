package com.marshall.benjy.qld.core;

import com.marshall.benjy.qld.core.game.Application;
import com.marshall.benjy.qld.core.game.QLDConfig;
import org.junit.jupiter.api.Test;

public class BasicTest {


    @Test
    public void test() {
        Application game = new Application(new QLDConfig(true));
        game.dispose();
    }
}
