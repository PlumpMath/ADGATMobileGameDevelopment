package com.icival.testDataCommunicationActivity;

import org.cocos2d.menus.CCMenu;
import org.cocos2d.menus.CCMenuItemFont;
import org.cocos2d.menus.CCMenuItemImage;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCLabel;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGSize;

import com.icival.mobilegamedevelopment.Activities;
import com.icival.mobilegamedevelopment.UILayer;

public class DataComUILayer extends UILayer
{
	/** Properties **********************************************************************************/
	private CGSize m_screenSize;
	private CCMenuItemImage m_buttonBack;
	private CCMenuItemFont m_buttonRetry;
	private CCLabel m_player1Score;
	private CCLabel m_player2Score;
	
	/** Constructor *********************************************************************************/
	public DataComUILayer()
	{
		// device size
		m_screenSize = CCDirector.sharedDirector().displaySize();
		
		// create your submenus here
		m_buttonBack = CCMenuItemImage.item("button_back.jpg", "button_back.jpg", this, "gotoMainScreen");
		m_buttonRetry = CCMenuItemFont.item("retry", this, "retryGame");
		
		// display your buttons
		m_uiMenu = CCMenu.menu(m_buttonBack, m_buttonRetry);
		this.addChild(m_uiMenu);

		// fix your button positions
		m_buttonBack.setPosition(m_uiMenu.convertToNodeSpace(m_buttonBack.getContentSize().width/2, m_screenSize.height-30));
		m_buttonRetry.setPosition(m_uiMenu.convertToNodeSpace(m_screenSize.width/2, m_screenSize.height/2));
		m_buttonRetry.setAnchorPoint(CGPoint.ccp(0.5f, 0.5f));
		m_buttonRetry.setVisible(false);
		
		// display your scores
		m_player1Score = CCLabel.makeLabel("Player1 :0", "arial", 20);
		m_player1Score.setAnchorPoint(CGPoint.ccp(0.0f, 0.5f));
		m_player1Score.setPosition(CGPoint.ccp(20, m_screenSize.height - 60.0f));
		this.addChild(m_player1Score);
		
		m_player2Score = CCLabel.makeLabel("PLayer2 :0", "arial", 20);
		m_player2Score.setAnchorPoint(CGPoint.ccp(1.0f, 0.5f));
		m_player2Score.setPosition(CGPoint.ccp(m_screenSize.width - 20, m_screenSize.height - 60.0f));
		this.addChild(m_player2Score);
	}
	
	/** Methods *************************************************************************************/
	public void gotoMainScreen()
	{
		CCDirector.sharedDirector().replaceScene(new Activities());
	}
	
	// update score
	public void updatePlayer1Score(int p_score)
	{
		String score = "Player1: " + p_score;
		m_player1Score.setString(score);
	}
	
	public void updatePlayer2Score(int p_score)
	{
		String score = "Player2: " + p_score;
		m_player2Score.setString(score);
	}
	
	// update winner
	public void setPlayer1Display(String p_display)
	{
		String result = "Player1: " + p_display;
		m_player1Score.setString(result);
		m_buttonRetry.setVisible(true);
	}
	
	public void setPlayer2Display(String p_display)
	{
		String result = "Player1: " + p_display;
		m_player2Score.setString(result);
		m_buttonRetry.setVisible(true);
	}
	
	// retry game
	public void retryGame()
	{
		CCDirector.sharedDirector().replaceScene(new DataComGameScreen());
	}
}
