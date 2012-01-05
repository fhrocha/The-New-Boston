package com.kritsolutions.travis;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Camera extends Activity implements View.OnClickListener {

	ImageButton imageButton;
	Button button;
	ImageView imageView;
	Intent intentCamera;
	final static int cameraData = 0;
	Bitmap bitmap;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.photo);
		initialize();
		InputStream is = getResources().openRawResource(R.drawable.ic_launcher);
		bitmap = BitmapFactory.decodeStream(is);
	}

	private void initialize() {
		
		imageView = (ImageView) findViewById(R.id.ivReturnedPicture);
		
		imageButton = (ImageButton) findViewById(R.id.ibTakePicture);
		imageButton.setOnClickListener(this);
		
		button = (Button) findViewById(R.id.bSetWallPaper);
		button.setOnClickListener(this);
	}

	public void onClick(View v) {
		
		switch (v.getId()) {
		case R.id.bSetWallPaper:
			try {
				getApplicationContext().setWallpaper(bitmap);
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		
		case R.id.ibTakePicture:
			intentCamera = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
			startActivityForResult(intentCamera, cameraData);
			break;
		default:
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == RESULT_OK) {
			Bundle extras = data.getExtras();
			bitmap = (Bitmap)extras.get("data");
			imageView.setImageBitmap(bitmap);
		}
	}	
}
