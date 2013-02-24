package com.icival.mobilegamedevelopment;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.menus.CCMenu;
import org.cocos2d.menus.CCMenuItem;
import org.cocos2d.menus.CCMenuItemFont;
import org.cocos2d.menus.CCMenuItemImage;
import org.cocos2d.menus.CCMenuItemSprite;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;

import android.util.Log;

import com.icival.movementActivity.MovementGameScreen;
import com.icival.seperateClasses.SeperateClassesGameScreen;
import com.icival.testAccelerometerActivity.AccelerometerGameScreen;
import com.icival.testCollisionDetectionActivity.CollisionGameScreen;
import com.icival.testDataCommunicationActivity.DataComGameScreen;
import com.icival.touchActivity.TouchGameScreen;

public class Activities extends CCScene
{
	/** Properties **********************************************************************************/
	private CCMenu m_uiMenu;
	
	/** Constructor *********************************************************************************/
	public Activities()
	{
		this.addChild(CCSprite.sprite("button2.jpg"));
		
		// setup button
		CCMenuItemImage buttonUpdate = CCMenuItemImage.item("button_update.jpg", "button_update.jpg", this, "gotoUpdateSample");
		CCMenuItemImage buttonTouch = CCMenuItemImage.item("button_touch.jpg", "button_touch.jpg", this, "gotoTouchSample");
		CCMenuItemImage buttonAccel = CCMenuItemImage.item("button_accelerometer.jpg", "button_accelerometer.jpg", this, "gotoAccelSample");
		CCMenuItemFont buttonDataCom = CCMenuItemFont.item("Data Com", this, "gotoDataComSample");
		CCMenuItemFont buttonCollision = CCMenuItemFont.item("Collision Activity", this, "gotoCollisionSample");
		CCMenuItemFont buttonSeperateClasses = CCMenuItemFont.item("Seperate Classes", this, "gotoSeperateClasses");
		
		// display your buttons
		m_uiMenu = CCMenu.menu(buttonUpdate, buttonTouch, buttonAccel, buttonDataCom, buttonCollision, buttonSeperateClasses);
		m_uiMenu.alignItemsVertically(10);
		this.addChild(m_uiMenu);
	}
	
	/** Methods *************************************************************************************/
	public void gotoUpdateSample()
	{
		m_uiMenu.setVisible(false);
		CCScene scene = new MovementGameScreen();
		CCDirector.sharedDirector().replaceScene(scene);
	}
	
	public void gotoTouchSample()
	{
		m_uiMenu.setVisible(false);
		CCScene scene = new TouchGameScreen();
		CCDirector.sharedDirector().replaceScene(scene);
	}
	
	public void gotoAccelSample()
	{
		m_uiMenu.setVisible(false);
		CCScene scene = new AccelerometerGameScreen();
		CCDirector.sharedDirector().replaceScene(scene);
	}
	
	public void gotoDataComSample()
	{
		m_uiMenu.setVisible(false);
		CCScene scene = new DataComGameScreen();
		CCDirector.sharedDirector().replaceScene(scene);
	}
	
	public void gotoCollisionSample()
	{
		m_uiMenu.setVisible(false);
		CCScene scene = new CollisionGameScreen();
		CCDirector.sharedDirector().replaceScene(scene);
	}
	
	public void gotoSeperateClasses()
	{
		m_uiMenu.setVisible(false);
		CCScene scene = new SeperateClassesGameScreen();
		CCDirector.sharedDirector().replaceScene(scene);
	}
}
