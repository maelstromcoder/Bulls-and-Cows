import java.util.Random;
import java.util.Scanner;

public class BullsAndCows {
	
//	Bulls and Cows is an old code-breaking mind game. For this question, you will write a Java program that
//	implements a game of Bulls and Cows in which the player needs to guess a randomly generated secret 4-
//	digits number. When the player takes a guess, the program reveals the number of digits that match with
//	the secret number. If the matching digits are in the correct positions, they are called “bulls”, ifthey
//	are in different positions, they are called “cows”. After each guess, the program reveals how many bulls
//	and cows the player’s guess contains

	static Scanner console = new Scanner(System.in);
	
	static int guessCount = 1;
	static int bullCounter = 0, cowCounter = 0;
	static int array[];
	
	static boolean first = true;
	static boolean error, error1 = false, error2 = false;
	static String username;

	static int[] guess = new int[4];
	static int[] tempArray = new int[4];
	
	public static void main(String[] args) {
		
//		1. Generate a random secret number to be guessed using generateSecretDigits() and the integer
//		received as input.
		playBullsAndCows((int)Math.random());
	}
	
//	Write a method playBullsAndCows() which takes an integer as the seed and simulates a game of
//	Bulls and Cows. The method creates one object of type Scanner to retrieve the inputs from the user
//	(remember that to use Scanner you should add the appropriate import statement at the beginning of
//	your file). The method should then do the following:
	
	public static void playBullsAndCows(int seed) {
			
			array = generateSecretDigits(seed);
			intro();
			replay();
	}
	
	public static void replay() {
		// Win if bull is 4 at end of replay loop
		win();
		// If guess is above 5, activate
		aboveFive();
		// Loop of game core logic
//		7. If the player did not guess the number nor decided to quit, then the method goes back to step
//		three.
		gameLogic();
		
	}	
	
	// Game introduction and username
	public static void intro() { 
//		2. Display a welcome message to the player. It is up to you to decide how to welcome the users of
//		your program.
		if (first == true)
		{
			System.out.println("Get ready to ruuuuuuuuuuuuumble!");
			System.out.print("Choose your username: ");
			username = console.next();
			first = false;
		}
	}
	
	// Win condition
//	5. If the player guessed the secret number, then the method congratulates the player and let them
//	know how many tries were needed to win the game
	public static void win() {
		if (bullCounter == 4) {
			System.out.println("Congratulations, you guessed the secret number!  It took you " + guessCount + " attempts.");
			System.exit(0);
		} 
	}
	
	// Above five guess condition
//	6. If the player has not guessed the secret number after 5 guesses, then the method should start to
//	ask the player if they want to quit the game at each incorrect guess. If the player replies with a
//	‘y’ (which is retrieved as a String) the method should displays a message acknowledging that the
//	player wanted to quit and letting them know how many times they tried to guess the secret number.
	public static void aboveFive() {
		if (guessCount > 5) {
			System.out.print("Do you want to quit the game? Answers: y/n ");
			String value = console.next();
			value = value.toLowerCase();
			if (value.equals("y") || value.equals("yes")) {
				guessCount--;
				System.out.println("You've decided to quit the game, after making " + guessCount + " attempts");
				System.exit(0);
			}
			else if (value.equals("n") || value.equals("no")) {
				gameLogic();
			}
			else {
				gameLogic();
			}
		}
	}
	
	// Game engine
	public static void gameLogic() {
//		3. Display a message encouraging the player to make a guess. The message should also display the
//		guess attempt.
		System.out.print("Guess #" + guessCount + ", " + play());
		
		
//		4. The method retrieves the guess as an integer. Here you can assume that the player will enter an
//		input of the correct type.
//		If the player inputs a negative integer or an integer with more than 5 digits, then the method
//		displays a message letting them know they wasted one guess and that they should enter a
//		positive integer with at most 4 digits.
//		• Otherwise, the method displays how many bulls and cows the player’s guess contains.
		try {
			String x = console.next();
			for (int y = 0; y < x.length(); y++) {
				char z = x.charAt(y);
				if (z >= 48 && z <= 57 && x.length() == 4) {
					guess[y] = Character.getNumericValue(z);
					// Temp array created to not permanently change the array guess when sorting
					tempArray[y] = Character.getNumericValue(z); 
				}
				else {
					error1 = true;
					throw new Exception("");
				}
			}
			if (sameNumber(tempArray) == true) {
				error2 = true;
				throw new Exception("");
			}
		}
			
		catch (Exception e) {
			if (error1 == true) {
				System.out.println("You must enter a four-digit number! You just wasted one guess!");
				guessCount++;
				error1 = false;
				replay();
			}
			else if (error2 == true) {
				System.out.println("You are not allowed to repeat the same number!");
				guessCount++;
				error2 = false;
				replay();
			}
		}
		
		// If bull = 4, win condition
		counting(array, guess);
		if (bullCounter == 4) {
			replay();
		}
			
		guessCount++;
		replay();
			
	
	}
	
	// Assortment of choices
	public static String quitters() {
		String[] array = {"Only cowards quit here! " + username + ", are you one? ", "How many guesses do you really need, " + username + "? ", "Google AI was a hoax and so are you... "
				, "Java is a fun language, isn't it? ", "Guesses, guesses and guesses? ", "Why are you even trying, " + username + "? ", "This answer consists of 32 bits of what? ", 
				"Fun fact, " + username + " is the name of my cat "};
		Random rnd = new Random();
		return array[rnd.nextInt(7)];
	}
	
	// Duplicate numbers
	public static boolean sameNumber(int[] same) {

		// Sort array manually in ascending order
		same = sort(same);
		
		for (int i = 1; 0 < same.length; i++) {
			if (same[i] == same[i-1]) {
				return true;
			}
		}
		return false;
		
	}
	
	// Method to sort array in manual order
	public static int[] sort(int[] same) {
		int minval = same[0];
		int temp=0;
		
		for(int i = 0; i< same.length; i++)
		{ 
		    for(int j = 0; j< same.length-1; j++)
		    {
		        if(same[j+1]<same[j])
		        {
		            temp=same[j+1];
		            same[j+1]=same[j];
		            same[j]=temp;  
		        }
		    }
		}
		return same;
	}

	// Repeat play instructions
	public static String play() {
		if (guessCount < 5) {
			return "Enter a four-digit number, each digit should be unique : ";
		}
		else {
			return quitters();
		}
	}
	
	// Check bulls, cows and increase guess count	
	public static void counting(int[] secret, int[] guess) {

//		Write a method called getNumOfBulls() that takes as input two integer arrays. The first one represents
//		the digits of the secret number, while the second one represents the digits of the number guessed by the
//		player. The method returns the number of bulls in the guess of the players (see the intro paragraph for
//		the definition of bull in this context). If the input arrays have a different number of elements, then the
//		method should throw an IllegalArgumentException with an appropriate message.
		
		System.out.println("Bulls are: " + getNumOfBulls(secret, guess));
//		Write a method called getNumOfCows() that takes as input two integer arrays. The first one represents
//		the digits of the secret number, while the second one represents the digits of the number guessed by the
//		player. The method returns the number of cows in the guess of the players (see the intro paragraph for
//		the definition of cow in this context). If the input arrays have a different number of elements, then the
//		method should throw an IllegalArgumentException with an appropriate message.		
		
		System.out.println("Cows are: " + getNumOfCows(secret, guess));
	}
	
	// Get the bulls for the game
	public static String getNumOfBulls(int secret[], int guess[]) {
		bullCounter=0;
		
		for (int i = 0; i < secret.length; i++) {
			if (secret[i] == guess[i])
			{
				bullCounter++;
			}
		}
		return "" + bullCounter;
	}
	
	// Get the cows for the game
	public static String getNumOfCows(int[] secret, int[] guess) {
		if (bullCounter < 4) {
			cowCounter = 0;
			
			for (int i = 0; i < secret.length; i++) {
				for (int x = secret.length-1; x >= 0; x--) {
					if (secret[i] == guess[x]) {
						if (i != x) {
							cowCounter++;
						}
					}
				}
			}
			return "" + cowCounter;
		}
		else {
			return "0";
		}
	}
		
	// Verifies if the numbers are in the array
	public static boolean contains(int[] array, int x) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == x) {
				return true;
			}
		}
		return false;
	}
	
	// Generate 4 random numbers numbers based off seed
//	Write a method called generateSecretDigits() that takes as input an integer and returns an array
//	of integers containing the 4 digits that make up the randomly generated secret number. The method
//	uses the input to create an object of type Random with the provided seed (remember that to use Random
//	you should add the appropriate import statement at the beginning of your file). The method then uses
//	such object to generate a random integer between 0 and 9 (both included). If the integer is not already
//	part of the array, then the method uses it to initialize one of the elements of the array, otherwise a new
//	integer is generated. The process continues until all four elements of the array have been generated.
//	This allows us to generate an array containing four digits that are all different from one another. Make
//	sure to initialize the elements of the array from the first one to the last one
	public static int[] generateSecretDigits(int seed) {
		
		Random rnd = new Random();
		rnd.setSeed(seed);
		int digits[] = new int[4];
		
		for (int i = 0; i < digits.length; i++) {
			digits[i] = rnd.nextInt(10);
		}
		return digits;
	}
}
