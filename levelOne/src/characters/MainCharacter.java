package characters;

import Items.Inventory;
import back.Entity;
import back.Point;
import front.Constantes;

public class MainCharacter extends CombativeCharacter {
	private Point location;
	private int direction;
	private int level;

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	 

	public void setDirection(int i) {

		this.direction = i;
		switch (i) {
		case Constantes.DIRECTION_NORTH:
			this.setSprite("file:images/player/player_north.png");
			break;
		case Constantes.DIRECTION_EAST:
			this.setSprite("file:images/player/player_east.png");
			break;
		case Constantes.DIRECTION_SOUTH:
			this.setSprite("file:images/player/player_south.png");
			break;
		case Constantes.DIRECTION_WEST:
			this.setSprite("file:images/player/player_west.png");
			break;
		default:
			break;
		}
	}

	public int getDirection() {
		return this.direction;
	}

	public String toString() {
		return "Player Stats : \nLevel : " + level +"\n Xp : " + getXp() +"\nAttackPower : "+ this.getAttackPower()+"\nDefensePower : " + this.getDefensePower();
	}
	

	public void addXp(int xp) {
		this.setXp(this.getXp()+xp);
		while (this.getXp()>10+level) {
			levelUp();
			this.setXp(this.getXp()-10);
			
		}
	}
	public void levelUp() {
		this.level+=1;
		this.setAttack(getAttack()+1);
		this.setDefense(getDefense()+1);
		this.setMaxHp(getMaxHp()+2);
		this.setCurrentHp(getMaxHp());

	}


	public MainCharacter(String name, String spritepath, Point location, Inventory inventory, float money, int hp,
			int xp, int attack, int defense, int speed, int direction,int level) {
		super(name, spritepath, inventory, money, hp, xp, attack, defense, speed);
		this.direction = direction;
		this.location = location;
		this.level = 0;

	}

	public static MainCharacter MAIN_CHARACTER = new MainCharacter("Toto", "file:images/player/player_south.png",
			new Point(2, 4), new Inventory(), 50, 50, 1, 10, 5, 1, Constantes.DIRECTION_SOUTH,1);

	@Override
	public Entity copieProfonde() {
		// TODO Auto-generated method stub
		return null;
	}

}
