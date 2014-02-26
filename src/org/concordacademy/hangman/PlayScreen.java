package org.concordacademy.hangman;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

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
	            words = stringBuilder.toString();
			}
			
		}
		catch (FileNotFoundException e) {
	        Log.e("login activity", "File not found: " + e.toString());
	    } catch (IOException e) {
	        Log.e("login activity", "Can not read file: " + e.toString());
	    }

	    return words;
	}

}
