package org.cocos2dx.cpp;

import android.app.NativeActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class Cocos2dxActivity extends NativeActivity{
	private FacebookConnectPlugin facebookLoginPlugin = null;
	private FacebookPostPlugin facebookPostPlugin = null;
	private FacebookPickFriendPlugin facebookPickFriendPlugin = null;
	private FacebookSendRequestsPlugin facebookSendRequestsPlugin = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		facebookLoginPlugin = new FacebookConnectPlugin(this);
        facebookLoginPlugin.onCreate(savedInstanceState);  
		
        facebookPostPlugin = new FacebookPostPlugin(this);
        facebookPostPlugin.onCreate(savedInstanceState);
        
        facebookPickFriendPlugin = new FacebookPickFriendPlugin(this);
        facebookPickFriendPlugin.onCreate(savedInstanceState);
        
        facebookSendRequestsPlugin = new FacebookSendRequestsPlugin(this);
        facebookSendRequestsPlugin.onCreate(savedInstanceState);
		//For supports translucency
		
		//1.change "attribs" in cocos\2d\platform\android\nativeactivity.cpp
		/*const EGLint attribs[] = {
	            EGL_SURFACE_TYPE, EGL_WINDOW_BIT,
	            EGL_RENDERABLE_TYPE, EGL_OPENGL_ES2_BIT,  
	            //EGL_BLUE_SIZE, 5,   -->delete 
	            //EGL_GREEN_SIZE, 6,  -->delete 
	            //EGL_RED_SIZE, 5,    -->delete 
	            EGL_BUFFER_SIZE, 32,  //-->new field
	            EGL_DEPTH_SIZE, 16,
	            EGL_STENCIL_SIZE, 8,
	            EGL_NONE
	    };*/
		
		//2.Set the format of window
		// getWindow().setFormat(PixelFormat.TRANSLUCENT);
		
	}
	
	@Override
	public void onResume() {
	    super.onResume();
	    Log.i("Rye", "onResume");
	    facebookLoginPlugin.onResume();
	    facebookPostPlugin.onResume();
	    facebookPickFriendPlugin.onResume();
	    facebookSendRequestsPlugin.onResume();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		Log.i("Rye", "onActivityResult");
	    super.onActivityResult(requestCode, resultCode, data);
	    facebookLoginPlugin.onActivityResult(requestCode, resultCode, data);
	    facebookPostPlugin.onActivityResult(requestCode, resultCode, data);
	    facebookPickFriendPlugin.onActivityResult(requestCode, resultCode, data);
	    facebookSendRequestsPlugin.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onPause() {
	    super.onPause();
	    Log.i("Rye", "onPause");
	    facebookLoginPlugin.onPause();
	    facebookPostPlugin.onPause();
	    facebookPickFriendPlugin.onPause();
	    facebookSendRequestsPlugin.onPause();
	}

	@Override
	public void onDestroy() {
		Log.i("Rye", "onDestroy");
		facebookLoginPlugin.onDestory();
		facebookPostPlugin.onDestory();
		facebookPickFriendPlugin.onDestory();
		facebookSendRequestsPlugin.onDestory();
	    super.onDestroy();
	    
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
	    super.onSaveInstanceState(outState);
	    Log.i("Rye", "onSaveInstanceState");
	    facebookLoginPlugin.onSaveInstanceState(outState);
	    facebookPostPlugin.onSaveInstanceState(outState);
	    facebookPickFriendPlugin.onSaveInstanceState(outState);
	    facebookSendRequestsPlugin.onSaveInstanceState(outState);
	}
}
