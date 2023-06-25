package com.marshall.benjy.qld.core.engine.messaging;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;

import java.util.function.Consumer;

public class Subscriber<T> {

    private Consumer<T> consumer;
    public Subscriber(CamelContext context, String topic, Consumer<T> consumer) {
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
