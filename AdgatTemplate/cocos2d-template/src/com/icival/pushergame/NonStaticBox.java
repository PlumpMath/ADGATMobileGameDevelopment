package com.icival.pushergame;

import org.cocos2d.nodes.CCNode;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;

import com.icival.mobilegamedevelopment.Constants;
import com.icival.seperateClasses.Ball;

public class NonStaticBox extends CCNode
{
	/** Properties **********************************************************************************/
	/** Public ****************/
	public float g_halfWidth;
	public CGPoint g_direction;
	
	/** Private ***************/
	private CCSprite m_skin;
	
	/** Constructor *********************************************************************************/
	public NonStaticBox(String p_fileName)
	{	
		m_skin = CCSprite.sprite(p_fileName);
		m_skin.setAnchorPoint(CGPoint.ccp(0.5f, 0.5f));
		m_skin.setPosition(CGPoint.ccp(0.0f, 0.0f));
		this.addChild(m_skin);
		
		g_halfWidth = m_skin.getContentSize().width/2;
		g_direction = CGPoint.zero();
	}
	
	/** Methods *************************************************************************************/
	/** Public ****************/
	public void update(float p_deltaTime)
	{
		// update gravity on the velocity vector of your object
		g_direction.y -= (Constants.GRAVITY/10) * p_deltaTime;
		
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
		CGPoint origin = CGPoint.ccp(-g_halfWidth, -g_halfWidth);
				origin = this.convertToWorldSpace(origin.x, origin.y);
		CGPoint size = CGPoint.ccp(g_halfWidth, g_halfWidth);
				size = this.convertToWorldSpace(size.x, size.y);
		return CGRect.make(origin.x, origin.y, size.x, size.y);
	}
	
	public boolean boxCollidedToStaticCircle(NonStaticCircle p_ball)
	{
		// check first which axis need to compute
		boolean computeForX = false;
		CGPoint vector = CGPoint.ccpSub(p_ball.getPosition(), this.getPosition());
		CGPoint dummyBoxPos = this.getPosition();
		CGPoint dummyBallPos = p_ball.getPosition();
		
		if( vector.x > vector.y )
		{
			computeForX = true;
			dummyBoxPos.y = 0;
			dummyBallPos.y = 0;
		}
		else
		{
			computeForX = false;
			dummyBoxPos.x = 0;
			dummyBallPos.x = 0;
		}
		
		float totalRadius = (this.g_halfWidth + p_ball.g_radius);
		if( totalRadius > CGPoint.ccpDistance(dummyBoxPos, dummyBallPos) )
		{
			CGPoint pushBack = CGPoint.ccpSub(dummyBoxPos, dummyBallPos);
					pushBack = CGPoint.ccpNormalize(pushBack);
					pushBack = CGPoint.ccpMult(pushBack, totalRadius * 1.001f);
					pushBack = CGPoint.ccpAdd(pushBack, p_ball.getPosition());
			this.setPosition(pushBack);
			g_direction = CGPoint.zero();
			return true;
		}
		return false;
	}
	
	public boolean boxCollidedToHero(PusherHero p_hero)
	{
		// check first which axis need to compute
		boolean computeForX = false;
		CGPoint vector = CGPoint.ccpSub(p_hero.getPosition(), this.getPosition());
		CGPoint dummyBoxPos = this.getPosition();
		CGPoint dummyHeroPos = p_hero.getPosition();
		
		if( Math.abs(vector.x) > Math.abs(vector.y) )
		{
			computeForX = true;
			dummyBoxPos.y = 0;
			dummyHeroPos.y = 0;
		}
		else
		{
			computeForX = false;
			dummyBoxPos.x = 0;
			dummyHeroPos.x = 0;
		}
		
		float totalRadius = (this.g_halfWidth + p_hero.g_radius);
		if( totalRadius > CGPoint.ccpDistance(dummyBoxPos, dummyHeroPos) )
		{
			CGPoint pushBack = CGPoint.ccpSub(dummyHeroPos, dummyBoxPos);
					pushBack = CGPoint.ccpNormalize(pushBack);
					pushBack = CGPoint.ccpMult(pushBack, totalRadius * 1.001f);
					pushBack = CGPoint.ccpAdd(pushBack, this.getPosition());
					
			// reuse the uncomputed value
			if( computeForX )
			{
				pushBack.y = p_hero.getPosition().y;
				p_hero.g_direction.x = 0;
			}
			else
			{
				pushBack.x = p_hero.getPosition().x;
				p_hero.g_direction.y = 0;
			}
				
			p_hero.setPosition(pushBack);
			
			return true;
		}
		return false;
	}
	
	/** Protected *************/
	protected void limitBorders(CGPoint p_newPosition)
	{
		// limit left x
		if( p_newPosition.x < (0 + g_halfWidth) )
		{
			p_newPosition.x = (0 + g_halfWidth);
			g_direction.x = 0.0f;
		}
		
		// limit right x
		if( p_newPosition.x > (Constants.SCREEN_SIZE.width - g_halfWidth) )
		{
			p_newPosition.x = (Constants.SCREEN_SIZE.width - g_halfWidth);
			g_direction.x = 0.0f;
		}
		
		// limit top y
		if( p_newPosition.y < (0 + g_halfWidth) )
		{
			p_newPosition.y = (0 + g_halfWidth);
			g_direction.y = 0.0f;
		}
		
		// limit bottom y
		if( p_newPosition.y > (Constants.SCREEN_SIZE.height - g_halfWidth) )
		{
			p_newPosition.y = (Constants.SCREEN_SIZE.height - g_halfWidth);
			g_direction.y = 0.0f;
		}
	}
	
	/** Private ***************/
	
}
