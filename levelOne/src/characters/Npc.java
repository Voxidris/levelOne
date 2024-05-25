package characters;

import Items.Inventory;

public class Npc extends Character {

	private String[] dialog;

	public String[] getDialog() {
		return dialog;
	}

	public void setDialog(String[] dialog) {
		this.dialog = dialog;
	}

	public Npc( String name, String spritepath, Inventory inventory, double money,
			String[] dialog) {
		super(name, spritepath, inventory, money);
		this.dialog = dialog;
	}
	public Npc(Npc other) {
	    super(other.getName(), other.getSpritePath(), other.getInventory(), other.getMoney());
	    this.dialog = other.dialog.clone();
	}
	
	public Npc copieProfonde() {
		return new Npc(this);
	}


	public static Npc LEWIS = new Npc("Lewis","file:images/npc/lewis.png", new Inventory(), 10,
			new String[] { "That's my job" });
	public static Npc THEO = new Npc("Theo","file:images/npc/theo.png", new Inventory(), 10,
			new String[] { "You have to kill 4 monsters of this world to win","There is 2 of them in this village","3 of them are also in the forest south from here","Don't forget to equip a weapon" });
}
