package org.concordacademy.hangman;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
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
	boolean first;
	// Below is an array of the Letters already guessed.
	private ArrayList<Character> chosenLetters = new ArrayList<Character>();
	Random random = new Random();
    ImageView img;
    
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_playscreen);
		Log.i(PS, "Loading Play Screen.");
first = true;
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
		secretWord = wordLineArray.get(getRandomNumber(0, wordLineArray.size())).toCharArray();
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
		
		boolean wrong= true;
		for (int i = 0; i < secretWord.length; i++) {
			// Change Guess to CharArray and 0 Index.
			if (!guess.isEmpty()) {
				// This checks if the guess is correct.
				if (guess.toCharArray()[0] == secretWord[i]) {
					Log.i(PS, "Correct Guess");
					displayedWord[i] = guess.toCharArray()[0];
					wrong = false;
					
				} 
			}
		}
		if (wrong == true) {
			switchImage(null);
		}
		// Add Guess to the already chosen letter array
		if (!guess.isEmpty()) {
			chosenLetters.add(guess.toCharArray()[0]);
		}
		
		
	}
	
	//Image Method
	
    
	public void switchImage(View view) {
		img = (ImageView) findViewById(R.id.imageView1);
		if(first == true){
		img.setTag("0");
		first = false;
		}
        if (img.getTag() == "0") {
            img.setImageResource(R.drawable.gallows1);
            img.setTag("1");
            return;
        } else if (img.getTag() == "1") {
            img.setImageResource(R.drawable.gallows2);
            img.setTag("2");
            return;
        } else if (img.getTag() == "2") {
            img.setImageResource(R.drawable.gallows3);
            img.setTag("3");
            return;
        } else if (img.getTag() == "3") {
            img.setImageResource(R.drawable.gallows4);
            img.setTag("4");
            return;
        } else if (img.getTag() == "4") {
            img.setImageResource(R.drawable.gallows5);
            img.setTag("5");
            return;
        } else if (img.getTag() == "5") {
            img.setImageResource(R.drawable.gallows6);
            img.setTag("6");
            return;
        } else if (img.getTag() == "6") {
        	Intent pushToLoseScreen = new Intent(PlayScreen.this, LostScreen.class);
			startActivity(pushToLoseScreen);
			return;
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
		checkWin();
	}
	
	// The Series of Print outs let the console know that you won the game.
		public void checkWin() {
			Log.i(PS, "CheckWinWorking");
			int x = 0;
			for (int i = 0; i < displayedWord.length; i++) {
				if (!(displayedWord[i] == '-')) {
					x++;
				}
			}
			if (x == displayedWord.length) {
				Log.i(PS, "DisplayedWord equals SecretWord");
				Intent pushToWinScreen = new Intent(PlayScreen.this, WinScreen.class);
				startActivity(pushToWinScreen);
				
			} else {
				Log.i(PS, "DisplayedWord does not equal SecretWord");
			} 
		}
	
}
