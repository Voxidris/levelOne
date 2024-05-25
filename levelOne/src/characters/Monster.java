package characters;

import java.util.Random;

import Items.IConsumables;
import Items.Inventory;
import Items.Item;
import Items.Potion;

/**
 * The Monster class represents a combative character that has an inventory of loot.
 * It extends the CombativeCharacter class.
 */
public class Monster extends CombativeCharacter {
    /**
     * The inventory of loot that the monster has.
     */
    Inventory loot;

    /**
     * Gets the inventory of loot.
     *
     * @return the loot inventory
     */
    public Inventory getLoot() {
        return loot;
    }

    /**
     * Sets the inventory of loot.
     *
     * @param loot the loot inventory to set
     */
    public void setLoot(Inventory loot) {
        this.loot = loot;
    }

    /**
     * Constructs a Monster with the specified attributes.
     *
     * @param name the name of the monster
     * @param spritepath the path to the monster's sprite image
     * @param inventory the monster's inventory
     * @param money the amount of money the monster has
     * @param hp the health points of the monster
     * @param level the level of the monster
     * @param attack the attack value of the monster
     * @param defense the defense value of the monster
     * @param speed the speed value of the monster
     * @param loots the inventory of loot the monster has
     */
    public Monster(String name, String spritepath, Inventory inventory, double money, int hp, int xp, int attack,
                   int defense, int speed, Inventory loots) {
        super(name, spritepath, inventory, money, hp, xp, attack, defense, speed);
        this.loot = loots;
    }

    /**
     * Constructs a Monster by copying another Monster.
     *
     * @param m the Monster to copy
     */
    public Monster(Monster m) {
        super(m.getName(), m.getSpritePath(), m.getInventory(), m.getMoney(), m.getMaxHp(), m.getXp(), m.getAttack(),
              m.getDefense(), m.getSpeed());
        this.loot = m.getLoot();
    }

    /**
     * Creates a deep copy of this Monster.
     *
     * @return a new Monster that is a deep copy of this Monster
     */
    @Override
    public Monster copieProfonde() {
        return new Monster(this);
    }
    
    /**
     * Adds the loot from this Monster to the specified main character.
     *
     * @param mc the main character to receive the loot
     */
    public void addLoot(MainCharacter mc) {
        for (Item i : loot.getContenant()) {
            mc.getInventory().addItem(i);
        }
        mc.setMoney(mc.getMoney() + this.getMoney());
        mc.addXp(this.getXp());
    }
    
    public IConsumables itemToUse() {
	Random r = new Random();
	if (r.nextInt(11) >= 6 && (this.getCurrentHp() <= this.getMaxHp() / 2)) {
		for (Item i : this.getInventory().getContenant()) {
			if (i instanceof IConsumables)
				return (IConsumables) i;
		}
	}
	return null;

    }
    /**
     * A static instance of a Cultist Monster.
     */
    public static Monster CULTIST = new Monster("Cultist", "file:images/monsters/Cultist-pretty.png",
            new Inventory(new Item[] { Potion.SMALL_POTION, Potion.SMALL_POTION }),
            1, 30, 5, 10, 5, 0, new Inventory(new Item[] { Potion.SMALL_POTION }));

    /**
     * A static instance of a Vermin Monster.
     */
    public static Monster VERMIN = new Monster("Vermin", "file:images/monsters/Gigantic_Vermin.png",
            new Inventory(new Item[] { Potion.BIG_POTION, Potion.BIG_POTION, Potion.SMALL_POTION }),
            1, 30, 5, 10, 10, 0, new Inventory(new Item[] { Potion.BIG_POTION }));
}
