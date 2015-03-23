package accountingGame;

import java.util.ArrayList;

public class TextManager {
	
	//http://www.java-gaming.org/index.php?topic=33492.0 taken from cubemaster21 with slight modification 
	private ArrayList<String> dialogueText = new ArrayList();
	private String text="";
	
	public void nextLine(String text, int textBoxWidth)
	{
		dialogueText.clear();
		int currentPixel=0;
		int breakTextAt =0;
		int startTextIndex=0;
		try
		{
			for (int i =0;i<text.length();i++)
			{
				String line = text.charAt(i)+"";
				breakTextAt=i;
				if (line.equals(" ") || line.equals("."))
				{
					breakTextAt = i+1;
				}
				currentPixel += line.length()*8;
				
				if ((currentPixel >= textBoxWidth)||line.equals("."))
				{
					i = breakTextAt;
					dialogueText.add(text.substring(startTextIndex,breakTextAt));
					startTextIndex = breakTextAt;
					currentPixel = 0;
				}
			}
			dialogueText.add(text.substring(startTextIndex,text.length()));	
		}
		catch (NullPointerException e)
		{
			//System.out.println("no text");
		}
	}

	public ArrayList<String> getDialogueText() {
		return dialogueText;
	}

	public void setDialogueText(ArrayList<String> dialogueText) {
		this.dialogueText = dialogueText;
	}
}
