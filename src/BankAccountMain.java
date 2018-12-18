import java.util.ArrayList;
import java.util.Scanner;

public class BankAccountMain 
{
	private double OVER_DRAFT_FEE = 15;
	private double RATE = .0025;
	private double TRANSACTION_FEE = 1.5;
	private double MIN_BAL = 300;
	private double MIN_BAL_FEE = 10;
	private int FREE_TRANSACTIONS = 10;
	
	public static void main(String[] args) 
	{
		ArrayList<BankAccount> accounts = new ArrayList<BankAccount>();
		
		Scanner in = new Scanner(System.in);

		System.out.println("Welcome to our bank. Would you like to add an account, make a transaction, or terminate program.");
		String userWant = in.next();
		
		while (!userWant.equals("add an account") && !userWant.equals("make a transaction") && !userWant.equals("terminate program"))
		{
			System.out.println("Would you like to add an account, make a transaction, or terminate program.");
			userWant = in.next();
		}
		
		while (!userWant.equals("terminate"))
		{
			if (userWant.equals("add an account"))
			{
				
			}
		}
		
		System.out.print("Thank you for visiting our bank");
		
	}
}
