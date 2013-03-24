package com.icival.pushergame;

import org.cocos2d.types.CGPoint;

import android.util.Log;

import com.icival.mobilegamedevelopment.Constants;

public class MovingBox extends StaticBox
{
	/** Constants ***********************************************************************************/
	public static final int GO_LEFT			= 0;
	public static final int GO_RIGHT		= 1;
	public static final int GO_UP			= 2;
	public static final int GO_DOWN			= 3;
	public static final int GO_STILL		= -1;
	public static final float DURATION_1	= 2.5f;	// seconds
	
	/** Properties **********************************************************************************/
	private int m_currentDirection			= GO_STILL;
	private int m_patternIndex				= 0;
	private int m_patterns[]				= { MovingBox.GO_LEFT, MovingBox.GO_RIGHT };// default patter
	private float m_currentDuration			= 0.0f;
	private float m_speed					= Constants.SCREEN_SIZE.width * 0.10f;
	
	
	/** Constructor *********************************************************************************/
	public MovingBox(
		String p_fileName,
		int p_patterns[]) 
	{
		super(p_fileName);
		// TODO Auto-generated constructor stub
		
		m_patterns = p_patterns;
	}
	
	/** Methods *************************************************************************************/
	/** Public ****************/
	@Override
	public void update(float p_deltaTime)
	{
		// check first if the pattern is valid
		if( m_patternIndex >= m_patterns.length )
		{
			Log.i("Error!", "pattern is not valid!");
			return;
		}
		
		// check and generate movements based on patters
		switch(m_patterns[m_patternIndex])
		{
			case GO_LEFT:
				g_direction.y = 0;
				g_direction.x = -m_speed;
			break;
			case GO_RIGHT:
				g_direction.y = 0;
				g_direction.x = m_speed;
			break;
			case GO_UP:	
				g_direction.y = m_speed;
				g_direction.x = 0;
			break;
			case GO_DOWN:	
				g_direction.y = -m_speed;
				g_direction.x = 0;
			break;
			case GO_STILL:	
				g_direction.y = 0;
				g_direction.x = 0;
			break;
		}
		
		// update objects position
		CGPoint newPosition = this.getPosition();
				newPosition.x += g_direction.x * p_deltaTime;
				newPosition.y += g_direction.y * p_deltaTime;
		this.setPosition(newPosition);
		
		// hold all the delta 
		m_currentDuration += p_deltaTime;
		
		// check if the duration to change current pattern index have passed
		if( m_currentDuration < DURATION_1 )
		{
			return;
		}
		
		// check for next pattern index and reset the duration
		m_patternIndex++;
		m_currentDuration = 0.0f;
		
		if( m_patternIndex >= m_patterns.length )
		{
			m_patternIndex = 0;
		}
	}
}
