package accountingGame.sprite;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import accountingGame.QuestTemplate;
import accountingGame.screens.SkillTree;

import com.golden.gamedev.object.Sprite;

public class PlayerSprite extends Sprite{
	
	
	
	private SkillTree skillLevel;
	private int moveSpeed=1;
	private QuestTemplate activeSideQuest;
	BufferedImage playerUp, playerDown, playerLeft, playerRight;
	
	public PlayerSprite()
	{
		
	}
	
	public PlayerSprite(BufferedImage playerDown, BufferedImage playerUp, BufferedImage playerLeft, BufferedImage playerRight, SkillTree skillLevel)
	{
		setImage(playerDown);
		this.playerDown = playerDown;
		this.playerUp = playerUp;
		this.playerRight = playerRight;
		this.playerLeft = playerLeft;
		this.setSkillLevel(skillLevel);
	}
	
	public SkillTree getSkillLevel() {
		return skillLevel;
	}

	public void setSkillLevel(SkillTree skillLevel) {
		this.skillLevel = skillLevel;
	}

	public int getMoveSpeed() {
		return moveSpeed;
	}

	public void setMoveSpeed(int moveSpeed) {
		this.moveSpeed = moveSpeed;
	}
	
	public void changeDirection(int key)
	{
		switch(key)
		{
		case KeyEvent.VK_W:
			setImage(playerUp);
			break;
		case KeyEvent.VK_S:
			setImage(playerDown);
			break;
		case KeyEvent.VK_A:
			setImage(playerLeft);
			break;
		case KeyEvent.VK_D:
			setImage(playerRight);
			break;
		}
	}


}
