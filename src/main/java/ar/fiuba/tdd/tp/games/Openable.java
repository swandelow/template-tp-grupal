package ar.fiuba.tdd.tp.games;

/**
 * Created by swandelow on 4/27/16.
 */
public interface Openable {

    /**
     * For game objects that can be opened and modify the character.
     * @param character A game character.
     * @return A result message for Open operation.
     */
    String open(Character character);
}
