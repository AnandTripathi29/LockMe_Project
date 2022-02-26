package package1;

import java.io.File;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class FileUser {

	private String userName;
	private ArrayList<String> userfiles;

	// Constructor
	public FileUser(String folderName) {
		File folder;
		userName = folderName;
		userfiles = new ArrayList<>();

		folder = new File(userName);
		if (!folder.isDirectory())
			folder.mkdir();
		else {
			File[] files = folder.listFiles();
			for (File file : files) {
				userfiles.add(file.getName());
			}
		}

	}

	// Methods
	public static boolean verifyUser(String userName) {
		// Find the username in the root directory
		File userFile = new File(userName);
		if (userFile.exists())
			return true;

		return false;
	}

	public void searchFile() {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the filename to be searched");

		String fileName = input.next();
		File file = new File(userName + "\\" + fileName);
		if (file.exists()) {
			System.out.println("File is present in the folder");
			LockerMain.userAction(userName);
		} else {
			System.out.println("File not found");
			LockerMain.userAction(userName);
		}
		input.close();
	}

	public void displayFiles() {
		File folder = new File(userName);
		File[] files = folder.listFiles();

		System.out.println("Below files are present : ");
		// iterate the files
		for (File file : files) {
			System.out.println(file.getName());
		}
		// System.out.println(userfiles);
		userfiles.sort(null);
		Iterator<String> iterator = userfiles.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}

	public void deleteFile() {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the filename to be deleted");

		String fileName = input.next();
		File file = new File(userName + "\\" + fileName);
		if (file.exists()) {
			file.delete();
			System.out.println("File deleted successfully");
		} else
			System.out.println("File not found");

		input.close();
	}

	public void createFile() {

		FileOutputStream fileOut = null;
		Scanner input = new Scanner(System.in);

		System.out.println("Enter the file name");
		String fileName = input.nextLine();
		String filePath = userName + "//" + fileName + ".txt";
		userfiles.add(fileName);// Add file to the array list
		System.out.println(userfiles);
		try {
			File newFile = new File(filePath);
			// if (!newFile.exists())
			// System.out.println(newFile.createNewFile());
			fileOut = new FileOutputStream(newFile);
			System.out.println("Enter the text in file");
			String fileText = input.nextLine();

			fileOut.write(fileText.getBytes());

			fileOut.close();
			System.out.println("File written successfully");
			LockerMain.userAction(userName);

		} catch (Exception e) {
			e.printStackTrace();
		}

		input.close();
	}

}
