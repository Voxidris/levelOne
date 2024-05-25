package back;

public abstract class Entity extends Visible {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public Entity(String name, String spritepath) {
		super(spritepath);
		this.name = name;

	}

	@Override
	public String toString() {
		return name;
	}
	
	public abstract Entity copieProfonde();

	

}
