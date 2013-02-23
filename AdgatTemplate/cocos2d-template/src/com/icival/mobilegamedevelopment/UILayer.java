package com.icival.mobilegamedevelopment;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.menus.CCMenu;
import org.cocos2d.menus.CCMenuItemImage;
import org.cocos2d.menus.CCMenuItemSprite;
import org.cocos2d.nodes.CCDirector;

import com.icival.movementActivity.MovementGameScreen;

public class UILayer extends CCLayer
{
	/** Properties **********************************************************************************/
	protected GameScreen m_gameScreen;
	protected CCMenu m_uiMenu;
	
	/** Constructor *********************************************************************************/
	public UILayer()
	{
		// create your submenus here
		
		// display your button menus
	}
	
	/** Methods *************************************************************************************/
	public void setGameScreen(GameScreen p_GameScreen)
	{
		m_gameScreen = p_GameScreen;
	}
}
