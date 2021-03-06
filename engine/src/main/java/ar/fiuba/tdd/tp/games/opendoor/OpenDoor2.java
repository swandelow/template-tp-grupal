package ar.fiuba.tdd.tp.games.opendoor;

import ar.fiuba.tdd.tp.games.*;
import ar.fiuba.tdd.tp.games.items.Item;
import ar.fiuba.tdd.tp.games.items.containers.Box;

import java.util.Collection;
import java.util.Optional;

/**
 * Created by swandelow on 4/22/16.
 */
public class OpenDoor2 /*extends AbstractOpenDoor*/ {
/*
    private Box box;

    public OpenDoor2() {
        super("OpenDoor2", "You enter room 2. You won the game!");
    }

    @Override
    protected void registerKnownActions() {
        super.registerKnownActions();
        this.knownActions.put(ActionOld.OPEN, (itemName, args) -> this.openAction(itemName));
        this.knownActions.put(ActionOld.EXAMINE, (itemName, args) -> this.examineItem(itemName));
    }

    private String openAction(String itemName) {
        Item item = this.room.getItem(itemName);
        String result;
        if (this.door.equals(item)) {
            return this.openDoor();
        }
        if (this.box.equals(item)) {
            this.box.open();
            Collection<Item> extractedItems = this.box.extractAll();
            for (Item extractedItem : extractedItems) {
                this.room.addItem(extractedItem);
            }
            return "The box is opened!";
        }
        return "Open action not executed. Invalid item.";
    }

    @Override
    protected void buildRoom() {
        super.buildRoom();
        this.box = Box.createWithItemsAndClosed(1, this.key);
        this.box.addItem(this.key);
        this.room.addItem(this.box);
    }

    private String examineItem(String itemName) {
        Optional<Item> item = Optional.ofNullable(this.room.getItem(itemName));
        return item.isPresent() ? item.get().getDescription() : "Item not found.";
    }

//    private void addKnownActions() {
//        this.knownActions.put(ActionOld.OPEN, (itemName) -> this.openAction(itemName));
//        this.knownActions.put(ActionOld.EXAMINE, (itemName) -> this.examineItem(itemName));
//    }*/
}

