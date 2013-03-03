package com.icival.mobilegamedevelopment;

import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCSprite;

public class GameScreen extends CCScene
{
	/** Properties **********************************************************************************/
	protected UILayer m_uiLayer;
	protected GameLayer m_gameLayer;
	protected CCSprite m_mainBg;
	
	/** Constructor *********************************************************************************/
	public GameScreen()
	{
		// create ui layer
		m_uiLayer = new UILayer();
		m_uiLayer.setGameScreen(this);
		this.addChild(m_uiLayer, 20);
		
		// create game layer
		m_gameLayer = new GameLayer();
		m_gameLayer.setGameScreen(this);
		this.addChild(m_gameLayer, 10);
	}
	
	/** Methods *************************************************************************************/
}
