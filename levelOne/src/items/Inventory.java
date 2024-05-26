package items;

/**
 * The Inventory class represents an inventory that can hold a collection of items.
 */
public class Inventory {
    private int maxSize;
    private int currentSize;
    private Item[] contenant;

    /**
     * Gets the maximum size of the inventory.
     *
     * @return the maximum size of the inventory
     */
    public int getMaxSize() {
        return maxSize;
    }

    /**
     * Sets the maximum size of the inventory.
     *
     * @param maxSize the maximum size to set
     */
    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    /**
     * Gets the current size of the inventory.
     *
     * @return the current size of the inventory
     */
    public int getCurrentSize() {
        return currentSize;
    }

    /**
     * Sets the current size of the inventory.
     *
     * @param currentSize the current size to set
     */
    public void setCurrentSize(int currentSize) {
        this.currentSize = currentSize;
    }

    /**
     * Gets the items contained in the inventory.
     *
     * @return an array of items in the inventory
     */
    public Item[] getContenant() {
        return contenant;
    }

    /**
     * Sets the items to be contained in the inventory.
     *
     * @param contenant an array of items to set in the inventory
     */
    public void setContenant(Item[] contenant) {
        this.contenant = contenant;
    }

    /**
     * Checks if the inventory is full.
     *
     * @return true if the inventory is full, false otherwise
     */
    public boolean isFull() {
        return this.getMaxSize() == getCurrentSize();
    }

    /**
     * Gets the item at the specified index in the inventory.
     *
     * @param indice the index of the item to retrieve
     * @return the item at the specified index, or null if the index is out of bounds
     */
    public Item getItem(int indice) {
        if (indice < this.currentSize)
            return contenant[indice];
        else
            return null;
    }

    /**
     * Adds an item to the inventory if there is space.
     *
     * @param i the item to add
     */
    public void addItem(Item i) {
        if (!isFull()) {
            contenant[getCurrentSize()] = i;
            this.currentSize++;
        }
    }

    /**
     * Drops the specified item from the inventory.
     *
     * @param item the item to drop
     */
    public void dropItem(Item item) {
        for (int i = 0; i < getCurrentSize(); i++) {
            if (contenant[i] == item) {
                for (int j = i; j < getCurrentSize() - 1; j++) {
                    contenant[j] = contenant[j + 1];
                }
                contenant[getCurrentSize() - 1] = null;
                this.currentSize--;
                break;
            }
        }
    }

    /**
     * Constructs an inventory with the specified items.
     *
     * @param contenant an array of items to initialize the inventory with
     */
    public Inventory(Item[] contenant) {
        this.maxSize = INVENTORY_SIZE;
        this.contenant = new Item[maxSize];
        for (Item i : contenant) {
            addItem(i);
        }
        this.currentSize = contenant.length;
    }

    /**
     * Constructs an empty inventory.
     */
    public Inventory() {
        this.maxSize = INVENTORY_SIZE;
        this.contenant = new Item[this.maxSize];
        this.currentSize = 0;
    }

    /**
     * The constant representing the default size of the inventory.
     */
    public static final int INVENTORY_SIZE = 20;
}
