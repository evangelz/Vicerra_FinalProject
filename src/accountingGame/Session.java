package accountingGame;

import accountingGame.sprite.PlayerSprite;

public class Session{

		private static PlayerSprite player = null;

		public static PlayerSprite getCurrentPlayer() throws Exception{
			if (getPlayer()==null)
			{
				throw new Exception();
			}
			else
			{
				return getPlayer();
			}
		}

		public static PlayerSprite getPlayer() {
			return player;
		}

		public static void setPlayer(PlayerSprite player) {
			Session.player = player;
		}
	}
