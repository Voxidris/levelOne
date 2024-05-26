package characters;

import back.Entity;
import back.Point;
import front.Constantes;
import items.Inventory;

/**
 * La classe <code>MainCharacter</code> représente le personnage principal du jeu.
 * Elle hérite de la classe <code>CombativeCharacter</code> et ajoute des fonctionnalités 
 * spécifiques aux personnages principaux, telles que la localisation, la direction et le niveau.
 */
public class MainCharacter extends CombativeCharacter {
    
    /**
     * La localisation actuelle du personnage principal.
     */
    private Point location;

    /**
     * La direction actuelle du personnage principal.
     */
    private int direction;

    /**
     * Le niveau actuel du personnage principal.
     */
    private int level;

    /**
     * Retourne la localisation actuelle du personnage principal.
     * 
     * @return la localisation actuelle du personnage principal.
     */
    public Point getLocation() {
        return location;
    }

    /**
     * Définit la localisation actuelle du personnage principal.
     * 
     * @param location la nouvelle localisation du personnage principal.
     */
    public void setLocation(Point location) {
        this.location = location;
    }

    /**
     * Retourne le niveau actuel du personnage principal.
     * 
     * @return le niveau actuel du personnage principal.
     */
    public int getLevel() {
        return level;
    }

    /**
     * Définit le niveau actuel du personnage principal.
     * 
     * @param level le nouveau niveau du personnage principal.
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Définit la direction actuelle du personnage principal et met à jour le sprite en conséquence.
     * 
     * @param i la nouvelle direction du personnage principal.
     */
    public void setDirection(int i) {
        this.direction = i;
        switch (i) {
            case Constantes.DIRECTION_NORTH:
                this.setSprite("file:images/player/player_north.png");
                break;
            case Constantes.DIRECTION_EAST:
                this.setSprite("file:images/player/player_east.png");
                break;
            case Constantes.DIRECTION_SOUTH:
                this.setSprite("file:images/player/player_south.png");
                break;
            case Constantes.DIRECTION_WEST:
                this.setSprite("file:images/player/player_west.png");
                break;
            default:
                break;
        }
    }

    /**
     * Retourne la direction actuelle du personnage principal.
     * 
     * @return la direction actuelle du personnage principal.
     */
    public int getDirection() {
        return this.direction;
    }

    /**
     * Retourne une représentation sous forme de chaîne des statistiques du personnage principal.
     * 
     * @return une représentation sous forme de chaîne des statistiques du personnage principal.
     */
    public String toString() {
        return "Player Stats : \nLevel : " + level + "\nXp : " + getXp() + "\nAttackPower : " + this.getAttackPower() + "\nDefensePower : " + this.getDefensePower();
    }

    /**
     * Ajoute de l'expérience au personnage principal. Si l'expérience accumulée dépasse le seuil,
     * le personnage monte de niveau.
     * 
     * @param xp l'expérience à ajouter.
     */
    public void addXp(int xp) {
        this.setXp(this.getXp() + xp);
        while (this.getXp() > 5 + level) {
            levelUp();
            this.setXp(this.getXp() - 10);
        }
    }

    /**
     * Augmente le niveau du personnage principal, ainsi que ses statistiques.
     */
    public void levelUp() {
        System.out.println("level up");
        this.setLevel(level + 1);
        this.setAttack(getAttack() + 1);
        this.setDefense(getDefense() + 1);
        this.setMaxHp(getMaxHp() + 2);
        this.setCurrentHp(getMaxHp());
    }

    /**
     * Constructeur de la classe <code>MainCharacter</code>.
     * 
     * @param name le nom du personnage principal.
     * @param spritepath le chemin d'accès du sprite du personnage principal.
     * @param location la localisation actuelle du personnage principal.
     * @param inventory l'inventaire du personnage principal.
     * @param money l'argent possédé par le personnage principal.
     * @param hp les points de vie maximum du personnage principal.
     * @param xp l'expérience du personnage principal.
     * @param attack la puissance d'attaque du personnage principal.
     * @param defense la défense du personnage principal.
     * @param speed la vitesse du personnage principal.
     * @param direction la direction actuelle du personnage principal.
     */
    public MainCharacter(String name, String spritepath, Point location, Inventory inventory, float money, int hp,
            int xp, int attack, int defense, int speed, int direction) {
        super(name, spritepath, inventory, money, hp, xp, attack, defense, speed);
        this.direction = direction;
        this.location = location;
        this.level = 1;
    }

    /**
     * Le personnage principal singleton.
     */
    public static MainCharacter MAIN_CHARACTER = new MainCharacter("Toto", "file:images/player/player_south.png",
            new Point(2, 4), new Inventory(), 20, 50, 0, 10, 5, 1, Constantes.DIRECTION_SOUTH);

    @Override
    public Entity copieProfonde() {
        // TODO Auto-generated method stub
        return null;
    }
}
