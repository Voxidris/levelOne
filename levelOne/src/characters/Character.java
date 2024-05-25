package characters;

import Items.Inventory;
import back.Entity;

public abstract class Character extends Entity {
	private Inventory inventory;
	public double money;

	public Character(String name, String spritepath, Inventory inventory, double money) {
		super(name, spritepath);
		this.inventory = inventory;
		this.money = money;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory i) {
		this.inventory = i;
	}

}
