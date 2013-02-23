package com.icival.touchActivity;

import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.events.CCTouchDispatcher;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGSize;

import com.icival.mobilegamedevelopment.GameLayer;

import android.view.MotionEvent;

public class TouchGameLayer extends GameLayer
{
	/** Properties **********************************************************************************/
	private CCSprite m_ball;
	private CGSize m_screenSize;
	
	/** Constructor *********************************************************************************/
	public TouchGameLayer()
	{	
		// device size
		m_screenSize = CCDirector.sharedDirector().displaySize();
		
		// set game objects
		m_ball = CCSprite.sprite("ball.jpg");
		m_ball.setPosition(CGPoint.ccp(m_screenSize.width/2, m_screenSize.height/2));
		this.addChild(m_ball);
		
		// setup touches
		this.setIsTouchEnabled(true);
	}
	
	/** Methods *************************************************************************************/
	/** Touches **********************/
	@Override
    public boolean ccTouchesBegan(MotionEvent event) 
	{
		CGPoint touchPoint = CGPoint.make(event.getX(), event.getY());
				touchPoint = CCDirector.sharedDirector().convertToGL(touchPoint);

        //String title = String.format("touch began at point(%.2f, %.2f)", touchPoint.x, touchPoint.y);
				
		// move ball by actions
		CCMoveTo moveTo = CCMoveTo.action(0.25f, touchPoint);
		m_ball.runAction(moveTo);
		
		// display touch location
		((TouchGameScreen)m_gameScreen).setTouchLocation(touchPoint.x, touchPoint.y);
				
        return CCTouchDispatcher.kEventHandled;
    }
	
	@Override
	public boolean ccTouchesMoved(MotionEvent event)
	{
		CGPoint touchPoint = CGPoint.make(event.getX(), event.getY());
		touchPoint = CCDirector.sharedDirector().convertToGL(touchPoint);

		//String title = String.format("touch moved at point(%.2f, %.2f)", touchPoint.x, touchPoint.y);
		
        return CCTouchDispatcher.kEventHandled;
    }

	@Override
    public boolean ccTouchesEnded(MotionEvent event) 
    {
    	CGPoint touchPoint = CGPoint.make(event.getX(), event.getY());
		touchPoint = CCDirector.sharedDirector().convertToGL(touchPoint);

		//String title = String.format("touch ended at point(%.2f, %.2f)", touchPoint.x, touchPoint.y);
		
		// move ball by actions
		CCMoveTo moveTo = CCMoveTo.action(0.25f, touchPoint);
		m_ball.runAction(moveTo);
		
        return CCTouchDispatcher.kEventHandled;
    }

    @Override
    public boolean ccTouchesCancelled(MotionEvent event) 
    {
    	CGPoint touchPoint = CGPoint.make(event.getX(), event.getY());
		touchPoint = CCDirector.sharedDirector().convertToGL(touchPoint);

		//String title = String.format("touch cancelled at point(%.2f, %.2f)", touchPoint.x, touchPoint.y);
		
		// move ball by actions
		CCMoveTo moveTo = CCMoveTo.action(0.25f, touchPoint);
		m_ball.runAction(moveTo);
		
        return CCTouchDispatcher.kEventHandled;
    }
}
