package com.icival.touchActivity;

import com.icival.mobilegamedevelopment.GameScreen;

public class TouchGameScreen extends GameScreen
{
	/** Properties **********************************************************************************/
	
	/** Constructor *********************************************************************************/
	public TouchGameScreen()
	{
		// create ui layer
		m_uiLayer = new TouchUILayer();
		m_uiLayer.setGameScreen(this);
		this.addChild(m_uiLayer, 20);
		
		// create game layer
		m_gameLayer = new TouchGameLayer();
		m_gameLayer.setGameScreen(this);
		this.addChild(m_gameLayer, 10);
	}
	
	/** Methods *************************************************************************************/
	public void setTouchLocation(float p_x, float p_y)
	{
		((TouchUILayer)m_uiLayer).setTouchLocation(p_x, p_y);
	}
}
