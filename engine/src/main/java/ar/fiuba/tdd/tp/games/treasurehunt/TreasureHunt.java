package ar.fiuba.tdd.tp.games.treasurehunt;

/**
 * Created by Fede on 26/04/2016.
 */
public class TreasureHunt /*extends AbstractGame*/ {
/*
    private ArrayList<Stage> rooms;
    private int actualRoom;
    private Player player;
    private boolean isPoisoned;
    private Item treasure;

    private static final int CLOSET_MAX  = 5;
    private static final int TRUNK_MAX  = 5;
    private static final int BOX_MAX  = 1;


    public TreasureHunt() {
        super("TreasureHunt", "You are at room 1 and found the treasure. You won the game!");
    }

    @Override
    protected void doStart() {
        this.treasure = new Item("Treasure", "Its the treasure!");
        this.actualRoom = 0;
        this.rooms = new ArrayList<Stage>();
        this.player = new Player();
        this.buildRoomOne();
        //this.buildRoomTwo();
        //this.buildRoomThree();
        //this.buildRoomFour();
        //this.buildRoomFive();
    }

    @Override
    protected void registerKnownActions() {
        this.knownActions.put(ActionOld.LOOK_AROUND, (itemName, args) -> this.lookAround());
        this.knownActions.put(ActionOld.OPEN, (itemName, args) -> this.open(itemName));
        this.knownActions.put(ActionOld.PICK, (itemName, args) -> this.pick(itemName));
        this.knownActions.put(ActionOld.DROP, (itemName, args) -> this.drop(itemName));
    }

    private void buildRoomOne() {
        Stage stage = new Stage();

        Item antidote1 = new Item("Antidote1", "Antidote1");
        Key key1 = new Key("Key1", "Key1");

        ItemContainer box1 = new ItemContainer("Box1", "Box1", BOX_MAX);
        stage.addItem(box1);

        ItemContainer box2 = new ItemContainer("Box2", "Box2", BOX_MAX);
        box2.addItem(key1);

        ItemContainer trunk = new ItemContainer("Trunk1", "Trunk1", TRUNK_MAX);
        trunk.addItem(antidote1);
        trunk.addItem(box2);

        ItemContainer closet = new ItemContainer("Closet1", "Closet1", CLOSET_MAX);
        Item poison1 = new Item("Poison1", "Poison1");
        closet.addItem(poison1);

        LockedDoor door = new LockedDoor(key1.getName());

        stage.addItem(closet);
        stage.addItem(trunk);
        stage.addItem(door);

        this.rooms.add(stage);
    }

    /*private void buildRoomTwo() {

    }

    private void buildRoomThree() {

    }

    private void buildRoomFour() {

    }

    private void buildRoomFive() {

    }

    @Override
    public boolean isFinished() {
        return (this.player.hasItem(treasure.getName()) && this.actualRoom == 0);
    }

    @Override
    public String getDescription() {
        return "Welcome to: Treasure Hunt. Acepted commands: look around, open, pick";
    }

    private String lookAround() {
        return this.rooms.get(this.actualRoom).lookAround();
    }

    private String pick(String itemName) {
        Item item = this.rooms.get(this.actualRoom).getItem(itemName);
        if (item instanceof ItemContainer) {
            return "You cant pick up a container";
        }
        if (this.player.getInventory().size() < 2) {
            this.player.getInventory().addItem(item);
        }
        return "Ok";
    }


    private String drop(String item) {
        //TODO
        return "";

    }

    private String open(String itemName) {
        Item item = this.rooms.get(this.actualRoom).getItem(itemName);
        if (item instanceof ItemContainer) {
            Collection<Item> extractedItems = ((ItemContainer) item).extractAll();
            this.isPoisoned = this.hasPoison(extractedItems);
            for (Item extractedItem : extractedItems) {
                this.rooms.get(this.actualRoom).addItem(extractedItem);
            }
            return "The container is opened!";
        }

        if (item instanceof Door && !isPoisoned) {
            ((Door) item).open();
            return "The door is opened!";
        }


        return "You can only open containers and doors.";
    }

    private boolean hasPoison(Collection<Item> extractedItems) {
        for (Item extractedItem : extractedItems) {
            if (extractedItem.getName().startsWith("Poison")) {
                return true;
            }
        }
        return false;
    }*/
}
