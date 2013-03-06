package com.icival.pushergame;

import org.cocos2d.types.CGPoint;

import com.icival.mobilegamedevelopment.Constants;
import com.icival.mobilegamedevelopment.GameLayer;

public class Camera 
{
	/** Properties **********************************************************************************/
	private NonStaticCircle m_targetNode = null;
	private GameLayer m_parentNode = null;
	private boolean m_isPaused = true;
	private CGPoint m_creenPoints = Constants.CENTER;
	private CGPoint m_lowerBound = CGPoint.zero();
	private CGPoint m_higherBound = CGPoint.ccp(Constants.SCREEN_SIZE.width, Constants.SCREEN_SIZE.height);
	
	/** Getter/Setter *******************************************************************************/
	public void setTargetNode(NonStaticCircle p_targetNode)
	{
		m_targetNode = p_targetNode;
	}
	
	public void setParentNode(GameLayer p_gameLayer)
	{
		m_parentNode = p_gameLayer;
	}
	
	public void setScreenPoints(CGPoint p_screenPoints)
	{
		m_creenPoints = p_screenPoints;
	}
	
	/** Camera Functionalities **********************************************************************/
	public void reset()
	{
		m_creenPoints = Constants.CENTER;
	}
	
	public void pause()
	{
		m_isPaused = true;
	}
	
	public void run()
	{
		m_isPaused = false;
	}
	
	public void update(float p_deltaTime)
	{
		if( m_isPaused )
		{
			return;
		}
		
		if( m_targetNode == null )
		{
			return;
		}
		
		if( m_parentNode == null ) 
		{
			return;
		}
		
		// update camera
		CGPoint heroNewPosOnWorld = m_parentNode.convertToWorldSpace(m_targetNode.getPosition().x, 
																	 m_targetNode.getPosition().y);
    	CGPoint focus = CGPoint.ccpSub(m_creenPoints, heroNewPosOnWorld);
    			focus = CGPoint.ccpAdd(focus, m_parentNode.getPosition());
    	m_parentNode.setPosition(focus);
	}
}
