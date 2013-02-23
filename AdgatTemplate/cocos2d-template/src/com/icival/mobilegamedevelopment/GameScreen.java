package com.icival.mobilegamedevelopment;

import org.cocos2d.layers.CCScene;

public class GameScreen extends CCScene
{
	/** Properties **********************************************************************************/
	protected UILayer m_uiLayer;
	protected GameLayer m_gameLayer;
	
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
