package accountingGame.screens;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import accountingGame.AccountManager;

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
	Rectangle logInButtonRectangle, signUpButtonRectangle, exitButtonRectangle;
	BufferedImage logInButton1, signUpButton1, exitButton1, background,backgroundOverlay, newGameButtonHighlight, loadGameButtonHighlight,exitButtonHighlight,
	userCredentialsForm, signUpPopUp;
	Sprite logIn, signUp, exit, userCredentials,signUpScreen;
	
	TTextField username;
	TPasswordField password;
	TButton logInButton, signUpButton, exitButton,signUpScreenExit;
	FrameWork frame, userpassField;
	
	private SpriteGroup UI_POPUPS;
	private AccountManager accountManager;
	
	
	

	public MainMenu(GameEngine gameEngine) {
		super(gameEngine);
		
	}

	@Override
	public void initResources() {
		logInButton = new TButton("Log In",90,buttonPositionY, 409,122);
		signUpButton = new TButton("Sign Up",500,buttonPositionY, 409,122);
		//exitButton = new TButton("Exit",buttonPositionX,550, 409,122);
		frame = new FrameWork(bsInput, 1024,780);
		frame.add(logInButton);
		frame.add(signUpButton);
		//frame.add(exitButton);
		
		userpassField = new FrameWork(bsInput,500,500);
		username = new TTextField("",419,256,414,41);
		password = new TPasswordField("",419,321,415,45);
		userpassField.add(username);
		userpassField.add(password);
		
		background = getImage("images/BlurredBG.png");
		backgroundOverlay = getImage("images/BG_TranslucentBlack.png");
		logInButton1 = getImage("images/Button_LogIn_Neutral.png");
		signUpButton1 = getImage("images/Button_SignUp_Neutral.png");
		//exitButton1 = getImage("images/Button_Exit_Neutral.png");
		newGameButtonHighlight = getImage("images/Button_LogIn_Clicked.png");
		loadGameButtonHighlight = getImage("images/Button_SignUp_Clicked.png");
		logIn = new Sprite(logInButton1,90,500);
		signUp = new Sprite(signUpButton1,500,500);

		logInButtonRectangle = new Rectangle(90,buttonPositionY,409,122);
		signUpButtonRectangle =new Rectangle(500,buttonPositionY,409,122);

		
		userCredentialsForm = getImage("images/UI_UserCredentials.png");
		userCredentials = new Sprite(userCredentialsForm,110,200);
		
		signUpPopUp = getImage("images/PopupWindow_SignUp1.png");
		signUpScreen = new Sprite(signUpPopUp,100,10);
		signUpScreen.setActive(false);
		signUpScreenExit = new TButton("X", 689, 105, 30, 30);
		frame.add(signUpScreenExit);
		signUpScreenExit.setVisible(false);
		
		UI_POPUPS = new SpriteGroup("Popup");
		UI_POPUPS.add(signUpScreen);
		
		accountManager = new AccountManager();
		
	}
	
	@Override
	public void update(long elapsedTime) {
		highlightButton();
		//changeScreen();
        logIn.update(elapsedTime);
        signUp.update(elapsedTime);

		switchScreen();
        frame.update();
        userCredentials.update(elapsedTime);
        userpassField.update();
        closePopUp();
        UI_POPUPS.update(elapsedTime);
	}

	@Override
	public void render(Graphics2D g) {
		frame.render(g);
		g.drawImage(background,null,0,0);
		//g.drawImage(backgroundOverlay,null,0,0);
        logIn.render(g);
        signUp.render(g);

        showClosePopUp(g);
        userCredentials.render(g);
        userpassField.render(g);
        UI_POPUPS.render(g);
        
	}
	
	private void switchScreen()
	{
            if(logInButton.isMousePressed())
            {
            	
            	parent.nextGameID = 1;
        		finish();
            	/*accountManager.setPlayerUsername(username.getText());
            	accountManager.setPlayerPassword((org.apache.commons.codec.digest.DigestUtils.sha256Hex(password.getPasswordText())));
            	accountManager.checkLogIn();
            	System.out.println(org.apache.commons.codec.digest.DigestUtils.sha256Hex(password.getPasswordText()));
            	if (accountManager.isUserPassMatched())
            	{
            		parent.nextGameID = 1;
            		finish();
            	}*/
                
            }
            if(signUpButton.isMousePressed())
            {
               signUpScreen.setActive(true);
               
            }
	}
	
	private void highlightButton() {
		Point p = new Point (getMouseX(), getMouseY());
        if(logInButtonRectangle.contains(p))
        {
            logIn.setImage(newGameButtonHighlight);
            signUp.setImage(signUpButton1);
        }
        else if(signUpButtonRectangle.contains(p))
        {
        	logIn.setImage(logInButton1);
            signUp.setImage(loadGameButtonHighlight);

        }
        else
        {
        	logIn.setImage(logInButton1);
            signUp.setImage(signUpButton1);

        }
	}
	
	public void showClosePopUp(Graphics2D g)
	{
		if (signUpScreen.isActive())
		{
			signUpScreenExit.setVisible(true);
			signUpScreenExit.render(g);
		}
		
	}

	private void closePopUp() {
		if (signUpScreenExit.isMousePressed())
		{
			signUpScreen.setActive(false);
			signUpScreenExit.setVisible(false);
		}
	}

}
