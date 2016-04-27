package ar.fiuba.tdd.tp.games.items;

/**
 * Created by swandelow on 4/22/16.
 */
public class Key extends Item {

    public Key() {
        super("key", "it's a key.");
    }

    public Key(String name, String description) {
        super(name, description);
    }

    public void openDoor(Door door) {
        door.open();
    }
}
