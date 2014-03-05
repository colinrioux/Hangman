package org.concordacademy.hangman;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {

	private final String MA = "Main Activity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.i(MA, "Loading Title Screen.");
	}

	// This Below Starts the Intent Push to the "Play Screen"

	public void playGame(View v) {
		Log.i(MA, "Starting Game");
		Intent pushToPlayScreen = new Intent(MainActivity.this,
				PlayScreen.class);
		startActivity(pushToPlayScreen);

	}

	@Override
	public void onStart() {
		super.onStart();
		Log.i(MA, "onStart");

	}

	@Override
	public void onResume() {
		super.onResume();
		Log.i(MA, "onResume");
	}

	@Override
	public void onPause() {
		super.onPause();
		Log.i(MA, "onPause");
	}

	@Override
	public void onStop() {
		super.onStop();
		Log.i(MA, "onStop");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.i(MA, "onDestroy");
	}

}