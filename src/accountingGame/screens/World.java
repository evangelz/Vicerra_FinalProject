package accountingGame.screens;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.Font;

import accountingGame.sprite.PlayerSprite;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;
import com.golden.gamedev.gui.TButton;
import com.golden.gamedev.gui.toolkit.FrameWork;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.GameFont;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ImageBackground;

public class World extends GameObject {
	int buttonYPosition=10;
	int bakeryCounter, barbershopCounter, supermarketCounter, questBoardCounter,houseCounter;
	TButton bakery, barbershop, supermarket, questBoard, house;
	TButton questScreenExit, questScreenSubmit, notesScreenExit,questBoardAccept, questBoardExit;
	FrameWork frame;
	
	Background town;
	Rectangle questBox, notesBox, exitBox;
	Rectangle exitPopUpYesButtonRectangle, exitPopUpNoButtonRectangle;
	Rectangle profileButtonRectangle, questButtonRectangle, notesButtonRectangle,referenceButtonRectangle, exitButtonRectangle, questBoardRectangle;
	BufferedImage questButton, questButtonHighlight, notesButton, notesButtonHighlight,exitButton, exitButtonHighlight;
	BufferedImage questPopUp, notesPopUp,exitPopUp, questBoardPopUp;
	BufferedImage exitPopUpYesButton, exitPopUpNoButton, exitPopUpNoButtonHighlight, exitPopUpYesButtonHighlight;
	Sprite questScreen, notesScreen, exitScreen,uiTray, questBoardScreen;
	Sprite notesExit, exitYes, exitNo;
	Sprite quest,notes,exit;
	
	private GameFont text;
	
	private PlayerSprite player;
	private SkillTree skillTree;
	
	private SpriteGroup UI_POPUPS;
	private SpriteGroup PLAYER;
	private SpriteGroup UI_BUTTONS;
	
	public World(GameEngine gameEngine) {
		super(gameEngine);
		
	}

	@Override
	public void initResources() {
		bakeryCounter=0; 
		barbershopCounter=0; 
		supermarketCounter=0;
		questBoardCounter=0;
		houseCounter=0;
		frame = new FrameWork(bsInput, 1024,780);
		supermarket = new TButton("supermarket",644,82, 205,201);
		bakery = new TButton("bakery",170,494, 123,132);
		barbershop = new TButton("barbershop",804,507, 126,134);
		questBoard = new TButton("quest board",432,357, 86,65);
		house = new TButton("house",23,89, 144,81);
		frame.add(bakery);
		frame.add(supermarket);
		frame.add(barbershop);
		frame.add(questBoard);
		frame.add(house);

		town = new ImageBackground(getImage("images/TownMap1.png"));
		
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
		
		questBoardPopUp = getImage("images/PopupWindow_Quests1.png");
		questBoardScreen = new Sprite(questBoardPopUp,300,10);
		questBoardScreen.setActive(false);
		questBoardExit = new TButton("X", 889, 105, 30, 30);
		frame.add(questBoardExit);
		questBoardExit.setVisible(false);
		
		notesPopUp = getImage("images/PopupWindow_Notes1.png");
		notesScreen = new Sprite(notesPopUp,100,10);
		notesScreen.setActive(false);
		notesScreenExit = new TButton("X", 689, 105, 30, 30);
		frame.add(notesScreenExit);
		notesScreenExit.setVisible(false);
	
		exitPopUp = getImage("");
		exitScreen = new Sprite(exitPopUp,0,0);
		exitPopUpYesButton = getImage("");
		exitPopUpYesButtonHighlight = getImage("");
		exitPopUpNoButton = getImage("");
		exitPopUpNoButtonHighlight=getImage("");
		exitYes = new Sprite(exitPopUpYesButton,0,0);
		exitNo = new Sprite(exitPopUpNoButton,0,0);
		exitPopUpYesButtonRectangle = new Rectangle(0,0,0,0);
		exitPopUpNoButtonRectangle = new Rectangle(0,0,0,0);
		exitScreen.setActive(false);
		exitYes.setActive(false);
		exitNo.setActive(false);
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
		UI_POPUPS.add(questBoardScreen);
		UI_POPUPS.add(exitScreen);
		
		PLAYER = new SpriteGroup("Character");
		PLAYER.add(player);
		PLAYER.setBackground(town);
		
		text = fontManager.getFont(getImages("images/smallfont.png", 8, 12),
                " !\"#$%&'()*+,-./0123456789:;<=>?" +
                "@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_" +
                "`abcdefghijklmnopqrstuvwxyz{|}~~");
		
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
        }
        else if(exitPopUpNoButtonRectangle.contains(p))
        {
        	exitNo.setImage(exitPopUpNoButtonHighlight);
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
	            if(exit.getImage().equals(exitButtonHighlight))
	            {
	            	//showExit();
	            	enableOrDisableMap(false);
	            }
			}
			/*if(click())
			{
				if(notesExit.getImage().equals(notesExitHighlight))
				{
					notesScreen.setActive(false);
					notesExit.setActive(false);
				}
				else if(exitNo.getImage().equals(exitPopUpNoButtonHighlight))
				{
					exitScreen.setActive(false);
					exitYes.setActive(false);
					exitNo.setActive(false);
				}
				else if(exitYes.getImage().equals(exitPopUpYesButtonHighlight))
				{
					parent.nextGameID = 0;
					finish();
				}
			}*/
			
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
		if (questBoardExit.isMousePressed())
		{
			questBoardScreen.setActive(false);
			questBoardExit.setVisible(false);
			enableOrDisableMap(true);
		}
		
	}
	
	public void showClosePopUp(Graphics2D g)
	{
		if (questScreen.isActive())
		{
			questScreenExit.setVisible(true);
			questScreenExit.render(g);
			UI_POPUPS.render(g);
		}
		if(notesScreen.isActive())
		{
			notesScreenExit.setVisible(true);
			notesScreenExit.render(g);
			UI_POPUPS.render(g);
			text.drawString(g, "Cash is 1000 pesos", 350, 200);
		}
		if(questBoardScreen.isActive())
		{
			questBoardExit.setVisible(true);
			questBoardExit.render(g);
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
		if(bakery.isMousePressed())
		{
			player.setLocation(156, 460);
			parent.nextGameID = 3;
			finish();
			if (bakery.isMousePressed() && bakeryCounter==2)
			{
				System.out.println("Enter");
			}
		}
		if(supermarket.isMousePressed())
		{
			player.setLocation(648, 110);
			parent.nextGameID = 4;
			finish();
			if (supermarket.isMousePressed() && supermarketCounter==2)
			{
				System.out.println("Enter1");
				//supermarketCounter=0;
			}
		}
		if(barbershop.isMousePressed())
		{
			player.setLocation(891, 490);
			parent.nextGameID = 2;
			finish();
			if (barbershop.isMousePressed() && barbershopCounter==2)
			{
				System.out.println("Enter2");
				
				//barbershopCounter=0;
			}
		}
		if(questBoard.isMousePressed())
		{
			player.setLocation(425, 250);
			questBoardScreen.setActive(true);
			enableOrDisableMap(false);
			if (questBoard.isMousePressed() && questBoardCounter==2)
			{
				System.out.println("Enter3");
				//questBoardCounter=0;
			}
		}
		if(house.isMousePressed())
		{
			player.setLocation(0, 0);
			if (house.isMousePressed() && houseCounter==2)
			{
				System.out.println("Enter4");
				//houseCounter=0;
			}
		}
	}
	
	private void enableOrDisableMap(boolean visible)
	{
		bakery.setVisible(visible);
		supermarket.setVisible(visible);
		barbershop.setVisible(visible);
		questBoard.setVisible(visible);
		house.setVisible(visible);
	}
	
	

}