package org.concordacademy.hangman;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class PlayScreen extends Activity {

	// The String Below will tell Console/LogCat the processes of The PlayScreen Activity

	private final String PS = "Play Screen";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_playscreen);
		Log.i(PS, "Loading Play Screen.");
	}

}
