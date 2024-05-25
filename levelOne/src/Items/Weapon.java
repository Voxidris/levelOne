package Items;

public class Weapon extends Item {

	private int attackPower;
	private int shieldPower;

	public int getAttackPower() {
		return attackPower;
	}

	public void setAttackPower(int attackPower) {
		this.attackPower = attackPower;
	}

	public int getShieldPower() {
		return shieldPower;
	}

	public void setShieldPower(int shieldPower) {
		this.shieldPower = shieldPower;
	}

	public Weapon(String name, float value, String description, String sprite, int attackPower,
			int shieldPower) {
		super(name, sprite, value, description);
		this.attackPower = attackPower;
		this.shieldPower = shieldPower;
	}

	public Weapon(Weapon w) {
		super(w.getName(), w.getSpritePath(), w.getValue(), w.getDescription());
		w.attackPower = attackPower;
		w.shieldPower = shieldPower;
	}

	public static Weapon INFINITY_SWORD = new Weapon("Infinity Sword", 10, "Strong Sword",
			"file:images/weapons/Infinity_Sword.png", 10, 0);
	public static Weapon RUSTY_SWORD = new Weapon("Rusty Sword", 10, "Basic Sword",
			"file:images/weapons/Rusty_Sword.png", 5, 0);
	public static Weapon SPIKED_SHIELD = new Weapon("Spiked Shield", 10, "Shield",
			"file:images/weapons/Spiked_Shield.png", 2, 5);

	@Override
	public Weapon copieProfonde() {
		return new Weapon(this);
	}

}
