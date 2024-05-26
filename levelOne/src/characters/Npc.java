package characters;

import items.Inventory;

/**
 * La classe <code>Npc</code> représente un personnage non joueur (PNJ) dans le jeu.
 * Elle hérite de la classe <code>Character</code> et ajoute des fonctionnalités spécifiques aux PNJ,
 * telles que la gestion des dialogues.
 */
public class Npc extends Character {

    /**
     * Tableau des dialogues du PNJ.
     */
    private String[] dialog;

    /**
     * Retourne le tableau des dialogues du PNJ.
     * 
     * @return le tableau des dialogues.
     */
    public String[] getDialog() {
        return dialog;
    }

    /**
     * Définit le tableau des dialogues du PNJ.
     * 
     * @param dialog le nouveau tableau des dialogues.
     */
    public void setDialog(String[] dialog) {
        this.dialog = dialog;
    }

    /**
     * Constructeur de la classe <code>Npc</code>.
     * 
     * @param name le nom du PNJ.
     * @param spritepath le chemin d'accès du sprite du PNJ.
     * @param inventory l'inventaire du PNJ.
     * @param money l'argent possédé par le PNJ.
     * @param dialog le tableau des dialogues du PNJ.
     */
    public Npc(String name, String spritepath, Inventory inventory, double money, String[] dialog) {
        super(name, spritepath, inventory, money);
        this.dialog = dialog;
    }

    /**
     * Constructeur de copie de la classe <code>Npc</code>.
     * 
     * @param other l'objet <code>Npc</code> à copier.
     */
    public Npc(Npc other) {
        super(other.getName(), other.getSpritePath(), other.getInventory(), other.getMoney());
        this.dialog = other.dialog.clone();
    }

    /**
     * Retourne une copie profonde du PNJ.
     * 
     * @return une copie profonde du PNJ.
     */
    public Npc copieProfonde() {
        return new Npc(this);
    }

    /**
     * PNJ singleton représentant Lewis.
     */
    public static Npc LEWIS = new Npc("Lewis", "file:images/npc/lewis.png", new Inventory(), 10,
            new String[] { "I saw a monster in the east", "please kill him" });

    /**
     * PNJ singleton représentant Theo.
     */
    public static Npc THEO = new Npc("Theo", "file:images/npc/theo.png", new Inventory(), 10,
            new String[] { "You have to kill 4 monsters of this world to win", "There are 2 of them in this village",
                    "3 of them are also in the forest south from here", "Don't forget to equip a weapon" });
}
