package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.games.items.Item;

import java.util.*;

/**
 * Created by swandelow on 4/21/16.
 */
public class Stage {

    private Map<String, Item> items;

    public Stage() {
        this.items = new HashMap<String, Item>();
    }

    public void addItem(Item item) {
        this.items.put(item.getName(), item);
    }

    public Item pickItem(String itemName) {
        return this.items.remove(itemName);
    }

    public String lookAround() {
        StringBuilder sb = new StringBuilder();
        sb.append("Items in the room: ");
        Iterator<Item> it = items.values().iterator();
        while (it.hasNext()) {
            sb.append(it.next().getName());
            if (it.hasNext()) {
                sb.append(", ");
            } else {
                sb.append(".");
            }
        }
        return sb.toString();
    }

    public Item getItem(String itemName) {
        return this.items.get(itemName);
    }

}
