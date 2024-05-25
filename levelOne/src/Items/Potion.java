package Items;

import back.Entity;
import characters.CombativeCharacter;

public class Potion extends Item implements IConsumables {
	private int type;
	private int boostValue;
	private int duration;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getBoostValue() {
		return boostValue;
	}

	public void setBoostValue(int boostValue) {
		this.boostValue = boostValue;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public void DecreaseDuration(CombativeCharacter c) {
		duration--;
		if (duration <= 0)
			c.setCurrentPotion(null);
	}

	public final static int HEAL = 1;
	public final static int ATTACK = 2;
	public final static int DEFENSE = 3;
	public final static int SPEED = 4;

	@Override
	public void useItem(CombativeCharacter c) {
		if (type == HEAL & duration == 1) {
			c.setCurrentHp(Math.min(getBoostValue() + c.getCurrentHp(), c.getMaxHp()));
		} else
			c.setCurrentPotion(this);

		c.getInventory().dropItem(this);
	}

	public Potion(String name, String sprite, float value, String description, int type, int boostValue, int duration) {
		super(name, sprite, value, description);
		this.type = type;
		this.boostValue = boostValue;
		this.duration = duration;
	}

	public Potion(Potion other) {
		super(other.getName(), other.getSpritePath(), other.getValue(), other.getDescription());
		this.type = other.type;
		this.boostValue = other.boostValue;
		this.duration = other.duration;
	}

	public static Potion SMALL_POTION = new Potion("Croissant", "file:images/items/croissant.png", 5, "Restore 20HP",
			HEAL, 20, 1);
	public static Potion BIG_POTION = new Potion("Baguette", "file:images/items/baguette.png", 10, "Restore 50 HP",
			HEAL, 50, 1);
	public static Potion ATTACK_POTION = new Potion("Fried_Mushroom", "file:images/items/Fried_Mushroom.png", 8,
			"Boost Attack by 5 for 3 rounds", ATTACK, 5, 3);

	public static Potion DEFENSE_POTION = new Potion("Dexterity Potion","file:images/items/DexterityPotion.png",7,"Add 3 defense for 4 rounds",DEFENSE,3,4);
	
	@Override
	public Entity copieProfonde() {
		return new Potion(this);
	}

}
