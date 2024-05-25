package map;

import back.Entity;

public class Tile {
	// Var
	private Bloc bloc;
	private Entity entity;

	public Tile(Bloc b) {
		this.bloc = b;
		entity = null;
	}

	public Tile(Bloc b, Entity entity) {
		this.bloc = b;
		this.entity = entity;
	}

	// GetSet
	public Bloc getBloc() {
		return bloc;
	}

	public void setBloc(Bloc bloc) {
		this.bloc = bloc;
	}

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		if (!bloc.isSolid())
			this.entity = entity;
		else
			this.entity = null;
	}
}
