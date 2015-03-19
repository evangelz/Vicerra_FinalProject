package accountingGame;

import java.util.ArrayList;

import accountingGame.npc.NPC;
import accountingGame.npc.QuestItem;

public abstract class QuestTemplate {
	
	private String answer;
	private String requirement;
	private String questTitle;
	private ArrayList<QuestItem> questInformation = new ArrayList();
	private ArrayList<NPC> npc = new ArrayList();
	private String type;
	
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getRequirement() {
		return requirement;
	}
	public void setRequirement(String requirement) {
		this.requirement = requirement;
	}
	public ArrayList<QuestItem> getQuestInformation() {
		return questInformation;
	}
	public void setQuestInformation(ArrayList<QuestItem> questInformation) {
		this.questInformation = questInformation;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public ArrayList<NPC> getNpc() {
		return npc;
	}
	public void setNpc(ArrayList<NPC> npc) {
		this.npc = npc;
	}
	public String getQuestTitle() {
		return questTitle;
	}
	public void setQuestTitle(String questTitle) {
		this.questTitle = questTitle;
	}
	
}
