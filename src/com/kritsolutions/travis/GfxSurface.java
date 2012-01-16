package com.kritsolutions.travis;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

public class GfxSurface extends Activity implements OnTouchListener {
	
	MyBringBackSurface myBringBackSurface;
	float x, y;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		myBringBackSurface = new MyBringBackSurface(this);
		myBringBackSurface.setOnTouchListener(this);
		x = 0;
		y = 0;
		setContentView(myBringBackSurface);
		
	}

	@Override
	protected void onPause() {
		super.onPause();
		myBringBackSurface.pause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		myBringBackSurface.resume();
	}

	public boolean onTouch(View v, MotionEvent event) {
		x = event.getX();
		y = event.getY();
		return true;
	}
	
	public class MyBringBackSurface extends SurfaceView implements Runnable {

		SurfaceHolder ourHolder;
		Thread ourThread;
		boolean isRunning = false;
		
		public MyBringBackSurface(Context context) {
			super(context);
			ourHolder = getHolder();
			ourThread = new Thread(this);
			ourThread.start();
		}
		
		public void pause() {
			isRunning = false;
			while(true) {
				try {
					ourThread.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				break;
			}
			ourThread = null;
		}
		
		public void resume() {
			isRunning = true;
			ourThread = new Thread(this);
			ourThread.start();		
		}

		public void run() {
			while(isRunning) {
				if(!ourHolder.getSurface().isValid())
					continue;
				
				Canvas canvas = ourHolder.lockCanvas();
				canvas.drawRGB(2, 2, 150);
				if(x != 0 && y != 0) {
					Bitmap test = BitmapFactory.decodeResource(getResources(), R.drawable.dot_mac_logo);
					canvas.drawBitmap(test, x-(test.getWidth()/2), y-(test.getHeight()/2), null);
				}
				ourHolder.unlockCanvasAndPost(canvas);
			}
		}

	}

}
