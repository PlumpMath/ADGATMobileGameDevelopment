package com.icival.testCollisionDetectionActivity;

import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;
import org.cocos2d.types.CGSize;

import com.icival.mobilegamedevelopment.GameLayer;

public class CollisionGameLayer extends GameLayer
{
	/** Properties **********************************************************************************/
	private CCSprite m_ball1;
	private CCSprite m_ball2;
	private CGSize m_screenSize;
	
	/** Constructor *********************************************************************************/
	public CollisionGameLayer()
	{	
		// device size
		m_screenSize = CCDirector.sharedDirector().displaySize();
		
		// setup game objects
		m_ball1 = CCSprite.sprite("ball.jpg");
		m_ball2.setPosition(CGPoint.ccp(m_screenSize.width * 0.25f, m_screenSize.height/2));
		this.addChild(m_ball1);
		
		m_ball1 = CCSprite.sprite("ball.jpg");
		m_ball2.setPosition(CGPoint.ccp(m_screenSize.width * 0.75f, m_screenSize.height/2));
		this.addChild(m_ball2);
		
		// setup game loop
		this.scheduleUpdate();
	}
	
	/** Methods *************************************************************************************/
	
	/** GameLoop *********************/
	@Override
	public void update(float dt)
	{
		// move ball1
		CGPoint ball1CurrentPosition = m_ball1.getPosition();
		ball1CurrentPosition.x += 1;
		m_ball1.setPosition(ball1CurrentPosition);
		
		// loop the movement of your ball1
		if( ball1CurrentPosition.x > m_screenSize.width )
		{
			ball1CurrentPosition.x = 0;
			m_ball1.setPosition(ball1CurrentPosition);
		}
		
		// move ball2
		CGPoint ball2CurrentPosition = m_ball1.getPosition();
		ball2CurrentPosition.x -= 1;
		m_ball1.setPosition(ball2CurrentPosition);
		
		// loop the movement of your ball2
		if( ball2CurrentPosition.x < 0 )
		{
			ball2CurrentPosition.x = m_screenSize.width;
			m_ball2.setPosition(ball2CurrentPosition);
		}
		
		// check rect collision
		this.checkRectCollision();
		
		// check circle collision
		//this.checkCircleCollision();
	}
	
	/** GameLoop *********************/
	/** Rect Collision ***************/
	public void checkRectCollision()
	{
		CGRect ball1Rect = m_ball1.getBoundingBox();
		CGRect ball2Rect = m_ball2.getBoundingBox();
		if( CGRect.containsRect(ball1Rect, ball2Rect) )
		{
			// collided!
			m_ball1.setOpacity(100);
			m_ball2.setOpacity(100);
		}
		else if( m_ball1.getOpacity() != 255 )
		{
			m_ball1.setOpacity(255);
			m_ball2.setOpacity(255);
		}
	}
	
	/** Circle Collision *************/
	public void checkCircleCollision()
	{
		
	}
}
