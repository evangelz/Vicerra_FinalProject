package accountingGame;

import java.util.ArrayList;

import accountingGame.sprite.PlayerSprite;

public class QuestSystem {
	
	PlayerSprite player;
	QuestList questList;
	ArrayList<String> unlockedSkill = new ArrayList(); 
	
	public QuestSystem(PlayerSprite player, QuestList questList)
	{
		this.player = player;
		this.questList = questList;
		
	}
	
	public void matchDifficulty()
	{
	
	}
	
	public void generateQuestInformation()
	{
		
	}
	
	public void computeReward()
	{
		
	}
	
	public void verifyAnswer()
	{
		
	}
}
