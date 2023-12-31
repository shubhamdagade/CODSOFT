//Task1-Number Game

package Tasks;

import java.util.*;

public class Task1_NumberGame 
{
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		Random r = new Random();
		int a = r.nextInt(100);
		int n = 10; // no of attempts
		int i;
		int score = 0;
		boolean playAgain = true;

		System.out.println("Welcome to the Number Game!");

		while (playAgain) 
		{
			a = r.nextInt(100);
			boolean guessedCorrectly = false;

			System.out.println("\nYou have only " + n + " Attempts to Guess Number.");
			System.out.println("All the best!");

			for (i = 0; i < n; i++) 
			{
				System.out.println("\nEnter the Number you want to guess: ");
				int guess = sc.nextInt();
				if (a == guess && n != i) 
				{
					System.out.println("Congratulations!!");
					System.out.println("You gussed the correct number");
					score++;
					guessedCorrectly = true;
					break;
				} 
				else if (a > guess) 
				{
					System.out.println("Too Low! Try Again.");
				} 
				else if (a < guess)
				{
					System.out.println("Too High! Try Again.");
				}
			}
			if (!guessedCorrectly)
			{
				System.out.println("\nSorry, Attempts are ended.");
				System.out.println("The correct number is: " + a);
			}

			System.out.println("Your current score: " + score);
			System.out.print("\nDo you want to Play Again? (yes/no): ");
			String playAgainInput = sc.next().toLowerCase();
			playAgain = playAgainInput.equals("yes");

		}

		System.out.println("\nThank You for Playing!!");

	}

}


