package Items;

import back.Entity;

public abstract class Item extends Entity{
	private float value;
	private String description;
	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public Item(String name, String spritepath, float value, String description ) {
		super(name,spritepath);
		this.value = value;
		this.description = description;
	}


}
