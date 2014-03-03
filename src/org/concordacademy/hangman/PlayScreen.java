package org.concordacademy.hangman;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class PlayScreen extends Activity {

	// The String Below will tell Console/LogCat the processes of The PlayScreen Activity

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
				InputStream inputstream = openFileInput("wordsEn.txt");
				if (inputstream != null) {
					InputStreamReader inputStreamReader = new InputStreamReader(inputstream);
					BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
					String receiveString = "";
					StringBuilder stringBuilder = new StringBuilder();
					
					while ( (receiveString = bufferedReader.readLine()) != null ) {
						wordLineArray.add(receiveString);
		                stringBuilder.append(receiveString);
		            }
					inputstream.close();
					// Possible pointless code below
		            words = stringBuilder.toString();
				}
				
			}
			catch (FileNotFoundException e) {
		        Log.e("login activity", "File not found: " + e.toString());
		    } catch (IOException e) {
		        Log.e("login activity", "Can not read file: " + e.toString());
		    }
			
			//R Generator for Strings in wordLineArray 
			//String secretWordString = wordLineArray.get(getRandomNumber(0, wordLineArray.size()));
			String secretWordString = "HelloWorld";
			secretWord = secretWordString.toCharArray();
			for (int i = 0; i < secretWord.length; i++) {
				displayedWord[i] = '-';
			}
		    return words;
		  
		}
		
		// Choose a random number that is assigned to a corresponding String in ArrayList
		
		public int getRandomNumber(int min, int max) {
			int number = min + (int)(Math.random() * ((max - min) + 1));
			return number;
				
		}
		
		public void startGame() {
			//readFromFile();
			String secretWordString = "HelloWorld";
			secretWord = secretWordString.toCharArray();
			displayedWord = new char[secretWord.length];
			for (int i = 0; i < secretWord.length; i++) {
				displayedWord[i] = '-';
			}

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
		
		public boolean checkWin() {
			if (displayedWord == secretWord) {
				return true;
			} else {
				return false;
			}
		}
		
		public void guessButtonClick(View v) {
			TextView displayText = (TextView) findViewById(R.id.displayedWord);
			displayText.setText(displayedWord.toString());
			EditText inputGuess = (EditText) findViewById(R.id.textField);
			String guess = inputGuess.getText().toString();
			findLetters(guess);
			
		}
		
		

		
		
		
		
		

}
