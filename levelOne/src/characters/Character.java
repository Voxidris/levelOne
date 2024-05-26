package characters;

import back.Entity;
import items.Inventory;

/**
 * La classe <code>Character</code> représente un personnage dans le jeu.
 * Elle hérite de la classe <code>Entity</code> et ajoute des fonctionnalités 
 * spécifiques aux personnages, telles qu'un inventaire et de l'argent.
 */
public abstract class Character extends Entity {
    /**
     * L'inventaire du personnage.
     */
    private Inventory inventory;

    /**
     * L'argent possédé par le personnage.
     */
    public double money;

    /**
     * Constructeur de la classe <code>Character</code>.
     * 
     * @param name le nom du personnage.
     * @param spritepath le chemin d'accès du sprite du personnage.
     * @param inventory l'inventaire du personnage.
     * @param money l'argent possédé par le personnage.
     */
    public Character(String name, String spritepath, Inventory inventory, double money) {
        super(name, spritepath);
        this.inventory = inventory;
        this.money = money;
    }

    /**
     * Retourne l'argent possédé par le personnage.
     * 
     * @return l'argent possédé par le personnage.
     */
    public double getMoney() {
        return money;
    }

    /**
     * Définit l'argent possédé par le personnage.
     * 
     * @param money le nouvel argent possédé par le personnage.
     */
    public void setMoney(double money) {
        this.money = money;
    }

    /**
     * Retourne l'inventaire du personnage.
     * 
     * @return l'inventaire du personnage.
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * Définit l'inventaire du personnage.
     * 
     * @param i le nouvel inventaire du personnage.
     */
    public void setInventory(Inventory i) {
        this.inventory = i;
    }
}
