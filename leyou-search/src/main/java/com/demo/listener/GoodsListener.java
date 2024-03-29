package com.demo.listener;

import com.demo.service.SearchService;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GoodsListener {
    private final SearchService searchService;

    public GoodsListener(SearchService searchService) {
        this.searchService = searchService;
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "leyou.create.index.queue", durable = "true"),
            exchange = @Exchange(value = "leyou.item.exchange", ignoreDeclarationExceptions = "true", type = ExchangeTypes.TOPIC),
            key = {"item.insert", "item.update"}
        ))
    public void listenerCreate(Long id) throws IOException {
        if (id == null) return;
        this.searchService.createIndex(id);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "leyou.delete.index.queue", durable = "true"),
            exchange = @Exchange(value = "leyou.item.exchange", ignoreDeclarationExceptions = "true", type = ExchangeTypes.TOPIC),
            key = {"item.delete"}
    ))
    public void listenerDelete(Long id) {
        if (id == null) return;
        this.searchService.deleteIndex(id);
    }
}
