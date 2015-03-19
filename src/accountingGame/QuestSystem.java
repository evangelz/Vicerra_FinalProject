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
		for(int i=0;i<player.getSkillLevel().getSkillTree().size();i++)
		{
			if (player.getSkillLevel().getSkillTree().get(i).isUnlocked())
			{
				unlockedSkill.add(player.getSkillLevel().getSkillTree().get(i).getSkillName());
			}
		}
		for (int i=0;i<unlockedSkill.size();i++)
		{
			if(unlockedSkill.get(i).equals((questList.getCompleteQuestList().get(i).getType())))
			{
				//TODO: arraylist of quest available
				questList.getAvailableQuestList().add(questList.getCompleteQuestList().remove(i));
			}
		}
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
