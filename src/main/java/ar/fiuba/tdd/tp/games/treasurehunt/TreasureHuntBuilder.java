package ar.fiuba.tdd.tp.games.treasurehunt;

import ar.fiuba.tdd.tp.games.*;
import ar.fiuba.tdd.tp.games.behavior.Behavior;
import ar.fiuba.tdd.tp.games.cursedobject.CursedDoor;
import ar.fiuba.tdd.tp.games.items.Door;
import ar.fiuba.tdd.tp.games.items.Item;
import ar.fiuba.tdd.tp.games.items.containers.ItemContainer;

import java.util.Iterator;
import java.util.function.Predicate;

/**
 * Created by keynaka on 16/05/16.
 */
@SuppressWarnings("CPD-START")

public class TreasureHuntBuilder implements GameBuilder{
    private ConcreteGame treasureHunt;

    @Override
    public Game build() {
        treasureHunt = new ConcreteGame();
        treasureHunt.setName("Treasure Hunt");
        treasureHunt.setEndGameMessage("You won the game!");
        treasureHunt.setGameDescription("This is Treasure Hunt. Try to get out of the last room to win the game.");
        treasureHunt.setPlayer(this.buildPlayer());
        treasureHunt.addStage(this.buildRoom1());
        treasureHunt.addStage(this.buildRoom2());
        treasureHunt.addStage(this.buildRoom3());
        treasureHunt.addStage(this.buildRoom4());
        treasureHunt.addStage(this.buildRoom5());
        treasureHunt.setWinningCondition(this.buildWinningCondition());
        registerKnownActions();
        return treasureHunt;
    }

    private void registerKnownActions() {
        treasureHunt.registerKnownAction(Action.LOOK_AROUND, (itemName, args) -> this.lookAroundHandler());
        treasureHunt.registerKnownAction(Action.PICK, (itemName, args) -> this.pickHandler(itemName));
        treasureHunt.registerKnownAction(Action.OPEN, (itemName, args) -> this.openHandler(itemName));
        treasureHunt.registerKnownAction(Action.DROP, (itemName, args) -> this.dropHandler(itemName));
    }

    private String lookAroundHandler() {
        return treasureHunt.getCurrentStage().lookAround();
    }

    private String dropHandler(String itemName) {
        Item item = treasureHunt.getPlayer().getInventory().dropItem(itemName);
        return item.execute(treasureHunt, Action.DROP);
    }

    private String pickHandler(String itemName) {
        Stage currentStage = treasureHunt.getCurrentStage();
        Item item = currentStage.getItem(itemName);
        return item.execute(treasureHunt, Action.PICK);
    }

    private void pickBehavior(String itemName) {
        Stage currentStage = treasureHunt.getCurrentStage();
        Item item = currentStage.pickItem(itemName);
        treasureHunt.getPlayer().addToInventory(item);
    }

    private void pickBehavior2(String poison) {
        Stage currentStage = treasureHunt.getCurrentStage();
        currentStage.pickItem(poison);
        treasureHunt.getPlayer().modifyState(CharacterState.CURSED);
    }

    private String openHandler(String itemName) {
        Stage currentStage = treasureHunt.getCurrentStage();
        Item item = currentStage.getItem(itemName);
        return item.execute(treasureHunt, Action.OPEN);
    }

    private void openBehavior(String nextStage) {
        treasureHunt.getPlayer().setCurrentStage(nextStage);
    }

    private void openBehavior2(String container) {
        Stage currentStage = treasureHunt.getCurrentStage();

        Item item = currentStage.getItem(container);
        ItemContainer itemContainer = (ItemContainer) item;

        Iterator<Item> it = itemContainer.getAllItems().iterator();
        while (it.hasNext()) {
            currentStage.addItem(it.next());
        }
    }

    private Predicate<ConcreteGame> buildWinningCondition() {
        return (concreteGame) -> {
            return concreteGame.getPlayer().hasItem("treasure");
        };
    }

    private Player buildPlayer() {
        Player player = new Player();
        player.setCurrentStage("room1");
        return player;
    }

    private Stage buildStage(String stageName, Item... itemsToAdd) {
        Stage stage = new Stage(stageName);
        stage.addItems(itemsToAdd);
        return stage;
    }

    private Stage buildRoom1() {
        Item item = this.buildKey1();
        item.registerActionAndHelp(Action.PICK, "pick key1");
        Item door = this.buildDoor1();
        Stage room1 = buildStage("room1", door, item);
        return room1;
    }

    private Item buildKey1() {
        Behavior behavior = new Behavior();
        behavior.setActionName("pick");
        behavior.setResultMessage("There you go.");
        behavior.setExecutionCondition((game) -> true);
        behavior.setBehaviorAction((treasureHunt) -> { this.pickBehavior("key1"); });
        Item key = new Item("key1", "it's a key1.");
        key.addBehavior(behavior);
        key.addBehavior(this.buildDropKeyBehavior());
        return key;
    }

    private Behavior buildDropKeyBehavior() {
        Behavior behavior = new Behavior();
        behavior.setActionName("drop");
        behavior.setResultMessage("key dropped");
        behavior.setExecutionCondition((game) -> true);
        behavior.setBehaviorAction((treasureHunt) -> { });
        return behavior;
    }

    private Item buildDoor1() {
        Behavior behavior = new Behavior();
        behavior.setActionName("open");
        behavior.setResultMessage("Door1 opened.");
        behavior.setExecutionCondition((game) ->  game.getPlayer().hasItem("key1"));
        behavior.setBehaviorAction((treasureHunt) -> { this.openBehavior("room2"); });
        Item door = new Item("door1", "it's a door1.");
        door.addBehavior(behavior);
        return door;
    }

    private Item buildKey2() {
        Behavior behavior = new Behavior();
        behavior.setActionName("pick");
        behavior.setResultMessage("There you go.");
        behavior.setExecutionCondition((game) -> true);
        behavior.setBehaviorAction((treasureHunt) -> { this.pickBehavior("key2"); });
        Item key = new Item("key2", "it's a key2.");
        key.addBehavior(behavior);
        key.addBehavior(this.buildDropKeyBehavior());
        return key;
    }

    private Stage buildRoom2() {
        Item item = this.buildKey2();
        item.registerActionAndHelp(Action.PICK, "pick key2");
        Item door = this.buildDoor2();
        Stage room1 = buildStage("room2", door, item);
        return room1;
    }

    private Item buildDoor2() {
        Behavior behavior = new Behavior();
        behavior.setActionName("open");
        behavior.setResultMessage("Door2 opened.");
        behavior.setExecutionCondition((game) ->  game.getPlayer().hasItem("key2"));
        behavior.setBehaviorAction((treasureHunt) -> { this.openBehavior("room3"); });
        Item door = new Item("door2", "it's a door2.");
        door.addBehavior(behavior);
        return door;
    }

    private Item buildKey3() {
        Behavior behavior = new Behavior();
        behavior.setActionName("pick");
        behavior.setResultMessage("There you go.");
        behavior.setExecutionCondition((game) -> true);
        behavior.setBehaviorAction((treasureHunt) -> { this.pickBehavior("key3"); });
        Item key = new Item("key3", "it's a key3.");
        key.addBehavior(behavior);
        key.addBehavior(this.buildDropKeyBehavior());
        return key;
    }

    private Stage buildRoom3() {
        Item box = (this.buildBox());
        box.registerActionAndHelp(Action.OPEN, "open box");
        Item door = this.buildDoor3();
        Stage room = buildStage("room3", door, box);
        return room;
    }

    private Item buildBox() {
        Behavior behavior = new Behavior();
        behavior.setActionName("open");
        behavior.setResultMessage("Box opened.");
        behavior.setExecutionCondition((game) -> true);
        behavior.setBehaviorAction((treasureHunt) -> { this.openBehavior2("box"); });
        ItemContainer box = new ItemContainer("box", "it's a box.",1);
        box.addItem(this.buildKey3());
        box.addBehavior(behavior);
        return box;
    }

    private Item buildDoor3() {
        Behavior behavior = new Behavior();
        behavior.setActionName("open");
        behavior.setResultMessage("Door3 opened.");
        behavior.setExecutionCondition((game) -> game.getPlayer().hasItem("key3"));
        behavior.setBehaviorAction((treasureHunt) -> { this.openBehavior("room4"); });
        Item door = new Item("door3", "it's a door3.");
        door.addBehavior(behavior);
        return door;
    }

    private Item buildKey4() {
        Behavior behavior = new Behavior();
        behavior.setActionName("pick");
        behavior.setResultMessage("There you go.");
        behavior.setExecutionCondition((game) -> true);
        behavior.setBehaviorAction((treasureHunt) -> { this.pickBehavior("key4"); });
        Item key = new Item("key4", "it's a key4.");
        key.addBehavior(behavior);
        key.addBehavior(this.buildDropKeyBehavior());
        return key;
    }

    private Item buildPoison() {
        Behavior behavior = new Behavior();
        behavior.setActionName("pick");
        behavior.setResultMessage("You are cursed.");
        behavior.setExecutionCondition((game) -> true);
        behavior.setBehaviorAction((treasureHunt) -> { this.pickBehavior2("poison"); });
        Item poison = new Item("poison", "it's a poison.");
        poison.addBehavior(behavior);
        poison.addBehavior(this.buildDropKeyBehavior());
        return poison;
    }

    private Item buildArmario() {
        Behavior behavior = new Behavior();
        behavior.setActionName("open");
        behavior.setResultMessage("Armario opened.");
        behavior.setExecutionCondition((game) -> true);
        behavior.setBehaviorAction((treasureHunt) -> { this.openBehavior2("armario"); });
        ItemContainer box = new ItemContainer("armario", "it's a armario.",5);
        box.addItem(this.buildKey4());
        box.addItem(this.buildPoison());
        box.addBehavior(behavior);
        return box;
    }

    private Stage buildRoom4() {
        Item armario = (this.buildArmario());
        armario.registerActionAndHelp(Action.OPEN, "open armario");
        Item door = this.buildDoor4();
        Stage room = buildStage("room4", door, armario);
        return room;
    }

    private boolean doorConditionAndPlayerState(String key) {
        Player player = treasureHunt.getPlayer();
        return (player.hasItem(key));

    }

    private Item buildDoor4() {
        Behavior behavior = new Behavior();
        behavior.setActionName("open");
        behavior.setResultMessage("Door4 opened.");
        behavior.setExecutionCondition((game) -> this.doorConditionAndPlayerState("key4"));
        behavior.setBehaviorAction((treasureHunt) -> { this.openBehavior("room5"); });
        Item door = new Item("door4", "it's a door4.");
        door.addBehavior(behavior);
        return door;
    }

    private Item buildTreasure() {
        Behavior behavior = new Behavior();
        behavior.setActionName("pick");
        behavior.setResultMessage("There you go.");
        behavior.setExecutionCondition((game) -> true);
        behavior.setBehaviorAction((treasureHunt) -> { this.pickBehavior("treasure"); });
        Item treasure = new Item("treasure", "it's a treasure.");
        treasure.addBehavior(behavior);
        treasure.addBehavior(this.buildDropKeyBehavior());
        return treasure;
    }

    private Stage buildRoom5() {
        Stage stage = new Stage("room5");
        stage.addItem(this.buildTreasure());
        return stage;
    }

    @SuppressWarnings("CPD-END")

    private Door buildDoor(String name, String nextStageName) {
        Door door = new Door(name, State.CLOSED);
        door.setNextStageName(nextStageName);
        return door;
    }
}

