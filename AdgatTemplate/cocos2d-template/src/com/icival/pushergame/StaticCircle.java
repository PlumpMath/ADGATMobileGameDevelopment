package com.icival.pushergame;

import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;

import com.icival.mobilegamedevelopment.Constants;
import com.icival.seperateClasses.Ball;

public class StaticCircle extends NonStaticCircle
{

	/** Properties **********************************************************************************/
	/** Public ****************/
	
	/** Private ***************/
	
	/** Constructor *********************************************************************************/
	public StaticCircle(String p_fileName) 
	{
		super(p_fileName);
		// TODO Auto-generated constructor stub
	}
	
	/** Methods *************************************************************************************/
	/** Public ****************/
	@Override
	public void update(float p_deltaTime)
	{
	}
	
	public boolean circleCollidedToHero(PusherHero p_hero)
	{
		float totalRadius = (this.g_radius + p_hero.g_radius);
		if( totalRadius > CGPoint.ccpDistance(p_hero.getPosition(), this.getPosition()) )
		{
			CGPoint pushBack = CGPoint.ccpSub(p_hero.getPosition(), this.getPosition());
					pushBack = CGPoint.ccpNormalize(pushBack);
					pushBack = CGPoint.ccpMult(pushBack, totalRadius * 1.001f);
					pushBack = CGPoint.ccpAdd(pushBack, this.getPosition());
			p_hero.setPosition(pushBack);
			p_hero.g_direction = CGPoint.zero();
			return true;
		}
		return false;
	}
	
	/** Protected *************/
	
	/** Private ***************/
}
