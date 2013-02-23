package com.icival.mobilegamedevelopment;

import org.cocos2d.events.CCTouchDispatcher;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCLabel;
import org.cocos2d.opengl.CCGLSurfaceView;
import org.cocos2d.types.CGPoint;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import com.icival.mobilegamedevelopment.R;
import com.icival.movementActivity.MovementGameScreen;
import com.icival.testAccelerometerActivity.AccelerometerGameScreen;
import com.icival.touchActivity.TouchGameScreen;

public class GameActivity extends Activity 
{
	private CCGLSurfaceView mGLSurfaceView;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        
        // set the window status, no tile, full screen and don't sleep
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        mGLSurfaceView = new CCGLSurfaceView(this);
        
        this.setContentView(mGLSurfaceView);
        
        // attach the OpenGL view to a window
        CCDirector.sharedDirector().attachInView(mGLSurfaceView);

        // no effect here because device orientation is controlled by manifest
        //CCDirector.sharedDirector().setDeviceOrientation(CCDirector.kCCDeviceOrientationPortrait);
        CCDirector.sharedDirector().setDeviceOrientation(CCDirector.kCCDeviceOrientationLandscapeLeft);
        
        // show FPS
        // set false to disable FPS display, but don't delete fps_images.png!!
        CCDirector.sharedDirector().setDisplayFPS(true);

        // frames per second
        //CCDirector.sharedDirector().setAnimationInterval(1.0f / 60);
        CCDirector.sharedDirector().setAnimationInterval(1.0f / 30);
        
        // disable depth test
        //CCDirector.sharedDirector().setAlphaBlending(CCDirector.sharedDirector().gl, false);
        
        // set designed resolution
        //CCDirector.sharedDirector().setScreenSize(1024, 768);
        
        //CCScene scene = new GameScreen();
        //CCScene scene = new MovementGameScreen();
        //CCScene scene = new TouchGameScreen();
        //CCScene scene = new AccelerometerGameScreen();
        CCScene scene = new Activities();
        
        // Make the Scene active
        CCDirector.sharedDirector().runWithScene(scene);
    }
    
    @Override
    public void onStart() 
    {
        super.onStart();        
    }

    //@Override
    public void onPause() 
    {
        super.onPause();
        CCDirector.sharedDirector().pause();
    }

    //@Override
    public void onResume() 
    {
        super.onResume();
        CCDirector.sharedDirector().resume();
    }

    @Override
    public void onDestroy() 
    {
        super.onDestroy();
        CCDirector.sharedDirector().end();
    }
    
    @Override
    public void onBackPressed()
    {
    }
    
}