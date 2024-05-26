package items;

import back.Entity;
import characters.CombativeCharacter;

/**
 * The Potion class represents a potion item that can be consumed by a character
 * in the game.
 */
public class Potion extends Item implements IConsumables {
	private int type;
	private int boostValue;
	private int duration;

	/**
	 * Gets the type of the potion.
	 *
	 * @return the type of the potion
	 */
	public int getType() {
		return type;
	}

	/**
	 * Sets the type of the potion.
	 *
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * Gets the boost value of the potion.
	 *
	 * @return the boost value of the potion
	 */
	public int getBoostValue() {
		return boostValue;
	}

	/**
	 * Sets the boost value of the potion.
	 *
	 * @param boostValue the boost value to set
	 */
	public void setBoostValue(int boostValue) {
		this.boostValue = boostValue;
	}

	/**
	 * Gets the duration of the potion.
	 *
	 * @return the duration of the potion
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * Sets the duration of the potion.
	 *
	 * @param duration the duration to set
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}

	/**
	 * Decreases the duration of the potion and removes it from the character's
	 * inventory if the duration reaches 0.
	 *
	 * @param c the character using the potion
	 */
	public void DecreaseDuration(CombativeCharacter c) {
		duration--;
		if (duration <= 0)
			c.setCurrentPotion(null);
	}

	// Constants for potion types
	public final static int HEAL = 1;
	public final static int ATTACK = 2;
	public final static int DEFENSE = 3;
	public final static int SPEED = 4;

	/**
	 * Uses the potion item on the given character.
	 *
	 * @param c the character to use the potion on
	 */
	@Override
	public void useItem(CombativeCharacter c) {
		if (type == HEAL & duration == 1) {
			c.setCurrentHp(Math.min(getBoostValue() + c.getCurrentHp(), c.getMaxHp()));
		} else
			c.setCurrentPotion(this);

		c.getInventory().dropItem(this);
	}

	/**
	 * Constructs a potion with the specified attributes.
	 *
	 * @param name        the name of the potion
	 * @param sprite      the path to the sprite of the potion
	 * @param value       the value of the potion
	 * @param description the description of the potion
	 * @param type        the type of the potion
	 * @param boostValue  the boost value of the potion
	 * @param duration    the duration of the potion
	 */
	public Potion(String name, String sprite, float value, String description, int type, int boostValue, int duration) {
		super(name, sprite, value, description);
		this.type = type;
		this.boostValue = boostValue;
		this.duration = duration;
	}

	/**
	 * Constructs a new potion by copying the attributes of another potion.
	 *
	 * @param other the potion to copy
	 */
	public Potion(Potion other) {
		super(other.getName(), other.getSpritePath(), other.getValue(), other.getDescription());
		this.type = other.type;
		this.boostValue = other.boostValue;
		this.duration = other.duration;
	}

	// Predefined potion instances
	public static Potion SMALL_POTION = new Potion("Croissant", "file:images/items/croissant.png", 5, "Restore 20HP",
			HEAL, 20, 1);
	public static Potion BIG_POTION = new Potion("Baguette", "file:images/items/baguette.png", 10, "Restore 50 HP",
			HEAL, 50, 1);
	public static Potion ATTACK_POTION = new Potion("Fried_Mushroom", "file:images/items/Fried_Mushroom.png", 8,
			"Boost Attack by 5 for 3 rounds", ATTACK, 5, 3);
	public static Potion DEFENSE_POTION = new Potion("Dexterity Potion", "file:images/items/DexterityPotion.png", 7,
			"Add 3 defense for 4 rounds", DEFENSE, 3, 4);

	/**
	 * Creates a deep copy of the potion.
	 *
	 * @return a deep copy of the potion
	 */
	@Override
	public Entity copieProfonde() {
		return new Potion(this);
	}
}
