package com.icival.pushergame;

import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;

import com.icival.mobilegamedevelopment.Constants;
import com.icival.seperateClasses.Ball;

public class PusherHero extends CircleObject
{
	/** Properties **********************************************************************************/
	/** Public ****************/
	
	/** Private ***************/
	private CGPoint m_autoMove;
	
	/** Constructor *********************************************************************************/
	public PusherHero(String p_fileName) 
	{
		super(p_fileName);
		// TODO Auto-generated constructor stub
		m_autoMove = CGPoint.ccp(0, 0);
	}
	
	/** Methods *************************************************************************************/
	/** Public ****************/
	@Override
	public void update(float p_deltaTime)
	{
		// update gravity on the velocity vector of your object
		g_direction.y -= Constants.GRAVITY * p_deltaTime;
		
		// compute for new position
		CGPoint newPosition = this.getPosition();
				newPosition.x += g_direction.x * p_deltaTime;
				newPosition.y += g_direction.y * p_deltaTime;
			
		// add automove to new position
		newPosition.x += m_autoMove.x * p_deltaTime;
		newPosition.y += m_autoMove.y * p_deltaTime;
				
		// check for boundery
		this.limitBorders(newPosition);
				
		// increment ball's position
		this.setPosition(newPosition);
	}
	
	public void playerControl(CGPoint p_touchPoint)
	{
		// touch region checking
		if( p_touchPoint.x < Constants.SCREEN_SIZE.width/2 )
		{
			// left region
			//m_autoMove.y = 50;
			g_direction.y = Constants.GRAVITY;
		}
		else
		{
			// right region
			g_direction.y = Constants.GRAVITY;
		}
	}
	
	public void playerControlX(float p_accelerometer)
	{
		g_direction.x += p_accelerometer;
	}
	
	/** Protected *************/
	@Override
	protected void limitBorders(CGPoint p_newPosition)
	{
		// limit top y
		if( p_newPosition.y < (0 + g_radius) )
		{
			p_newPosition.y = (0 + g_radius);
			g_direction.y = 0.0f;
		}
		
		// limit bottom y
		if( p_newPosition.y > (Constants.SCREEN_SIZE.height - g_radius) )
		{
			p_newPosition.y = (Constants.SCREEN_SIZE.height - g_radius);
			g_direction.y = 0.0f;
		}
	}
	
	/** Private ***************/
}
