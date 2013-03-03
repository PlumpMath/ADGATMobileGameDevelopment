package com.icival.mobilegamedevelopment;

import org.cocos2d.nodes.CCDirector;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGSize;

public class Constants
{
	public static CGSize SCREEN_SIZE = CCDirector.sharedDirector().displaySize();
	public static CGPoint CENTER = CGPoint.ccp(SCREEN_SIZE.width/2, SCREEN_SIZE.height/2);
	public static float GRAVITY = SCREEN_SIZE.height;
}
