package accountingGame.npc;

import com.golden.gamedev.object.Sprite;

public class NPC extends Sprite {
	private String dialogue;
	private String nPCName;
	
	public String getDialogue() {
		return dialogue;
	}

	public void setDialogue(String dialogue) {
		this.dialogue = dialogue;
	}

	public String getnPCName() {
		return nPCName;
	}

	public void setnPCName(String nPCName) {
		this.nPCName = nPCName;
	}
	
}
