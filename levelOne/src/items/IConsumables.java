package items;

import characters.CombativeCharacter;

/**
 * The IConsumables interface represents items that can be consumed by a character in the game.
 */
public interface IConsumables {
    
    /**
     * Uses the item on the specified character.
     *
     * @param c the character on which the item is used
     */
    public abstract void useItem(CombativeCharacter c);
}
