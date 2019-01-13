/**
 * @author Avleen Kaur
 * Period 7
 */
public class CheckingAccount extends BankAccount
{
	
	private final double OVER_DRAFT_FEE;
	private final double TRANSACTION_FEE;	
	private final int FREE_TRANS;
	private int numTransactions;
	
	/**
	 * 
	 * @param n name of account holder
	 * @param b account balance
	 * @param odf over draft fee
	 * @param tf transaction fee
	 * @param freeTrans number of free transactions allowed
	 */
	public CheckingAccount(String n, double b, double odf, double tf, int freeTrans) 
	{
		super(n,b);
		OVER_DRAFT_FEE = odf;
		TRANSACTION_FEE = tf;
		FREE_TRANS = freeTrans;
	}
	
	/**
	 * 
	 * @param n name of account holder
	 * @param odf over draft fee
	 * @param tf transaction fee
	 * @param freeTrans number of free transactions allowed
	 */
	public CheckingAccount(String n, double odf, double tf, int freeTrans) 
	{
		super(n);
		OVER_DRAFT_FEE = odf;
		TRANSACTION_FEE = tf;
		FREE_TRANS = freeTrans;
	}
	
	/**
	 * It deposits the amount wanted into the account if the amount is greater than 0 
	 * and subtracts a transaction fee if the number of free transaction allowed is exceeded
	 * @param amt amount being deposited
	 */
	public void deposit(double amt)
	{
		if(amt <= 0)
		{
			throw (new IllegalArgumentException());
		}
		else
		{
			numTransactions ++;
			if(numTransactions > FREE_TRANS)
			{
				super.deposit(amt);
				super.withdraw(TRANSACTION_FEE);
			}
			else
			{
				super.deposit(amt);
			}
		}
		
	}
	
	/**
	 *It withdraws the amount wanted from the account if the account balance is greater than or equal to 0,
	 *subtracts a transaction fee if the number of free transaction allowed is exceeded, and subtracts an over draft
	 *fee if the balance after withdrawing is less than 0
	 *@param amt amount being withdrawn 
	 */
	public void withdraw(double amt)
	{
		if(getBalance() >= 0)
		{
			super.withdraw(amt);
			numTransactions ++;
			if(numTransactions > FREE_TRANS)
			{
				super.withdraw(TRANSACTION_FEE);
			}
			if(getBalance() < 0)
			{
				super.withdraw(OVER_DRAFT_FEE);
			}
		}
		else
		{
			throw (new IllegalArgumentException());
		}
	}
	/**
	 * It transfers amount wanted from one account to another if the account balance minus the transfer amount and transaction fee is 
	 * greater than 0 and if the name of the account you want to transfer to matches the name your account has
	 * @param other the bank account you want to transfer to
	 * @param amt the amount you want to transfer
	 */
	public void transfer(BankAccount other, double amt)
	{
		if(getBalance() - amt - TRANSACTION_FEE > 0)
		{
			if(getName().equals(other.getName()))
				{
					this.withdraw(amt);
					other.deposit(amt);
				}
			else
			{
				throw (new IllegalArgumentException());
			}
		}
		else
		{
			throw (new IllegalArgumentException());
		}
		
	}
	
	/**
	 * sets the number of transactions to 0 at the end of month
	 */
	public void endOfMonthUpdate() 
	{
		
		numTransactions = 0;
	}

}
