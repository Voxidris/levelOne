package map;

import back.Entity;

/**
 * Represents a tile on the game map.
 */
public class Tile {

    /**
     * The block associated with the tile.
     */
    private Bloc bloc;

    /**
     * The entity occupying the tile.
     */
    private Entity entity;

    /**
     * Constructs a new tile with the specified block.
     *
     * @param b the block associated with the tile
     */
    public Tile(Bloc b) {
        this.bloc = b;
        entity = null;
    }

    /**
     * Constructs a new tile with the specified block and entity.
     *
     * @param b      the block associated with the tile
     * @param entity the entity occupying the tile
     */
    public Tile(Bloc b, Entity entity) {
        this.bloc = b;
        this.entity = entity;
    }

    /**
     * Gets the block associated with the tile.
     *
     * @return the block associated with the tile
     */
    public Bloc getBloc() {
        return bloc;
    }

    /**
     * Sets the block associated with the tile.
     *
     * @param bloc the block to set
     */
    public void setBloc(Bloc bloc) {
        this.bloc = bloc;
    }

    /**
     * Gets the entity occupying the tile.
     *
     * @return the entity occupying the tile
     */
    public Entity getEntity() {
        return entity;
    }

    /**
     * Sets the entity occupying the tile.
     * <p>
     * If the tile's block is solid, the entity will not be set.
     *
     * @param entity the entity to set
     */
    public void setEntity(Entity entity) {
        if (!bloc.isSolid())
            this.entity = entity;
        else
            this.entity = null;
    }
}
