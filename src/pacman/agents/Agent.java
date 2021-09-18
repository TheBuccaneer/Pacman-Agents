package pacman.agents;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pacman.m.Game;

public abstract class Agent
{
	private int x;
	private int y;

	private int curDirection = -1;

	private ImageView iv;

	public static final int UP = 0;
	public static final int RIGHT = 1;
	public static final int DOWN = 2;
	public static final int LEFT = 3;

	public Agent(int x, int y, String path)
	{
		this.x = x;
		this.y = y;

		this.setIV(path);
	}

	protected void setIV(String path)
	{
		this.iv = new ImageView(new Image(path));
		this.iv.setX(this.x * Game.GRID_SIZE);
		this.iv.setY(this.y * Game.GRID_SIZE);
		this.iv.setFitHeight(Game.GRID_SIZE);
		this.iv.setFitWidth(Game.GRID_SIZE);
	}

	public ImageView getIV()
	{
		return this.iv;
	}

	public int getX()
	{
		return this.x;
	}

	public int getY()
	{
		return this.y;
	}

	protected int[] getDirection(int[][] board)
	{
		int[] dirs =
		{ board[this.y - 1][this.x], board[this.y][this.getNewX(1)], board[this.y + 1][this.x],
				board[this.y][this.getNewX(-1)] };

		if (this.curDirection == UP)
			dirs[DOWN] = Game.DIRECTION_COMMING_FROM;
		if (this.curDirection == RIGHT)
			dirs[LEFT] = Game.DIRECTION_COMMING_FROM;
		if (this.curDirection == DOWN)
			dirs[UP] = Game.DIRECTION_COMMING_FROM;
		if (this.curDirection == LEFT)
			dirs[RIGHT] = Game.DIRECTION_COMMING_FROM;

		return dirs;
	}

	private int getNewX(int i)
	{
		int newX = this.x + i;
		if (newX == Game.NUM_OF_GRID_X)
			newX = 0;
		else if (newX < 0)
			newX = Game.NUM_OF_GRID_X - 1;
		return newX;
	}

	protected void setNextPosition(int xTrans, int yTrans, int newDirection, String path, int[][] board)
	{
		this.setNextPosition(xTrans, yTrans, newDirection, board);
		this.setIV(path);
	}

	protected void setNextPosition(int xTrans, int yTrans, int newDirection, int[][] board)
	{
		this.y = this.y + yTrans;
		this.x = this.getNewX(xTrans);
		this.curDirection = newDirection;
	}

	public abstract void percept(Game game);

}
