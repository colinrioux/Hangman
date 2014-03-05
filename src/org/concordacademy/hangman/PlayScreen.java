package org.concordacademy.hangman;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class PlayScreen extends Activity {
	/*
	 * Before we Begin, I would like to thank Sam Lazarus for debugging my code
	 * and helping me find the errors. It ended up being just the order of the
	 * code written Monday Night.
	 */
	/*
	 * I would also like to Thank Zach Yeddia for helping me code some of it
	 * under your provision.
	 */
	// The String Below will tell Console/LogCat the processes of The PlayScreen
	// Activity

	private final String PS = "Play Screen";
	private char[] secretWord;
	private char[] displayedWord;
	// Below is an array of the Letters already guessed.
	private ArrayList<Character> chosenLetters = new ArrayList<Character>();
	Random random = new Random();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_playscreen);
		Log.i(PS, "Loading Play Screen.");

		startGame();
	}

	// Read Text File entitled wordsEn.txt
	public String readFromFile() {
		String words = "";
		// Array List That Words being added to
		ArrayList<String> wordLineArray = new ArrayList<String>();

		try {
			InputStream inputstream = getResources().openRawResource(
					R.raw.words);
			if (inputstream != null) {
				InputStreamReader inputStreamReader = new InputStreamReader(
						inputstream);
				BufferedReader bufferedReader = new BufferedReader(
						inputStreamReader);
				String receiveString = "";
				StringBuilder stringBuilder = new StringBuilder();

				while ((receiveString = bufferedReader.readLine()) != null) {
					// wordLineArray defined and filled. **Array**
					wordLineArray.add(receiveString);
					stringBuilder.append(receiveString);
				}
				inputstream.close();
				// Possible pointless code below
				words = stringBuilder.toString();
			}

		} catch (FileNotFoundException e) {
			Log.e("login activity", "File not found: " + e.toString());
		} catch (IOException e) {
			Log.e("login activity", "Can not read file: " + e.toString());
		}

		// R Generator for Strings in wordLineArray
		secretWord = wordLineArray
				.get(getRandomNumber(0, wordLineArray.size())).toCharArray();
		// Put displayedWord up here. Needed to be defined in order to run
		// readFromFile() Below.
		displayedWord = new char[secretWord.length];
		for (int i = 0; i < secretWord.length; i++) {
			displayedWord[i] = '-';
		}
		TextView displayText = (TextView) findViewById(R.id.displayedWord);
		displayText.setText(String.valueOf(displayedWord));
		return words;

	}

	// Choose a random number that is assigned to a corresponding String in
	// ArrayList

	public int getRandomNumber(int min, int max) {
		int number = min + (int) (Math.random() * ((max - min) + 1));
		return number;

	}

	public void startGame() {
		// Running the function @ the Start of the game in onCreate(). This
		// removes repeats of code.
		readFromFile();
	}

	public void findLetters(String guess) {
		for (int i = 0; i < secretWord.length; i++) {
			// Change Guess to CharArray and 0 Index.
			if (!guess.isEmpty()) {
				if (guess.toCharArray()[0] == secretWord[i]) {
					Log.i(PS, "Correct Guess");
					displayedWord[i] = guess.toCharArray()[0];
				}
			}
		}
		// Add Guess to the already chosen letter array
		if (!guess.isEmpty()) {
			chosenLetters.add(guess.toCharArray()[0]);
		}
	}

	// The Series of Print outs let the console know that you won the game.
	public boolean checkWin() {
		if (displayedWord == secretWord) {
			return true;
		} else {
			return false;
		}
	}

	public void guessButtonClick(View v) {
		TextView displayText = (TextView) findViewById(R.id.displayedWord);
		EditText inputGuess = (EditText) findViewById(R.id.textField);
		// Its an error only because toLowerCase is awkward with a string.
		// Program still runs.
		String guess = inputGuess.getText().toString().toLowerCase();
		findLetters(guess);
		displayText.setText(String.valueOf(displayedWord));
		Log.i(PS, String.valueOf(displayedWord));
		Log.i(PS, String.valueOf(secretWord));
		inputGuess.setText("");
	}

	// Method to change imageView to gallows images.

	public void switchImage(View view) {
		ImageView img = (ImageView) findViewById(R.id.imageView1);
		img.setTag("0");
		if (img.getTag() == "0") {
			img.setImageResource(R.drawable.gallows1);
			img.setTag("1");
		} else if (img.getTag() == "1") {
			img.setImageResource(R.drawable.gallows2);
			img.setTag("2");
		} else if (img.getTag() == "2") {
			img.setImageResource(R.drawable.gallows3);
			img.setTag("3");
		} else if (img.getTag() == "3") {
			img.setImageResource(R.drawable.gallows4);
			img.setTag("4");
		} else if (img.getTag() == "4") {
			img.setImageResource(R.drawable.gallows5);
			img.setTag("5");;
		} else if (img.getTag() == "5") {
			img.setImageResource(R.drawable.gallows6);
			img.setTag("6");
		} else if (img.getTag() == "6") {
			return;
		}

	}

}
