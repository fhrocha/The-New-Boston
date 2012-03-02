package com.kritsolutions.travis;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class StatusBar extends Activity implements OnClickListener {

	private NotificationManager nm;
	private static final int uniqueID = 1882771;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.statusbar);
		Button stat = (Button)findViewById(R.id.bStatusBar);
		stat.setOnClickListener(this);
		
		nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		nm.cancel(uniqueID);
	}

	public void onClick(View v) {
		
		Intent intent = new Intent(this, StatusBar.class);
		PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
		String body = "This is a message";
		String title = "Sample Title";
		
		Notification n = new Notification(R.drawable.lightning, body, System.currentTimeMillis());
		n.setLatestEventInfo(this, title, body, pi);
		n.defaults = Notification.DEFAULT_ALL;
		nm.notify(uniqueID, n);
		
		finish();
	}

}
