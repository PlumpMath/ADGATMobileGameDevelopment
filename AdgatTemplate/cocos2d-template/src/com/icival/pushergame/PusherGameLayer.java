package com.icival.pushergame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import javax.microedition.khronos.opengles.GL10;

import org.cocos2d.utils.PlistParser;
import org.cocos2d.actions.base.CCRepeatForever;
import org.cocos2d.actions.interval.CCAnimate;
import org.cocos2d.events.CCTouchDispatcher;
import org.cocos2d.nodes.CCAnimation;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.nodes.CCTextureCache;
import org.cocos2d.nodes.CCSpriteFrame;
import org.cocos2d.nodes.CCSpriteFrameCache;
import org.cocos2d.opengl.CCTexture2D;
import org.cocos2d.types.CCTexParams;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;
import org.cocos2d.types.CGSize;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.MotionEvent;

import com.icival.mobilegamedevelopment.GameLayer;
import com.icival.seperateClasses.Ball;
import com.icival.mobilegamedevelopment.Constants;
import com.icival.pushergame.Camera;

public class PusherGameLayer extends GameLayer
{
	/** Properties **********************************************************************************/
	private PusherHero 					m_hero;
	private ArrayList<StaticCircle> 	m_staticCircles;
	private ArrayList<NonStaticBox> 	m_nonStaticBoxes;
	private ArrayList<StaticBox> 		m_staticBoxes;
	private ArrayList<MovingBox>		m_movingBoxes;
	
	private CGSize m_screenSize;
	private CCSprite m_background;
	private Camera m_camera;
	
	/** Constructor *********************************************************************************/
	public PusherGameLayer()
	{	
		// device size
		m_screenSize = Constants.SCREEN_SIZE;
		
		// repeat image
		//CCTexParams params = new CCTexParams(GL10.GL_LINEAR,GL10.GL_LINEAR,GL10.GL_REPEAT,GL10.GL_REPEAT);
		//CCTexture2D.setTexParameters(params);
		//m_background.getTexture().setTexParameters(params);
		
		// create bg (parallax)
		//m_background = CCSprite.sprite("middleground.png");
		//m_background.setAnchorPoint(CGPoint.ccp(0.5f, 0.5f));
		//m_background.setPosition(CGPoint.ccp(m_screenSize.width/2, m_screenSize.height/2));
		//this.addChild(m_background);
		
		
		// create hero
		m_hero = new PusherHero("ball.png");
		m_hero.setPosition(CGPoint.ccp(m_screenSize.width * 0.5f, m_screenSize.height * 0.75f));
		this.addChild(m_hero);
		
		// create camera
		m_camera = new Camera();
		m_camera.setTargetNode(null);
		m_camera.setParentNode(this);
		m_camera.setScreenPoints(CGPoint.ccp(100, 100));
		m_camera.run();
		
		// create gameobjects
		// obstacles
    	m_staticCircles 	= new ArrayList<StaticCircle>();
    	m_nonStaticBoxes 	= new ArrayList<NonStaticBox>();
    	m_staticBoxes		= new ArrayList<StaticBox>();
    	m_movingBoxes		= new ArrayList<MovingBox>();
    	
    	// create static boxes
    	//LevelManager.createRandomLevel(0.0f, 0.0f, this);
    	LevelManager.create001Level(0.0f, 0.0f, this);
    	//LevelManager.create001Level(Constants.SCREEN_SIZE.width*1, 0.0f, this);
    	//LevelManager.create002Level(Constants.SCREEN_SIZE.width*2, 0.0f, this);
    	//LevelManager.create003Level(Constants.SCREEN_SIZE.width*3, 0.0f, this);
    	//LevelManager.create004Level(Constants.SCREEN_SIZE.width*4, 0.0f, this);
    	//LevelManager.create005Level(Constants.SCREEN_SIZE.width*5, 0.0f, this);
    	
    	// check limitations
    	for( int i = 6; i < 10; i++ )
    	{
    		LevelManager.create001Level(Constants.SCREEN_SIZE.width*i, 0.0f, this);
    	}
    	
    	/*
    	// create create moving boxes
    	MovingBox movingBox = null;
    	int pattern1[] = {MovingBox.GO_STILL, MovingBox.GO_LEFT, MovingBox.GO_STILL, MovingBox.GO_RIGHT};
    	movingBox = new MovingBox("brickA.jpg", pattern1 );
    	movingBox.setPosition(Constants.CENTER);
    	this.pushMovingBox(movingBox);
    	
    	int pattern2[] = {MovingBox.GO_STILL, MovingBox.GO_DOWN, MovingBox.GO_STILL, MovingBox.GO_UP};
    	movingBox = new MovingBox("brickA.jpg", pattern2 );
    	movingBox.setPosition(CGPoint.ccp(Constants.SCREEN_SIZE.width, Constants.CENTER.y+30));
    	this.pushMovingBox(movingBox);
    	
    	int pattern3[] = {MovingBox.GO_DOWN, MovingBox.GO_RIGHT, MovingBox.GO_UP, MovingBox.GO_LEFT};
    	movingBox = new MovingBox("brickA.jpg", pattern3 );
    	movingBox.setPosition(CGPoint.ccp(Constants.SCREEN_SIZE.width+Constants.CENTER.x, Constants.CENTER.y+30));
    	this.pushMovingBox(movingBox);
		 */
    	
		// setup touches
		this.setIsTouchEnabled(true);
		
		// setup game loop
		this.scheduleUpdate();
		
		// setup accelerometer
		this.setIsAccelerometerEnabled(true);
		
		// sample spritesheet
		CCSpriteFrameCache.sharedSpriteFrameCache().addSpriteFrames("balls.plist");
		CCSpriteFrame frame = CCSpriteFrameCache.sharedSpriteFrameCache().getSpriteFrame("ball_1.png");
		
		/*
		// create your sprite
		CCSprite ballAnimation = CCSprite.sprite(frame);
		ballAnimation.setPosition(Constants.CENTER);
		this.addChild(ballAnimation);
		
		// create animation for your sprite
		CCSpriteFrame imgA = CCSpriteFrameCache.sharedSpriteFrameCache().getSpriteFrame("ball_2.png");
		CCSpriteFrame imgB = CCSpriteFrameCache.sharedSpriteFrameCache().getSpriteFrame("ball_3.png");
		CCSpriteFrame imgC = CCSpriteFrameCache.sharedSpriteFrameCache().getSpriteFrame("ball_1.png");
		CCAnimation animation = CCAnimation.animation("",0.5f);
					animation.addFrame(imgA);
					animation.addFrame(imgB);
					animation.addFrame(imgC);
		CCAnimate animate = CCAnimate.action(0.5f,animation,false);
		CCRepeatForever foreverAnimation = CCRepeatForever.action(animate);
		ballAnimation.runAction(foreverAnimation);
		 */
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
		for( int i = 0; i < m_staticCircles.size(); i++ )
		{
			staticCircle = m_staticCircles.get(i);
			staticCircle.circleCollidedToHero(m_hero);
		}
		
		/** StaticBox Update/Collision *********************/
		StaticBox staticBox;
		// check collision hero to non static box
		for( int i = 0; i < m_staticBoxes.size(); i++ )
		{
			staticBox = m_staticBoxes.get(i);
			staticBox.boxCollidedToHero(m_hero);
		}
		
		/** Non StaticBox Update/Collision *****************/
		NonStaticBox nonStaticBox;
		for( int i = 0; i < m_nonStaticBoxes.size(); i++ )
		{
			nonStaticBox = m_nonStaticBoxes.get(i);
			nonStaticBox.update(p_deltaTime);
		}
		
		/** MovingBoxes Update/Collision *******************/
		MovingBox movingBox;
		for( int i = 0; i < m_movingBoxes.size(); i++ )
		{
			movingBox = m_movingBoxes.get(i);
			movingBox.update(p_deltaTime);
			movingBox.boxCollidedToHero(m_hero);
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
		//m_hero.playerControl(touchPoint);
		
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
    	m_hero.playerControlY(-accelX);
    }
    
    /** Push GameObjects *************/
	public void pushStaticBox(StaticBox p_staticBox)
	{
		this.addChild(p_staticBox);
		m_staticBoxes.add(p_staticBox);
	}
	
	public void pushMovingBox(MovingBox p_movingBox)
	{
		this.addChild(p_movingBox);
		m_movingBoxes.add(p_movingBox);
	}
    
}
