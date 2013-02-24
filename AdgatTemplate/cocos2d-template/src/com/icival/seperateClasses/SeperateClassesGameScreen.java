package com.icival.seperateClasses;

import com.icival.mobilegamedevelopment.GameScreen;
import com.icival.touchActivity.TouchGameLayer;
import com.icival.touchActivity.TouchUILayer;

public class SeperateClassesGameScreen extends GameScreen
{
/** Properties **********************************************************************************/
	
	/** Constructor *********************************************************************************/
	public SeperateClassesGameScreen()
	{
		// create ui layer
		m_uiLayer = new SeperateClassesUILayer();
		m_uiLayer.setGameScreen(this);
		this.addChild(m_uiLayer, 20);
		
		// create game layer
		m_gameLayer = new SeperateClassesGameLayer();
		m_gameLayer.setGameScreen(this);
		this.addChild(m_gameLayer, 10);
	}
	
	/** Methods *************************************************************************************/
	
}
