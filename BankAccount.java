/**
 * @author Avleen Kaur
 * Period 7
 */

public abstract class BankAccount 
{
	private static int nextAccNum;
	private String name;
	private int accNum;
	private double balance;
	
	/**
	 * @param n name of account holder
	 */
	public BankAccount(String n)
	{
		name = n;
		accNum = nextAccNum;
		nextAccNum ++;
		balance = 0;
	}
	
	/**
	 * @param n name of account holder
	 * @param b account balance
	 */
	public BankAccount(String n, double b)
	{
		name = n;
		accNum = nextAccNum;
		nextAccNum ++;
		balance = b;
	}
	
	/**
	 * It adds amount to balance if amount is greater than 0.
	 * @param amt amount being deposited into account
	 */
	public void deposit(double amt)
	{
		if(amt < 0)
		{
			throw (new IllegalArgumentException());
		}
		balance += amt;
	}
	/**
	 * It subtracts amount from balance if amount is greater than 0
	 * @param amt amount being withdrawn from account
	 */
	public void withdraw(double amt)
	{
		if(amt < 0)
		{
			throw (new IllegalArgumentException());
		}
		balance -= amt;
	}
	
	/**
	 * returns name of account holder
	 * @return name of account holder
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * returns account balance
	 * @return balance
	 */
	public double getBalance()
	{
		return balance;
	}
	
	/**
	 * returns account number
	 * @return account number
	 */
	public int getAccountNumber()
	{
		return accNum;
	}
	
	/**
	 * end of month update
	 */
	public abstract void endOfMonthUpdate();
	
	/**
	 * Transfers money from one account to another account
	 * @param other the account you want to transfer to
	 * @param amt amount of money being transfered
	 */
	public void transfer(BankAccount other, double amt)
	{
		this.withdraw(amt);
		other.deposit(amt);
	}
	
	/**
	 * Returns a properly formatted string displaying all the account information
	 */
	public String toString()
	{
		return "Account Number: " + accNum + " \t Name: " + name + "\tBalance: " + "$" + balance;
	}

}
