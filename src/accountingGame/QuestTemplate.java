package accountingGame;

import java.util.ArrayList;
import java.util.HashMap;

import accountingGame.npc.NPC;
import accountingGame.npc.QuestItem;

public class QuestTemplate {
	
	private String answer;
	private String requirement;
	private String questTitle;
	private HashMap<String, QuestItem> questInformation = new HashMap();
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
	public HashMap<String, QuestItem> getQuestInformation() {
		return questInformation;
	}
	public void setQuestInformation(HashMap<String, QuestItem> questInformation) {
		this.questInformation = questInformation;
	}
	
}
