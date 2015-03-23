package accountingGame.sprite;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import accountingGame.QuestTemplate;


import com.golden.gamedev.object.Sprite;

public class PlayerSprite extends Sprite{
	
	private int playerID;
	private int questID;
	private QuestTemplate[] activeQuest = new QuestTemplate[1];
	private String playerNotes;
	BufferedImage playerUp, playerDown, playerLeft, playerRight;
	
	public PlayerSprite()
	{
		
	}
	
	public PlayerSprite(BufferedImage playerDown, BufferedImage playerUp, BufferedImage playerLeft, BufferedImage playerRight)
	{
		setImage(playerDown);
		this.playerDown = playerDown;
		this.playerUp = playerUp;
		this.playerRight = playerRight;
		this.playerLeft = playerLeft;
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

	public int getQuestID() {
		return questID;
	}

	public void setQuestID(int questID) {
		this.questID = questID;
	}

	public String getPlayerNotes() {
		return playerNotes;
	}

	public void setPlayerNotes(String playerNotes) {
		this.playerNotes = playerNotes;
	}

	public QuestTemplate[] getActiveQuest() {
		return activeQuest;
	}

	public void setActiveQuest(QuestTemplate[] activeQuest) {
		this.activeQuest = activeQuest;
	}

	public int getPlayerID() {
		return playerID;
	}

	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}


}
