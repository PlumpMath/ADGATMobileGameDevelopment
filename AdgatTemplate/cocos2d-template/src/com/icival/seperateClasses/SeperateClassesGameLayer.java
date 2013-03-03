package com.icival.seperateClasses;

import java.util.ArrayList;

import org.cocos2d.events.CCTouchDispatcher;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;
import org.cocos2d.types.CGSize;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.MotionEvent;

import com.icival.mobilegamedevelopment.GameLayer;

public class SeperateClassesGameLayer extends GameLayer
{
	/** Properties **********************************************************************************/
	private Ball m_ball1;
	private Ball m_ball2;
	private Ball m_currentBall;
	private ArrayList<Ball> m_balls;
	private CGSize m_screenSize;
	private CCSprite m_background;
	
	/** Constructor *********************************************************************************/
	public SeperateClassesGameLayer()
	{	
		// device size
		m_screenSize = CCDirector.sharedDirector().displaySize();
		
		// create bg
		m_background = CCSprite.sprite("xBy320.jpg");
		m_background.setAnchorPoint(CGPoint.ccp(0.5f, 0.5f));
		m_background.setPosition(CGPoint.ccp(m_screenSize.width/2, m_screenSize.height/2));
		this.addChild(m_background);
		
		// set game objects
		m_ball1 = new Ball("ball.png");
		m_ball1.setPosition(CGPoint.ccp(m_screenSize.width * 0.75f, m_screenSize.height/2));
		this.addChild(m_ball1);
		
		m_ball2 = new Ball("ball.png");
		m_ball2.setPosition(CGPoint.ccp(m_screenSize.width * 0.25f, m_screenSize.height/2));
		this.addChild(m_ball2);
		
		// hold your two balls
		m_balls = new ArrayList<Ball>();
		m_balls.add(m_ball1);
		m_balls.add(m_ball2);
		
		// setup touches
		this.setIsTouchEnabled(true);
		
		// setup game loop
		this.scheduleUpdate();
	}
	
	/** Methods *************************************************************************************/
	/** GameLoop *********************/
	@Override
	public void update(float p_deltaTime)
	{
		// update
		for( int i = 0; i < m_balls.size(); i++ )
		{
			Ball ball = m_balls.get(i);
			ball.update(p_deltaTime);
		}
		
		// check collision
		for( int i = 0; i < m_balls.size(); i++ )
		{
			for( int j = 0; j < m_balls.size(); j++ )
			{
				Ball ball1 = m_balls.get(i);
				Ball ball2 = m_balls.get(j);
				
				if( !ball1.equals(ball2) && ball1.ballCollidedToOtherBall(ball2) )
				{
					continue;
				}
			}
		}
	}
	
	/** Touches **********************/
	@SuppressLint("NewApi")
	@Override
    public boolean ccTouchesBegan(MotionEvent event) 
	{
		CGPoint touchPoint = CGPoint.make(event.getX(), event.getY());
		touchPoint = CCDirector.sharedDirector().convertToGL(touchPoint);
		
		for( int i = 0; i < m_balls.size(); i++ )
		{
			Ball ball = m_balls.get(i);
			if( CGRect.containsPoint(ball.getBoundingBox(), touchPoint) )
			{
				m_currentBall = ball;
				ball.g_prevTouch = touchPoint;
				ball.g_direction = CGPoint.zero();
				break;
			}
		}
		
        return CCTouchDispatcher.kEventHandled;
    }
	
	@Override
	public boolean ccTouchesMoved(MotionEvent event)
	{
		CGPoint touchPoint = CGPoint.make(event.getX(), event.getY());
		touchPoint = CCDirector.sharedDirector().convertToGL(touchPoint);
		
        return CCTouchDispatcher.kEventHandled;
    }

	@Override
    public boolean ccTouchesEnded(MotionEvent event) 
    {
    	CGPoint touchPoint = CGPoint.make(event.getX(), event.getY());
				touchPoint = CCDirector.sharedDirector().convertToGL(touchPoint);

		// compute for last touch
		if( m_currentBall != null )
		{
			m_currentBall.computeDirection(touchPoint);
			m_currentBall = null;
		}
		
        return CCTouchDispatcher.kEventHandled;
    }

    @Override
    public boolean ccTouchesCancelled(MotionEvent event) 
    {
    	CGPoint touchPoint = CGPoint.make(event.getX(), event.getY());
				touchPoint = CCDirector.sharedDirector().convertToGL(touchPoint);

		// compute for last touch
		//m_currentBall.computeDirection(touchPoint);
		//m_currentBall = null;
		
        return CCTouchDispatcher.kEventHandled;
    }
}
