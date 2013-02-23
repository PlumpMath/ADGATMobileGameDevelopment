package com.icival.testDataCommunicationActivity;

import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.events.CCTouchDispatcher;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGSize;

import android.view.MotionEvent;

import com.icival.mobilegamedevelopment.GameLayer;
import com.icival.touchActivity.TouchGameScreen;

public class DataComGameLayer extends GameLayer
{
	/** Properties **********************************************************************************/
	private CGSize m_screenSize;
	private int m_player1Score;
	private int m_player2Score;
	private int m_targetScore;
	
	/** Constructor *********************************************************************************/
	public DataComGameLayer()
	{	
		// device size
		m_screenSize = CCDirector.sharedDirector().displaySize();
		
		// set default values
		m_player1Score = 0;
		m_player2Score = 0;
		m_targetScore = 30;
		
		// setup touches
		this.setIsTouchEnabled(true);
	}
	
	/** Methods *************************************************************************************/
	/** Touches **********************/
	@Override
    public boolean ccTouchesBegan(MotionEvent event) 
	{
		CGPoint touchPoint = CGPoint.make(event.getX(), event.getY());
				touchPoint = CCDirector.sharedDirector().convertToGL(touchPoint);

        // check for tap region
		if( touchPoint.x < m_screenSize.width/2 )	// player 1
		{
			m_player1Score++;
			((DataComGameScreen)m_gameScreen).updatePlayer1Score(m_player1Score);
		}
		else // player2
		{
			m_player2Score++;
			((DataComGameScreen)m_gameScreen).updatePlayer2Score(m_player2Score);
		}
		
		// check score
		this.checkWinner();
		
        return CCTouchDispatcher.kEventHandled;
    }
	
	// check winner
	public void checkWinner()
	{
		if( m_player1Score >= m_targetScore )
		{
			((DataComGameScreen)m_gameScreen).setPlayer1Display("Panalo!");
			((DataComGameScreen)m_gameScreen).setPlayer2Display("Talo!");
			
			// display touch
			this.setIsTouchEnabled(false);
			return;
		}
		
		if( m_player2Score >= m_targetScore )
		{
			((DataComGameScreen)m_gameScreen).setPlayer1Display("Talo!");
			((DataComGameScreen)m_gameScreen).setPlayer2Display("Panalo!");
			
			// disable touch
			this.setIsTouchEnabled(false);
			return;
		}
	}
	
}
