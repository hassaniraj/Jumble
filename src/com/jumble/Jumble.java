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
		Scanner userInput = new Scanner(System.in);
		System.out.println("Enter your input String:");
		String input = userInput.nextLine();
		userInput.close();
		String result = "";
		Set<String> wordSet = myJumbler.readFile("F:\\Words.txt");
		myJumbler.getJumbledWords(result, input);
		List<String> validWordsList = myJumbler.getValidWords(wordSet);
		System.out.println("\nThe valid words are:");
		Iterator<String> validWordsIterator=validWordsList.iterator();
		while(validWordsIterator.hasNext()){
			System.out.println(validWordsIterator.next());
		}

	}
}