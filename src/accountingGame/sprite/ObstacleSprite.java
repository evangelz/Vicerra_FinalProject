package accountingGame.sprite;

import java.awt.image.BufferedImage;

import com.golden.gamedev.object.Sprite;

public class ObstacleSprite extends Sprite {
	

	
	public ObstacleSprite(BufferedImage image, double x, double y)
	{
		setImage(image);
		move(x,y);
	}
}
