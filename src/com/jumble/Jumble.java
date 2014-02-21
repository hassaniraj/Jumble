package com.jumble;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Jumble {
	private static Set<String> generatedWordset = new HashSet<String>();

	/**
	 * Function to read an input file and generate a word set containing all the
	 * valid words from the file
	 * 
	 * @param fileName
	 * @return Set<String> a set of words from the given file
	 */
	public Set<String> readFile(String fileName) {
		Set<String> wordSet = new HashSet<String>();
		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				wordSet.add(line);
			}
			bufferedReader.close();
		} catch (FileNotFoundException fnot) {
			fnot.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return wordSet;
	}

	/**
	 * Function to get the jumbled words for the given input
	 * 
	 * @param result
	 * @param input
	 */
	public void getJumbledWords(String result, String input) {
		if (input.length() == 0) {
			return;
		}
		for (int i = 0; i < input.length(); i++) {
			String stringLeft = input.substring(0, i) + input.substring(i + 1);
			String word = result + input.charAt(i);
			generatedWordset.add(word);
			getJumbledWords(word, stringLeft);
		}
	}

	/**
	 * Function to get the valid words from the generated combinations of words
	 * by comparing from the global file
	 * 
	 * @param wordSet
	 * @return
	 */
	public List<String> getValidWords(Set<String> wordSet) {
		List<String> validWords = new ArrayList<>();
		Iterator<String> generatedWordsIterator = generatedWordset.iterator();
		while (generatedWordsIterator.hasNext()) {
			String word = generatedWordsIterator.next();
			if (wordSet.contains(word)) {
				validWords.add(word);
			}
		}
		return validWords;
	}

	public static void main(String[] args) {
		Jumble myJumbler = new Jumble();
		// Check for proper number of arguments
		if (args.length != 1) {
			System.out.println("The number of arguments are not valid");
			System.exit(0);
		} else {
			Scanner userInput = new Scanner(System.in);
			System.out.println("Enter your input String:");
			String input = userInput.nextLine();
			// Check if the input is not empty or null
			if (input.equalsIgnoreCase("") || input == null) {
				System.out.println("Not a valid input");
				System.exit(0);
			} else {
				// Check if the input is not a digit
				for (int i = 0; i < input.length(); i++) {
					if (Character.isDigit(input.charAt(i))) {
						System.out.println("Not a valid input");
						System.exit(0);
					}
				}

			}
			// If valid input
			userInput.close();
			String result = "";
			Set<String> wordSet = myJumbler.readFile(args[0]);
			myJumbler.getJumbledWords(result, input);
			List<String> validWordsList = myJumbler.getValidWords(wordSet);
			if (!validWordsList.isEmpty()) {
				System.out.println("\nThe valid words are:");
				Iterator<String> validWordsIterator = validWordsList.iterator();
				while (validWordsIterator.hasNext()) {
					System.out.println(validWordsIterator.next());
				}
			}

		}

	}
}