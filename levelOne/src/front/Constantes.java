package front;

import javafx.scene.input.KeyCode;

public class Constantes {
	public final static int NUMBER_OF_ROW = 9;
	public final static int NUMBER_OF_COL = 16;

	public final static int CASE_HEIGHT = 60;
	public final static int CASE_WIDTH = 60;

	public final static int STAGE_HEIGHT = NUMBER_OF_ROW * CASE_HEIGHT;
	public final static int STAGE_WIDTH = NUMBER_OF_COL * CASE_WIDTH;

	public final static KeyCode KEY_INVENTORY = KeyCode.E;
	public final static KeyCode KEY_MOVE_TOP = KeyCode.Z;
	public final static KeyCode KEY_MOVE_BOTTOM = KeyCode.S;
	public final static KeyCode KEY_MOVE_LEFT = KeyCode.Q;
	public final static KeyCode KEY_MOVE_RIGHT = KeyCode.D;
	public final static KeyCode KEY_INTERACTION = KeyCode.F;
	public final static KeyCode KEY_ESCAPE = KeyCode.ESCAPE;


	public final static int DIRECTION_NORTH = 0;
	public final static int DIRECTION_EAST = 1;
	public final static int DIRECTION_SOUTH = 2;
	public final static int DIRECTION_WEST = 3;

	public final static int GAME_SCENE = 0;
	public final static int INVENTORY_SCENE = 1;
	public final static int COMBAT_SCENE = 2;

}
