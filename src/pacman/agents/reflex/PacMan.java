package pacman.agents.reflex;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import pacman.agents.Agent;
import pacman.m.Game;

public class PacMan extends Agent
{

	private static final String PATH_RIGHT_IMG = "pacright.png";
	private static final String PATH_UP_IMG = "pacup.png";
	private static final String PATH_DOWN_IMG = "pacdown.png";
	private static final String PATH_LEFT_IMG = "pacleft.png";

	public PacMan(int x, int y)
	{
		super(x, y, PATH_RIGHT_IMG);
	}

	@Override
	public void percept(Game game)
	{
		final int[][] board = game.getBoard(); // model board data
		final int[] dirs = super.getDirection(board);

		int highestAction = -1;
		List<Integer> possibleDirection = new ArrayList<Integer>(4);
		possibleDirection.add(-1);

		for (int i = 0; i < 4; i++)
		{
			if (highestAction < dirs[i])
				possibleDirection.clear();
			else if (highestAction > dirs[i])
				continue;

			highestAction = dirs[i];
			possibleDirection.add(i);
		}

		int newDirection = possibleDirection.get(0);
		int numOfSolutions = possibleDirection.size();

		if (numOfSolutions > 1)
			newDirection = possibleDirection.get(new Random().nextInt(numOfSolutions));

		if (newDirection == UP)
			super.setNextPosition(0, -1, UP, PATH_UP_IMG, board);
		else if (newDirection == RIGHT)
			super.setNextPosition(+1, 0, RIGHT, PATH_RIGHT_IMG, board);
		else if (newDirection == DOWN)
			super.setNextPosition(0, +1, DOWN, PATH_DOWN_IMG, board);
		else
			super.setNextPosition(-1, 0, LEFT, PATH_LEFT_IMG, board);

	}

}