package characters;

import Items.Inventory;
import Items.Potion;
import Items.Weapon;

public abstract class CombativeCharacter extends Character {

	private int maxHp;
	private int xp;
	private int speed;
	private int currentHp;
	private int attack;
	private int defense;
	private Potion currentPotion;
	private Weapon currentWeapon;

	public Weapon getCurrentWeapon() {
		return currentWeapon;
	}

	public void setCurrentWeapon(Weapon currentWeapon) {
		this.currentWeapon = currentWeapon;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getMaxHp() {
		return maxHp;
	}

	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}

	public int getCurrentHp() {
		return currentHp;
	}

	public void setCurrentHp(int currentHp) {
		this.currentHp = currentHp;
	}

	public Potion getCurrentPotion() {
		return currentPotion;
	}

	public void setCurrentPotion(Potion currentPotion) {
		this.currentPotion = currentPotion;
	}

	public int getXp() {
		return xp;
	}

	public void setXp(int xp) {
		this.xp = xp;
	}

	public CombativeCharacter(String name, String spritepath, Inventory inventory, double money, int hp, int xp,
			int attack, int defense, int speed) {
		super(name, spritepath, inventory, money);
		this.maxHp = hp;
		this.currentHp = hp;
		this.money = money;
		this.xp = xp;
		this.speed = speed;
		this.attack = attack;
		this.defense = defense;
		this.currentWeapon = null;
	}

	public int getAttackPower() {
		int potionBoost = 0;
		if (currentPotion != null)
			if (currentPotion.getType() == Potion.ATTACK)
				potionBoost = currentPotion.getBoostValue();

		int weaponBoost = 0;
		if (currentWeapon != null)
			weaponBoost = currentWeapon.getAttackPower();
		return getAttack() + potionBoost + weaponBoost;
	}

	public int getDefensePower() {
		int potionBoost = 0;
		if (currentPotion != null)
			if (currentPotion.getType() == Potion.DEFENSE)
				potionBoost = currentPotion.getBoostValue();

		int weaponBoost = 0;
		if (currentWeapon != null)
			weaponBoost = currentWeapon.getShieldPower();
		return getDefense() + potionBoost + weaponBoost;
	}

	public int attack(CombativeCharacter c) {
		int dmgDone = this.getAttackPower() - c.getDefensePower();
		c.setCurrentHp(c.getCurrentHp() - dmgDone);
		if (this.getCurrentPotion() != null) {
			this.getCurrentPotion().DecreaseDuration(this);

			
		}
		return dmgDone;
	}

}
