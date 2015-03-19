package accountingGame;

import java.awt.Dimension;

import com.golden.gamedev.GameLoader;

public class Driver {

	public static void main(String[] args)
	{
		GameLoader game = new GameLoader();
		game.setup(new ScreenHandler(), new Dimension(1024,780),false);
		game.start();
	}
}
