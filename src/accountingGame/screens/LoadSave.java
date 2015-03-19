package accountingGame.screens;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;
import com.golden.gamedev.object.Sprite;

public class LoadSave extends GameObject{
	
	Rectangle slot1ButtonRectangle, slot2ButtonRectangle, slot3ButtonRectangle;
	BufferedImage background,slot1Button, slot2Button, slot3Button, loadButton, deleteButton, slot1ButtonHighlight, slot2ButtonHighlight, slot3ButtonHighlight;
	Sprite slot1,slot2,slot3,load,delete;

	public LoadSave(GameEngine gameEngine) {
		super(gameEngine);
	}

	@Override
	public void initResources() {
		background = getImage("");
		slot1Button = getImage("");
		slot2Button = getImage("");
		slot3Button = getImage("");
		loadButton = getImage("");
		deleteButton = getImage("");
		slot1ButtonHighlight=getImage("");
		slot2ButtonHighlight=getImage("");
		slot3ButtonHighlight=getImage("");
		slot1 = new Sprite(slot1Button,0,0);
		slot2 = new Sprite(slot2Button,0,0);
		slot3 = new Sprite(slot3Button,0,0);
		
	}
	
	@Override
	public void update(long elapsedTime) {
		slotHighlight();
		loadGame();
		
		slot1.update(elapsedTime);
		slot2.update(elapsedTime);
		slot3.update(elapsedTime);
	}
	
	@Override
	public void render(Graphics2D g) {
		g.drawImage(background,null,0,0);
        slot1.render(g);
        slot2.render(g);
        slot3.render(g);
		
	}

	public void slotHighlight()
	{ //TODO ask lawrence
		Point p = new Point (getMouseX(), getMouseY());
	        if(slot1ButtonRectangle.contains(p))
	        {
	            slot1.setImage(slot1ButtonHighlight);
	            slot2.setImage(slot2Button);
	            slot3.setImage(slot3Button);
	        }
	        else if(slot2ButtonRectangle.contains(p))
	        {
	        	slot1.setImage(slot1Button);
	            slot2.setImage(slot2ButtonHighlight);
	            slot3.setImage(slot3Button);
	        }
	        else if(slot3ButtonRectangle.contains(p))
	        {
	        	slot1.setImage(slot1Button);
	            slot2.setImage(slot2Button);
	            slot3.setImage(slot3ButtonHighlight);
	        }
	        else
	        {
	        	slot1.setImage(slot1Button);
	            slot2.setImage(slot2Button);
	            slot3.setImage(slot3Button);
	        }
	        
	}
	
	private void loadGame() {
		if(click()){
            if(slot1.getImage().equals(slot1ButtonHighlight))
            {
                //TODO:load game
            }
            if(slot2.getImage().equals(slot2ButtonHighlight))
            {
               
            }
            if(slot3.getImage().equals(slot3ButtonHighlight))
            {
                
            }
        }
	}


}
