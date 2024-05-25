package map;

import back.Visible;

public class Bloc extends Visible {
	// Var
	private boolean isSolid;
	private int id;

	// Cons
	public Bloc(int id, String spritePath, boolean isSolid) {
		super(spritePath);
		this.isSolid = isSolid;
		this.id = id;

	}

	public Bloc(Bloc b) {
		super(b.getSpritePath());
		this.isSolid = b.isSolid();
		this.id = b.getId();
	}

	// GetSet
	public boolean isSolid() {
		return isSolid;
	}

	public void setSolid(boolean isSolid) {
		this.isSolid = isSolid;
	}

	public int getId() {
		return id;
	}

	// Methode
	public Bloc copieProfonde() {
		return new Bloc(this.id, getSpritePath(), this.isSolid);
	}

	public final static Bloc GRASS = new Bloc(0, "file:images/dirt.jpg", false);
	public final static Bloc RED = new Bloc(1, "file:images/rouge.jpg", true);
	public final static Bloc ROAD = new Bloc(1, "file:images/block/road.png", false);
	public final static Bloc TREE = new Bloc(1, "file:images/block/tree.jpg", true);
	public final static Bloc HOUSE = new Bloc(1, "file:images/block/house.jpg", true);
	public final static Bloc WATER = new Bloc(1, "file:images/block/water.jpg", true);

}
