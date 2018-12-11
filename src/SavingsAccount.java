
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

	public void endOfMonthUpdate() 
	{
		
	}

}
