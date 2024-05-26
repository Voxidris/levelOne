package map;

import back.Visible;

/**
 * Represents a block in the game map.
 */
public class Bloc extends Visible {

    /**
     * Indicates whether the block is solid.
     */
    private boolean isSolid;

    /**
     * The ID of the block.
     */
    private int id;

    /**
     * Constructs a new block with the specified ID, sprite path, and solidity.
     *
     * @param id         the ID of the block
     * @param spritePath the path to the sprite image of the block
     * @param isSolid    indicates whether the block is solid
     */
    public Bloc(int id, String spritePath, boolean isSolid) {
        super(spritePath);
        this.isSolid = isSolid;
        this.id = id;
    }

    /**
     * Constructs a new block by copying the attributes of another block.
     *
     * @param b the block to copy
     */
    public Bloc(Bloc b) {
        super(b.getSpritePath());
        this.isSolid = b.isSolid();
        this.id = b.getId();
    }

    /**
     * Gets whether the block is solid.
     *
     * @return true if the block is solid, otherwise false
     */
    public boolean isSolid() {
        return isSolid;
    }

    /**
     * Sets whether the block is solid.
     *
     * @param isSolid true to make the block solid, otherwise false
     */
    public void setSolid(boolean isSolid) {
        this.isSolid = isSolid;
    }

    /**
     * Gets the ID of the block.
     *
     * @return the ID of the block
     */
    public int getId() {
        return id;
    }

    /**
     * Creates a deep copy of the block.
     *
     * @return a deep copy of the block
     */
    public Bloc copieProfonde() {
        return new Bloc(this.id, getSpritePath(), this.isSolid);
    }

    /**
     * Represents a grass block.
     */
    public final static Bloc GRASS = new Bloc(0, "file:images/dirt.jpg", false);

    /**
     * Represents a red block.
     */
    public final static Bloc RED = new Bloc(1, "file:images/rouge.jpg", true);

    /**
     * Represents a road block.
     */
    public final static Bloc ROAD = new Bloc(1, "file:images/block/road.png", false);

    /**
     * Represents a tree block.
     */
    public final static Bloc TREE = new Bloc(1, "file:images/block/tree.jpg", true);

    /**
     * Represents a house block.
     */
    public final static Bloc HOUSE = new Bloc(1, "file:images/block/house.jpg", true);

    /**
     * Represents a water block.
     */
    public final static Bloc WATER = new Bloc(1, "file:images/block/water.jpg", true);

    /**
     * Represents a wall block.
     */
    public final static Bloc WALL = new Bloc(1, "file:images/block/default_wall.png", true);
}
