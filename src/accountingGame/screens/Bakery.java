package accountingGame.screens;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import accountingGame.sprite.PlayerSprite;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;
import com.golden.gamedev.gui.TButton;
import com.golden.gamedev.gui.toolkit.FrameWork;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ImageBackground;

public class Bakery extends GameObject {
	int buttonYPosition=10;
	TButton welcome, cash, cakeInventory, breadInventory, manager,records ;
	TButton questScreenExit, questScreenSubmit, notesScreenExit;
	FrameWork frame;
	
	Background town;
	Rectangle questBox, notesBox, exitBox;
	Rectangle exitPopUpYesButtonRectangle, exitPopUpNoButtonRectangle;
	Rectangle profileButtonRectangle, questButtonRectangle, notesButtonRectangle,referenceButtonRectangle, exitButtonRectangle, questBoardRectangle;
	BufferedImage questButton, questButtonHighlight, notesButton, notesButtonHighlight,exitButton, exitButtonHighlight;
	BufferedImage questPopUp, notesPopUp,exitPopUp, dialogueBox;
	BufferedImage exitPopUpYesButton, exitPopUpNoButton, exitPopUpNoButtonHighlight, exitPopUpYesButtonHighlight;
	Sprite questScreen, notesScreen, exitScreen,uiTray, dialogueTray;
	Sprite questExit,notesExit, exitYes, exitNo;
	Sprite quest,notes,exit;
	
	
	private PlayerSprite player;
	private SkillTree skillTree;
	
	private SpriteGroup UI_POPUPS;
	private SpriteGroup PLAYER;
	private SpriteGroup UI_BUTTONS;
	
	public Bakery(GameEngine gameEngine) {
		super(gameEngine);
		
	}

	@Override
	public void initResources() {
		frame = new FrameWork(bsInput, 1024,780);
		welcome = new TButton("welcome",399,512, 219,106);
		cash = new TButton("cash",665,305, 103,64);
		breadInventory = new TButton("bread",266,132,78,525);
		cakeInventory = new TButton("cake",495,263, 156,199);
		manager = new TButton("manager",650,173, 72,127);
		records = new TButton("records",426,248, 62,60);
		frame.add(welcome);
		frame.add(cash);
		frame.add(breadInventory);
		frame.add(cakeInventory);
		frame.add(records);
		frame.add(manager);

		town = new ImageBackground(getImage("images/Bakery_Inside1.png"));
		
		uiTray = new Sprite(getImage("images/UITray1.png"),0,0);
		questButton = getImage("images/UIButton_Quests_Neutral.png");
		questButtonHighlight = getImage("images/UIButton_Quests_Clicked.png");
		notesButton = getImage("images/UIButton_Notes_Neutral.png");
		notesButtonHighlight = getImage("images/UIButton_Notes_Clicked.png");
		exitButton = getImage("images/UIButton_Exit_Neutral.png");
		exitButtonHighlight = getImage("images/UIButton_Exit_Clicked.png");
		
		quest = new Sprite(questButton,60,buttonYPosition);
		notes = new Sprite(notesButton,450,buttonYPosition);
		exit = new Sprite(exitButton,800,buttonYPosition);
		questButtonRectangle = new Rectangle(60,buttonYPosition,217,52);
		notesButtonRectangle = new Rectangle(450,buttonYPosition,227,52);
		exitButtonRectangle=new Rectangle(800,buttonYPosition,226,52);
		
		questPopUp = getImage("images/PopupWindow_Quests1.png");
		questScreen = new Sprite(questPopUp,100,10);
		questScreen.setActive(false);
		questScreenExit = new TButton("X", 689, 105, 30, 30);
		frame.add(questScreenExit);
		questScreenExit.setVisible(false);
		
		notesPopUp = getImage("images/PopupWindow_Notes1.png");
		notesScreen = new Sprite(notesPopUp,100,10);
		notesScreen.setActive(false);
		notesScreenExit = new TButton("X", 689, 105, 30, 30);
		frame.add(notesScreenExit);
		notesScreenExit.setVisible(false);
		
		exitPopUp = getImage("images/PopupWindow_LogOut.png");
		exitScreen = new Sprite(exitPopUp,100,0);
		exitPopUpYesButton = getImage("images/Button_Yes_Neutral.png");
		exitPopUpYesButtonHighlight = getImage("images/Button_Yes_Clicked.png");
		exitPopUpNoButton = getImage("images/Button_No_Neutral.png");
		exitPopUpNoButtonHighlight=getImage("images/Button_No_Clicked.png");
		exitYes = new Sprite(exitPopUpYesButton,330,350);
		exitNo = new Sprite(exitPopUpNoButton,520,350);
		exitPopUpYesButtonRectangle = new Rectangle(330,350,180,60);
		exitPopUpNoButtonRectangle = new Rectangle(520,350,180,60);
		exitScreen.setActive(false);
		exitYes.setActive(false);
		exitNo.setActive(false);
		
		dialogueBox = getImage("images/DialogueBox.png");
		dialogueTray = new Sprite(dialogueBox,0,600);
		dialogueTray.setActive(false);
		
		
		//questBox = new Rectangle(320,240,0,0);
		
		/*
		

		exitPopUpYesButton = getImage("");
		exitPopUpNoButton = getImage("");

		exitScreen = new Sprite(exitPopUp,0,0);

		
		notesBox = new Rectangle(0,0,0,0);
		exitBox = new Rectangle(0,0,0,0);
		exitPopUpYesButtonRectangle = new Rectangle(0,0,0,0);
		exitPopUpNoButtonRectangle = new Rectangle(0,0,0,0);
		*/
		skillTree = new SkillTree();
		player = new PlayerSprite(getImage("images/CharacterFront.png"),getImage("images/CharacterBack.png"),getImage("images/CharacterLeft.png"),getImage("images/CharacterRight.png"),skillTree);
		

		
		UI_BUTTONS = new SpriteGroup("UI");
		UI_BUTTONS.add(quest);
		UI_BUTTONS.add(notes);
		UI_BUTTONS.add(exit);
		
		UI_POPUPS = new SpriteGroup("Popup");
		UI_POPUPS.add(questScreen);
		UI_POPUPS.add(notesScreen);
		UI_POPUPS.add(exitScreen);
		UI_POPUPS.add(dialogueTray);
		
		PLAYER = new SpriteGroup("Character");
		PLAYER.add(player);
		PLAYER.setBackground(town);
		
		
	}
	
	@Override
	public void update(long elapsedTime) {
		uiTray.update(elapsedTime);
		quest.update(elapsedTime);
		notes.update(elapsedTime);
		exit.update(elapsedTime);
		
		highlightButton();
		popUp();
		UI_POPUPS.update(elapsedTime);
		PLAYER.update(elapsedTime);
		frame.update();
		moveTo();
		town.setToCenter(player);
		closePopUp();
	}

	@Override
	public void render(Graphics2D g) {
		frame.render(g);
		town.render(g);
		uiTray.render(g);
		PLAYER.render(g);
		UI_BUTTONS.render(g);
		showClosePopUp(g);
		
		
		
	}

	private void highlightButton() {
		Point p = new Point (getMouseX(), getMouseY());
        if(questButtonRectangle.contains(p))
        {
            quest.setImage(questButtonHighlight);
        }
        else if(notesButtonRectangle.contains(p))
        {
            notes.setImage(notesButtonHighlight);
        }
        else if(exitButtonRectangle.contains(p))
        {
            exit.setImage(exitButtonHighlight);
        }
        else if(exitPopUpYesButtonRectangle.contains(p))
        {
        	exitYes.setImage(exitPopUpYesButtonHighlight);
        	exitNo.setImage(exitPopUpNoButton);
        }
        else if(exitPopUpNoButtonRectangle.contains(p))
        {
        	exitNo.setImage(exitPopUpNoButtonHighlight);
        	exitYes.setImage(exitPopUpYesButton);
        
        }
        else
        {
        	quest.setImage(questButton);
        	notes.setImage(notesButton);
        	exit.setImage(exitButton);
        	exitYes.setImage(exitPopUpYesButton);
        	exitNo.setImage(exitPopUpNoButton);
        }
	}
	
	private void popUp()
	{
			if(click())
			{
	            if(quest.getImage().equals(questButtonHighlight))
	            {
	            	questScreen.setActive(true);
	            	enableOrDisableMap(false);
	            	
	            }
	            if(notes.getImage().equals(notesButtonHighlight))
	            {
	            	notesScreen.setActive(true);
	            	enableOrDisableMap(false);
	            }
	            else if(exitYes.getImage().equals(exitPopUpYesButtonHighlight) && exitYes.isActive())
	            {
	            	exitScreen.setActive(true);
	            	exitYes.setActive(true);
					exitNo.setActive(true);
	            	enableOrDisableMap(false);
	            }
			}
			if(click())
			{
				if(exitNo.getImage().equals(exitPopUpNoButtonHighlight))
				{
					exitScreen.setActive(false);
					exitYes.setActive(false);
					exitNo.setActive(false);
					enableOrDisableMap(true);
				}
				else if(exitYes.getImage().equals(exitPopUpYesButtonHighlight) && exitYes.isActive())
				{
					parent.nextGameID = 0;
					finish();
				}
			}
			
	}
	
	private void closePopUp() {
		if (questScreenExit.isMousePressed())
		{
			questScreen.setActive(false);
			questScreenExit.setVisible(false);
			enableOrDisableMap(true);
		}
		if (notesScreenExit.isMousePressed())
		{
			notesScreen.setActive(false);
			notesScreenExit.setVisible(false);
			enableOrDisableMap(true);
		}
		if(click() && dialogueTray.isActive())
		{
			dialogueTray.setActive(false);
			enableOrDisableMap(true);
		}
		
	}
	
	public void showClosePopUp(Graphics2D g)
	{
		if (questScreen.isActive())
		{
			questScreenExit.setVisible(true);
			questScreenExit.render(g);
			questScreen.render(g);
		}
		if(notesScreen.isActive())
		{
			notesScreenExit.setVisible(true);
			notesScreenExit.render(g);
			notesScreen.render(g);
		
		}
		if(dialogueTray.isActive())
		{
			UI_POPUPS.render(g);
		}
		if(exitScreen.isActive())
		{
			exitScreen.render(g);
			exitYes.render(g);
			exitNo.render(g);
		}
		
	}

	public void moveTo()
	{
		if(welcome.isMousePressed())
		{
			parent.nextGameID = 1;
			finish();
		}
		if(cash.isMousePressed())
		{
			dialogueTray.setActive(true);
			enableOrDisableMap(false);
		}
		if(cakeInventory.isMousePressed() || breadInventory.isMousePressed())
		{
			dialogueTray.setActive(true);
			enableOrDisableMap(false);
		}
		if(manager.isMousePressed())
		{
			dialogueTray.setActive(true);
			enableOrDisableMap(false);

		}
		if(records.isMousePressed())
		{
			dialogueTray.setActive(true);
			enableOrDisableMap(false);
		}
	}
	
	private void enableOrDisableMap(boolean visible)
	{
		welcome.setVisible(visible);
		cash.setVisible(visible);
		cakeInventory.setVisible(visible);
		breadInventory.setVisible(visible);
		manager.setVisible(visible);
		records.setVisible(visible);
	}
	
	

}
