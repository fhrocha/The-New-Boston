package com.kritsolutions.travis;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;

public class SoundStuff extends Activity implements OnClickListener, OnLongClickListener {

	SoundPool sP;
	int explosion = 0;
	MediaPlayer mP;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		View v = new View(this);
		v.setOnClickListener(this);
		v.setOnLongClickListener(this);
		setContentView(v);
		sP = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
		explosion = sP.load(this, R.raw.splashsound, 1);
		mP = MediaPlayer.create(this, R.raw.splashsound);
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(explosion != 0) {
			sP.play(explosion, 1, 1, 0, 0, 1);
		}
	}

	public boolean onLongClick(View v) {
		mP.start();
		return false;
	}

}
