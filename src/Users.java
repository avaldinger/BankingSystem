import java.io.Serializable;
import java.util.Random;

class Users implements Serializable  {
	private String name;
	private String account_name;
	String password;
	int id;
	private int balance;
	
	String aToZ="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	String randomStr=generateRandom(aToZ);


	private static String generateRandom(String aToZ) {
	    Random rand = new Random();
	    StringBuilder res = new StringBuilder();
	    for (int i = 0; i < 8; i++) {
	       int randIndex = rand.nextInt(aToZ.length()); 
	       res.append(aToZ.charAt(randIndex));            
	    }
		return res.toString();
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public int getBalance() {
		return balance;
	}

	public String getPassword() {
		return password;
	}
	
	public String setPassword(String password) {
		return this.password = password;
	}

	public String getAccount_name() {
		return account_name;
	}

	public Users(String name, String account_name) {
		this.name = name;
		this.id = (int) ((Math.random() * 9000) + 1000);
		this.password = generateRandom(aToZ);
		this.balance = 0;
		this.account_name = account_name;
	}

	public Users(String name, int id, int balance, String account_name) {
		this.name = name;
		this.id = (int) ((Math.random() * 9000) + 1000);
		this.password = generateRandom(aToZ);
		this.balance = balance;
		this.account_name = account_name;

	}

	public Users(String name, int balance, String account_name) {
		this.name = name;
		this.id = (int) ((Math.random() * 9000) + 1000);
		this.password = generateRandom(aToZ);
		this.balance = balance;
		this.account_name = account_name;

	}

	public String toString() {
		return "Name: " + name + "\nBalance: " + balance + "\nAccount ID: " + id + "\nAccount name: " + account_name;
	}

}