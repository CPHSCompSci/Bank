package app;

import java.util.ArrayList;

public class Bank {
	private static final boolean LOG = true;

	private static int accountCounter = 1;
	private String name;
	ArrayList<Account> accounts;

	public Bank() {
		this("Bank Name");
	}

	public Bank(String name) {
		this.name = name;
		accounts = new ArrayList<>();
		log("Bank Created");
	}

	public int createAccount(String name) {
		Account newAccount = new Account(name);
		accounts.add(newAccount);

		log("Added account " + newAccount);
		return newAccount.accountNumber;
	}

	public boolean closeAccount(int accountNumber) {
		Account account = findAccount(accountNumber);
		if (account == null) {
			log("Could not close account " + accountNumber);
			return false;
		}
		accounts.remove(account);
		log("Successfully closed " + account);
		return true;
	}

	public boolean deposit(int accountNumber, int amount) {
		Account account = findAccount(accountNumber);
		if (account == null) {
			log("Could not deposit to account " + accountNumber);
			return false;
		}
		account.amount += amount;
		log("Successfully deposited $" + amount + " to " + account);
		return true;
	}

	public boolean withdraw(int accountNumber, int amount) {
		Account account = findAccount(accountNumber);
		if (account == null) {
			log("Could not withdraw from account " + accountNumber);
			return false;
		}
		if (account.amount < amount) {
			log("Insufficient funds in " + account);
			return false;
		}
		account.amount -= amount;
		log("Successfully withdrew $" + amount + " from " + account);
		return true;
	}

	public int checkBalance(int accountNumber) {
		Account account = findAccount(accountNumber);
		if (account == null) {
			log("Could not check balance of account " + accountNumber);
			return -1;
		}
		log("Successfully checked balance of account " + account);
		return account.amount;
	}

	public void saveAccounts() {
		// TODO
		log("Save not yet implemented.");
	}

	public void loadAccounts() {
		// TODO
		log("Load not yet implemented.");
	}

	private Account findAccount(int accountNumber) {
		for (int i = accounts.size() - 1; i >= 0; i--) {
			if (accounts.get(i).getAccountNumber() == accountNumber)
				return accounts.get(i);
		}
		return null;
	}

	private void log(String message) {
		if (LOG)
			System.out.println(name + " ::: " + message + ".");
	}

	private class Account {
		int accountNumber;
		String name;
		int amount;

		private Account(String name) {
			this.name = name;
			amount = 0;
			accountNumber = accountCounter++;
		}

		private int getAccountNumber() {
			return accountNumber;
		}

		public String toString() {
			// TODO
			return "{" + accountNumber + "::" + name + "::$" + amount + "}";
		}

	}
}
