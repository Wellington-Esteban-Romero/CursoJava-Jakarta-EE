package com.webapp.headers.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Carro {
    private List<ItemCarro> items;

    public Carro() {
        items = new ArrayList<>();
    }

    public List<ItemCarro> getItems() {
        return items;
    }

    public void addItem(ItemCarro item) {
        if (!items.contains(item)) {
            items.add(item);
        } else {
           Optional<ItemCarro>  optionalItemCarro = items.stream()
                   .filter(itemCarro -> itemCarro.equals(item))
                   .findAny();
           if (optionalItemCarro.isPresent()) {
               ItemCarro itemCarro = optionalItemCarro.get();
               itemCarro.setCantidad(itemCarro.getCantidad() + 1);
           }
        }
    }

    public int getTotal() {
        return items.stream()
                .mapToInt(ItemCarro::getImporte)
                .sum();
    }
}
