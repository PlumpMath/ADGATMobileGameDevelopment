package com.icival.pushergame;

import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;

import org.cocos2d.events.CCTouchDispatcher;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.opengl.CCTexture2D;
import org.cocos2d.types.CCTexParams;
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
	private ArrayList<StaticCircle> m_staticCircle;
	private ArrayList<NonStaticBox> m_nonStaticBox;
	private CGSize m_screenSize;
	private CCSprite m_background;
	private Camera m_camera;
	
	/** Constructor *********************************************************************************/
	public PusherGameLayer()
	{	
		// device size
		m_screenSize = Constants.SCREEN_SIZE;
		
		// repeat image
		CCTexParams params = new CCTexParams(GL10.GL_LINEAR,GL10.GL_LINEAR,GL10.GL_REPEAT,GL10.GL_REPEAT);
		CCTexture2D.setTexParameters(params);
		//m_background.getTexture().setTexParameters(params);
		
		// create bg (parallax)
		m_background = CCSprite.sprite("middleground.png");
		m_background.setAnchorPoint(CGPoint.ccp(0.5f, 0.5f));
		m_background.setPosition(CGPoint.ccp(m_screenSize.width/2, m_screenSize.height/2));
		this.addChild(m_background);
		
		
		// create hero
		m_hero = new PusherHero("ball.png");
		m_hero.setPosition(CGPoint.ccp(m_screenSize.width * 0.5f, m_screenSize.height * 0.75f));
		this.addChild(m_hero);
		
		// create camera
		m_camera = new Camera();
		m_camera.setTargetNode(m_hero);
		m_camera.setParentNode(this);
		//m_camera.setScreenPoints(CGPoint.ccp(100, 100));
		m_camera.run();
		
		// create gameobjects
		// static objects
    	m_staticCircle = new ArrayList<StaticCircle>();
    	// non static objects
    	m_nonStaticBox = new ArrayList<NonStaticBox>();
		
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
		
		/** StaticCircle Update/Collision *********************/
		StaticCircle staticCircle;
		// check collision hero to static objects
		for( int i = 0; i < m_staticCircle.size(); i++ )
		{
			staticCircle = m_staticCircle.get(i);
			staticCircle.circleCollidedToHero(m_hero);
		}
		
		/** NonStaticBox Update/Collision *********************/
		NonStaticBox nonStaticBox;
		// check collision hero to non static box
		for( int i = 0; i < m_nonStaticBox.size(); i++ )
		{
			nonStaticBox = m_nonStaticBox.get(i);
			nonStaticBox.boxCollidedToHero(m_hero);
		}
		
		// update non static box
		for( int i = 0; i < m_nonStaticBox.size(); i++ )
		{
			nonStaticBox = m_nonStaticBox.get(i);
			nonStaticBox.update(p_deltaTime);
		}
		
		// check for non static box collided to static circle
		//for( int i = 0; i < m_nonStaticBox.size(); i++ )
		//{
		//	nonStaticBox = m_nonStaticBox.get(i);
		//	for( int j = 0; j < m_staticCircle.size(); j++ )
		//	{
		//		staticCircle = m_staticCircle.get(j);
		//		nonStaticBox.boxCollidedToStaticCircle(staticCircle);
		//	}
		//}
		
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
    public void createStateA(float p_initialX)
    {
    	
    }
    
    public void createObstacles()
    {
		int staticObjectCount = 6;
		float staticObjectWidth = m_hero.g_radius * 2.0f;
		float staticObjectTotalWidth = staticObjectWidth * staticObjectCount;
		float initX = Constants.CENTER.x - ((staticObjectTotalWidth-staticObjectWidth)/2);
		StaticCircle staticCircle;
		
		// create multiple staticobject
		for( int i = 0; i < staticObjectCount; i++ )
		{
			float staticObjectX = initX;
			float staticObjectY = m_screenSize.height * 0.5f;
			
			staticCircle = new StaticCircle("ball.png");
			staticCircle.setPosition(CGPoint.ccp(staticObjectX + (staticObjectWidth*i), staticObjectY));
			this.addChild(staticCircle);
			m_staticCircle.add(staticCircle);
		}
		
		NonStaticBox box;
		for( int i = 0; i < 5; i++ )
		{
			float nonStaticObjectX = m_screenSize.width * 0.6f;
			float nonStaticObjectY = m_screenSize.height * 0.90f;
			
			box = new NonStaticBox("box.jpg");
			box.setPosition(CGPoint.ccp(nonStaticObjectX + (50*i), nonStaticObjectY));
			this.addChild(box);
			m_nonStaticBox.add(box);
		}
    }
}
