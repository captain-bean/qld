package com.marshall.benjy.qld.core.game.messaging;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;

import java.util.function.Consumer;

public class QLDConsumer<T> {

    private Consumer<T> consumer;
    public QLDConsumer(CamelContext context, String topic, Consumer<T> consumer) {
        this.consumer = consumer;

        try {
            context.addRoutes(new RouteBuilder() {
                @Override
                public void configure() throws Exception {
                    from("vm:" + topic).bean(consumer);
                }
            });
        } catch (Exception ex) {
        }
    }

}
