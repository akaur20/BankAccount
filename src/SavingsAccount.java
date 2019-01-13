/**
 * @author Avleen Kaur
 * Period 7
 */

public class SavingsAccount extends BankAccount
{

	private double intRate;
	private final double MIN_BAL;
	private final double MIN_BAL_FEE;
	
	/**
	 * 
	 * @param n name of account holder
	 * @param b account balance
	 * @param r interest rate
	 * @param mb minimum balance
	 * @param mbf minimum balance fee
	 */
	public SavingsAccount(String n, double b, double r, double mb, double mbf) 
	{
		super(n,b);
		intRate = r;
		MIN_BAL = mb;
		MIN_BAL_FEE = mbf;
	}
	
	/**
	 * 
	 * @param n name of account holder
	 * @param r interest rate
	 * @param mb minimum balance
	 * @param mbf minimum balance fee
	 */
	public SavingsAccount(String n, double r, double mb, double mbf)
	{
		super(n);
		intRate = r;
		MIN_BAL = mb;
		MIN_BAL_FEE = mbf;
	}
	
	/**
	 *It withdraws the amount wanted from the account if the amount is greater than 0 and if account balance minus the amount wanted
	 *is greater than or equal to 0 and subtracts a minimum balance fee if the the balance minus amount is less than minimum balance required
	 *@param amt amount being withdrawn 
	 */
	public void withdraw(double amt)
	{
		if(amt > 0)
		{
			if(getBalance() - amt < MIN_BAL)
			{
				if(getBalance() - amt >= 0 )
				{
					super.withdraw(amt);
					super.withdraw(MIN_BAL_FEE);
					return;
				}

			}
			else
			{
				super.withdraw(amt);
				return;
			}
		}
			
		throw (new IllegalArgumentException());

	}
	
	/**
	 * Transfers money from one account to another if the balance in the first account minus the amount is greater than 0 and the names of 
	 * both accounts are the same
	 * @param other the account you want to transfer money into
	 * @param amt the amount being transfered
	 */
	public void transfer(BankAccount other, double amt)
	{
		if(getBalance() - amt  > 0)
		{
			if(getName().equals(other.getName()))
			{
				this.withdraw(amt);
				other.deposit(amt);
				return;
			}

		}
		throw (new IllegalArgumentException());

	}
	
	/**
	 * Adds interest to the balance of the account
	 */
	public void addInterest()
	{
		super.deposit(intRate * getBalance());
	}
	
	/**
	 * Adds interest to the account at the end of the month
	 */
	public void endOfMonthUpdate() 
	{
		addInterest();
	}

}
