package back;

import front.Constantes;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Visible {
	private ImageView sprite;
	private String spritePath;
	

	public String getSpritePath() {
		return spritePath;
	}

	public void setSpritePath(String spritePath) {
		this.spritePath = spritePath;
	}

	public ImageView getSprite() {
		return sprite;
	}

	public void setSprite(String spritePath) {
	        sprite = new ImageView(new Image(spritePath));
	        sprite.setFitHeight(Constantes.CASE_HEIGHT);
	        sprite.setFitWidth(Constantes.CASE_WIDTH);
	        sprite.prefHeight(Constantes.CASE_HEIGHT);
	        sprite.prefWidth(Constantes.CASE_WIDTH);
	}

	public Visible(String spritepath) {
		this.spritePath = spritepath;
		this.sprite = new ImageView(new Image(spritepath));
		sprite.setFitHeight(Constantes.CASE_HEIGHT);
        sprite.setFitWidth(Constantes.CASE_WIDTH);
	}

}
