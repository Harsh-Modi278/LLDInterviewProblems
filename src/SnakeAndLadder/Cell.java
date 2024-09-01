package SnakeAndLadder;

public class Cell {
	private GameEntity _entity;
	
	Cell() {
		_entity = null;
	}
	
	public void setEntity(GameEntity entity) {
		this._entity = entity;
	}
	
	public GameEntity getEntity() {
		return this._entity;
	}
}
