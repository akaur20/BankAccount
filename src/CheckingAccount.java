
public class CheckingAccount extends BankAccount
{
	
	private final double OVER_DRAFT_FEE;
	private final double TRANSACTION_FEE;	
	private final int FREE_TRANS;
	private int numTransactions;
	
	public CheckingAccount(String n, double b, double odf, double tf, int freeTrans) 
	{
		super(n,b);
		OVER_DRAFT_FEE = odf;
		TRANSACTION_FEE = tf;
		FREE_TRANS = freeTrans;
	}
	
	public CheckingAccount(String n, double odf, double tf, int freeTrans) 
	{
		super(n);
		OVER_DRAFT_FEE = odf;
		TRANSACTION_FEE = tf;
		FREE_TRANS = freeTrans;
	}
	
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
	

	public void endOfMonthUpdate() 
	{
		
		numTransactions = 0;
	}

}
