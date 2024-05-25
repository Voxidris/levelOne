package map;

import Items.Potion;
import Items.Weapon;
import back.Point;
import characters.Merchant;
import characters.Monster;
import characters.Npc;
import front.Constantes;

public class Biome {
	// Var
	private int height;
	private int width;

	private Tile[][] tiles;
	private int[][] neighborScreen;

	public final static int VILLAGE_BIOME = 1;
	public final static int FOREST_BIOME = 2;
	public final static int MOUNTAIN_BIOME = 3;

	// Super
	public Biome(int i) {
		if (i == VILLAGE_BIOME)
			initVillage();
		if (i == FOREST_BIOME)
			initForest();
		if (i == MOUNTAIN_BIOME)
			initMountain();

	}

	public int[][] getNeighborScreen() {
		return neighborScreen;
	}

	public void setNeighborScreen(int[][] neighborScreen) {
		this.neighborScreen = neighborScreen;
	}

	public int getNeighbor(int direction, int coordinate) {
		return this.neighborScreen[direction][coordinate];
	}

	public void setNeighbor(int biome, Point p, int direction) {
		neighborScreen[p.getX()][p.getY()] = biome;
	}

	// GetSet
	public Tile getTile(Point p) {
		return this.tiles[p.getY()][p.getX()];
	}

	public void setTile(Point p, Tile t) {
		this.tiles[p.getY()][p.getX()] = t;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	// Meth
	public boolean isTileExist(Point p) {

		if (p.getX() < 0 || p.getY() < 0) {
			System.out.println("Bloque");
			return false;
		}
		if (p.getX() >= this.width || p.getY() >= this.height) {
			System.out.println("Bloque");
			return false;
		}
		return true;
	}

	public boolean moveIsPossible(Point p) {

		if (!this.isTileExist(p)) {
			System.out.println("Bloque");
			return false;
		}
		if (this.getTile(p).getBloc().isSolid()) {
			System.out.println("Bloque");
			return false;
		}
		if (this.getTile(p).getEntity() != null) {
			return false;
		}

		return true;
	}

	public void initVillage() {

		this.height = 20;
		this.width = 40;
		this.tiles = new Tile[this.height][this.width];
		this.neighborScreen = new int[4][Math.max(this.height, this.width)];

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < Math.max(this.height, this.width); j++) {
				neighborScreen[i][j] = 0;
			}
		}
		for (int i = 0; i < this.getWidth(); i++) {
			for (int j = 0; j < this.getHeight(); j++) {
				Point p = new Point(i, j);
				if (i > 35) {
					this.setTile(p, new Tile(Bloc.WATER.copieProfonde()));
				} else if (i > 5 & i < 8) {
					this.setTile(p, new Tile(Bloc.ROAD.copieProfonde()));

				} else if (i > 20 & i < 30 & i % 2 == 0 & j % 2 == 1 & j < 15) {
					this.setTile(p, new Tile(Bloc.HOUSE.copieProfonde()));
				} else
					this.setTile(p, new Tile(Bloc.GRASS.copieProfonde()));
				// this.getTile(i, j).setBloc(Bloc.BLOC_GRASS.copieProfonde());
				if (i <= 30)
					neighborScreen[Constantes.DIRECTION_SOUTH][i] = FOREST_BIOME;

			}
		}

		this.getTile(new Point(5, 5)).setEntity(Npc.THEO.copieProfonde());
		this.getTile(new Point(8, 2)).setEntity(Merchant.VIEUX.copieProfonde());

		this.getTile(new Point(7, 9)).setEntity(Weapon.INFINITY_SWORD.copieProfonde());
		this.getTile(new Point(7, 8)).setEntity(Weapon.RUSTY_SWORD.copieProfonde());
		this.getTile(new Point(6, 8)).setEntity(Potion.SMALL_POTION.copieProfonde());
		this.getTile(new Point(7, 6)).setEntity(Potion.ATTACK_POTION.copieProfonde());
		this.getTile(new Point(1, 1)).setEntity(new Monster(Monster.CULTIST.copieProfonde()));
		this.getTile(new Point(20, 10)).setEntity(new Monster(Monster.VERMIN.copieProfonde()));

	}

	public void initForest() {
		this.height = 18;
		this.width = 30;
		this.tiles = new Tile[this.height][this.width];
		this.neighborScreen = new int[4][Math.max(this.height, this.width)];

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < Math.max(this.height, this.width); j++) {
				neighborScreen[i][j] = 0;
			}
		}

		for (int i = 0; i < this.getWidth(); i++) {
			for (int j = 0; j < this.getHeight(); j++) {
				Point p = new Point(i, j);
				if ((i % 3 == 0 & j > 5) | i == 0 | i == 29) {
					this.setTile(p, new Tile(Bloc.TREE.copieProfonde()));
				} else if (i > 5 && i < 8 && j > 5 && j < 8) {
					this.setTile(p, new Tile(Bloc.HOUSE.copieProfonde()));
				} else if (i > 15 && i < 18 && j > 12 && j < 15) {
					this.setTile(p, new Tile(Bloc.HOUSE.copieProfonde()));
				} else {
					this.setTile(p, new Tile(Bloc.GRASS.copieProfonde()));
				}
				if (i <= 30)
					neighborScreen[Constantes.DIRECTION_NORTH][i] = VILLAGE_BIOME;
			}
		}

		this.getTile(new Point(7, 9)).setEntity(Weapon.INFINITY_SWORD);
		this.getTile(new Point(7, 8)).setEntity(Weapon.RUSTY_SWORD);
		this.getTile(new Point(6, 8)).setEntity(Potion.SMALL_POTION.copieProfonde());
		this.getTile(new Point(7, 6)).setEntity(Potion.BIG_POTION.copieProfonde());
		this.getTile(new Point(1, 1)).setEntity(new Monster(Monster.CULTIST.copieProfonde()));
		this.getTile(new Point(20, 10)).setEntity(new Monster(Monster.VERMIN.copieProfonde()));

		this.getTile(new Point(10, 10)).setEntity(Npc.LEWIS);

	}

	public void initMountain() {

	}

	// this.getTile(3, 3).setUpBloc(Bloc.BLOC_HOUSE.copieProfonde());

	// String[] dialog = {"Bonjour, prend ce present."};
	// this.getTile(5, 5).setPnj(new Pnj("./images/vieux.png",dialog));
}
