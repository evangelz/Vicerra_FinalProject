package accountingGame;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

import com.golden.gamedev.object.collision.CollisionGroup;

public class CollisionHandler extends CollisionGroup{

	public CollisionHandler(SpriteGroup PLAYER, SpriteGroup TOWNOBJECTS_GROUP)
	{
		setCollisionGroup(PLAYER,TOWNOBJECTS_GROUP);
	}
	

	@Override
	public void collided(Sprite player, Sprite bldg) 
	{
		revertPosition1();
		
	}
	


}
