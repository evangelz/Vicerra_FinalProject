package accountingGame;

import java.awt.Dimension;

import com.golden.gamedev.GameLoader;

public class Driver {

	public static void main(String[] args)
	{
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		GameLoader game = new GameLoader();
		game.setup(new ScreenHandler(), new Dimension(1024,780),false);
		game.start();
	}
}
