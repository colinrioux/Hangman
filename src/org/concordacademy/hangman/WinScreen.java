package org.concordacademy.hangman;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class WinScreen extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_winscreen);
	}
	
	public void returnToPlayScreen(View v) {
		Intent loseReturnToPlayScreen = new Intent(WinScreen.this, PlayScreen.class);
		startActivity(loseReturnToPlayScreen);
	}

}
