
public abstract class BankAccount 
{
	private static int nextAccNum;
	private String name;
	private int accNum;
	private double balance;
	
	public BankAccount(String n)
	{
		name = n;
		accNum = nextAccNum;
		nextAccNum ++;
		balance = 0;
	}
	
	public BankAccount(String n, double b)
	{
		name = n;
		accNum = nextAccNum;
		nextAccNum ++;
		balance = b;
	}
	
	public void deposit(double amt)
	{
		if(amt < 0)
		{
			throw (new IllegalArgumentException());
		}
		balance += amt;
	}
	
	public void withdraw(double amt)
	{
		if(amt < 0)
		{
			throw (new IllegalArgumentException());
		}
		balance -= amt;
	}
	
	public String getName()
	{
		return name;
	}
	
	public double getBalance()
	{
		return balance;
	}
	
	public int getAccountNumber()
	{
		return accNum;
	}
	
	public abstract void endOfMonthUpdate();
	
	public void transfer(BankAccount other, double amt)
	{
		this.withdraw(amt);
		other.deposit(amt);
	}
	
	public String toString()
	{
		return "accNum/tname/t$balance";
	}

}
