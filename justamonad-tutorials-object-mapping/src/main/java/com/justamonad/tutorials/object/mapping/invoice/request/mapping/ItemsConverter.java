package com.justamonad.tutorials.object.mapping.invoice.request.mapping;

import com.justamonad.tutorials.object.mapping.invoice.request.pojo.Item;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public final class ItemsConverter {

    public List<Item> to() {
        Item item = new Item();
        return Collections.singletonList(item);
    }

}
