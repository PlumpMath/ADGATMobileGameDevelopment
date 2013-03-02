package com.icival.mobilegamedevelopment;

import org.cocos2d.nodes.CCDirector;
import org.cocos2d.types.CGSize;

public class Constants
{
	public static CGSize SCREEN_SIZE = CCDirector.sharedDirector().displaySize();
	public static float GRAVITY = SCREEN_SIZE.height;
}
