package accountingGame.npc;

import java.util.ArrayList;
import java.util.HashMap;

import com.golden.gamedev.object.Sprite;

public class QuestItem extends Sprite{
	
	private boolean interactable =false;
	private int value;
	private String records;
	private String info;
	
	public boolean isInteractable() {
		return interactable;
	}
	public void setInteractable(boolean interactable) {
		this.interactable = interactable;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}

	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getRecords() {
		return records;
	}
	public void setRecords(String string) {
		this.records = string;
	}


	
}
