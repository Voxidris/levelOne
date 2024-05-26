package front;

import back.Entity;
import back.Point;
import characters.Character;
import characters.CombativeCharacter;
import characters.MainCharacter;
import characters.Merchant;
import characters.Monster;
import characters.Npc;
import items.IConsumables;
import items.Inventory;
import items.Item;
import items.Weapon;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;
import map.Biome;
import map.Tile;

/**
 * The {@code Game} class represents the main game logic and UI for the
 * application. It handles the main character's interaction with the game world,
 * including movement, combat, dialogue, and inventory management.
 */
public class Game {
	private Scene mapScene;
	private Biome biome;
	private MainCharacter mc;
	private Monster monster;
	private Stage primaryStage;
	private ProgressBar hpBarMc;
	private Button okButton;
	private Scene fightScene;
	private Scene txtScene;
	private EventHandler<ActionEvent> okEvent;

	public static final int MAP_SITUATION = 1;
	public static final int FIGHT_SITUATION = 2;
	public static final int REWARD_SITUATION = 3;
	public static final int SHOP_SITUATION = 4;

	private int endGameCount;

	/**
	 * Constructs a new {@code Game} with the specified primary stage.
	 *
	 * @param primaryStage the primary stage for this application
	 */
	public Game(Stage primaryStage) {
		this.mc = MainCharacter.MAIN_CHARACTER;
		mc.setCurrentHp(mc.getMaxHp());
		this.monster = null;
		this.biome = new Biome(Biome.VILLAGE_BIOME);
		this.primaryStage = primaryStage;
		this.hpBarMc = buildHpBar(mc);
		this.mapScene = new Scene(this.loadBiome());
		this.loadMapKey();

		this.primaryStage.setTitle("Game");
		this.primaryStage.setScene(this.mapScene);
		this.primaryStage.show();

	}

	/**
	 * Returns the maximum row offset for the camera.
	 *
	 * @return the maximum row offset
	 */
	public int getMaxRowOffset() {
		return this.biome.getHeight() - Constantes.NUMBER_OF_ROW;
	}

	/**
	 * Returns the maximum column offset for the camera.
	 *
	 * @return the maximum column offset
	 */

	public int getMaxColOffset() {
		return this.biome.getWidth() - Constantes.NUMBER_OF_COL;
	}

	/**
	 * Returns half the horizontal screen size.
	 *
	 * @return half the horizontal screen size
	 */
	public int getHalfHorizontalScreenSize() {
		return (int) Math.ceil(Constantes.NUMBER_OF_ROW / 2);
	}

	/**
	 * Returns half the vertical screen size.
	 *
	 * @return half the vertical screen size
	 */

	public int getHalfVerticalScreenSize() {
		return (int) Math.ceil(Constantes.NUMBER_OF_COL / 2);
	}

	/**
	 * Loads the current map and positions the camera based on the main character's
	 * location.
	 *
	 * @return the root node of the biome layout
	 */
	public StackPane loadBiome() {
		GridPane gridPane = new GridPane();
		gridPane.setPrefWidth(Constantes.STAGE_WIDTH);
		gridPane.setPrefHeight(Constantes.STAGE_HEIGHT);

		// camera offset

		int colOffset;
		int mcPosX;
		if (this.mc.getLocation().getX() <= getHalfVerticalScreenSize()) {
			colOffset = 0;
			mcPosX = mc.getLocation().getX();
		} else if (this.mc.getLocation().getX() >= getMaxColOffset() + getHalfVerticalScreenSize()) {
			colOffset = getMaxColOffset();
			mcPosX = mc.getLocation().getX() - colOffset;
		} else {
			colOffset = (this.mc.getLocation().getX() - getHalfVerticalScreenSize());
			mcPosX = getHalfVerticalScreenSize();
		}

		int rowOffset;
		int mcPosY;
		if (this.mc.getLocation().getY() <= getHalfHorizontalScreenSize()) {
			rowOffset = 0;
			mcPosY = mc.getLocation().getY();
		} else if (this.mc.getLocation().getY() >= this.getMaxRowOffset() + getHalfHorizontalScreenSize()) {
			rowOffset = this.getMaxRowOffset();
			mcPosY = mc.getLocation().getY() - rowOffset;
		} else {
			rowOffset = this.mc.getLocation().getY() - getHalfHorizontalScreenSize();
			mcPosY = getHalfHorizontalScreenSize();

		}

		for (int i = colOffset; i < colOffset + Constantes.NUMBER_OF_COL; i++) {
			for (int j = rowOffset; j < rowOffset + Constantes.NUMBER_OF_ROW; j++) {
				Tile t = biome.getTile(new Point(i, j));
				ImageView img = t.getBloc().copieProfonde().getSprite();
				gridPane.add(img, i - colOffset, j - rowOffset);
				if (t.getEntity() != null)
					gridPane.add(t.getEntity().getSprite(), i - colOffset, j - rowOffset);
			}
		}
		ImageView image = new ImageView(this.mc.getSprite().getImage());
		image.setFitHeight(Constantes.CASE_HEIGHT);
		image.setFitWidth(Constantes.CASE_WIDTH);
		gridPane.add(image, mcPosX, mcPosY);

		StackPane sp = new StackPane();

		VBox vbox = buildHpBox();
		sp.getChildren().addAll(gridPane, vbox);

		BorderStroke borderStroke = new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(0),
				new BorderWidths(5));
		Border border = new Border(borderStroke);

		sp.setBorder(border);
		// System.out.println(mc.getLocation().toString());

		return sp;
	}

	/**
	 * Builds a VBox containing the main character's HP bar.
	 *
	 * @return the VBox containing the HP bar
	 */
	private VBox buildHpBox() {
		hpBarMc = buildHpBar(mc);
		hpBarMc.getTransforms().setAll(new Translate(0, 120), new Rotate(-90, 0, 0));
		hpBarMc.setPrefSize(120, 25);

		VBox vbox = new VBox();
		vbox.setTranslateX(Constantes.STAGE_WIDTH - 40);
		vbox.setTranslateY(Constantes.STAGE_HEIGHT - 180);
		vbox.setSpacing(8);
		ImageView heart = new ImageView(new Image("file:images/HUD/Undertale.png"));
		heart.setFitHeight(45);
		heart.setFitWidth(45);
		// heart.setStyle("-fx-border-color: blue; -fx-border-width: 10px;
		// -fx-border-radius: 5px;");
		heart.setTranslateX(-10);
		heart.setLayoutX(heart.getBoundsInParent().getHeight() - 5);
		vbox.getChildren().addAll(heart, hpBarMc);
		vbox.setPrefSize(50, 200);
		return vbox;

	}

	/**
	 * Builds a ProgressBar representing the HP bar of the specified combative
	 * character.
	 *
	 * @param c the combative character whose HP bar is to be built
	 * @return the HP bar
	 */

	private ProgressBar buildHpBar(CombativeCharacter c) {
		ProgressBar hpBar = new ProgressBar();
		hpBar.setProgress((float) c.getCurrentHp() / (float) c.getMaxHp());
		hpBar.setStyle("-fx-border-color: black; -fx-border-width: 2px; -fx-border-radius: 5px; -fx-accent:red");

		Tooltip tooltip = new Tooltip();
		tooltip.setText(c.getCurrentHp() + "/" + c.getMaxHp());
		tooltip.setShowDelay(Duration.seconds(0));
		Tooltip.install(hpBar, tooltip);

		hpBar.setOnMouseExited(e -> tooltip.hide());
		hpBar.setOnMouseClicked(e -> { // MouseClicked and mouseEnteredInversé???
			tooltip.show(hpBar, e.getScreenX(), e.getScreenY());
		});
		DoubleProperty progress = new SimpleDoubleProperty((float) c.getCurrentHp() / (float) c.getMaxHp());
		hpBar.progressProperty().bind(progress);

		return hpBar;
	}

	/**
	 * Loads a dialog box with the specified NPC's dialogue at the given index.
	 *
	 * @param npc   the NPC whose dialogue is to be loaded
	 * @param index the index of the dialogue line
	 */
	public void loadDialogBox(Npc npc, int index) { // Faire pour plusieurs lignes de dialogue

		Button nextButton = new Button("Next");

		Button previousButton = new Button("Previous");

		StackPane root = new StackPane(loadBiome(), textBoxBuild(npc.getDialog()[index], 5, mapScene), previousButton,
				nextButton);

		// previousButton.setPadding(new Insets(0,0,20,20));
		// nextButton.setPadding(new Insets(0,0,20,20));

		StackPane.setAlignment(previousButton, Pos.BOTTOM_LEFT);
		StackPane.setAlignment(nextButton, Pos.BOTTOM_RIGHT);
		nextButton.setLayoutY(-20);

		if (index == 0)
			previousButton.setDisable(true);
		Scene sceneB = new Scene(root);
		primaryStage.setScene(sceneB);

		EventHandler<ActionEvent> dialogHandler = e -> {
			if (index < npc.getDialog().length - 1) {
				loadDialogBox(npc, index + 1);
			} else {
				primaryStage.setScene(mapScene);
				this.mapScene.setRoot(this.loadBiome());

			}
		};
		nextButton.setOnAction(dialogHandler);

		previousButton.setOnAction(e -> {
			if (index > 0)
				loadDialogBox(npc, index - 1);
		});
		sceneB.setOnKeyReleased(e -> dialogHandler.handle(null));
	}

	/**
	 * Builds a text box with the specified text and situation.
	 *
	 * @param txt          the text to display
	 * @param situation    the current game situation
	 * @param currentScene the current scene
	 * @return the root node of the text box layout
	 */
	public StackPane textBoxBuild(String txt, int situation, Scene currentScene) {
		double rectangleWidth = Constantes.STAGE_WIDTH;
		double rectangleHeight = Constantes.STAGE_HEIGHT / 4;

		if (situation == FIGHT_SITUATION) {
			rectangleWidth = Constantes.STAGE_WIDTH;
			rectangleHeight = Constantes.STAGE_HEIGHT / 2 - 20;
		}

		ImageView textBox = new ImageView(new Image("file:images/HUD/text.jpg"));
		textBox.setFitHeight(rectangleHeight);
		textBox.setFitWidth(rectangleWidth);

		Text text = new Text(txt);
		text.setFont(Font.font("Arial", 20));
		text.setTextAlignment(TextAlignment.CENTER);
		text.setFill(Color.BLACK);

		VBox vbox = new VBox(5);
		vbox.getChildren().add(text);

		if (situation == 5)
			vbox.setPadding(new Insets(60, 0, 60, 0));
		else {
			okButton = new Button("Ok");
			vbox.getChildren().add(okButton);
			vbox.setPadding(new Insets(0, 0, 200, 0));

		}

		vbox.setAlignment(Pos.BOTTOM_CENTER);
		vbox.setPadding(new Insets(0, 0, 60, 0));
		StackPane root = new StackPane(textBox, vbox);

		root.setAlignment(Pos.BOTTOM_CENTER);
		root.setPrefWidth(Constantes.STAGE_WIDTH);
		root.setPrefHeight(Constantes.STAGE_HEIGHT);

		return root;
	}

	/**
	 * Loads a text box with the specified situation and text.
	 *
	 * @param situation the current game situation
	 * @param txt       the text to display
	 */
	public void loadTextBox(int situation, String txt) {
		Scene currentScene = mapScene;
		if (situation == FIGHT_SITUATION)
			currentScene = fightScene;

		// okButton.setPadding(Insets.EMPTY);
		StackPane textPane = textBoxBuild(txt, situation, currentScene);
		textPane.getChildren().add(0, currentScene.getRoot());

		txtScene = new Scene(textPane);

		if (situation == MAP_SITUATION)
			okEvent = e -> {
				primaryStage.setScene(mapScene);
			};
		okButton.setOnAction(okEvent);
		txtScene.setOnKeyReleased(e -> {
			if (e.getCode() == Constantes.KEY_INTERACTION)
				okEvent.handle(null);
		});
		primaryStage.setScene(txtScene);

	}

	/**
	 * Loads the key bindings for the map scene.
	 */

	public void loadMapKey() {
		this.mapScene.setOnKeyReleased(e -> {
			// inventory
			if (e.getCode().equals(Constantes.KEY_INVENTORY)) {
				displayInventory(this.mc, MAP_SITUATION);

				// movement
			} else if (e.getCode().equals(Constantes.KEY_MOVE_TOP)) {
				move(Constantes.DIRECTION_NORTH);
			} else if (e.getCode().equals(Constantes.KEY_MOVE_BOTTOM)) {
				move(Constantes.DIRECTION_SOUTH);
			} else if (e.getCode().equals(Constantes.KEY_MOVE_LEFT)) {
				move(Constantes.DIRECTION_WEST);
			} else if (e.getCode().equals(Constantes.KEY_MOVE_RIGHT)) {
				move(Constantes.DIRECTION_EAST);

				// interaction
			} else if (e.getCode().equals(Constantes.KEY_INTERACTION)) {
				interaction();

			}

			this.mapScene.setRoot(this.loadBiome());
		});
	}
    /**
     * Returns the point representing the case in front of the main character based on his direction.
     *
     * @return the point representing the case in front of the main character
     */
	public Point getCase() {
		switch (this.mc.getDirection()) {
		case Constantes.DIRECTION_NORTH:
			return new Point(this.mc.getLocation().getX(), this.mc.getLocation().getY() - 1);
		case Constantes.DIRECTION_SOUTH:
			return new Point(this.mc.getLocation().getX(), this.mc.getLocation().getY() + 1);
		case Constantes.DIRECTION_EAST:
			return new Point(this.mc.getLocation().getX() + 1, this.mc.getLocation().getY());
		case Constantes.DIRECTION_WEST:
			return new Point(this.mc.getLocation().getX() - 1, this.mc.getLocation().getY());
		default:
			return this.mc.getLocation();
		}
	}
    /**
     * Moves the main character in the specified direction if possible.
     *
     * @param direction the direction to move the main character
     */
	public void move(int direction) {

		this.mc.setDirection(direction);
		Point p = getCase();
		if (mc.getCurrentPotion() != null)
			mc.getCurrentPotion().DecreaseDuration(mc);

		if (biome.moveIsPossible(p)) {
			this.mc.setLocation(p);
		} else if (!biome.isTileExist(p)) {
			if ((direction == Constantes.DIRECTION_NORTH | direction == Constantes.DIRECTION_SOUTH)) {
				if (biome.getNeighbor(direction, p.getX()) != 0) {
					Biome nextBiome = new Biome(biome.getNeighbor(direction, p.getX()));
					this.biome = nextBiome;
					if (direction == Constantes.DIRECTION_NORTH)
						this.mc.setLocation(new Point(p.getX(), biome.getHeight() - 1));
					else
						this.mc.setLocation(new Point(p.getX(), 0));
				}
			} else if ((direction == Constantes.DIRECTION_EAST | direction == Constantes.DIRECTION_WEST)
					& biome.getNeighbor(direction, p.getY()) != 0) {
				Biome nextBiome = new Biome(biome.getNeighbor(direction, p.getY()));
				this.biome = nextBiome;
				if (direction == Constantes.DIRECTION_EAST)
					this.mc.setLocation(new Point(0, p.getY()));
				else
					this.mc.setLocation(new Point(biome.getWidth() - 1, p.getY()));

			}

		}

	}
	   /**
     * Handles interaction with entities in front of the main character.
     */

	public void interaction() {
		Tile t = this.biome.getTile(getCase());
		Entity e = t.getEntity();
		if (e != null) {
			if (e instanceof Npc) {
				Npc npc = (Npc) e;
				loadDialogBox(npc, 0);
			} else if (e instanceof Item) {
				Item item = (Item) e;
				loadTextBox(MAP_SITUATION, item.toString() + " found");
				this.mc.getInventory().addItem(item);
				t.setEntity(null);
			} else if (e instanceof Monster) {
				Monster m = (Monster) e;
				this.monster = m;
				fight();
			} else if (e instanceof Merchant) {
				Merchant merchant = (Merchant) e;
				displayInventory(merchant, 4);
			}

		}
	}
    /**
     * Displays the inventory of the specified character in the given situation.
     *
     * @param character the character whose inventory is to be displayed
     * @param situation the current game situation
     */

	public void displayInventory(Character character, int situation) {
		Scene previousScene = primaryStage.getScene();

		TabPane tabPane = new TabPane();
		Tab allTab = new Tab("Tous");
		Tab consumableTab = new Tab("Consommables");
		Tab weaponTab = new Tab("Équipement");
		tabPane.getTabs().addAll(allTab, consumableTab, weaponTab);

		GridPane allGridPane = new GridPane();
		allGridPane.setVgap(1);
		allGridPane.setHgap(1);
		allTab.setContent(allGridPane);

		GridPane weaponGridPane = new GridPane();
		weaponGridPane.setVgap(1);
		weaponGridPane.setHgap(1);
		weaponTab.setContent(weaponGridPane);

		GridPane consumableGridPane = new GridPane();
		consumableGridPane.setVgap(1);
		consumableGridPane.setHgap(1);
		consumableTab.setContent(consumableGridPane);

		Button closeButton = new Button("Fermer l'inventaire");

		ImageView characterImage = new ImageView(new Image(character.getSpritePath()));
		characterImage.setFitWidth(100);
		characterImage.setFitHeight(100);

		Label money = new Label("Money : " + character.getMoney());
		ImageView moneyImage = new ImageView(new Image("file:images/star_bit.png"));
		moneyImage.setFitHeight(50);
		moneyImage.setFitWidth(50);
		money.setGraphic(moneyImage);

		HBox bottomBox = new HBox(20);
		bottomBox.setAlignment(Pos.CENTER);
		bottomBox.setSpacing(10);
		bottomBox.getChildren().addAll(characterImage, money, closeButton);

		if (character instanceof MainCharacter) {
			Label weapon = new Label("Current Weapon : ");
			ImageView weaponImage;
			Text mcString = new Text(mc.toString());
			if (mc.getCurrentWeapon() != null)
				weaponImage = new ImageView(new Image(mc.getCurrentWeapon().getSpritePath()));
			else {
				weaponImage = new ImageView(new Image("file:images/weapons/poing.png"));
				weaponImage.setFitWidth(50);
				weaponImage.setFitHeight(50);
			}
			weapon.setGraphic(weaponImage);
			weapon.setContentDisplay(ContentDisplay.RIGHT);
			bottomBox.getChildren().add(0, mcString);
			bottomBox.getChildren().add(1, weapon);
		}

		// Création d'un conteneur racine VBox et définition des contraintes de taille
		BorderPane root = new BorderPane();
		if (situation != FIGHT_SITUATION)
			root.setTop(tabPane);
		root.setBottom(bottomBox);
		BorderPane.setMargin(tabPane, new Insets(10));

		if (situation == REWARD_SITUATION) {
			Label t = new Label("You Won and collected this : ");
			root.setTop(t);
			t.setPrefHeight(Constantes.STAGE_HEIGHT * 0.5);
			closeButton.setText("ok");
		}

		if (situation == SHOP_SITUATION) {
			HBox topBox = new HBox();
			Button sell = new Button("sell");
			Button buy = new Button("buy");
			topBox.getChildren().addAll(buy, sell);
			buy.setOnAction(e -> {
				Merchant m = (Merchant) biome.getTile(getCase()).getEntity();
				displayInventory(m, SHOP_SITUATION);
			});
			sell.setOnAction(e -> displayInventory(mc, SHOP_SITUATION));

			root.setLeft(topBox);
		}

		if (situation == FIGHT_SITUATION) {
			buildInventory(consumableGridPane, character, IConsumables.class, previousScene, situation);
			root.setCenter(consumableGridPane);

		} else {
			tabPane.getSelectionModel().select(allTab);
			buildInventory(allGridPane, character, Item.class, previousScene, situation);
			root.setCenter(allGridPane);
		}

		// listeners
		allTab.setOnSelectionChanged(e -> {
			buildInventory(allGridPane, character, Item.class, previousScene, situation);
			root.setCenter(allGridPane);
		});
		consumableTab.setOnSelectionChanged(e -> {
			buildInventory(consumableGridPane, character, IConsumables.class, previousScene, situation);
			root.setCenter(consumableGridPane);
		});
		weaponTab.setOnSelectionChanged(e -> {
			buildInventory(weaponGridPane, character, Weapon.class, previousScene, situation);
			root.setCenter(weaponGridPane);
		});

		// border and background
		BorderStroke borderStroke = new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(0),
				new BorderWidths(5));
		Border border = new Border(borderStroke);
		allGridPane.setBorder(border);
		consumableGridPane.setBorder(border);
		weaponGridPane.setBorder(border);

		root.setPrefWidth(Constantes.STAGE_WIDTH);
		root.setPrefHeight(Constantes.STAGE_HEIGHT);

		Scene inventoryScene = new Scene(root, Constantes.STAGE_WIDTH, Constantes.STAGE_HEIGHT);
		// close inventory
		EventHandler<ActionEvent> closeInventoryHandler = e -> {
			primaryStage.setScene(previousScene);
			if (situation == REWARD_SITUATION | situation == SHOP_SITUATION)
				primaryStage.setScene(this.mapScene);
			mapScene.setRoot(loadBiome());
			primaryStage.setTitle("Game");
		};
		inventoryScene.setOnKeyReleased(e -> {
			if (e.getCode() == Constantes.KEY_INVENTORY)
				closeInventoryHandler.handle(null);
		});
		closeButton.setOnAction(e -> {
			closeInventoryHandler.handle(null);
		});

		// change scene
		primaryStage.setScene(inventoryScene);
		primaryStage.setTitle("inventory");

	}
	  /**
     * Builds the inventory view for the specified character and class of items.
     *
     * @param gridPane the GridPane to populate with items
     * @param character the character whose inventory is to be built
     * @param superclazz the class of items to display
     * @param previousScene the previous scene to return to
     * @param situation the current game situation
     */

	private void buildInventory(GridPane gridPane, Character character, Class<?> superclazz, Scene previousScene,
			int situation) {
		gridPane.getChildren().clear();
		int itemDisplayed = 0;
		Inventory inventory = character.getInventory();
		if (situation == REWARD_SITUATION & character instanceof Monster) {
			inventory = ((Monster) character).getLoot();
		}
		for (int row = 0; row < (inventory.getCurrentSize() / 5) + 1; row++) {
			for (int col = 0; col < 5; col++) {
				if (inventory.getCurrentSize() > row * 5 + col) {
					Item item = inventory.getItem(row * 5 + col);
					if (item != null) {
						if (superclazz.isAssignableFrom(item.getClass())) {
							Label itemCase = createItemView(character, item, previousScene, situation);
							gridPane.add(itemCase, itemDisplayed % 5, itemDisplayed / 5);
							itemDisplayed++;
						}
					}
				}
			}

		}

	}

	 /**
     * Creates a view for the specified item.
     *
     * @param character the character interacting with the item
     * @param item the item to create a view for
     * @param previousScene the previous scene to return to
     * @param situation the current game situation
     * @return the Label representing the item view
     */
	private Label createItemView(Character character, Item item, Scene previousScene, int situation) {
		ImageView imageView = new ImageView(new Image(item.getSpritePath()));
		imageView.setFitWidth(100);
		imageView.setFitHeight(100);

		Label button = new Label();
		button.setGraphic(imageView);
		button.setText(item.getName());
		button.setAlignment(Pos.CENTER);

		Tooltip tooltip = new Tooltip();
		tooltip.setText(item.getDescription() + "\nValue :" + item.getValue() + "Gold");
		if (item instanceof Weapon) {
			tooltip.setText(tooltip.getText() + "\nAttack Power :" + ((Weapon) item).getAttackPower()
					+ "\nShieldPower : " + ((Weapon) item).getShieldPower());
		}
		tooltip.setShowDelay(Duration.seconds(0));
		Tooltip.install(button, tooltip);
		button.setOnMouseExited(g -> tooltip.hide());

		button.setOnMouseEntered(f -> {
			tooltip.show(button, f.getScreenX(), f.getScreenY());
		});
		button.setOnMouseClicked(event -> {
			if (situation == FIGHT_SITUATION | situation == MAP_SITUATION) {
				if (item instanceof IConsumables) {
					((IConsumables) item).useItem(mc);
					primaryStage.setScene(previousScene);
					if (situation == FIGHT_SITUATION) {
						loadTextBox(FIGHT_SITUATION, "player use " + item.getName());
						okButton.setOnAction(e -> monsterTurn());
					} else {
						mapScene.setRoot(loadBiome());
					}
				} else if (item instanceof Weapon & situation == MAP_SITUATION) {
					mc.setCurrentWeapon((Weapon) item);
					primaryStage.setScene(previousScene);

					return;
				}
			} else if (situation == SHOP_SITUATION) {
				if (character instanceof Merchant) {
					Merchant m = (Merchant) character;
					m.buyItem(mc, item);
					displayInventory(m, SHOP_SITUATION);

				} else/* if (character instanceof MainCharacter) */ {
					Merchant merchant = (Merchant) biome.getTile(getCase()).getEntity();
					merchant.sellItem(mc, item);
					displayInventory(mc, SHOP_SITUATION);

				}

			}
		});

		return button;
	}

    /**
     * Starts a fight with the current monster.
     */
	public void fight() {

		// Screen setup
		ImageView imageMc = new ImageView(new Image("file:images/player/duel_player.png"));
		imageMc.setFitHeight(Constantes.STAGE_WIDTH / 4);
		imageMc.setFitWidth(Constantes.STAGE_WIDTH / 4);
		Label labelMc = new Label(mc.getName());
		// progressBar1.setPrefWidth(100);

		Image monsterImage = new Image(monster.getSpritePath());
		ImageView imageMonster = new ImageView(monsterImage);
		imageMonster.setFitHeight(Constantes.STAGE_WIDTH / 4);
		imageMonster.setFitWidth(Constantes.STAGE_WIDTH / 4);
		Label labelMonster = new Label(monster.getName());

		// progressBar2.setPrefWidth(100);

		Button buttonAttack = new Button("Attack");
		Button buttonItem = new Button("Use Item");

		VBox topLeftBox = new VBox(10);
		ProgressBar hpBarMc = buildHpBar(this.mc);
		topLeftBox.getChildren().addAll(imageMc, labelMc, hpBarMc);
		topLeftBox.setAlignment(Pos.CENTER_LEFT);

		VBox topRightBox = new VBox(10);
		ProgressBar hpBarMonster = buildHpBar(this.monster);
		topRightBox.getChildren().addAll(imageMonster, labelMonster, hpBarMonster);
		topRightBox.setAlignment(Pos.CENTER_RIGHT);

		HBox bottomBox = new HBox(10);
		bottomBox.getChildren().addAll(buttonAttack, buttonItem);

		BackgroundImage bg = new BackgroundImage(new Image("file:images/HUD/text.jpg"), BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
				new BackgroundSize(1.0, 1.0, true, true, true, false));
		bottomBox.setBackground(new Background(bg));
		bottomBox.setAlignment(Pos.CENTER);
		bottomBox.setPrefWidth(Constantes.STAGE_WIDTH);
		bottomBox.setPrefHeight(Constantes.STAGE_HEIGHT / 2);

		BorderPane root = new BorderPane();
		root.setTop(new HBox(Constantes.STAGE_WIDTH / 2, topLeftBox, topRightBox));
		root.setBottom(bottomBox);
		root.setPrefSize(Constantes.STAGE_WIDTH, Constantes.STAGE_HEIGHT);
		fightScene = new Scene(root);
		primaryStage.setScene(fightScene);

		// Game Turn

		buttonAttack.setOnAction(e -> {
			attackTurn();
		});

		fightScene.setOnKeyReleased(f -> {
			if (f.getCode() == Constantes.KEY_INVENTORY)
				inventoryTurn();
		});
		buttonItem.setOnAction(e -> {
			inventoryTurn();
		});

	}
/**
 * Handle the mainCharacter attack
 */
	private void attackTurn() {
		int dmg = mc.attack(monster);
		okEvent = event -> {
			if (monster.getCurrentHp() > 0)
				monsterTurn();
			else
				endCombatScreen();
		};
		loadTextBox(FIGHT_SITUATION, "player attack and deal " + dmg);
	}
	/**
	 * handle the monster action
	 */

	private void monsterTurn() {
		okEvent = e -> fight();
		IConsumables i = monster.itemToUse();
		if (i != null) {
			i.useItem(monster);
			loadTextBox(FIGHT_SITUATION, monster.getName() + " use " + ((Entity) i).getName());

		} else {
			int dmg = monster.attack(mc);
			loadTextBox(FIGHT_SITUATION, monster.getName() + " attack and deal " + dmg);
			if (mc.getCurrentHp() <= 0) {
				endGameScreen("You lose");
				return;
			}
		}

	}
	/**
	 * handle the mc using a consummable in fight
	 */

	private void inventoryTurn() {
		displayInventory(this.mc, FIGHT_SITUATION);
	}
	/**
	 * Handle the action following the main character defeating the monster his facing
	 */

	private void endCombatScreen() {
		monster.addLoot(mc);
		this.biome.getTile(getCase()).setEntity(null);
		displayInventory(this.monster, REWARD_SITUATION);
		this.monster = null;
		endGameCount++;
		if (endGameCount >= 4)
			endGameScreen("You Won !!");
	}
/**
 * generic scene for win or loose
 * @param txt the text to show, win or loose
 */
	private void endGameScreen(String txt) {
		Text t = new Text(txt);

		Button b = new Button("Close game");
		VBox vbox = new VBox();
		vbox.getChildren().addAll(t, b);
		vbox.setAlignment(Pos.CENTER);
		vbox.setSpacing(50);
		Scene looseScreen = new Scene(vbox, Constantes.STAGE_WIDTH, Constantes.STAGE_HEIGHT);
		primaryStage.setScene(looseScreen);
		b.setOnAction(e -> primaryStage.close());
	}

}
