package pacman;

import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pacman.m.Game;
import pacman.v.Board;

public class Main extends Application
{

	public static void main(String[] args)
	{
		launch(args);
	}

	@Override
	public void start(Stage stage)
	{
		Board scene = new Board(new Pane());
		stage.setScene(scene);
		stage.show();
		new Thread(new Runnable()
		{

			@Override
			public void run()
			{
				new Game(scene);

			}
		}).start();

	}

}
