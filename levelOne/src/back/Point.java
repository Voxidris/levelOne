package back;

/**
 * La classe <code>Point</code> représente un point dans un espace bidimensionnel.
 */
public class Point {
    /**
     * La coordonnée x du point.
     */
    private int x;

    /**
     * La coordonnée y du point.
     */
    private int y;

    /**
     * Retourne la coordonnée x du point.
     * 
     * @return la coordonnée x du point.
     */
    public int getX() {
        return x;
    }

    /**
     * Définit la coordonnée x du point.
     * 
     * @param x la nouvelle coordonnée x du point.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Retourne la coordonnée y du point.
     * 
     * @return la coordonnée y du point.
     */
    public int getY() {
        return y;
    }

    /**
     * Définit la coordonnée y du point.
     * 
     * @param y la nouvelle coordonnée y du point.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Constructeur de la classe <code>Point</code>.
     * 
     * @param x la coordonnée x initiale du point.
     * @param y la coordonnée y initiale du point.
     */
    public Point(int x, int y) {
        super();
        this.x = x;
        this.y = y;
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères du point.
     * 
     * @return une chaîne de caractères représentant le point sous la forme "X: x, Y: y".
     */
    @Override
    public String toString() {
        return "X: " + x + ", Y: " + y;
    }
}
