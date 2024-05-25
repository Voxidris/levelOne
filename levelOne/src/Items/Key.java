package Items;

import back.Entity;
import back.Point;
import characters.CombativeCharacter;
import characters.MainCharacter;

public class Key extends Item {
	

	private Point useLocation;
	private int idDoor;
	
	

	public int getIdDoor() {
		return idDoor;
	}

	public void setIdDoor(int idDoor) {
		this.idDoor = idDoor;
	}

	
	public void useItem(MainCharacter c) {
			//open door
			c.getInventory().dropItem(this);
		
	}
	
	public Key(int id,String name, String sprite, float value, String description) {
		super(name, sprite, value,description);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Entity copieProfonde() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
