package characters;

import items.Inventory;
import items.Potion;
import items.Weapon;

/**
 * La classe <code>CombativeCharacter</code> représente un personnage combattant dans le jeu.
 * Elle hérite de la classe <code>Character</code> et ajoute des fonctionnalités 
 * spécifiques aux personnages combattants, telles que des points de vie, de l'attaque et de la défense.
 */
public abstract class CombativeCharacter extends Character {

    /**
     * Les points de vie maximum du personnage.
     */
    private int maxHp;

    /**
     * L'expérience du personnage.
     */
    private int xp;

    /**
     * La vitesse du personnage non utilisé dans le jeu finalement.
     */
    private int speed;

    /**
     * Les points de vie actuels du personnage.
     */
    private int currentHp;

    /**
     * La puissance d'attaque du personnage.
     */
    private int attack;

    /**
     * La défense du personnage.
     */
    private int defense;

    /**
     * La potion actuellement utilisée par le personnage.
     */
    private Potion currentPotion;

    /**
     * L'arme actuellement utilisée par le personnage.
     */
    private Weapon currentWeapon;

    /**
     * Constructeur de la classe <code>CombativeCharacter</code>.
     * 
     * @param name le nom du personnage.
     * @param spritepath le chemin d'accès du sprite du personnage.
     * @param inventory l'inventaire du personnage.
     * @param money l'argent possédé par le personnage.
     * @param hp les points de vie maximum du personnage.
     * @param xp l'expérience du personnage.
     * @param attack la puissance d'attaque du personnage.
     * @param defense la défense du personnage.
     * @param speed la vitesse du personnage.
     */
    public CombativeCharacter(String name, String spritepath, Inventory inventory, double money, int hp, int xp,
            int attack, int defense, int speed) {
        super(name, spritepath, inventory, money);
        this.maxHp = hp;
        this.currentHp = hp;
        this.money = money;
        this.xp = xp;
        this.speed = speed;
        this.attack = attack;
        this.defense = defense;
        this.currentWeapon = null;
    }

    /**
     * Retourne l'arme actuellement utilisée par le personnage.
     * 
     * @return l'arme actuellement utilisée par le personnage.
     */
    public Weapon getCurrentWeapon() {
        return currentWeapon;
    }

    /**
     * Définit l'arme actuellement utilisée par le personnage.
     * 
     * @param currentWeapon la nouvelle arme utilisée par le personnage.
     */
    public void setCurrentWeapon(Weapon currentWeapon) {
        this.currentWeapon = currentWeapon;
    }

    /**
     * Retourne la puissance d'attaque du personnage.
     * 
     * @return la puissance d'attaque du personnage.
     */
    public int getAttack() {
        return attack;
    }

    /**
     * Définit la puissance d'attaque du personnage.
     * 
     * @param attack la nouvelle puissance d'attaque du personnage.
     */
    public void setAttack(int attack) {
        this.attack = attack;
    }

    /**
     * Retourne la défense du personnage.
     * 
     * @return la défense du personnage.
     */
    public int getDefense() {
        return defense;
    }

    /**
     * Définit la défense du personnage.
     * 
     * @param defense la nouvelle défense du personnage.
     */
    public void setDefense(int defense) {
        this.defense = defense;
    }

    /**
     * Retourne la vitesse du personnage.
     * 
     * @return la vitesse du personnage.
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Définit la vitesse du personnage.
     * 
     * @param speed la nouvelle vitesse du personnage.
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * Retourne les points de vie maximum du personnage.
     * 
     * @return les points de vie maximum du personnage.
     */
    public int getMaxHp() {
        return maxHp;
    }

    /**
     * Définit les points de vie maximum du personnage.
     * 
     * @param maxHp les nouveaux points de vie maximum du personnage.
     */
    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    /**
     * Retourne les points de vie actuels du personnage.
     * 
     * @return les points de vie actuels du personnage.
     */
    public int getCurrentHp() {
        return currentHp;
    }

    /**
     * Définit les points de vie actuels du personnage.
     * 
     * @param currentHp les nouveaux points de vie actuels du personnage.
     */
    public void setCurrentHp(int currentHp) {
        this.currentHp = currentHp;
    }

    /**
     * Retourne la potion actuellement utilisée par le personnage.
     * 
     * @return la potion actuellement utilisée par le personnage.
     */
    public Potion getCurrentPotion() {
        return currentPotion;
    }

    /**
     * Définit la potion actuellement utilisée par le personnage.
     * 
     * @param currentPotion la nouvelle potion utilisée par le personnage.
     */
    public void setCurrentPotion(Potion currentPotion) {
        this.currentPotion = currentPotion;
    }

    /**
     * Retourne l'expérience du personnage.
     * 
     * @return l'expérience du personnage.
     */
    public int getXp() {
        return xp;
    }

    /**
     * Définit l'expérience du personnage.
     * 
     * @param xp la nouvelle expérience du personnage.
     */
    public void setXp(int xp) {
        this.xp = xp;
    }

    /**
     * Calcule et retourne la puissance d'attaque totale du personnage,
     * incluant les bonus de potion et d'arme.
     * 
     * @return la puissance d'attaque totale du personnage.
     */
    public int getAttackPower() {
        int potionBoost = 0;
        if (currentPotion != null && currentPotion.getType() == Potion.ATTACK) {
            potionBoost = currentPotion.getBoostValue();
        }

        int weaponBoost = 0;
        if (currentWeapon != null) {
            weaponBoost = currentWeapon.getAttackPower();
        }
        return getAttack() + potionBoost + weaponBoost;
    }

    /**
     * Calcule et retourne la puissance de défense totale du personnage,
     * incluant les bonus de potion et d'arme.
     * 
     * @return la puissance de défense totale du personnage.
     */
    public int getDefensePower() {
        int potionBoost = 0;
        if (currentPotion != null && currentPotion.getType() == Potion.DEFENSE) {
            potionBoost = currentPotion.getBoostValue();
        }

        int weaponBoost = 0;
        if (currentWeapon != null) {
            weaponBoost = currentWeapon.getShieldPower();
        }
        return getDefense() + potionBoost + weaponBoost;
    }

    /**
     * Effectue une attaque sur un autre personnage combattant.
     * 
     * @param c le personnage combattant à attaquer.
     * @return les dégâts infligés.
     */
    public int attack(CombativeCharacter c) {
        int dmgDone = this.getAttackPower() - c.getDefensePower();
        c.setCurrentHp(c.getCurrentHp() - dmgDone);
        if (this.getCurrentPotion() != null) {
            this.getCurrentPotion().DecreaseDuration(this);
        }
        return dmgDone;
    }
}
