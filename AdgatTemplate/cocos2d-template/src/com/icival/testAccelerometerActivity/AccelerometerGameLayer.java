package com.icival.testAccelerometerActivity;

import org.cocos2d.events.CCTouchDispatcher;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGSize;

import com.icival.mobilegamedevelopment.GameLayer;

import android.view.MotionEvent;

public class AccelerometerGameLayer extends GameLayer
{
	/** Properties **********************************************************************************/
	private CCSprite m_ball;
	private CGSize m_screenSize;
	
	/** Constructor *********************************************************************************/
	public AccelerometerGameLayer()
	{	
		// device size
		m_screenSize = CCDirector.sharedDirector().displaySize();
		
		// set game objects
		m_ball = CCSprite.sprite("ball.jpg");
		m_ball.setPosition(CGPoint.ccp(m_screenSize.width/2, m_screenSize.height/2));
		this.addChild(m_ball);
		
		// setup accelerometer
		this.setIsAccelerometerEnabled(true);
	}
	
	/** Methods *************************************************************************************/
    /** Accelerometer ****************/
    @Override
    public void ccAccelerometerChanged(float accelX, float accelY, float accelZ) 
    {
		// Override to process accelerometer events.
    	CGPoint ballCurrentPosition = m_ball.getPosition();
    	ballCurrentPosition.x += accelY;
    	float offset = m_ball.getContentSize().width/2;
    	// bounderies
    	if( ballCurrentPosition.x < offset || ballCurrentPosition.x > (m_screenSize.width-offset) )
    	{
    		ballCurrentPosition.x = m_ball.getPosition().x;
    	}
    	
    	m_ball.setPosition(ballCurrentPosition);
    }
}
