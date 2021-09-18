package pacman.m;

import javafx.application.Platform;
import pacman.agents.reflex.PacMan;
import pacman.v.Board;

public class Game
{

	private PacMan pacman;
	private Board scene;

	public final static int GRID_SIZE = 40;
	public final static int NUM_OF_GRID_X = 19;
	public final static int NUM_OF_GRID_Y = 15;

	public final static int GHOST = 0;
	public final static int WALL = 1;
	public final static int DIRECTION_COMMING_FROM = 2;
	public final static int EMPTY = 3;
	public final static int COIN = 4;

	private final static int UPDATE_TIME = 50;

	private int board[][] =
	{
			{ WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL,
					WALL, WALL },
			{ WALL, COIN, COIN, COIN, COIN, COIN, COIN, COIN, COIN, WALL, COIN, COIN, COIN, COIN, COIN, COIN, COIN,
					COIN, WALL },
			{ WALL, COIN, WALL, WALL, COIN, WALL, WALL, WALL, COIN, WALL, COIN, WALL, WALL, WALL, COIN, WALL, WALL,
					COIN, WALL },
			{ WALL, COIN, COIN, COIN, COIN, COIN, COIN, COIN, COIN, COIN, COIN, COIN, COIN, COIN, COIN, COIN, COIN,
					COIN, WALL },
			{ WALL, COIN, WALL, WALL, COIN, WALL, COIN, WALL, WALL, WALL, WALL, WALL, COIN, WALL, COIN, WALL, WALL,
					COIN, WALL },
			{ WALL, COIN, COIN, COIN, COIN, WALL, COIN, COIN, COIN, WALL, COIN, COIN, COIN, WALL, COIN, COIN, COIN,
					COIN, WALL },
			{ WALL, WALL, WALL, WALL, COIN, WALL, WALL, WALL, COIN, WALL, COIN, WALL, WALL, WALL, COIN, WALL, WALL,
					WALL, WALL },
			{ COIN, COIN, COIN, COIN, COIN, COIN, COIN, WALL, COIN, COIN, COIN, WALL, COIN, COIN, COIN, COIN, COIN,
					COIN, COIN },
			{ WALL, WALL, WALL, WALL, COIN, WALL, WALL, WALL, COIN, WALL, COIN, WALL, WALL, WALL, COIN, WALL, WALL,
					WALL, WALL },
			{ WALL, COIN, COIN, COIN, COIN, WALL, COIN, COIN, COIN, WALL, COIN, COIN, COIN, WALL, COIN, COIN, COIN,
					COIN, WALL },
			{ WALL, COIN, WALL, WALL, COIN, WALL, COIN, WALL, WALL, WALL, WALL, WALL, COIN, WALL, COIN, WALL, WALL,
					COIN, WALL },
			{ WALL, COIN, COIN, COIN, COIN, COIN, COIN, COIN, COIN, COIN, COIN, COIN, COIN, COIN, COIN, COIN, COIN,
					COIN, WALL },
			{ WALL, COIN, WALL, WALL, COIN, WALL, WALL, WALL, COIN, WALL, COIN, WALL, WALL, WALL, COIN, WALL, WALL,
					COIN, WALL },
			{ WALL, COIN, COIN, COIN, COIN, COIN, COIN, COIN, COIN, WALL, COIN, COIN, COIN, COIN, COIN, COIN, COIN,
					COIN, WALL },
			{ WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL,
					WALL, WALL } };

	public Game(Board scene)
	{
		this.scene = scene;
		int playerStartY = 9;
		int playerStartX = 11;

		this.board[playerStartX][playerStartY] = Game.EMPTY;
		this.pacman = new PacMan(playerStartY, playerStartX);

		this.drawBoard();
		this.GAMELOOP();

	}

	private void drawBoard()
	{
		Platform.runLater(new Runnable()
		{
			@Override
			public void run()
			{
				scene.drawBoard(getGame());
			}
		});

	}

	private void update()
	{
		this.pacman.percept(this);
		this.board[this.pacman.getY()][this.pacman.getX()] = Game.EMPTY;
	}

	private void GAMELOOP()
	{
		while (true)
		{
			try
			{
				Thread.sleep(UPDATE_TIME);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			this.update();
			this.drawBoard();

		}
	}

	public int[][] getBoard()
	{
		return this.board;
	}

	public void setBoard(int x, int y, int stage)
	{
		this.board[y][x] = stage;
	}

	public PacMan getPlayer()
	{
		return this.pacman;
	}

	public Game getGame()
	{
		return this;
	}

}
