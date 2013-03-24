package com.icival.pushergame;

import org.cocos2d.types.CGPoint;

import com.icival.mobilegamedevelopment.Constants;

public class StaticBox extends NonStaticBox
{
	/** Properties **********************************************************************************/
	/** Public ****************/
	
	/** Private ***************/
	
	/** Constructor *********************************************************************************/
	public StaticBox(String p_fileName) 
	{
		super(p_fileName);
		// TODO Auto-generated constructor stub
	}
	
	/** Methods *************************************************************************************/
	/** Public ****************/
	@Override
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
	
	/** Protected *************/
	
	/** Private ***************/
}
