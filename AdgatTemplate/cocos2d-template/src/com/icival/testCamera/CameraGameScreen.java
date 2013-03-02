package com.icival.testCamera;

import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGSize;

import com.icival.mobilegamedevelopment.GameScreen;
import com.icival.testCamera.CameraGameLayer;
import com.icival.testCamera.CameraUILayer;

public class CameraGameScreen extends GameScreen
{
	/** Properties **********************************************************************************/
	private CGSize m_screenSize;
	private CCSprite m_background;
	
	/** Constructor *********************************************************************************/
	public CameraGameScreen()
	{
		// get screen size
		m_screenSize = CCDirector.sharedDirector().displaySize();
		
		// create background
		m_background = CCSprite.sprite("background.jpg");
		m_background.setAnchorPoint(CGPoint.ccp(0.5f, 0.5f));
		m_background.setPosition(CGPoint.ccp(m_screenSize.width/2, m_screenSize.height/2));
		this.addChild(m_background);
		
		// create ui layer
		m_uiLayer = new CameraUILayer();
		m_uiLayer.setGameScreen(this);
		this.addChild(m_uiLayer, 20);
		
		// create game layer
		m_gameLayer = new CameraGameLayer();
		m_gameLayer.setGameScreen(this);
		this.addChild(m_gameLayer, 10);
	}
	
	/** Methods *************************************************************************************/
	
}
