// Task2_Word Counter

package Tasks;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Task2_WordCounter {
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
        String inputText = "";

        System.out.println("Welcome to Word Counter");
        System.out.println("1. Enter '1' to input text manually.");
        System.out.println("2. Enter '2' to provide a file path to read text from a file.");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            System.out.print("Enter the text: ");
            inputText = scanner.nextLine();
       } 
            else if (choice == 2) {
            System.out.print("Enter the file path: ");
            String filePath = scanner.nextLine();
            try {
                File file = new File(filePath);
                Scanner fileScanner = new Scanner(file);
                while (fileScanner.hasNextLine()) {
                    inputText += fileScanner.nextLine() + " ";
                }
                fileScanner.close();
            } catch (FileNotFoundException e) {
                System.out.println("File not found.");
                System.exit(1);
            }
        } 
            else {
            System.out.println("Invalid choice. \nExiting program.");
            System.exit(1);
        }
        scanner.close();

        Map<String, Integer> wordCount = countWords(inputText);

        int totalWords = 0;
        for (int count : wordCount.values()) {
            totalWords += count;
        }
        System.out.println("Total words: " + totalWords);

        int uniqueWords = wordCount.size();
        System.out.println("Unique words: " + uniqueWords);

        System.out.println("Word frequency:");
        for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static Map<String, Integer> countWords(String text) {
        Map<String, Integer> wordCount = new HashMap<>();

        String[] words = text.split("[\\s\\p{Punct}]+");

        Set<String> stopWords = new HashSet<>();


        for (String word : words) {
            if (!stopWords.contains(word.toLowerCase())) {
                wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
            }
        }

        return wordCount;
	}

}
