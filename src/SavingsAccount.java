
public class SavingsAccount extends BankAccount
{

	private double intRate;
	private double MIN_BAL;
	private double MIN_BAL_FEE;
	public SavingsAccount(String n, double b, double r, double mb, double mbf) 
	{
		super(n,b);
		intRate = r;
		MIN_BAL = mb;
		MIN_BAL_FEE = mbf;
	}
	
	public SavingsAccount(String n, double r, double mb, double mbf)
	{
		super(n);
		intRate = r;
		MIN_BAL = mb;
		MIN_BAL_FEE = mbf;
	}
	
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
	
	public void addInterest()
	{
		super.deposit(intRate * getBalance());
	}
	
	public void endOfMonthUpdate() 
	{
		addInterest();
	}

}
