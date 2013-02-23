package com.icival.testAccelerometerActivity;

import com.icival.mobilegamedevelopment.GameScreen;

public class AccelerometerGameScreen extends GameScreen
{
/** Properties **********************************************************************************/
	
	/** Constructor *********************************************************************************/
	public AccelerometerGameScreen()
	{
		// create ui layer
		m_uiLayer = new AccelerometerUILayer();
		m_uiLayer.setGameScreen(this);
		this.addChild(m_uiLayer, 20);
		
		// create game layer
		m_gameLayer = new AccelerometerGameLayer();
		m_gameLayer.setGameScreen(this);
		this.addChild(m_gameLayer, 10);
	}
	
	/** Methods *************************************************************************************/
}
