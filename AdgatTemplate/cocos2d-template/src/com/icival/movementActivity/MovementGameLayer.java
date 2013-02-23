package com.icival.movementActivity;

import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.events.CCTouchDispatcher;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGSize;

import android.view.MotionEvent;

import com.icival.mobilegamedevelopment.GameLayer;

public class MovementGameLayer extends GameLayer
{
	/** Properties **********************************************************************************/
	private CCSprite m_ball;
	private CGSize m_screenSize;
	
	/** Constructor *********************************************************************************/
	public MovementGameLayer()
	{	
		// device size
		m_screenSize = CCDirector.sharedDirector().displaySize();
		
		// set game objects
		m_ball = CCSprite.sprite("ball.jpg");
		m_ball.setPosition(CGPoint.ccp(m_screenSize.width/2, m_screenSize.height/2));
		this.addChild(m_ball);
		
		// setup game loop
		this.scheduleUpdate();
	}
	
	/** Methods *************************************************************************************/
	
	/** GameLoop *********************/
	@Override
	public void update(float dt)
	{
		// move ball
		CGPoint ballCurrentPosition = m_ball.getPosition();
		ballCurrentPosition.x += 1;
		m_ball.setPosition(ballCurrentPosition);
		
		// loop the movement of your ball
		if( ballCurrentPosition.x > m_screenSize.width )
		{
			ballCurrentPosition.x = 0;
			m_ball.setPosition(ballCurrentPosition);
		}
	}
}
