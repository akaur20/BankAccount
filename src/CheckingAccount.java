
public class CheckingAccount extends BankAccount
{
	
	private double OVER_DRAFT_FEE;
	private double TRANSACTION_FEE;	
	private int FREE_TRANS;
	private int numTransacrions;
	
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
		
	}
	

	public void endOfMonthUpdate() 
	{
		
		
	}

}
