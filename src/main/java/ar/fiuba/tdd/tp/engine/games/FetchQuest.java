package ar.fiuba.tdd.tp.engine.games;

import ar.fiuba.tdd.tp.engine.core.Game;
import ar.fiuba.tdd.tp.engine.models.item.Item;

/**
 * Created by Nico on 21/05/2016.
 */
public class FetchQuest extends Game {

    // Game constants
    public static final int ID_STICK = Item.generateNewId();
    public static final String PICK = "pick";

    public FetchQuest() {
        setGameName("FetchQuest");
    }
}
