package accountingGame;

import accountingGame.screens.Bakery;
import accountingGame.screens.BarberShop;
import accountingGame.screens.MainMenu;
import accountingGame.screens.Supermarket;
import accountingGame.screens.World;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;

public class ScreenHandler extends GameEngine{

	@Override
	public GameObject getGame(int gameID) {
		{distribute = true;}
		 switch(gameID){
         case 0: return new MainMenu(this);
         case 1: return new World(this);
         case 2: return new BarberShop(this);
         case 3: return new Bakery(this);
         case 4: return new Supermarket(this);
         
     }
		return null;
	}

}
