package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.games.treasurehunt.TreasureHunt;
import ar.fiuba.tdd.tp.red.server.Command;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Created by Fede on 26/04/2016.
 */
public class TreasureHuntTest {
    private TreasureHunt target = new TreasureHunt();

    @Test
    public void testRoomOne() {

        this.target.start();

        String response = this.target.play(new Command(Action.LOOK_AROUND, ""));
        assertEquals("Items in the room: Box1, Closet1, door, Trunk1.", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(Action.PICK, "Box1"));
        assertEquals("You cant pick up a container", response);

        response = this.target.play(new Command(Action.OPEN, "Box1"));
        assertEquals("The container is opened!", response);

        response = this.target.play(new Command(Action.LOOK_AROUND, ""));
        assertEquals("Items in the room: Box1, Closet1, door, Trunk1.", response);

        response = this.target.play(new Command(Action.OPEN, "Trunk1"));
        assertEquals("The container is opened!", response);

        response = this.target.play(new Command(Action.OPEN, "Box2"));
        assertEquals("The container is opened!", response);

        response = this.target.play(new Command(Action.OPEN, "Closet1"));
        assertEquals("The container is opened!", response);

        response = this.target.play(new Command(Action.LOOK_AROUND, ""));
        assertEquals("Items in the room: Antidote1, Box1, Box2, Closet1, door, Key1, Poison1, Trunk1.", response);

    }
}