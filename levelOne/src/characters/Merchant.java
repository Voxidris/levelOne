package characters;

import Items.Inventory;
import Items.Item;
import Items.Potion;
import Items.Weapon;
import back.Entity;

public class Merchant extends Character {
	private double costMultiplicator;

	public double getCostMultiplicator() {
		return costMultiplicator;
	}

	public void setCostMultiplicator(double costMultiplicator) {
		this.costMultiplicator = costMultiplicator;
	}

	public Merchant(String name, String spritepath, Inventory inventory, double money, double mult) {
		super(name, spritepath, inventory, money);
		this.costMultiplicator = mult;

	}

	public Merchant(Merchant m) {
		super(m.getName(), m.getSpritePath(), m.getInventory(), m.getMoney());
		this.costMultiplicator = m.costMultiplicator;
	}

	public Entity copieProfonde() {
		return new Merchant(this);
	}

	public static Merchant VIEUX = new Merchant("old man", "file:images/npc/vieux.png",
			new Inventory(new Item[] { Potion.SMALL_POTION, Potion.DEFENSE_POTION,Potion.ATTACK_POTION,Weapon.INFINITY_SWORD, Weapon.SPIKED_SHIELD }), 10, 1.5);

	public void buyItem(MainCharacter mc, Item item) {
		double value = item.getValue() * this.getCostMultiplicator();

		if (mc.getMoney() > value) {

			mc.getInventory().addItem(item);
			this.getInventory().dropItem(item);

			mc.setMoney(mc.getMoney() - value);
			this.setMoney(this.getMoney() + value);

		}
	}

	public void sellItem(MainCharacter mc, Item item) {
		if (this.getMoney() > item.getValue()) {
			mc.getInventory().dropItem(item);
			this.getInventory().addItem(item);

			mc.setMoney(mc.getMoney() + item.getValue());
			this.setMoney(this.getMoney() - item.getValue());
		}
	}
}
