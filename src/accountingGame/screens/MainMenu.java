package accountingGame.screens;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import accountingGame.AccountManager;
import accountingGame.QuestTemplate;
import accountingGame.Session;
import accountingGame.sprite.PlayerSprite;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;
import com.golden.gamedev.gui.TButton;
import com.golden.gamedev.gui.TPasswordField;
import com.golden.gamedev.gui.TTextField;
import com.golden.gamedev.gui.toolkit.FrameWork;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

public class MainMenu extends GameObject {
	int buttonPositionY = 500;
	Rectangle logInButtonRectangle, signUpButtonRectangle, exitButtonRectangle, signUpScreenButtonRectangle;
	BufferedImage logInButton1, signUpButton1, exitButton1, background, logInButtonHighlight, signUpButtonHighlight,exitButtonHighlight,
	userCredentialsForm, signUpPopUp, signUpPopUpButton, signUpPopUpButtonHighlight;
	Sprite logIn, signUp, exit, userCredentials,signUpScreen,signUpScreenButton;
	
	TTextField username,accountUsername;
	TPasswordField password,accountPassword,accountPasswordConfirm;
	TButton logInButton, signUpButton, exitButton,signUpScreenExit,signUpButtonSmaller;
	FrameWork frame, userpassField;
	

	private AccountManager accountManager;
	
	
	

	public MainMenu(GameEngine gameEngine) {
		super(gameEngine);
		
	}

	@Override
	public void initResources() {
		logInButton = new TButton("Log In",90,buttonPositionY, 409,122);
		signUpButton = new TButton("Sign Up",500,buttonPositionY, 409,122);
		signUpButtonSmaller = new TButton("Sign Up",400,380,167,50);
		
		frame = new FrameWork(bsInput, 1024,780);
		frame.add(logInButton);
		frame.add(signUpButton);
		frame.add(signUpButtonSmaller);
		
		accountUsername = new TTextField("",475,218,300,33);
		accountPassword = new TPasswordField("",475,266,300,33);
		accountPasswordConfirm = new TPasswordField("", 475, 316, 300, 33);
		accountUsername.setVisible(false);
		accountPassword.setVisible(false);
		accountPasswordConfirm.setVisible(false);
		accountUsername.setEnabled(false);
		accountPassword.setEnabled(false);
		accountPasswordConfirm.setEnabled(false);
		
		userpassField = new FrameWork(bsInput,500,500);
		username = new TTextField("",419,256,414,41);
		password = new TPasswordField("",419,321,415,45);
		userpassField.add(username);
		userpassField.add(password);
		userpassField.add(accountUsername);
		userpassField.add(accountPassword);
		userpassField.add(accountPasswordConfirm);
		
		background = getImage("images/BlurredBG.png");
		logInButton1 = getImage("images/Button_LogIn_Neutral.png");
		signUpButton1 = getImage("images/Button_SignUp_Neutral.png");
		
		logInButtonHighlight = getImage("images/Button_LogIn_Clicked.png");
		signUpButtonHighlight = getImage("images/Button_SignUp_Clicked.png");
		logIn = new Sprite(logInButton1,90,500);
		signUp = new Sprite(signUpButton1,500,500);

		logInButtonRectangle = new Rectangle(90,buttonPositionY,409,122);
		signUpButtonRectangle =new Rectangle(500,buttonPositionY,409,122);

		
		userCredentialsForm = getImage("images/UI_UserCredentials.png");
		userCredentials = new Sprite(userCredentialsForm,110,200);
		
		signUpPopUp = getImage("images/PopupWindow_SignUp.png");
		signUpScreen = new Sprite(signUpPopUp,30,50);
		signUpScreen.setActive(false);
		signUpScreenExit = new TButton("X", 768, 150, 30, 30);
		frame.add(signUpScreenExit);
		signUpScreenExit.setVisible(false);
		
		signUpPopUpButton = getImage("images/PopupWindow_SignUp1.png");
		signUpScreenButton = new Sprite(signUpPopUpButton,400,380);
		signUpPopUpButtonHighlight =getImage("images/PopupWindow_SignUp2.png");
		signUpScreenButtonRectangle = new Rectangle(400,380,167,50);

		accountManager = new AccountManager();
		
	}
	
	@Override
	public void update(long elapsedTime) {
		highlightButton();
		//changeScreen();
        logIn.update(elapsedTime);
        signUp.update(elapsedTime);
        signUpScreen.update(elapsedTime);
        signUpScreenButton.update(elapsedTime);
		switchScreen();
        frame.update();
        userCredentials.update(elapsedTime);
        userpassField.update();
        closePopUp();
        
	}

	@Override
	public void render(Graphics2D g) {
		frame.render(g);
		g.drawImage(background,null,0,0);
		//g.drawImage(backgroundOverlay,null,0,0);
        logIn.render(g);
        signUp.render(g);
        userCredentials.render(g);
        userpassField.render(g);
        showClosePopUp(g);
        
	}
	
	private void switchScreen()
	{
            if(logInButton.isMousePressed())
            {
            	
        		PlayerSprite player;
            	if (username.getText().length() == 0 || password.getPasswordText().length()==0)
            	{
            		parent.nextGameID = 0;
            		finish();
            	}
            	else
            	{
	            	accountManager.checkLogIn(username.getText(),org.apache.commons.codec.digest.DigestUtils.sha256Hex(password.getPasswordText()));
	            	System.out.println(org.apache.commons.codec.digest.DigestUtils.sha256Hex(password.getPasswordText()));
	            	if (accountManager.isUserPassMatched())
	            	{
	            		player = new PlayerSprite(getImage("images/CharacterFront.png"),getImage("images/CharacterBack.png"),getImage("images/CharacterLeft.png"),getImage("images/CharacterRight.png"));
	            		player.setPlayerID(accountManager.getPlayerID());
	            		player.getActiveQuest()[0] = accountManager.getActiveQuest(player.getPlayerID());
	            		player.setQuestID(accountManager.getActiveQuestID());
	            		player.setPlayerNotes(accountManager.getPlayerNotes());
	            		Session.setPlayer(player);
	            		System.out.println(accountManager.getActiveQuest(player.getPlayerID()));
	            		System.out.println(player.getActiveQuest()[0]);
	            		parent.nextGameID = 1;
	            		finish();
	            	}
	            	else
	            	{
	            		parent.nextGameID = 0;
	            		finish();
	            	}
            	}
            }
            if(signUpButton.isMousePressed())
            {
            	enableOrDisable(true);
            }
            if(signUpButtonSmaller.isMousePressed())
            {
            	System.out.println("ok");
            	if (accountUsername.getText().length()<4 || (accountPassword.getPasswordText().length())<4)
            	{
            		System.out.println("lenght error");
            	}
            	else if (accountUsername.getText().equals(accountManager.checkUsernameExist(accountUsername.getText())))
            	{
            		System.out.println("username exists");
            	}
            	else if (!(org.apache.commons.codec.digest.DigestUtils.sha256Hex(accountPassword.getPasswordText()).equals(org.apache.commons.codec.digest.DigestUtils.sha256Hex(accountPasswordConfirm.getPasswordText()))))
            	{
            		System.out.println("password not match");
            	}
            	else
            	{
	            	accountManager.createAccount(accountUsername.getText(),org.apache.commons.codec.digest.DigestUtils.sha256Hex(accountPassword.getPasswordText()));
	            	accountManager.checkLogIn(accountUsername.getText(),org.apache.commons.codec.digest.DigestUtils.sha256Hex(accountPassword.getPasswordText()));
	            	accountManager.insertSkill(1, 1);
	            	accountManager.setUserPassMatched(false);
	            	enableOrDisable(false);
            	}
            }
	}
	
	private void highlightButton() {
		Point p = new Point (getMouseX(), getMouseY());
        if(logInButtonRectangle.contains(p))
        {
            logIn.setImage(logInButtonHighlight);
            signUp.setImage(signUpButton1);
        }
        else if(signUpButtonRectangle.contains(p))
        {
        	logIn.setImage(logInButton1);
            signUp.setImage(signUpButtonHighlight);

        }
        else if (signUpScreenButtonRectangle.contains(p))
        {
        	signUpScreenButton.setImage(signUpPopUpButtonHighlight);
        }
        else
        {
        	logIn.setImage(logInButton1);
            signUp.setImage(signUpButton1);
            signUpScreenButton.setImage(signUpPopUpButton);
        }
	}
	
	public void showClosePopUp(Graphics2D g)
	{
		if (signUpScreen.isActive())
		{
			signUpButtonSmaller.render(g);
			signUpScreenExit.render(g);
			signUpScreen.render(g);
			signUpScreenButton.render(g);
			accountPasswordConfirm.render(g);
			accountUsername.render(g);
			accountPassword.render(g);
		}
		
	}

	private void closePopUp() {
		if (signUpScreenExit.isMousePressed())
		{
			enableOrDisable(false);
		}
	}
	
	private void enableOrDisable(boolean visible)
	{
		accountUsername.setEnabled(visible);
		accountPassword.setEnabled(visible);
		accountPasswordConfirm.setEnabled(visible);
		signUpScreen.setActive(visible);
		accountUsername.setVisible(visible);
		accountPassword.setVisible(visible);
		accountPasswordConfirm.setVisible(visible);
		accountUsername.setText("");
		accountPassword.setText("");
		accountPasswordConfirm.setText("");
		signUpButtonSmaller.setEnabled(visible);
		signUpScreenExit.setVisible(visible);
		logInButton.setVisible(!visible);
		signUpButton.setVisible(!visible);
		username.setEnabled(!visible);
		password.setEnabled(!visible);
	}

}
