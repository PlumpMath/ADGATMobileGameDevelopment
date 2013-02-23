package com.icival.movementActivity;

import com.icival.mobilegamedevelopment.GameScreen;

public class MovementGameScreen extends GameScreen
{
	/** Properties **********************************************************************************/
	
	/** Constructor *********************************************************************************/
	public MovementGameScreen()
	{
		// create ui layer
		m_uiLayer = new MovementUILayer();
		m_uiLayer.setGameScreen(this);
		this.addChild(m_uiLayer, 20);
		
		// create game layer
		m_gameLayer = new MovementGameLayer();
		m_gameLayer.setGameScreen(this);
		this.addChild(m_gameLayer, 10);
	}
	
	/** Methods *************************************************************************************/
}
