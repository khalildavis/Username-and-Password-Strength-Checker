package password;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class YourPasswordStrengthChecker {
	//to have them just in case
	static char[] symbols = "!@#$%^&*()-_=+[{]}|:;'<>.?/`~".toCharArray();
	static char[] lowercase = "abcdefghijklmnopqrstuvwxyz".toCharArray();
	static char[] uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
	static char[] numbers = "1234567890".toCharArray();
	static char[] allCharacters = "!@#$%^&*()-_=+[{]}|:;'<>.?/`~abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
	static Random random = new SecureRandom();
	//the typical length requirement for a strong password is between 8 and 26 (but I will be using 28)
	public static String printPasswordStrength(String input) {
		//checks the length of your password
		String passwordStrength = null;
		boolean hasLower = false;
		boolean hasUpper = false;
		boolean hasNum = false;
		boolean hasSpecial = false;
		//to define what a special character is
		Set<Character> specialSet = new HashSet<Character>(Arrays.asList('`', '~', '!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '_', '=', '+', '[', '{', ']', '}', ';', ':', '"', ',', '<', '.', '>', '/', '?'));
		//to loop over the password to check for other criteria like upper and lower letter and number
		for(char i: input.toCharArray()) {
			if(Character.isLowerCase(i)) {
				hasLower = true;
			}
			if(Character.isUpperCase(i)) {
				hasUpper = true;
			}
			if(Character.isDigit(i)) {
				hasNum = true;
			}
			if(specialSet.contains(i)) {
				hasSpecial = true;
			}
		}
		if(hasLower && hasUpper && hasNum && hasSpecial) {
			passwordStrength = "Strong";
			return passwordStrength;
		}
		else if(hasLower || hasUpper || hasSpecial) {
			passwordStrength = "Moderate";
			return passwordStrength;
		}
		else {
			passwordStrength = "Weak";
			return passwordStrength;
		}
	}
	//just in case a new, strong password must be generated
	public static String getPassword(int length) {
		//to make the program generate a password at least 8 characters long
		assert length >= 8;
		//to make the program generate a password no more than 28 characters long
		assert length <= 28;
		char[] password = new char[length];
		//to get the minimum requirements out the way
		password[0] = symbols[random.nextInt(symbols.length)];
		password[1] = lowercase[random.nextInt(lowercase.length)];
		password[2] = uppercase[random.nextInt(uppercase.length)];
		password[3] = numbers[random.nextInt(numbers.length)];
		//get rest of characters
		for(int i = 4; i < length; i++) {
			password[i] = allCharacters[random.nextInt(allCharacters.length)];
		}
		//to shuffle the characters in the password
		for(int i = 0; i < password.length; i++) {
			int randomPos = random.nextInt(password.length);
			char newPassword = password[i];
			password[i] = password[randomPos];
			password[randomPos] = newPassword;
		}
		return new String(password);
	}
	//typical length of a username is between 6 and 16 characters (strong ones usually have a number, uppercase letter, and symbol)
	public static String printUsernameStrength(String input) {
		String usernameStrength;
		boolean hasLower = false;
		boolean hasUpper = false;
		boolean hasNum = false;
		boolean hasSpecial = false;
		//to define what a special character is
		Set<Character> specialSet = new HashSet<Character>(Arrays.asList('`', '~', '!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '_', '=', '+', '[', '{', ']', '}', ';', ':', '"', ',', '<', '.', '>', '/', '?'));
		//to loop over the password to check for other criteria like upper and lower letter and number
		for(char i: input.toCharArray()) {
			if(Character.isLowerCase(i)) {
				hasLower = true;
			}
			if(Character.isUpperCase(i)) {
				hasUpper = true;
			}
			if(Character.isDigit(i)) {
				hasNum = true;
			}
			if(specialSet.contains(i)) {
				hasSpecial = true;
			}
		}
		if(hasLower && hasUpper && hasNum && hasSpecial && input.length() >= 6 && input.length() <= 16) {
			usernameStrength = "Strong";
			return usernameStrength;
		}
		else if(hasLower || hasUpper || hasSpecial || input.length() >= 6) {
			usernameStrength = "Moderate";
			return usernameStrength;
		}
		else {
			usernameStrength = "Weak";
			return usernameStrength;
		}
	}
	//in case a random, secure username must be generated
	public static String getUsername(int length) {
		//to make the program generate a username at least 6 characters long
		assert length >= 6;
		//to make the program generate a username no more than 16 characters long
		assert length <= 16;
		char[] username = new char[length];
		for(int i = 0; i < length; i++) {
			username[i] = allCharacters[random.nextInt(allCharacters.length)];
		}
		//randomizing method is the same as how it was in the password method
		for(int i = 0; i < username.length; i++) {
			int randomPos = random.nextInt(username.length);
			char newUsername = username[i];
			username[i] = username[randomPos];
			username[randomPos] = newUsername;
		}
		return new String(username);
	}
	public static void main(String[] args) {
		HashMap<String, String> credentials = new HashMap<String, String>();
		String user;
		String pass;
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter your username");
		user = scan.nextLine();
		System.out.println("Enter your password");
		pass = scan.nextLine();
		printUsernameStrength(user);
		printPasswordStrength(pass);
		if(printUsernameStrength(user) != "Strong") {
			System.out.println("Username is not strong enough; choose a number between 6 and 16 for new username");
			String newUser = scan.nextLine();
			int nu = Integer.parseInt(newUser);
			user = getUsername(nu);
		}
		if(printPasswordStrength(pass) != "Strong") {
			System.out.println("Password is not strong enough; choose a number between 8 and 28 for new username");
			String newPass = scan.nextLine();
			int np = Integer.parseInt(newPass);
			pass = getPassword(np);
		}
		credentials.put(user, pass);
		System.out.println("Usernames are: " + credentials.keySet() + System.lineSeparator());
		System.out.println("Passwords are: " + credentials.values());
	}
}
