package com.marshall.benjy.qld.core.engine.messaging;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;

public class Publisher<T> {
    private ProducerTemplate template;
    public Publisher(CamelContext context) {
        this.template = context.createProducerTemplate();
    }

    public void sendMessage(String topic, T message) {
        template.sendBody("vm:" + topic, message);
    }
}
