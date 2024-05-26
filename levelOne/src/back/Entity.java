package back;

/**
 * La classe abstraite <code>Entity</code> représente une entité pouvant être vu dans le jeu ayant un nom.
 * Elle hérite de la classe <code>Visible</code>.
 */
public abstract class Entity extends Visible {
    /**
     * Le nom de l'entité.
     */
    private String name;

    /**
     * Retourne le nom de l'entité.
     * 
     * @return le nom de l'entité.
     */
    public String getName() {
        return name;
    }

    /**
     * Définit le nom de l'entité.
     * 
     * @param name le nouveau nom de l'entité.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Constructeur de la classe <code>Entity</code>.
     * 
     * @param name le nom de l'entité.
     * @param spritepath le chemin du sprite associé à l'entité.
     */
    public Entity(String name, String spritepath) {
        super(spritepath);
        this.name = name;
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères de l'entité.
     * 
     * @return le nom de l'entité.
     */
    @Override
    public String toString() {
        return name;
    }
    
    /**
     * Méthode abstraite pour effectuer une copie profonde de l'entité.
     * Chaque classe concrète doit implémenter cette méthode pour retourner une
     * nouvelle instance de l'entité qui est une copie profonde de l'originale.
     * 
     * @return une nouvelle instance de <code>Entity</code> qui est une copie profonde de l'originale.
     */
    public abstract Entity copieProfonde();
}
