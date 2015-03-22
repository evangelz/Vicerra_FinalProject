package accountingGame.screens;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import accountingGame.AccountManager;
import accountingGame.QuestList;
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

public class World extends GameObject {
	
	TextManager dialogueText;
	private int buttonYPosition=10;
	private int questSlotXPosition= 540;
	private int questSlotYPosition=170;
	private int questTitleXPosition = 670;
	private int questTitleYPosition = 185;
	private int questRequirementYPosition=210;
	private AccountManager updatePlayerAccount;
	
	TButton bakery, barbershop, supermarket, questBoard, house;
	TButton questScreenExit, questScreenSubmit, notesScreenExit,questBoardAccept, questBoardExit;
	FrameWork frame;
	
	TTextField answer;
	
	Background town;
	Rectangle questBox, notesBox, exitBox;
	Rectangle exitPopUpYesButtonRectangle, exitPopUpNoButtonRectangle,submitButtonRectangle;
	Rectangle profileButtonRectangle, questButtonRectangle, notesButtonRectangle,referenceButtonRectangle, exitButtonRectangle, questBoardRectangle;
	Rectangle questSlot1Rectangle, questSlot2Rectangle, questSlot3Rectangle, questSlot4Rectangle;
	
	BufferedImage questButton, questButtonHighlight, notesButton, notesButtonHighlight,exitButton, exitButtonHighlight, submitButton,submitButtonHighlight;
	BufferedImage questPopUp, notesPopUp,exitPopUp, questBoardPopUp;
	BufferedImage questSlot1, questSlot2, questSlot3,questSlot4, questSlot1Highlight, questSlot2Highlight, questSlot3Highlight,questSlot4Highlight;
	BufferedImage exitPopUpYesButton, exitPopUpNoButton, exitPopUpNoButtonHighlight, exitPopUpYesButtonHighlight;
	
	Sprite questSlot1Image,questSlot2Image,questSlot3Image,questSlot4Image, questSlot1ImageHighlight,questSlot2ImageHighlight,questSlot3ImageHighlight,questSlot4ImageHighlight;
	Sprite questScreen, notesScreen, exitScreen,uiTray, questBoardScreen,submitButtonImage;
	Sprite notesExit, exitYes, exitNo;
	Sprite quest,notes,exit;
	
	private GameFont text;
	boolean willPrint;
	private PlayerSprite player;
	private SkillTree skillTree;
	
	private SpriteGroup UI_POPUPS;
	private SpriteGroup PLAYER;
	private SpriteGroup UI_BUTTONS;
	
	private QuestList questList;
	
	
	public World(GameEngine gameEngine) {
		super(gameEngine);
		
	}

	@Override
	public void initResources() {
		updatePlayerAccount = new AccountManager();
		dialogueText = new TextManager();
		frame = new FrameWork(bsInput, 1024,780);
		bakery = new TButton("bakery",170,494, 123,132);
		barbershop = new TButton("barbershop",804,507, 126,134);
		questBoard = new TButton("quest board",432,357, 86,65);
		house = new TButton("house",23,89, 144,81);
		supermarket = new TButton("supermarket",644,82,205,201);
		frame.add(bakery);
		frame.add(supermarket);
		frame.add(barbershop);
		frame.add(questBoard);
		frame.add(house);
		
		answer = new TTextField("answer",140,500,300,45);
		frame.add(answer);
		answer.setEnabled(false);
		
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
		questScreen = new Sprite(questPopUp,-140,10);
		questScreen.setActive(false);
		questScreenExit = new TButton("X", 449, 105, 30, 30);
		frame.add(questScreenExit);
		questScreenExit.setVisible(false);
		
		questBoardPopUp = getImage("images/PopupWindow_QuestBoard1.png");
		questBoardScreen = new Sprite(questBoardPopUp,300,10);
		questBoardScreen.setActive(false);
		questBoardExit = new TButton("X", 889, 105, 30, 30);
		frame.add(questBoardExit);
		questBoardExit.setVisible(false);
		
		questSlot1 = getImage("images/Button_Quest1_Neutral.png");
		questSlot1Image = new Sprite(questSlot1,questSlotXPosition,questSlotYPosition);
		questSlot1Highlight = getImage("images/Button_Quest1_Clicked.png");
		questSlot1ImageHighlight = new Sprite(questSlot1,questSlotXPosition,questSlotYPosition);
		questSlot1Rectangle = new Rectangle(questSlotXPosition,questSlotYPosition,380,103);
		
		questSlot2 = getImage("images/Button_Quest2_Neutral.png");
		questSlot2Image = new Sprite(questSlot2,questSlotXPosition,questSlotYPosition+113);
		questSlot2Highlight = getImage("images/Button_Quest2_Clicked.png");
		questSlot2ImageHighlight = new Sprite(questSlot2,questSlotXPosition,293);
		questSlot2Rectangle = new Rectangle(questSlotXPosition,questSlotYPosition+113,380,103);
		
		questSlot3 = getImage("images/Button_Quest3_Neutral.png");
		questSlot3Image = new Sprite(questSlot3,questSlotXPosition,questSlotYPosition+223);
		questSlot3Highlight = getImage("images/Button_Quest3_Clicked.png");
		questSlot3ImageHighlight = new Sprite(questSlot3,questSlotXPosition,questSlotYPosition+223);
		questSlot3Rectangle = new Rectangle(questSlotXPosition,questSlotYPosition+226,380,103);
		
		questSlot4 = getImage("images/Button_Quest4_Neutral.png");
		questSlot4Image = new Sprite(questSlot4,questSlotXPosition,questSlotYPosition+339);
		questSlot4Highlight = getImage("images/Button_Quest4_Clicked.png");
		questSlot4ImageHighlight = new Sprite(questSlot4,questSlotXPosition,questSlotYPosition+339);
		questSlot4Rectangle = new Rectangle(questSlotXPosition,questSlotYPosition+339,380,103);
		
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
		
		submitButton = getImage("images/Button_Submit_Neutral.png");
		submitButtonHighlight = getImage("images/Button_Submit_Clicked.png");
		submitButtonImage = new Sprite(submitButton,185,562);
		submitButtonRectangle = new Rectangle(185,562,201,60);
		submitButtonImage.setActive(false);
		
		skillTree = new SkillTree();
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
		UI_POPUPS.add(questBoardScreen);
		UI_POPUPS.add(exitScreen);
		
		PLAYER = new SpriteGroup("Character");
		PLAYER.add(player);
		PLAYER.setBackground(town);
		
		text = fontManager.getFont(getImages("images/smallfont.png", 8, 12),
                " !\"#$%&'()*+,-./0123456789:;<=>?" +
                "@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_" +
                "`abcdefghijklmnopqrstuvwxyz{|}~~");
		
		questList = new QuestList();
		questList.run(player.getPlayerID());
	}
	
	@Override
	public void update(long elapsedTime) {
		uiTray.update(elapsedTime);
		quest.update(elapsedTime);
		notes.update(elapsedTime);
		exit.update(elapsedTime);
		highlightButton();
		highlightQuestSlot();
		popUp();
		UI_POPUPS.update(elapsedTime);
		PLAYER.update(elapsedTime);
		frame.update();
		moveTo();
		town.setToCenter(player);
		pickQuest();
		closePopUp();
		submitAnswer();
	}



	@Override
	public void render(Graphics2D g) {
		frame.render(g);
		town.render(g);
		uiTray.render(g);
		PLAYER.render(g);
		UI_BUTTONS.render(g);
		
		showClosePopUp(g);
		submitAnswer();
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
	
	private void highlightQuestSlot() {
		Point p = new Point (getMouseX(), getMouseY());
        if(questSlot1Rectangle.contains(p))
        {
        	questSlot1Image.setImage(questSlot1Highlight);
        	questSlot2Image.setImage(questSlot2);
        	questSlot3Image.setImage(questSlot3);
        	questSlot4Image.setImage(questSlot4);
        }
        else if(questSlot2Rectangle.contains(p))
        {
        	questSlot1Image.setImage(questSlot1);
        	questSlot2Image.setImage(questSlot2Highlight);
        	questSlot3Image.setImage(questSlot3);
        	questSlot4Image.setImage(questSlot4);
        }
        else if(questSlot3Rectangle.contains(p))
        {
        	questSlot1Image.setImage(questSlot1);
        	questSlot2Image.setImage(questSlot2);
        	questSlot3Image.setImage(questSlot3Highlight);
        	questSlot4Image.setImage(questSlot4);
        }
        else if(questSlot4Rectangle.contains(p))
        {
        	questSlot1Image.setImage(questSlot1);
        	questSlot2Image.setImage(questSlot2);
        	questSlot3Image.setImage(questSlot3);
        	questSlot4Image.setImage(questSlot4Highlight);
        }
        else
        {
        	questSlot1Image.setImage(questSlot1);
        	questSlot2Image.setImage(questSlot2);
        	questSlot3Image.setImage(questSlot3);
        	questSlot4Image.setImage(questSlot4);
        }
	}
	
	private void pickQuest()
	{
		if(click())
		{
            if(questSlot1Image.getImage().equals(questSlot1Highlight))
            {
            	questFullErrorDisplay(0);
            	
            }
            if(questSlot2Image.getImage().equals(questSlot2Highlight))
            {
            	questFullErrorDisplay(1);
            }
            if(questSlot3Image.getImage().equals(questSlot3Highlight))
            {
            	questFullErrorDisplay(2);
            }
            if(questSlot4Image.getImage().equals(questSlot4Highlight))
            {
            	questFullErrorDisplay(3);
            }
		  }
		  
	}

	private void questFullErrorDisplay(int i) {
		if(player.getActiveQuest()[0] == null)
		  {
			questScreen.setActive(true);
        	player.getActiveQuest()[0] = questList.getAvailableQuestList().get(i);
        	questList.getAvailableQuestList().add(questList.getAvailableQuestList().remove(i));
        	updatePlayerAccount.updateQuest(player.getActiveQuest()[0].getQuestID(), player.getPlayerID());
		  }
		  else
		  {
			  //TODO : error message
			  System.out.println("error");
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
			answer.setText("answer");
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
			enableOrDisableMap(false);
			questScreenExit.setVisible(true);
			questScreenExit.render(g);
			questScreen.render(g);
			answer.render(g);
			submitButtonImage.render(g);
			renderActiveQuest(g);
		}
		if(notesScreen.isActive())
		{
			enableOrDisableMap(false);
			notesScreenExit.setVisible(true);
			notesScreenExit.render(g);
			notesScreen.render(g);
			dialogueText.nextLine(player.getPlayerNotes(), 370);
			dialoguePrinter(g,350,200);
		}
		if(questBoardScreen.isActive())
		{
			enableOrDisableMap(false);
			questBoardExit.setVisible(true);
			questBoardExit.render(g);
			questBoardScreen.render(g);
			questSlot1Image.render(g);
			questSlot2Image.render(g);
			questSlot3Image.render(g);
			questSlot4Image.render(g);
			dialogueText.nextLine(questList.getAvailableQuestList().get(0).getQuestTitle(),200);
			dialoguePrinter(g,questTitleXPosition,questTitleYPosition);
			dialogueText.nextLine(questList.getAvailableQuestList().get(1).getQuestTitle(),200);
			dialoguePrinter(g,questTitleXPosition,questTitleYPosition+113);	
			dialogueText.nextLine(questList.getAvailableQuestList().get(2).getQuestTitle(),200);
			dialoguePrinter(g,questTitleXPosition,questTitleYPosition+226);		
			dialogueText.nextLine(questList.getAvailableQuestList().get(3).getQuestTitle(),200);
			dialoguePrinter(g,questTitleXPosition,questTitleYPosition+339);
			dialogueText.nextLine(questList.getAvailableQuestList().get(0).getRequirement(), 349);
			dialoguePrinter(g,questSlotXPosition+15,questRequirementYPosition);
			dialogueText.nextLine(questList.getAvailableQuestList().get(1).getRequirement(), 349);
			dialoguePrinter(g,questSlotXPosition+15,questRequirementYPosition+113);	
			dialogueText.nextLine(questList.getAvailableQuestList().get(2).getRequirement(), 349);
			dialoguePrinter(g,questSlotXPosition+15,questRequirementYPosition+226);	
			dialogueText.nextLine(questList.getAvailableQuestList().get(3).getRequirement(), 349);
			dialoguePrinter(g,questSlotXPosition+15,questRequirementYPosition+339);	
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

	private void dialoguePrinter(Graphics2D g, int x, int y) {
		for (int i=0;i<dialogueText.getDialogueText().size();i++)
		{
			text.drawString(g,dialogueText.getDialogueText().get(i) , x, y+i*15);
		}
	}

	

	public void moveTo()
	{
		if(bakery.isMousePressed())
		{
			player.setLocation(156, 460);
			parent.nextGameID = 3;
			finish();

		}
		if(supermarket.isMousePressed())
		{
			player.setLocation(648, 110);
			parent.nextGameID = 4;
			finish();

		}
		if(barbershop.isMousePressed())
		{
			player.setLocation(891, 490);
			parent.nextGameID = 2;
			finish();
			
		}
		if(questBoard.isMousePressed())
		{
			player.setLocation(425, 250);
			questBoardScreen.setActive(true);
			enableOrDisableMap(false);

		}
		if(house.isMousePressed())
		{
			player.setLocation(0, 0);

		}
	}
	
	private void enableOrDisableMap(boolean visible)
	{
		supermarket.setEnabled(visible);
		bakery.setEnabled(visible);
		barbershop.setEnabled(visible);
		questBoard.setEnabled(visible);
		house.setEnabled(visible);
		answer.setEnabled(!visible);
		submitButtonImage.setActive(!visible);
	}
	
	
	private void submitAnswer()
	{
		if(click())
		{
	        if(submitButtonImage.getImage().equals(submitButtonHighlight) && submitButtonImage.isActive())
	        {
	            if (answer.getText()!=null && answer.getText().toLowerCase().equals(player.getActiveQuest()[0].getAnswer()))
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
