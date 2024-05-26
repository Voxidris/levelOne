package map;

import back.Point;
import characters.Merchant;
import characters.Monster;
import characters.Npc;
import front.Constantes;
import items.Potion;
import items.Weapon;

/**
 * La classe <code>Biome</code> représente un biome dans le jeu, avec des caractéristiques
 * spécifiques telles que la hauteur, la largeur et les tuiles. Elle permet de gérer les
 * entités et les blocs dans un espace de jeu.
 */
public class Biome {
    /**
     * La hauteur du biome.
     */
    private int height;

    /**
     * La largeur du biome.
     */
    private int width;

    /**
     * Les tuiles du biome.
     */
    private Tile[][] tiles;

    /**
     * Les biomes voisins de ce biome.
     */
    private int[][] neighborScreen;

    /**
     * Constante représentant un biome de village.
     */
    public final static int VILLAGE_BIOME = 1;

    /**
     * Constante représentant un biome de forêt.
     */
    public final static int FOREST_BIOME = 2;

    /**
     * Constante représentant un biome de montagne.
     */
    public final static int MOUNTAIN_BIOME = 3;

    /**
     * Constructeur de la classe <code>Biome</code>.
     * 
     * @param i le type de biome à initialiser.
     */
    public Biome(int i) {
        if (i == VILLAGE_BIOME)
            initVillage();
        if (i == FOREST_BIOME)
            initForest();
        if (i == MOUNTAIN_BIOME)
            initMountain();
    }

    /**
     * Retourne la matrice des biomes voisins.
     * 
     * @return la matrice des biomes voisins.
     */
    public int[][] getNeighborScreen() {
        return neighborScreen;
    }

    /**
     * Définit la matrice des biomes voisins.
     * 
     * @param neighborScreen la nouvelle matrice des biomes voisins.
     */
    public void setNeighborScreen(int[][] neighborScreen) {
        this.neighborScreen = neighborScreen;
    }

    /**
     * Retourne le biome voisin dans une direction donnée.
     * 
     * @param direction la direction du voisin.
     * @param coordinate la coordonnée dans la direction donnée.
     * @return le biome voisin dans la direction et à la coordonnée données.
     */
    public int getNeighbor(int direction, int coordinate) {
        return this.neighborScreen[direction][coordinate];
    }

    /**
     * Définit un biome voisin dans une direction donnée.
     * 
     * @param biome le type de biome à définir.
     * @param p le point de la tuile.
     * @param direction la direction du biome voisin.
     */
    public void setNeighbor(int biome, Point p, int direction) {
        neighborScreen[p.getX()][p.getY()] = biome;
    }

    /**
     * Retourne la tuile à un point donné.
     * 
     * @param p le point de la tuile.
     * @return la tuile au point donné.
     */
    public Tile getTile(Point p) {
        return this.tiles[p.getY()][p.getX()];
    }

    /**
     * Définit une tuile à un point donné.
     * 
     * @param p le point de la tuile.
     * @param t la tuile à définir.
     */
    public void setTile(Point p, Tile t) {
        this.tiles[p.getY()][p.getX()] = t;
    }

    /**
     * Retourne la hauteur du biome.
     * 
     * @return la hauteur du biome.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Retourne la largeur du biome.
     * 
     * @return la largeur du biome.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Vérifie si une tuile existe à un point donné.
     * 
     * @param p le point de la tuile.
     * @return <code>true</code> si la tuile existe, <code>false</code> sinon.
     */
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

    /**
     * Vérifie si un déplacement est possible vers une tuile donnée.
     * 
     * @param p le point de la tuile.
     * @return <code>true</code> si le déplacement est possible, <code>false</code> sinon.
     */
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

    /**
     * Initialise un biome de village.
     */
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
                } else if (i == 0 || j == 0) {
                    this.setTile(p, new Tile(Bloc.WALL.copieProfonde()));
                } else if (i > 5 && i < 8) {
                    this.setTile(p, new Tile(Bloc.ROAD.copieProfonde()));
                } else if (i > 20 && i < 30 && i % 2 == 0 && j % 2 == 1 && j < 15) {
                    this.setTile(p, new Tile(Bloc.HOUSE.copieProfonde()));
                } else if (i > 20 && i < 30 && j < 15) {
                    this.setTile(p, new Tile(Bloc.ROAD.copieProfonde()));
                } else {
                    this.setTile(p, new Tile(Bloc.GRASS.copieProfonde()));
                }
                if (i < 29)
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

    /**
     * Initialise un biome de forêt.
     */
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
                if ((i % 3 == 0 && j > 5) || i == 0 || i == 29) {
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
        this.getTile(new Point(6, 8)).setEntity(Potion.SMALL_POTION.copieProfonde());
        this.getTile(new Point(7, 6)).setEntity(Potion.BIG_POTION.copieProfonde());
        this.getTile(new Point(1, 1)).setEntity(new Monster(Monster.CULTIST.copieProfonde()));
        this.getTile(new Point(20, 10)).setEntity(new Monster(Monster.VERMIN.copieProfonde()));

        this.getTile(new Point(10, 10)).setEntity(Npc.LEWIS);
    }

    /**
     * Initialise un biome de montagne.
     */
    public void initMountain() {
        // Non present finalement
    }
}
