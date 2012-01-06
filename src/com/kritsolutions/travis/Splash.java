package com.kritsolutions.travis;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class Splash extends Activity {
	
	MediaPlayer ourSong;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		this.ourSong = MediaPlayer.create(Splash.this, R.raw.splashsound);
		SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		if(playMusic(getPrefs)) {
			this.ourSong.start();
		}
		Thread timer = new Thread() {
			@Override
			public void run() {
				try {
					sleep(4000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					Intent openStartingPoint = new Intent("com.kritsolutions.travis.MENU");
					startActivity(openStartingPoint);
				}
			}
		};
		timer.start();
	}

	private boolean playMusic(SharedPreferences getPrefs) {
		return getPrefs.getBoolean("checkBox", true);
	}

	@Override
	protected void onPause() {
		super.onPause();
		this.ourSong.release();
		finish();
	}
}
