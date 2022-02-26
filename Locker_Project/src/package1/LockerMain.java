package package1;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

//import
public class LockerMain {
	public static void main(String[] args) {
		// 1.Display Welcome Screen with developer details
		displayWelcomeScreeen();
		// 2.Ask for user information
		signIn();
		
		

	}

	public static void signIn() {
		String userName;
		int opt;
		FileUser fileUser;

		Scanner input = new Scanner(System.in);
		try {
			//do {
				System.out.println("Enter your option:");
				System.out.println("1. Registed as a new user?");
				System.out.println("2. Login an existing user.");
				System.out.println("3. Exit");

				opt = Integer.parseInt(input.nextLine());
				
				if (opt == 1) {
					System.out.println("Enter user name to be registered:");
					userName = input.next();
					// Validate if user exists
					boolean userExists = FileUser.verifyUser(userName);
					if (userExists) {
						System.out.println("The given user name already exists. Please use another username");
						signIn();
					} else {
						userRegistration(userName);
					}
				} else if (opt == 2) {
					System.out.println("Enter your user name:");
					userName = input.next();
					// Validate if the user exists
					boolean userExists = FileUser.verifyUser(userName);
					if (!userExists) {
						System.out.println("The given user name does not exist. Please use correct username");
		//				input.close();
						userRegistration(userName);
					} else {
		//				input.close();
						userLogin(userName);
					}
				}
				else if(opt==3)
					exit();

			//} while (opt != 3);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void userAction(String userName) {
		Scanner cin = new Scanner(System.in);

		System.out.println("Do you want to process further? Select Y/N");
		if (cin.hasNext()) {
			String value = cin.nextLine();
			// cin.nextLine();
			char ch = value.charAt(0);
			if ((ch == 'Y') || (ch == 'y'))
				userLogin(userName);
			// cin.close();
			else
				exit();
		}
	}

	public static void userLogin(String folderName) {
		Scanner input = new Scanner(System.in);
		FileUser fileUser;

		fileUser = new FileUser(folderName);

		System.out.println("Choose the actions from the below options");
		System.out.println("1. List all the files");
		System.out.println("2. Enter a new file");
		System.out.println("3. Search the file");
		System.out.println("4. Delete an existing file");
		

		int opt = Integer.parseInt(input.nextLine());
		if (opt == 1) {
			fileUser.displayFiles();
		} else if (opt == 2) {
			fileUser.createFile();
		} else if (opt == 3) {
			fileUser.searchFile();
		} else if (opt == 4) {
			fileUser.deleteFile();
		}
		//input.close();
		
		userAction(folderName);
	}

	public static void userRegistration(String user) {
		String userName;
		//FileUser fileUser;

		//Scanner input = new Scanner(System.in);

		//System.out.println("Enter user name to be registered:");
		userName = user;
		// Validate if user exists
		boolean userExists = FileUser.verifyUser(userName);
		if (userExists) {
			System.out.println("The given user name already exists. Please use another username");
			userRegistration(userName);
		} else {
			// Display the files present with the users
			new FileUser(userName);
			System.out.println("User registration successful");
			userLogin(userName);
		}

	}

	public static void displayWelcomeScreeen() {
		System.out.println("Welcome to the Lockers Pvt Ltd");
		System.out.println("\n\n********************************************");
		System.out.println("\nDeveloper: Anand Prakash Tripathi");
		System.out.println("\n\n********************************************");

	}
	
	public static void exit() {
		System.out.println("Thank you for logging in");
	}
}
