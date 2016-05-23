package ar.fiuba.tdd.tp.engine.games;

import ar.fiuba.tdd.tp.engine.models.item.Item;

/**
 * Created by Nico on 21/05/2016.
 */
public class OpenDoor {

    // Game constants
    public static final int ID_KEY = Item.generateNewId();
    public static final String OPEN = "open";
    public static final String PICK = "pick";
    public static final String PICK_SUCCESS_MESSAGE = "There you go!";
    public static final String OPEN_SUCCESS_MESSAGE = "You enter room 2";
}