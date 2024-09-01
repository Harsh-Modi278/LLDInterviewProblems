package SnakeAndLadder;

public class Board {
	private Cell[][] _board;
	
	Board(int n) {
		this._board = new Cell[n][n];
		for (int i=0;i<n;i++) {
			for (int j=0;j<n;j++) {
				_board[i][j] = new Cell();
			}
		}
	}
	
	public Cell getCell(int index) {
		int boardRow = index / _board.length;
        int boardColumn = (index % _board.length);
        return _board[boardRow][boardColumn];
	}

	public void addGameEntity(int start, int end) {
		GameEntity entity;
        entity = new GameEntity();
        entity.setStart(start);
		entity.setEnd(end);

		this._board[start][end].setEntity(entity);
	}
}
