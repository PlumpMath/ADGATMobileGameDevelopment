package com.icival.testDataCommunicationActivity;

import com.icival.mobilegamedevelopment.GameScreen;

public class DataComGameScreen extends GameScreen
{
	/** Properties **********************************************************************************/
	
	/** Constructor *********************************************************************************/
	public DataComGameScreen()
	{
		// create ui layer
		m_uiLayer = new DataComUILayer();
		m_uiLayer.setGameScreen(this);
		this.addChild(m_uiLayer, 20);
		
		// create game layer
		m_gameLayer = new DataComGameLayer();
		m_gameLayer.setGameScreen(this);
		this.addChild(m_gameLayer, 10);
	}
	
	/** Methods *************************************************************************************/
	// update score
	public void updatePlayer1Score(int p_score)
	{
		((DataComUILayer)m_uiLayer).updatePlayer1Score(p_score);
	}
	
	public void updatePlayer2Score(int p_score)
	{
		((DataComUILayer)m_uiLayer).updatePlayer2Score(p_score);
	}
	
	// update winner
	public void setPlayer1Display(String p_display)
	{
		((DataComUILayer)m_uiLayer).setPlayer1Display(p_display);
	}
	
	public void setPlayer2Display(String p_display)
	{
		((DataComUILayer)m_uiLayer).setPlayer2Display(p_display);
	}
	
}
