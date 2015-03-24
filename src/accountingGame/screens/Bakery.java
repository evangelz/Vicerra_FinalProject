package accountingGame.screens;

import java.awt.Font;
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
import com.golden.gamedev.object.font.SystemFont;

public class Bakery extends GameObject {
	
	TextManager dialogueText;
	private int buttonYPosition=10;
	private int dialogueBoxWidth;
	TButton welcome, cash, cakeInventory, breadInventory, manager,records ;
	TButton questScreenExit, questScreenSubmit, notesScreenExit;
	FrameWork frame;
	
	TTextField answer;
	
	Background town;
	Rectangle questBox, notesBox, exitBox;
	Rectangle exitPopUpYesButtonRectangle, exitPopUpNoButtonRectangle,submitButtonRectangle, okButtonRectangle;
	Rectangle profileButtonRectangle, questButtonRectangle, notesButtonRectangle,referenceButtonRectangle, exitButtonRectangle, questBoardRectangle;
	Rectangle questCompleteOkRectangle, questFailedOkRectangle;
	Rectangle bakeryNPCRectangle,recordsRectangle, inventoryRectangle, cashRectangle;
	
	BufferedImage bakeryNPCGlow, bakeryNPCGlowHighlight, recordsGlow, recordsGlowHighlight, inventoryGlow, inventoryGlowHighlight, cashGlow, cashGlowHighlight;
	BufferedImage questButton, questButtonHighlight, notesButton, notesButtonHighlight,exitButton, exitButtonHighlight,submitButton,submitButtonHighlight;
	BufferedImage questPopUp, notesPopUp,exitPopUp, dialogueBox;
	BufferedImage exitPopUpYesButton, exitPopUpNoButton, exitPopUpNoButtonHighlight, exitPopUpYesButtonHighlight;
	BufferedImage questCompleteOk, questCompleteOkHighlight, questFailedOk, questFailedOkHighlight,questCompletePopUp,questFailedPopUp;
	
	Sprite bakeryNPCImage, recordsImage, inventoryImage, cashImage;
	Sprite questCompleteOkImage, questFailedOkImage;
	Sprite questScreen, notesScreen, exitScreen,uiTray, dialogueTray,submitButtonImage,questCompleteScreen, questFailedScreen;
	Sprite questExit,notesExit, exitYes, exitNo;
	Sprite quest,notes,exit;
	
	private AccountManager updatePlayerAccount;

	private PlayerSprite player;

	private SpriteGroup GLOW;
	private SpriteGroup UI_POPUPS;
	private SpriteGroup PLAYER;
	private SpriteGroup UI_BUTTONS;
	
	private SystemFont text;
	
	public Bakery(GameEngine gameEngine) {
		super(gameEngine);
		
	}

	@Override
	public void initResources() {
		updatePlayerAccount = new AccountManager();
		dialogueText= new TextManager();
		dialogueBoxWidth = 505;
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
		
		answer = new TTextField("answer",140,500,300,45);
		frame.add(answer);
		answer.setEnabled(false);
		
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
		questScreen = new Sprite(questPopUp,-140,10);
		questScreen.setActive(false);
		questScreenExit = new TButton("X", 449, 105, 30, 30);
		frame.add(questScreenExit);
		questScreenExit.setVisible(false);
		
		questCompletePopUp = getImage("images/PopUpWindow_QuestComplete.png");
		questCompleteScreen = new Sprite(questCompletePopUp, 300,300);
		questCompleteScreen.setActive(false);
		questCompleteOk = getImage("images/Button_Ok_Neutral.png");
		questCompleteOkHighlight = getImage("images/Button_Ok_Clicked.png");
		questCompleteOkImage = new Sprite(questCompleteOk, 467,394);
		questCompleteOkImage.setActive(false);
		questCompleteOkRectangle = new Rectangle(467,394,174,60);
		
		questFailedPopUp = getImage("images/PopUpWindow_QuestFailed.png");
		questFailedScreen = new Sprite(questFailedPopUp,300,300);
		questFailedScreen.setActive(false);
		questFailedOk = getImage("images/Button_Ok_Clicked.png");
		questFailedOkHighlight = getImage("images/Button_Ok_Neutral.png");
		questFailedOkImage = new Sprite(questFailedOk, 467,394);
		questFailedOkImage.setActive(false);
		questFailedOkRectangle = new Rectangle(467,394,174,60);
		
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
		
		dialogueBox = getImage("images/DialogueBoxBakery.png");
		dialogueTray = new Sprite(dialogueBox,260,539);
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
		
		//barberNPCGlow = getImage("images/Barbershop_NPC.png");
		bakeryNPCGlowHighlight = getImage("images/Bakery_NPC.png");
		bakeryNPCImage = new Sprite (bakeryNPCGlow, 644,166);
		bakeryNPCRectangle = new Rectangle(644,166,69,132);
			
		//recordsGlow = getImage("images/Barbershop_record.png");
		recordsGlowHighlight = getImage("images/Bakery_record.png");
		recordsImage = new Sprite (recordsGlow, 427,242);
		recordsRectangle = new Rectangle(427,242,61,65);
				
		//suppliesGlow = getImage("images/Barbershop_Equipment.png");
		inventoryGlowHighlight = getImage("images/Bakery_Inventory.png");
		inventoryImage = new Sprite (inventoryGlow, 491,257);
		inventoryRectangle = new Rectangle(491,257,162,207);
				
		//cashGlow = getImage("images/Barbershop_Cashbox.png");
		cashGlowHighlight = getImage("images/Bakery_Cashbox.png");
		cashImage = new Sprite (cashGlow, 667,275);
		cashRectangle = new Rectangle(667,275,102,90);
		
		
		GLOW = new SpriteGroup("glow");
		
		GLOW.add(bakeryNPCImage);
		GLOW.add(recordsImage);
		GLOW.add(inventoryImage);
		GLOW.add(cashImage);
		
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
		
		Font plainFont = new Font("Serif", Font.PLAIN, 20);
		
		text = new SystemFont(plainFont);
		
		/*text = fontManager.getFont(getImages("images/smallfont.png", 8, 12),
                " !\"#$%&'()*+,-./0123456789:;<=>?" +
                "@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_" +
                "`abcdefghijklmnopqrstuvwxyz{|}~~");*/
		
	}
	
	@Override
	public void update(long elapsedTime) {
		uiTray.update(elapsedTime);
		quest.update(elapsedTime);
		notes.update(elapsedTime);
		exit.update(elapsedTime);
		
		highlightButton();
		highlightOkButton();
		popUp();
		glowItems();
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
		GLOW.render(g);
		uiTray.render(g);
		//PLAYER.render(g);
		UI_BUTTONS.render(g);
		showClosePopUp(g);
		
		
		
	}
	
	private void glowItems()
	{
		Point p = new Point (getMouseX(), getMouseY());
		if(bakeryNPCRectangle.contains(p))
        {
        	bakeryNPCImage.setImage(bakeryNPCGlowHighlight);
        	recordsImage.setImage(recordsGlow);
			inventoryImage.setImage(inventoryGlow);
			cashImage.setImage(cashGlow);
			
        }
		else if(recordsRectangle.contains(p))
        {
			recordsImage.setImage(recordsGlowHighlight);
			bakeryNPCImage.setImage(bakeryNPCGlow);
			inventoryImage.setImage(inventoryGlow);
			cashImage.setImage(cashGlow);
        }
		else if(inventoryRectangle.contains(p))
		{
			inventoryImage.setImage(inventoryGlowHighlight);
			bakeryNPCImage.setImage(bakeryNPCGlow);
			recordsImage.setImage(recordsGlow);
			cashImage.setImage(cashGlow);
		}
		else if(cashRectangle.contains(p))
		{
			cashImage.setImage(cashGlowHighlight);
			bakeryNPCImage.setImage(bakeryNPCGlow);
			recordsImage.setImage(recordsGlow);
			inventoryImage.setImage(inventoryGlow);
		
		}
		else
		{
			bakeryNPCImage.setImage(bakeryNPCGlow);
			recordsImage.setImage(recordsGlow);
			inventoryImage.setImage(inventoryGlow);
			cashImage.setImage(cashGlow);
		}
	}
	
	private void highlightOkButton()
	{
		Point p = new Point (getMouseX(), getMouseY());
		if(questCompleteOkRectangle.contains(p) && questCompleteOkImage.isActive())
        {
        	questCompleteOkImage.setImage(questCompleteOkHighlight);
        	questFailedOkImage.setImage(questFailedOk);
        	
        	
        }
        else if(questFailedOkRectangle.contains(p) && questFailedOkImage.isActive())
        {
        	questFailedOkImage.setImage(questFailedOkHighlight);
        	questCompleteOkImage.setImage(questCompleteOk);
        }
        else
        {
        	questFailedOkImage.setImage(questFailedOk);
        	questCompleteOkImage.setImage(questCompleteOk);
        }
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
        else if(submitButtonRectangle.contains(p) && submitButtonImage.isActive())
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
	            	submitButtonImage.setActive(true);
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
	            if(questCompleteOkImage.getImage().equals(questCompleteOkHighlight))
	            {
	            	questCompleteScreen.setActive(false);
	            	questCompleteOkImage.setActive(false);
	            	
	            }
	            if(questFailedOkImage.getImage().equals(questFailedOkHighlight))
	            {
	            	questFailedScreen.setActive(false);
	            	questFailedOkImage.setActive(false);
	            	
	            }
				if(exitNo.getImage().equals(exitPopUpNoButtonHighlight) && exitNo.isActive())
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
			submitButtonImage.setActive(false);
			
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
			dialogueText.nextLine(player.getPlayerNotes(), 360);
			dialoguePrinter(g,350,200);
		
		}
		if(dialogueTray.isActive())
		{
			dialogueTray.render(g);
			//text.drawString(g, "Cash is 1000 pesos", 280, 547);
			dialoguePrinter(g, 280, 547);
		}
		if (questCompleteScreen.isActive())
		{
			questCompleteScreen.render(g);
			questCompleteOkImage.render(g);
			enableOrDisableMap(false);
		}
		if(questFailedScreen.isActive())
		{
			questFailedScreen.render(g);
			questFailedOkImage.render(g);
			enableOrDisableMap(false);
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
			updatePlayerAccount.updateAccount(player.getPlayerNotes(),player.getPlayerID());
			parent.nextGameID = 1;
			finish();
		}
		if(cash.isMousePressed())
		{
			
			if (player.getActiveQuest()[0] == null  || !(player.getActiveQuest()[0].getNpc().get(0).getNPCName().equals("baker")))
			{
				dialogueText.nextLine("So much cash, Maybe I should consider switching careers",dialogueBoxWidth-60);
			}
			else if (player.getActiveQuest()[0].getNpc().get(0).getNPCName().equals("baker"))
			{
				dialogueText.nextLine(player.getActiveQuest()[0].getQuestInformation().get("cash").getValue(), dialogueBoxWidth-60);
				notesChecker(player.getActiveQuest()[0].getQuestInformation().get("cash").getValue()+"");
			}
			
			dialogueTray.setActive(true);
			enableOrDisableMap(false);
		}
		if(cakeInventory.isMousePressed() || breadInventory.isMousePressed())
		{
			if (player.getActiveQuest()[0]==null  || !(player.getActiveQuest()[0].getNpc().get(0).getNPCName().equals("baker")))
			{
				dialogueText.nextLine("Yummy", dialogueBoxWidth-60);
			}
			else if (player.getActiveQuest()[0].getNpc().get(0).getNPCName().equals("baker"))
			{
				dialogueText.nextLine(player.getActiveQuest()[0].getQuestInformation().get("inventory").getValue(), dialogueBoxWidth-60);
				notesChecker(player.getActiveQuest()[0].getQuestInformation().get("inventory").getValue());
			}
			
			
			dialogueTray.setActive(true);
			enableOrDisableMap(false);
		}
		if(manager.isMousePressed())
		{
			if (player.getActiveQuest()[0]==null  || !(player.getActiveQuest()[0].getNpc().get(0).getNPCName().equals("baker")))
			{
				dialogueText.nextLine("Welcome, please help yourself",dialogueBoxWidth-60);
			}
			else if (player.getActiveQuest()[0].getNpc().get(0).getNPCName().equals("baker"))
			{
				dialogueText.nextLine(player.getActiveQuest()[0].getNpc().get(0).getDialogue(), dialogueBoxWidth-60);
				notesChecker(player.getActiveQuest()[0].getNpc().get(0).getDialogue());
			}
			
			
			dialogueTray.setActive(true);
			enableOrDisableMap(false);

		}
		if(records.isMousePressed())
		{
			if (player.getActiveQuest()[0]==null  || !(player.getActiveQuest()[0].getNpc().get(0).getNPCName().equals("baker")))
			{
				dialogueText.nextLine("Private records of Mr Baker, I better get permission to view it", dialogueBoxWidth-60);
			}
			else if (player.getActiveQuest()[0].getNpc().get(0).getNPCName().equals("baker"))
			{
				dialogueText.nextLine(player.getActiveQuest()[0].getQuestInformation().get("records").getValue(), dialogueBoxWidth-60);
				notesChecker(player.getActiveQuest()[0].getQuestInformation().get("records").getValue());
			}
			
			dialogueTray.setActive(true);
			enableOrDisableMap(false);
		}
	}

	private void notesChecker(String note) {
		if (!(player.getPlayerNotes().contains(note)))
		{
				player.setPlayerNotes(player.getPlayerNotes()+note+".");
		}
	}
	
	private void enableOrDisableMap(boolean visible)
	{
		welcome.setEnabled(visible);
		cash.setEnabled(visible);
		cakeInventory.setEnabled(visible);
		breadInventory.setEnabled(visible);
		manager.setEnabled(visible);
		records.setEnabled(visible);
		answer.setEnabled(!visible);
		
	}

	
	private void dialoguePrinter(Graphics2D g, int x, int y) {
		for (int i=0;i<dialogueText.getDialogueText().size();i++)
		{
			text.drawString(g,dialogueText.getDialogueText().get(i) , x, y+i*30);
		}
	}
	
	private void submitAnswer()
	{
		if(click())
		{
	        if(submitButtonImage.getImage().equals(submitButtonHighlight) && submitButtonImage.isActive() && player.getActiveQuest()[0]!= null)
	        {
	            if (answer.getText()!=null && answer.getText().toLowerCase().equals(player.getActiveQuest()[0].getAnswer()))

	            {
	            	//TODO: show success
	            	System.out.println("success");
	            	enableQuestComplete(true);
	            
	            	updatePlayerAccount.updateLevel(player.getActiveQuest()[0].getSkillLevel(),player.getPlayerID(),player.getActiveQuest()[0].getSkillID());
	            	updatePlayerAccount.removeQuest(player.getPlayerID());
	            	player.getActiveQuest()[0] = null;
	            	updatePlayerAccount.updateAccount("",player.getPlayerID());
	            	
	            }
	            else
	            {
	            	//show failure
	            	System.out.println("failure");
	            	enableQuestComplete(false);
	            	
	            	updatePlayerAccount.removeQuest(player.getPlayerID());
	            	player.getActiveQuest()[0] = null;
	            	updatePlayerAccount.updateAccount("",player.getPlayerID());
	            }
	            	
	        }
		}
	}
	
	private void enableQuestComplete(boolean enable) {
		questCompleteScreen.setActive(enable);
		questCompleteOkImage.setActive(enable);
		questFailedScreen.setActive(!enable);
		questFailedOkImage.setActive(!enable);
	}

}
