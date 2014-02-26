package org.cocos2dx.cpp;

import android.app.NativeActivity;
import android.content.Intent;
import android.os.Bundle;

public class Cocos2dxActivity extends NativeActivity{
	private FacebookConnectPlugin facebookLoginPlugin = null;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		facebookLoginPlugin = new FacebookConnectPlugin(this);
        facebookLoginPlugin.onCreate(savedInstanceState);  
		
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
	    facebookLoginPlugin.onResume();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	    super.onActivityResult(requestCode, resultCode, data);
	    facebookLoginPlugin.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onPause() {
	    super.onPause();
	    facebookLoginPlugin.onPause();
	}

	@Override
	public void onDestroy() {
	    super.onDestroy();
	    facebookLoginPlugin.onDestory();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
	    super.onSaveInstanceState(outState);
	    facebookLoginPlugin.onSaveInstanceState(outState);
	}
}
