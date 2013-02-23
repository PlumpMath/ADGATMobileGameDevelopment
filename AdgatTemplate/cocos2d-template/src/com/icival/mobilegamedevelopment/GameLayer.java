package com.icival.mobilegamedevelopment;

import org.cocos2d.events.CCTouchDispatcher;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.types.CGPoint;

import android.view.MotionEvent;

public class GameLayer extends CCLayer
{
	/** Properties **********************************************************************************/
	protected GameScreen m_gameScreen;
	
	/** Constructor *********************************************************************************/
	public GameLayer()
	{	
		// setup game loop
		this.scheduleUpdate();
		
		// setup touches
		this.setIsTouchEnabled(true);
		
		// setup accelerometer
		this.setIsAccelerometerEnabled(true);
	}
	
	/** Methods *************************************************************************************/
	public void setGameScreen(GameScreen p_GameScreen)
	{
		m_gameScreen = p_GameScreen;
	}
	
	/** GameLoop *********************/
	public void update(float dt)
	{
		
	}
	
	/** Touches **********************/
	@Override
    public boolean ccTouchesBegan(MotionEvent event) 
	{
		CGPoint touchPoint = CGPoint.make(event.getX(), event.getY());
				touchPoint = CCDirector.sharedDirector().convertToGL(touchPoint);

        //String title = String.format("touch began at point(%.2f, %.2f)", touchPoint.x, touchPoint.y);
        
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
		
        return CCTouchDispatcher.kEventHandled;
    }

    @Override
    public boolean ccTouchesCancelled(MotionEvent event) 
    {
    	CGPoint touchPoint = CGPoint.make(event.getX(), event.getY());
		touchPoint = CCDirector.sharedDirector().convertToGL(touchPoint);

		//String title = String.format("touch cancelled at point(%.2f, %.2f)", touchPoint.x, touchPoint.y);
		
        return CCTouchDispatcher.kEventHandled;
    }
    
    /** Accelerometer ****************/
    @Override
    public void ccAccelerometerChanged(float accelX, float accelY, float accelZ) 
    {
		// Override to process accelerometer events.
    }
}
