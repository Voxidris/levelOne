package characters;

import back.Entity;
import items.Inventory;
import items.Item;
import items.Potion;
import items.Weapon;

/**
 * La classe <code>Merchant</code> représente un marchand dans le jeu.
 * Elle hérite de la classe <code>Character</code> et ajoute des fonctionnalités 
 * spécifiques aux marchands, telles que la gestion des achats et des ventes.
 */
public class Merchant extends Character {
    
    /**
     * Multiplicateur de coût appliqué aux prix des objets.
     */
    private double costMultiplicator;

    /**
     * Retourne le multiplicateur de coût actuel du marchand.
     * 
     * @return le multiplicateur de coût actuel.
     */
    public double getCostMultiplicator() {
        return costMultiplicator;
    }

    /**
     * Définit le multiplicateur de coût du marchand.
     * 
     * @param costMultiplicator le nouveau multiplicateur de coût.
     */
    public void setCostMultiplicator(double costMultiplicator) {
        this.costMultiplicator = costMultiplicator;
    }

    /**
     * Constructeur de la classe <code>Merchant</code>.
     * 
     * @param name le nom du marchand.
     * @param spritepath le chemin d'accès du sprite du marchand.
     * @param inventory l'inventaire du marchand.
     * @param money l'argent possédé par le marchand.
     * @param mult le multiplicateur de coût appliqué par le marchand.
     */
    public Merchant(String name, String spritepath, Inventory inventory, double money, double mult) {
        super(name, spritepath, inventory, money);
        this.costMultiplicator = mult;
    }

    /**
     * Constructeur de copie de la classe <code>Merchant</code>.
     * 
     * @param m l'objet <code>Merchant</code> à copier.
     */
    public Merchant(Merchant m) {
        super(m.getName(), m.getSpritePath(), m.getInventory(), m.getMoney());
        this.costMultiplicator = m.costMultiplicator;
    }

    /**
     * Retourne une copie profonde du marchand.
     * 
     * @return une copie profonde du marchand.
     */
    @Override
    public Entity copieProfonde() {
        return new Merchant(this);
    }

    /**
     * Achète un objet au marchand.
     * 
     * @param mc le personnage principal qui achète l'objet.
     * @param item l'objet à acheter.
     */
    public void buyItem(MainCharacter mc, Item item) {
        double value = item.getValue() * this.getCostMultiplicator();
        if (mc.getMoney() > value) {
            mc.getInventory().addItem(item);
            this.getInventory().dropItem(item);
            mc.setMoney(mc.getMoney() - value);
            this.setMoney(this.getMoney() + value);
        }
    }

    /**
     * Vend un objet au marchand.
     * 
     * @param mc le personnage principal qui vend l'objet.
     * @param item l'objet à vendre.
     */
    public void sellItem(MainCharacter mc, Item item) {
        if (this.getMoney() > item.getValue()) {
            mc.getInventory().dropItem(item);
            this.getInventory().addItem(item);
            if (item == mc.getCurrentWeapon()) {
                mc.setCurrentWeapon(null);
            }
            mc.setMoney(mc.getMoney() + item.getValue());
            this.setMoney(this.getMoney() - item.getValue());
        }
    }

    /**
     * Marchand singleton représentant un vieux marchand.
     */
    public static Merchant VIEUX = new Merchant("old man", "file:images/npc/vieux.png",
            new Inventory(new Item[] { Potion.SMALL_POTION, Potion.DEFENSE_POTION, Potion.ATTACK_POTION, Weapon.INFINITY_SWORD, Weapon.SPIKED_SHIELD }), 40, 1.5);
}
