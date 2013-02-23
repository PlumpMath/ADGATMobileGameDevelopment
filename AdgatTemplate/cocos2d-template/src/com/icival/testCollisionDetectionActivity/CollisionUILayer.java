package com.icival.testCollisionDetectionActivity;

import org.cocos2d.menus.CCMenu;
import org.cocos2d.menus.CCMenuItemImage;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.types.CGSize;

import com.icival.mobilegamedevelopment.Activities;
import com.icival.mobilegamedevelopment.UILayer;

public class CollisionUILayer extends UILayer
{
	/** Properties **********************************************************************************/
	
	/** Constructor *********************************************************************************/
	public CollisionUILayer()
	{
		// device size
		CGSize deviceSize = CCDirector.sharedDirector().displaySize();
		
		// create your submenus here
		CCMenuItemImage buttonBack = CCMenuItemImage.item("button_back.jpg", "button_back.jpg", this, "gotoMainScreen");
		
		// display your buttons
		m_uiMenu = CCMenu.menu(buttonBack);
		this.addChild(m_uiMenu);

		// fix your button positions
		buttonBack.setPosition(m_uiMenu.convertToNodeSpace(buttonBack.getContentSize().width/2, deviceSize.height-30));
	}
	
	/** Methods *************************************************************************************/
	public void gotoMainScreen()
	{
		CCDirector.sharedDirector().replaceScene(new Activities());
	}
}
