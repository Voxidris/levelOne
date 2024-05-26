package items;

import back.Entity;

/**
 * The Item class represents an item that can be used in the game.
 */
public abstract class Item extends Entity {
    private float value;
    private String description;

    /**
     * Gets the description of the item.
     *
     * @return the description of the item
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the item.
     *
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the value of the item.
     *
     * @return the value of the item
     */
    public float getValue() {
        return value;
    }

    /**
     * Sets the value of the item.
     *
     * @param value the value to set
     */
    public void setValue(float value) {
        this.value = value;
    }

    /**
     * Constructs an item with the specified attributes.
     *
     * @param name        the name of the item
     * @param spritepath  the path to the sprite of the item
     * @param value       the value of the item
     * @param description the description of the item
     */
    public Item(String name, String spritepath, float value, String description) {
        super(name, spritepath);
        this.value = value;
        this.description = description;
    }
}
