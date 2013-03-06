package com.icival.pushergame;

import java.util.ArrayList;

import org.cocos2d.events.CCTouchDispatcher;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;
import org.cocos2d.types.CGSize;

import android.annotation.SuppressLint;
import android.view.MotionEvent;

import com.icival.mobilegamedevelopment.GameLayer;
import com.icival.seperateClasses.Ball;
import com.icival.mobilegamedevelopment.Constants;
import com.icival.pushergame.Camera;

public class PusherGameLayer extends GameLayer
{
	/** Properties **********************************************************************************/
	private PusherHero m_hero;
	private ArrayList<StaticCircle> m_staticObjects;
	private CGSize m_screenSize;
	private CCSprite m_background;
	private Camera m_camera;
	
	/** Constructor *********************************************************************************/
	public PusherGameLayer()
	{	
		// device size
		m_screenSize = Constants.SCREEN_SIZE;
		
		// create bg
		//m_background = CCSprite.sprite("background3.jpg");
		//m_background.setAnchorPoint(CGPoint.ccp(0.5f, 0.5f));
		//m_background.setPosition(CGPoint.ccp(m_screenSize.width/2, m_screenSize.height/2));
		//this.addChild(m_background);
		
		// create hero
		m_hero = new PusherHero("ball.png");
		m_hero.setPosition(CGPoint.ccp(m_screenSize.width * 0.5f, m_screenSize.height * 0.5f));
		this.addChild(m_hero);
		
		// create camera
		m_camera = new Camera();
		m_camera.setTargetNode(m_hero);
		m_camera.setParentNode(this);
		//m_camera.setScreenPoints(CGPoint.ccp(100, 100));
		m_camera.run();
		
		// create obstacles
		this.createObstacles();
		
		// setup touches
		this.setIsTouchEnabled(true);
		
		// setup game loop
		this.scheduleUpdate();
		
		// setup accelerometer
		this.setIsAccelerometerEnabled(true);
	}
	
	/** Methods *************************************************************************************/
	/** GameLoop *********************/
	@Override
	public void update(float p_deltaTime)
	{
		// update player
		m_hero.update(p_deltaTime);
		
		StaticCircle staticCircle;
		// check collision hero to static objects
		for( int i = 0; i < m_staticObjects.size(); i++ )
		{
			staticCircle = m_staticObjects.get(i);
			staticCircle.circleCollidedToHero(m_hero);
		}
		
		// update camera
		m_camera.update(p_deltaTime);
	}
	
	/** Touches **********************/
	@SuppressLint("NewApi")
	@Override
    public boolean ccTouchesBegan(MotionEvent event) 
	{
		CGPoint touchPoint = CGPoint.make(event.getX(), event.getY());
		touchPoint = CCDirector.sharedDirector().convertToGL(touchPoint);
		
		// player actions
		m_hero.playerControl(touchPoint);
		
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
        return CCTouchDispatcher.kEventHandled;
    }

    @Override
    public boolean ccTouchesCancelled(MotionEvent event) 
    {
    	CGPoint touchPoint = CGPoint.make(event.getX(), event.getY());
				touchPoint = CCDirector.sharedDirector().convertToGL(touchPoint);
        return CCTouchDispatcher.kEventHandled;
    }
    
    /** Accelerometer ****************/
    @Override
    public void ccAccelerometerChanged(float accelX, float accelY, float accelZ) 
    {
		// Override to process accelerometer events.
    	m_hero.playerControlX(accelY);
    }
    
    /** Obstacles ********************/
    public void createObstacles()
    {
    	// create static objects
		m_staticObjects = new ArrayList<StaticCircle>();
		
		int staticObjectCount = 6;
		float staticObjectWidth = m_hero.g_radius * 2.0f;
		float staticObjectTotalWidth = staticObjectWidth * staticObjectCount;
		float initX = Constants.CENTER.x - ((staticObjectTotalWidth-staticObjectWidth)/2);
		StaticCircle staticCircle;
		
		// create multiple hero
		for( int i = 0; i < staticObjectCount; i++ )
		{
			float staticObjectX = initX;
			float staticObjectY = m_screenSize.height * 0.5f;
			
			staticCircle = new StaticCircle("ball.png");
			staticCircle.setPosition(CGPoint.ccp(staticObjectX + (staticObjectWidth*i), staticObjectY));
			this.addChild(staticCircle);
			m_staticObjects.add(staticCircle);
		}
    }
}
