import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class BankingSystem {

	public static void Write(LinkedList ls) {
		try (FileOutputStream fs = new FileOutputStream("userdoc.bin")) {

			ObjectOutputStream os = new ObjectOutputStream(fs);

			os.writeObject(ls);

		} catch (FileNotFoundException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch
			e.printStackTrace();
		}

	}

	public static void displayDetails() throws ClassNotFoundException {

		try (FileInputStream fi = new FileInputStream("userdoc.bin")) {
			ObjectInputStream ois = new ObjectInputStream(fi);

			LinkedList<Users> ls = (LinkedList) ois.readObject();

			for (Object item : ls) {
				System.out.println(item.toString() + "\n");
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {

		// DEBUG: Reset file to empty list
		// saveToFile(new LinkedList());

		LinkedList accounts = loadFromFile();

		System.out.println("Number of accounts: " + accounts.size());

		accounts = createAccount(accounts, "Bob Marley", 1500, "Junior");
		accounts = createAccount(accounts, "John Doe", 2000, "Adult");
		accounts = createAccount(accounts, "Mark Smith", 2000, "Adult");

		// displayAccountBalance(accounts, 4518, "hklvb");
		System.out.println();
		saveToFile(accounts);

		boolean quit = false;
		do {
			System.out.println("1. Display Account Details");
			System.out.println("2. Deposit money");
			System.out.println("3. Withdraw money");
			System.out.println("4. Check balance");
			System.out.println("5. Change password");
			System.out.println("0. to quit: \n");
			System.out.print("Please enter your the number of the chosen function: ");
			Scanner number = new Scanner(System.in);
			try {
				int userChoice = number.nextInt();
				switch (userChoice) {
				case 1:
					System.out.println("Please enter your account number: ");
					try {
						int accn = number.nextInt();
						System.out.println("Please enter your password: ");
						String pw = number.next();
						checkAccountNumber(accounts, accn, pw);
					} catch (InputMismatchException e) {
						System.out.println("Input Mismatch! Please enter Numbers\n");

					}
					break;
				case 2:
					System.out.println("Please enter your account number: ");
					try {
						int accn = number.nextInt();
						System.out.println("Please enter your password: ");
						String pw = number.next();
						DepositMoney(accounts, accn, pw);
					} catch (InputMismatchException e) {
						System.out.println("Input Mismatch! Please enter Numbers\n");

					}
					break;

				case 3:
					System.out.println("Please enter your account number: ");
					try {
						int accn = number.nextInt();
						System.out.println("Please enter your password: ");
						String pw = number.next();
						withDrawMoney(accounts, accn, pw);
					} catch (InputMismatchException e) {
						System.out.println("\n Input Mismatch! Please enter Numbers\n");

					}

					break;

				case 4:
					System.out.println("Please enter your account number: ");
					try {
						int accn = number.nextInt();
						System.out.println("Please enter your password: ");
						String pw = number.next();
						displayAccountBalance(accounts, accn, pw);
					} catch (InputMismatchException e) {
						System.out.println("\n Input Mismatch! Please enter Numbers\n");

					}
					break;

				case 5:
					System.out.println("Please enter your account number: ");
					try {
						int accn = number.nextInt();
						System.out.println("Please enter your password: ");
						String pw = number.next();
						first: {
							System.out.println("Please enter your new password (minimum 6, maximum 10 character): ");
							String newpw = number.next();
							if (newpw.length() < 6 || newpw.length() > 10) {
								break first;
							} else {
								ChangePassword(accounts, accn, pw, newpw);
							}
						}

					} catch (InputMismatchException e) {
						System.out.println("\n Input Mismatch! Please enter Numbers\n");
					}
					break;

				case 0:
					System.out.println("Goodbye!");
					quit = true;
					break;

				default:
					System.out.println("Unknown choice, try again");
				}
			} catch (InputMismatchException e) {
				System.out.println("Input Mismatch! Please enter Numbers\n");

			}
		} while (!quit);

		saveToFile(accounts);

	}

	private static void checkAccountNumber(LinkedList accounts, int idToSearch, String pwToSearch) {
		boolean exists = false;

		for (Object currentID : accounts) {
			if (((Users) currentID).getId() == idToSearch && ((Users) currentID).getPassword() == pwToSearch) {
				// System.out.println(currentID.toString() + "\n");
				System.out.println("Name: " + ((Users) currentID).getName() + "\nBalance: "
						+ ((Users) currentID).getBalance() + "\nAccount ID: " + ((Users) currentID).getId()
						+ "\nAccount name: " + ((Users) currentID).getAccount_name() + "\nYour pw is: "
						+ ((Users) currentID).getPassword() + "\n");
				exists = true;
			}

		}

		if (exists == false) {
			System.out.println("The Id you entered and password: " + idToSearch + " " + pwToSearch + " are invalid.");
		}
	}

	private static void displayAccountBalance(LinkedList accounts, int idToSearch, String pwToSearch) {

		boolean exists = false;

		for (Object currentID : accounts) {
			if (((Users) currentID).getId() == idToSearch && ((Users) currentID).getPassword() == pwToSearch) {
				System.out.println("Your current balance Mr/Mrs.: " + ((Users) currentID).getName() + " is "
						+ ((Users) currentID).getBalance() + " €.");
				
				exists = true;
			}

		}
		if (exists == false) {
			System.out.println("The Id you entered and password: " + idToSearch + " " + pwToSearch + " are invalid.");
		}

	}

	private static void withDrawMoney(LinkedList accounts, int idToSearch, String pwToSearch) {

		boolean exists = false;

		for (Object currentID : accounts) {
			if (((Users) currentID).getId() == idToSearch && ((Users) currentID).getPassword() == pwToSearch) {
				System.out.println(
						"Please enter the amount you would like to withdraw Mr/Mrs.: " + ((Users) currentID).getName());
				
				exists = true;

			}
		}
		if (exists == false) {
			System.out.println("The Id you entered and password: " + idToSearch + " " + pwToSearch + " are invalid.");
		}
		
		Scanner dinero = new Scanner(System.in);
		int money = dinero.nextInt();
		for (Object currentBalance : accounts) {
			if (((Users) currentBalance).getBalance() >= money) {
				((Users) currentBalance).setBalance(((Users) currentBalance).getBalance() - money);
				System.out.println("Your new balance Mr/Mrs.: " + ((Users) currentBalance).getName() + " is "
						+ ((Users) currentBalance).getBalance());
			} else {
				System.out.println("Insufficient coverage.\n");
			}
		}
	}

	private static void DepositMoney(LinkedList accounts, int idToSearch, String pwToSearch) {

		boolean exists = false;

		for (Object currentID : accounts) {
			if (((Users) currentID).getId() == idToSearch && ((Users) currentID).getPassword() == pwToSearch) {
				System.out.println(
						"Please enter the amount you would like to deposit Mr/Mrs.: " + ((Users) currentID).getName());
				
				exists = true;

			}
		}
		if (exists == false) {
			System.out.println("The Id you entered: " + idToSearch + " is invalid.");
		}

		Scanner dinero = new Scanner(System.in);
		int money = dinero.nextInt();
		for (Object currentBalance : accounts) {
			((Users) currentBalance).setBalance(((Users) currentBalance).getBalance() + money);
			System.out.println("Your new balance Mr/Mrs.: " + ((Users) currentBalance).getName() + " is "
					+ ((Users) currentBalance).getBalance());

		}
	}

	private static void ChangePassword(LinkedList accounts, int idToSearch, String pwToSearch, String newPassword) {
		boolean exists = false;

		for (Object currentID : accounts) {
			if (((Users) currentID).getId() == idToSearch && ((Users) currentID).getPassword() == pwToSearch) {
				if (newPassword.length() >= 6 && newPassword.length() <= 10) {
					String newpw = ((Users) currentID).setPassword(newPassword);

					System.out.println("Your new pw Mr./Mrs. " + ((Users) currentID).getName() + " is "
							+ ((Users) currentID).getPassword() + "\n");
					
				} else {
					System.out.println("The length of the password is not between 6 and 10 characters\n");

				}
				exists = true;
			}

		}
		if (exists == false) {
			System.out.println("The Id and/or the password you entered: " + idToSearch + " " + pwToSearch + " are invalid.");
		}
	}

	private static void saveToFile(LinkedList accounts) {
		try (FileOutputStream fs = new FileOutputStream("userdoc.bin")) {

			ObjectOutputStream os = new ObjectOutputStream(fs);

			os.writeObject(accounts);

		} catch (FileNotFoundException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch
			e.printStackTrace();
		}
	}

	private static LinkedList createAccount(LinkedList accounts, String inputName, int initialBalance,
			String inputAccountName) {

		// Check if user already exists in the file
		boolean exists = false;

		for (Object currentItem : accounts) {
			if (((Users) currentItem).getName().matches(inputName)) {
				exists = true;
				break;
			}
		}

		if (exists == true) {
			System.out.println("ERROR: User already exists\n");
		}

		else {

			Users newAccount = new Users(inputName, initialBalance, inputAccountName);

			System.out.println("New user created with ID: " + newAccount.id + "\t pw: " + newAccount.password + " ("
					+ inputName + ")\n");

			accounts.add(newAccount);

		}

		return accounts;

	}

	private static LinkedList loadFromFile() throws ClassNotFoundException {

		LinkedList<Users> ls = new LinkedList();

		try (FileInputStream fi = new FileInputStream("userdoc.bin")) {
			ObjectInputStream ois = new ObjectInputStream(fi);

			ls = (LinkedList) ois.readObject();

			if (ls.size() == 0) {
				return new LinkedList();
			}

			for (Object item : ls) {
				System.out.println(item.toString() + "\n");
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ls;

	}
}
