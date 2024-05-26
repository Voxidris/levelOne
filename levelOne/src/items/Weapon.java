package items;
/**
 * Represents a weapon that can be used by characters in the game.
 */
public class Weapon extends Item {

    /**
     * The attack power of the weapon.
     */
    private int attackPower;

    /**
     * The shield power of the weapon.
     */
    private int shieldPower;

    /**
     * Gets the attack power of the weapon.
     *
     * @return the attack power of the weapon
     */
    public int getAttackPower() {
        return attackPower;
    }

    /**
     * Sets the attack power of the weapon.
     *
     * @param attackPower the new attack power of the weapon
     */
    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    /**
     * Gets the shield power of the weapon.
     *
     * @return the shield power of the weapon
     */
    public int getShieldPower() {
        return shieldPower;
    }

    /**
     * Sets the shield power of the weapon.
     *
     * @param shieldPower the new shield power of the weapon
     */
    public void setShieldPower(int shieldPower) {
        this.shieldPower = shieldPower;
    }

    /**
     * Constructs a new weapon with the specified attributes.
     *
     * @param name        the name of the weapon
     * @param value       the value of the weapon
     * @param description the description of the weapon
     * @param sprite      the path to the sprite image of the weapon
     * @param attackPower the attack power of the weapon
     * @param shieldPower the shield power of the weapon
     */
    public Weapon(String name, float value, String description, String sprite, int attackPower, int shieldPower) {
        super(name, sprite, value, description);
        this.attackPower = attackPower;
        this.shieldPower = shieldPower;
    }

    /**
     * Constructs a new weapon by copying the attributes of another weapon.
     *
     * @param w the weapon to copy
     */
    public Weapon(Weapon w) {
        super(w.getName(), w.getSpritePath(), w.getValue(), w.getDescription());
        this.attackPower = w.attackPower;
        this.shieldPower = w.shieldPower;
    }

    /**
     * Represents the infinity sword weapon.
     */
    public static Weapon INFINITY_SWORD = new Weapon("Infinity Sword", 10, "Strong Sword", "file:images/weapons/Infinity_Sword.png", 10, 1);

    /**
     * Represents the rusty sword weapon.
     */
    public static Weapon RUSTY_SWORD = new Weapon("Rusty Sword", 10, "Basic Sword", "file:images/weapons/Rusty_Sword.png", 5, 1);

    /**
     * Represents the spiked shield weapon.
     */
    public static Weapon SPIKED_SHIELD = new Weapon("Spiked Shield", 10, "Shield", "file:images/weapons/Spiked_Shield.png", 2, 5);

    /**
     * Creates a deep copy of the weapon.
     *
     * @return a deep copy of the weapon
     */
    @Override
    public Weapon copieProfonde() {
        return new Weapon(this);
    }
}
