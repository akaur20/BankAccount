import java.util.ArrayList;
import java.util.Scanner;

public class BankAccountMain 
{
	
	public static void main(String[] args) 
	{
		final double OVER_DRAFT_FEE = 15;
		final double RATE = .0025;
		final double TRANSACTION_FEE = 1.5;
		final double MIN_BAL = 300;
		final double MIN_BAL_FEE = 10;
		final int FREE_TRANSACTIONS = 10;
		 
		ArrayList<BankAccount> accounts = new ArrayList<BankAccount>();
		
		Scanner in = new Scanner(System.in);

		System.out.println("Welcome to our bank. Would you like to add an account, make a transaction, or terminate program.");
		String userWant = (in.nextLine()).toLowerCase();
		
		while (!userWant.equals("add an account") && !userWant.equals("make a transaction") && !userWant.equals("terminate program"))
		{
			System.out.println("Would you like to add an account, make a transaction, or terminate program.");
			userWant = (in.nextLine()).toLowerCase();
		}
		
		
		if (userWant.equals("add an account"))
		{
			System.out.println("Would you like to create a checking or savings account?");
			String checkOrSave = (in.nextLine()).toLowerCase();
			while (!checkOrSave.equals("checking") && !checkOrSave.equals("savings"))
			{
				System.out.println("Would you like to create a checking or savings account?");
				checkOrSave = (in.nextLine()).toLowerCase();
			}
			System.out.println("What is your name?");
			String name = (in.nextLine()).toLowerCase();
			if (checkOrSave.equals("checking"))
			{
				accounts.add(new CheckingAccount(name, OVER_DRAFT_FEE, TRANSACTION_FEE, FREE_TRANSACTIONS));
			}
			
			if(checkOrSave.equals("savings"))
			{
				accounts.add(new SavingsAccount(name, RATE, MIN_BAL_FEE, MIN_BAL_FEE));
			}
			
		}
		
		if(userWant.equals("make a transaction"))
		{
			System.out.println("Would you like to withdraw, deposit, transfer, or get account numbers?");
			String userTransaction = (in.nextLine()).toLowerCase();
			
			while (!userTransaction.equals("add an account") && !userTransaction.equals("make a transaction") && !userTransaction.equals("terminate program"))
			{
				System.out.println("Would you like to withdraw, deposit, transfer, or get account numbers?");
				userTransaction = (in.nextLine()).toLowerCase();
			}
			
			System.out.println("What is your account number?");
			int accNum = in.nextInt();

			BankAccount foundAccount;
			for (int i = 0; i < accounts.size(); i++)
			{
				try
				{
					if(accounts.get(i).getAccountNumber() == accNum)
					{
						foundAccount = accounts.get(i);
					}
				}
				catch(IllegalArgumentException a)
				{
					System.out.print("transaction not authorized");
				}
			}
			
			switch (userTransaction)
			{
			case "withdraw": 
			{
				System.out.println("How much money would you like to withdraw?");
				double withdrawalAmt = in.nextDouble();
				foundAccount.withdraw(withdrawalAmt);
				break;
			}
			case "deposit": 
			{
				System.out.println("How much money would you like to deposit?");
				double depositAmt = in.nextDouble();
				foundAccount.withdraw(depositAmt);
				break;
			}
			case "transfer": 
			{
				
				break;
			}
			case "get account numbers": 
			{
				System.out.println("What is your name?");
				String name = (in.nextLine()).toLowerCase();
				
				for (int i = 0; i < accounts.size(); i++)
				{
					if(accounts.get(i).getName() == name)
					{
						accounts.get(i).toString();
					}
					
				}
				break;
			}
			}
		}
		
		

		System.out.print("Thank you for visiting our bank");
		
	}
}
