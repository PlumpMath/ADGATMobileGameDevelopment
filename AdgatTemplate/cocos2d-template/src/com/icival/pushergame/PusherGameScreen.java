package com.icival.pushergame;

import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;

import com.icival.mobilegamedevelopment.Constants;
import com.icival.mobilegamedevelopment.GameScreen;
import com.icival.pushergame.PusherGameLayer;
import com.icival.pushergame.PusherUILayer;

public class PusherGameScreen extends GameScreen
{
/** Properties **********************************************************************************/
	
	/** Constructor *********************************************************************************/
	public PusherGameScreen()
	{
		// create main bg
		m_mainBg = CCSprite.sprite("background3.jpg");
		m_mainBg.setAnchorPoint(CGPoint.ccp(0.5f, 0.5f));
		m_mainBg.setPosition(CGPoint.ccp(Constants.CENTER.x, Constants.CENTER.y));
		this.addChild(m_mainBg);
		
		// create ui layer
		m_uiLayer = new PusherUILayer();
		m_uiLayer.setGameScreen(this);
		this.addChild(m_uiLayer, 20);
		
		// create game layer
		m_gameLayer = new PusherGameLayer();
		m_gameLayer.setGameScreen(this);
		this.addChild(m_gameLayer, 10);
	}
	
	/** Methods *************************************************************************************/
	
}
