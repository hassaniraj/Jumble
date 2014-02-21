					ReadMe

1.The the main program is Jumble.java.
2.The program takes the wordlist file(absolute path should be provided) as input and reads the words line by line from the file. It generates a Set containing all the valid words. 
The program generates combinations of all the words of the input and compares it with the given wordlist.
3.The output is the set of valid words after comparing the words from the word list provided.

Input Validations:-
The input string should not contain any digits(Assumption)

Functions:-
public Set<String> readFile(String fileName):-
	Reads a file with the given filename and generates a set which a wordlist from the given file.


public void getJumbledWords(String result, String input):-
        Gets the jumbled words from the input. It generates all the combinations of words from the string input. The words generated are stored in generatedWordset which is a global set of Strings.


public List<String> getValidWords(Set<String> wordSet):-
	This function generates and returns a list of valid words by comparing the generated words with the wordlist provided.


How to run the program:-
Compile:-
	javac Jumble.java pathtofile
	eg:- javac Jumble.java F:\\Words.txt

Running:-
	java Jumble.java


Sample Output:-
Enter your input String:
your

The valid words are:
or
your
you
our
