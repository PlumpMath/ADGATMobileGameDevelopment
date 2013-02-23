package com.icival.touchActivity;

import org.cocos2d.menus.CCMenu;
import org.cocos2d.menus.CCMenuItemImage;
import org.cocos2d.menus.CCMenuItemSprite;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCLabel;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGSize;

import com.icival.mobilegamedevelopment.Activities;
import com.icival.mobilegamedevelopment.UILayer;

public class TouchUILayer extends UILayer
{
	/** Properties **********************************************************************************/
	private CCLabel m_touchInfo;
	
	/** Constructor *********************************************************************************/
	public TouchUILayer()
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
		
		// label display
		CCLabel labelTitle = CCLabel.makeLabel("touch anywhere on your screen", "arial", 20);
		labelTitle.setAnchorPoint(CGPoint.ccp(0.5f, 0.5f));
		labelTitle.setPosition(CGPoint.ccp(deviceSize.width/2, deviceSize.height-30.0f));
		this.addChild(labelTitle);
		
		m_touchInfo = CCLabel.makeLabel("Touch Point: 0 0", "arial", 20);
		m_touchInfo.setAnchorPoint(CGPoint.ccp(0.5f, 0.5f));
		m_touchInfo.setPosition(CGPoint.ccp(deviceSize.width/2, deviceSize.height-60.0f));
		this.addChild(m_touchInfo);
	}
	
	/** Methods *************************************************************************************/
	public void gotoMainScreen()
	{
		CCDirector.sharedDirector().replaceScene(new Activities());
	}
	
	public void setTouchLocation(float p_x, float p_y)
	{
		String touchPoint = String.format("Touch Point: (%.2f, %.2f)", p_x, p_y);
		m_touchInfo.setString(touchPoint);
	}
}
