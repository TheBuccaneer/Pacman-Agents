package pacman.v;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import pacman.m.Game;

public class Board extends Scene
{
	private Pane root;

	public Board(Pane root)
	{
		super(root, Game.NUM_OF_GRID_X * Game.GRID_SIZE, Game.NUM_OF_GRID_Y * Game.GRID_SIZE);

	}


	public void drawBoard(Game game)
	{
		int[][] board = game.getBoard();
		this.root = new Pane();
		this.setFill(Color.BLACK);
		this.setRoot(this.root);
		for (int i = 0; i < Game.NUM_OF_GRID_Y; i++)
		{
			for (int j = 0; j < Game.NUM_OF_GRID_X; j++)
			{
				int startX = j * Game.GRID_SIZE;
				int startY = i * Game.GRID_SIZE;
				if (board[i][j] == Game.WALL)
				{
					Rectangle rec = new Rectangle(startX, startY, Game.GRID_SIZE, Game.GRID_SIZE);
					rec.setFill(Color.BLUE);
					this.root.getChildren().add(rec);
				} else if (board[i][j] == Game.COIN)
				{
					int half = Game.GRID_SIZE / 2;
					startX += half;
					startY += half;
					Circle c = new Circle(startX, startY, half / 4);
					c.setFill(Color.GOLD);
					this.root.getChildren().add(c);
				}
			}
		}
		this.root.getChildren().add(game.getPlayer().getIV());
	}

}
