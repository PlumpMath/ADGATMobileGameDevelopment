package com.icival.pushergame;

import org.cocos2d.nodes.CCNode;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;

import com.icival.mobilegamedevelopment.Constants;
import com.icival.seperateClasses.Ball;

public class NonStaticCircle extends CCNode
{
	/** Properties **********************************************************************************/
	/** Public ****************/
	public float g_radius;
	public CGPoint g_direction;
	
	/** Private ***************/
	private CCSprite m_skin;
	
	/** Constructor *********************************************************************************/
	public NonStaticCircle(String p_fileName)
	{	
		m_skin = CCSprite.sprite(p_fileName);
		m_skin.setAnchorPoint(CGPoint.ccp(0.5f, 0.5f));
		m_skin.setPosition(CGPoint.ccp(0.0f, 0.0f));
		this.addChild(m_skin);
		
		g_radius = m_skin.getContentSize().width/2;
		g_direction = CGPoint.zero();
	}
	
	/** Methods *************************************************************************************/
	/** Public ****************/
	public void update(float p_deltaTime)
	{
		// update gravity on the velocity vector of your object
		g_direction.y -= Constants.GRAVITY * p_deltaTime;
		
		// compute for new position
		CGPoint newPosition = this.getPosition();
				newPosition.x += g_direction.x * p_deltaTime;
				newPosition.y += g_direction.y * p_deltaTime;
			
		// check for boundery
		this.limitBorders(newPosition);
				
		// increment ball's position
		this.setPosition(newPosition);
	}
	
	public CGRect getBoundingBox()
	{
		CGPoint origin = CGPoint.ccp(-g_radius, -g_radius);
				origin = this.convertToWorldSpace(origin.x, origin.y);
		CGPoint size = CGPoint.ccp(g_radius, g_radius);
				size = this.convertToWorldSpace(size.x, size.y);
		return CGRect.make(origin.x, origin.y, size.x, size.y);
	}
	
	public boolean ballCollidedToOtherBall(Ball p_ball)
	{
		float totalRadius = (this.g_radius + p_ball.g_radius);
		if( totalRadius > CGPoint.ccpDistance(this.getPosition(), p_ball.getPosition()) )
		{
			CGPoint pushBack = CGPoint.ccpSub(this.getPosition(), p_ball.getPosition());
					pushBack = CGPoint.ccpNormalize(pushBack);
					pushBack = CGPoint.ccpMult(pushBack, totalRadius * 1.001f);
					pushBack = CGPoint.ccpAdd(pushBack, p_ball.getPosition());
			this.setPosition(pushBack);
			g_direction = CGPoint.zero();
			p_ball.g_prevTouch = CGPoint.zero();
			p_ball.g_direction = CGPoint.zero();
			return true;
		}
		return false;
	}
	
	/** Protected *************/
	protected void limitBorders(CGPoint p_newPosition)
	{
		// limit left x
		if( p_newPosition.x < (0 + g_radius) )
		{
			p_newPosition.x = (0 + g_radius);
			g_direction.x = 0.0f;
		}
		
		// limit right x
		if( p_newPosition.x > (Constants.SCREEN_SIZE.width - g_radius) )
		{
			p_newPosition.x = (Constants.SCREEN_SIZE.width - g_radius);
			g_direction.x = 0.0f;
		}
		
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
