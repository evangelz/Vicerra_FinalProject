package accountingGame.screens;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import accountingGame.AccountManager;
import accountingGame.Session;
import accountingGame.TextManager;
import accountingGame.sprite.PlayerSprite;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;
import com.golden.gamedev.gui.TButton;
import com.golden.gamedev.gui.TTextField;
import com.golden.gamedev.gui.toolkit.FrameWork;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.GameFont;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ImageBackground;

public class Supermarket extends GameObject {
	
	TextManager dialogueText;
	private int buttonYPosition=10;
	private int dialogueBoxWidth;
	TButton welcome, rightShelfInventory, leftShelfInventory,freezerInventory, boyEmployee,girlEmployee,records,pushcart ;
	TButton questScreenExit, questScreenSubmit, notesScreenExit;
	FrameWork frame;
	
	TTextField answer;
	
	Background town;
	Rectangle questBox, notesBox, exitBox;
	Rectangle exitPopUpYesButtonRectangle, exitPopUpNoButtonRectangle,submitButtonRectangle;
	Rectangle profileButtonRectangle, questButtonRectangle, notesButtonRectangle,referenceButtonRectangle, exitButtonRectangle, questBoardRectangle;
	BufferedImage questButton, questButtonHighlight, notesButton, notesButtonHighlight,exitButton, exitButtonHighlight,submitButton,submitButtonHighlight;
	BufferedImage questPopUp, notesPopUp,exitPopUp,dialogueBox;
	BufferedImage exitPopUpYesButton, exitPopUpNoButton, exitPopUpNoButtonHighlight, exitPopUpYesButtonHighlight;
	Sprite questScreen, notesScreen, exitScreen,uiTray,dialogueTray,submitButtonImage;
	Sprite questExit,notesExit, exitYes, exitNo;
	Sprite quest,notes,exit;
	
	private AccountManager updatePlayerAccount;
	
	private PlayerSprite player;

	private SpriteGroup UI_POPUPS;
	private SpriteGroup PLAYER;
	private SpriteGroup UI_BUTTONS;
	
	private GameFont text;
	
	public Supermarket(GameEngine gameEngine) {
		super(gameEngine);
		
	}

	@Override
	public void initResources() {
		updatePlayerAccount = new AccountManager();
		dialogueBoxWidth = 870;
		dialogueText = new TextManager();
		frame = new FrameWork(bsInput, 1024,780);
		welcome = new TButton("welcome",519,594, 144,65);
		rightShelfInventory = new TButton("right shelf",618,288,333,111);
		leftShelfInventory = new TButton("left shelf",173,288, 339,117);
		freezerInventory = new TButton("freezer", 84, 98, 533, 147);
		pushcart = new TButton("pushcart", 680, 458, 253, 157);
		boyEmployee = new TButton("boy employee",234,423, 63,159);
		girlEmployee = new TButton("girl employee",464,423, 58,156);
		records = new TButton("records",396,507, 57,35);
		frame.add(welcome);;
		frame.add(rightShelfInventory);
		frame.add(leftShelfInventory);
		frame.add(freezerInventory);
		frame.add(pushcart);
		frame.add(records);
		frame.add(girlEmployee);
		frame.add(boyEmployee);
		
		answer = new TTextField("answer",140,500,300,45);
		frame.add(answer);
		answer.setEnabled(false);
		town = new ImageBackground(getImage("images/SuperMarket_Inside1.png"));
		
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
		questScreen = new Sprite(questPopUp,-140,10);
		questScreen.setActive(false);
		questScreenExit = new TButton("X", 449, 105, 30, 30);
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
		
		dialogueBox = getImage("images/DialogueBoxSupa.png");
		dialogueTray = new Sprite(dialogueBox,82,517);
		dialogueTray.setActive(false);
		
		submitButton = getImage("images/Button_Submit_Neutral.png");
		submitButtonHighlight = getImage("images/Button_Submit_Clicked.png");
		submitButtonImage = new Sprite(submitButton,185,562);
		submitButtonRectangle = new Rectangle(185,562,201,60);
		submitButtonImage.setActive(false);
		

		
		try {
			player = Session.getCurrentPlayer();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
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
		submitAnswer();
		town.setToCenter(player);
		closePopUp();
	}

	@Override
	public void render(Graphics2D g) {
		frame.render(g);
		town.render(g);
		uiTray.render(g);
		//PLAYER.render(g);
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
        else if(submitButtonRectangle.contains(p))
        {
        	submitButtonImage.setImage(submitButtonHighlight);
        }
        else
        {
        	quest.setImage(questButton);
        	notes.setImage(notesButton);
        	exit.setImage(exitButton);
        	exitYes.setImage(exitPopUpYesButton);
        	exitNo.setImage(exitPopUpNoButton);
        	submitButtonImage.setImage(submitButton);
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
	            	exitScreen.setActive(true);
	            	exitYes.setActive(true);
					exitNo.setActive(true);
	            	enableOrDisableMap(false);
	            }
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
			answer.setText("answer");
			
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
			answer.render(g);
			submitButtonImage.render(g);
			renderActiveQuest(g);
		}
		if(notesScreen.isActive())
		{
			notesScreenExit.setVisible(true);
			notesScreenExit.render(g);
			notesScreen.render(g);
			dialogueText.nextLine(player.getPlayerNotes(), 370);
			dialoguePrinter(g,350,200);
			
		}
		if(dialogueTray.isActive())
		{
			dialogueTray.render(g);
			//text.drawString(g, "Cash is 1000 pesos", 102, 526);
			dialoguePrinter(g, 102, 526);
		}
		if(exitScreen.isActive())
		{
			exitScreen.render(g);
			exitYes.render(g);
			exitNo.render(g);
		}
	}

	private void renderActiveQuest(Graphics2D g) {
		if (player.getActiveQuest()[0] != null)
		{
			dialogueText.nextLine(player.getActiveQuest()[0].getQuestTitle(), 370);
			text.drawString(g, dialogueText.getDialogueText().get(0), 100, 175);
			dialogueText.nextLine(player.getActiveQuest()[0].getQuestStory(), 370);
			dialoguePrinter(g,100,200);
		}
	}

	public void moveTo()
	{
		if(welcome.isMousePressed())
		{
			dialogueText.nextLine("Please come back again",dialogueBoxWidth);
			updatePlayerAccount.updateAccount(player.getPlayerNotes(),player.getPlayerID());
			parent.nextGameID = 1;
			finish();
		}
		if(leftShelfInventory.isMousePressed() || rightShelfInventory.isMousePressed() || freezerInventory.isMousePressed())
		{
			
			if (player.getActiveQuest()[0]==null  || !(player.getActiveQuest()[0].getNpc().get(0).getNPCName().equals("supermarket")))
			{
				dialogueText.nextLine("I better buy some eggs for breakfast tomorrow",dialogueBoxWidth);
			}
			else if (player.getActiveQuest()[0].getNpc().get(0).getNPCName().equals("supermarket"))
			{
				dialogueText.nextLine("Upon doing an inventory count, This would cost around " +player.getActiveQuest()[0].getQuestInformation().get("inventory").getValue()+"", dialogueBoxWidth-60);
				player.setPlayerNotes(player.getPlayerNotes()+"Upon doing an inventory count, This would cost around " +player.getActiveQuest()[0].getQuestInformation().get("inventory").getValue()+"");
			}
			dialogueTray.setActive(true);
			enableOrDisableMap(false);
		}
		if(boyEmployee.isMousePressed())
		{
			
			if (player.getActiveQuest()[0]==null  || !(player.getActiveQuest()[0].getNpc().get(0).getNPCName().equals("supermarket")))
			{
				dialogueText.nextLine("I better buy some eggs for breakfast tomorrow",dialogueBoxWidth);
			}
			else if (player.getActiveQuest()[0].getNpc().get(0).getNPCName().equals("supermarket"))
			{
				dialogueText.nextLine("You're here to do the request? talk to the May for more information ", dialogueBoxWidth-60);
				player.setPlayerNotes(player.getPlayerNotes()+"BoyEmployee: You're here to do the request? talk to the May for more information ");
			}
			dialogueText.nextLine("Welcome, if you need anything don't hesitate to ask",dialogueBoxWidth);
			dialogueTray.setActive(true);
			enableOrDisableMap(false);
		}
		if(girlEmployee.isMousePressed())
		{
			
			if (player.getActiveQuest()[0]==null  || !(player.getActiveQuest()[0].getNpc().get(0).getNPCName().equals("supermarket")))
			{
				dialogueText.nextLine("Welcome, How may I help you?",dialogueBoxWidth);
			}
			else if (player.getActiveQuest()[0].getNpc().get(0).getNPCName().equals("supermarket"))
			{
				dialogueText.nextLine(player.getActiveQuest()[0].getNpc().get(0).getDialogue(), dialogueBoxWidth-60);
				player.setPlayerNotes(player.getPlayerNotes()+player.getActiveQuest()[0].getNpc().get(0).getDialogue());
			}
			dialogueTray.setActive(true);
			enableOrDisableMap(false);
		}
		if(pushcart.isMousePressed())
		{
			if (player.getActiveQuest()[0]==null  || !(player.getActiveQuest()[0].getNpc().get(0).getNPCName().equals("supermarket")))
			{
				dialogueText.nextLine("I used to ride on one of these",dialogueBoxWidth);
			}
			
			else if (player.getActiveQuest()[0].getNpc().get(0).getNPCName().equals("supermarket"))
			{
				dialogueText.nextLine("Let's see, according to the manager this was bought for " +player.getActiveQuest()[0].getQuestInformation().get("equipment").getValue()+"", dialogueBoxWidth-60);
				player.setPlayerNotes(player.getPlayerNotes()+"Let's see, according to the manager this was bought for " +player.getActiveQuest()[0].getQuestInformation().get("equipment").getValue()+"");
			}
			dialogueTray.setActive(true);
			enableOrDisableMap(false);
		}
		if(records.isMousePressed())
		{
			//TODO: use hashmap and dialogue
			dialogueText.nextLine("Better not touch it",dialogueBoxWidth);
			dialogueTray.setActive(true);
			enableOrDisableMap(false);
		}
	}
	
	private void enableOrDisableMap(boolean visible)
	{
		welcome.setEnabled(visible);
		rightShelfInventory.setEnabled(visible);
		leftShelfInventory.setEnabled(visible);
		freezerInventory.setEnabled(visible);
		boyEmployee.setEnabled(visible);
		girlEmployee.setEnabled(visible);
		records.setEnabled(visible);
		answer.setEnabled(!visible);
		submitButtonImage.setActive(!visible);
	}
	
	private void dialoguePrinter(Graphics2D g, int x, int y) {
		for (int i=0;i<dialogueText.getDialogueText().size();i++)
		{
			text.drawString(g,dialogueText.getDialogueText().get(i) , x, y+i*10);
		}
	}
	
	private void submitAnswer()
	{
		if(click())
		{
	        if(submitButtonImage.getImage().equals(submitButtonHighlight) && submitButtonImage.isActive())
	        {
	            if (answer.getText().toLowerCase().equals(player.getActiveQuest()[0].getAnswer()))
	            {
	            	//TODO: show success
	            	System.out.println("success");
	            	updatePlayerAccount.updateLevel(player.getActiveQuest()[0].getSkillLevel(),player.getPlayerID(),player.getActiveQuest()[0].getSkillID());
	            	updatePlayerAccount.removeQuest(player.getPlayerID());
	            	player.getActiveQuest()[0] = null;
	            	
	            }
	            else
	            {
	            	//show failure
	            	System.out.println("failure");
	            	updatePlayerAccount.removeQuest(player.getPlayerID());
	            	player.getActiveQuest()[0] = null;
	            }
	            	
	        }
		}
	}
}
