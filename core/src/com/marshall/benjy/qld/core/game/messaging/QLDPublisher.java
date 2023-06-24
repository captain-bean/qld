package com.marshall.benjy.qld.core.game.messaging;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;

public class QLDPublisher<T> {
    private ProducerTemplate template;
    public QLDPublisher(CamelContext context) {
        this.template = context.createProducerTemplate();
    }

    public void sendMessage(String topic, T message) {
        template.sendBody("vm:" + topic, message);
    }
}
