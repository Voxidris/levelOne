package back;

import front.Constantes;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * La classe <code>Visible</code> représente un élément visuel dans le jeu.
 * Elle gère le sprite et son chemin d'accès.
 */
public class Visible {
    /**
     * Le sprite de l'élément visuel.
     */
    private ImageView sprite;

    /**
     * Le chemin d'accès du sprite.
     */
    private String spritePath;

    /**
     * Retourne le chemin d'accès du sprite.
     * 
     * @return le chemin d'accès du sprite.
     */
    public String getSpritePath() {
        return spritePath;
    }

    /**
     * Définit le chemin d'accès du sprite.
     * 
     * @param spritePath le nouveau chemin d'accès du sprite.
     */
    public void setSpritePath(String spritePath) {
        this.spritePath = spritePath;
    }

    /**
     * Retourne le sprite de l'élément visuel.
     * 
     * @return le sprite de l'élément visuel.
     */
    public ImageView getSprite() {
        return sprite;
    }

    /**
     * Définit le sprite de l'élément visuel à partir d'un chemin d'accès.
     * 
     * @param spritePath le chemin d'accès du nouveau sprite.
     */
    public void setSprite(String spritePath) {
        sprite = new ImageView(new Image(spritePath));
        sprite.setFitHeight(Constantes.CASE_HEIGHT);
        sprite.setFitWidth(Constantes.CASE_WIDTH);
        sprite.prefHeight(Constantes.CASE_HEIGHT);
        sprite.prefWidth(Constantes.CASE_WIDTH);
    }

    /**
     * Constructeur de la classe <code>Visible</code>.
     * 
     * @param spritePath le chemin d'accès du sprite à initialiser.
     */
    public Visible(String spritePath) {
        this.spritePath = spritePath;
        this.sprite = new ImageView(new Image(spritePath));
        sprite.setFitHeight(Constantes.CASE_HEIGHT);
        sprite.setFitWidth(Constantes.CASE_WIDTH);
    }
}
