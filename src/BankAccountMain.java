import java.util.ArrayList;
import java.util.Scanner;

public class BankAccountMain 
{
	private static boolean isNumeric(String str)
	{
		try
		{
			Double.parseDouble(str);
			return true;
		}
		catch(IllegalArgumentException e)
		{
					return false;
		}
	}

	
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
		boolean cont = true;
		while(cont)
		{
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
				
				BankAccount account = null;
				if (checkOrSave.equals("checking"))
				{
					account = new CheckingAccount(name, OVER_DRAFT_FEE, TRANSACTION_FEE, FREE_TRANSACTIONS);
					accounts.add(account);
					System.out.println("Your new account number is "+account.getAccountNumber());
				}
				
				if(checkOrSave.equals("savings"))
				{
					account = new SavingsAccount(name, RATE, MIN_BAL_FEE, MIN_BAL_FEE);
					accounts.add(account);
					System.out.println("Your new account number is " + account.getAccountNumber());
				}
				
				
			}
			
			if(userWant.equals("make a transaction"))
			{
				if(accounts.size() == 0)
				{
					System.out.println("There are no accounts in the system to make a transaction");
				}
				else
				{
					System.out.println("Would you like to withdraw, deposit, transfer, or get account numbers?");
					String userTransaction = (in.nextLine()).toLowerCase();
					
					while (!userTransaction.equals("withdraw") && !userTransaction.equals("deposit") && !userTransaction.equals("get account numbers"))
					{
						System.out.println("Would you like to withdraw, deposit, transfer, or get account numbers?");
						userTransaction = (in.nextLine()).toLowerCase();
					}
					
					System.out.println("What is your account number?");
					String accNum1 = in.next();
					while (!isNumeric(accNum1))
					{
						System.out.println("What is your account number?");
						accNum1 = in.next();
					}
					
					int accNum = Integer.parseInt(accNum1);
					BankAccount foundAccount = null;
					for (int i = 0; i < accounts.size(); i++)
					{
							if(accounts.get(i).getAccountNumber() == accNum)
							{
								foundAccount = accounts.get(i);
							}
					}
					
					while (foundAccount == null)
					{
						System.out.println("No accounts were found with this account number. Would you like to enter another number?");
						String userAcctWant = in.next();
						while (!userTransaction.equals("yes") && !userTransaction.equals("no"))
						{
							System.out.println(" Would you like to enter another number?");
							userAcctWant = (in.nextLine()).toLowerCase();
						}
						
						if (userAcctWant.equals("yes"))
						{
							accNum1 = in.next();
							while (!isNumeric(accNum1))
							{
								System.out.println("What is your account number?");
								accNum1 = in.next();
							}
							
							accNum = Integer.parseInt(accNum1);
							foundAccount = null;
							for (int i = 0; i < accounts.size(); i++)
							{
									if(accounts.get(i).getAccountNumber() == accNum)
									{
										foundAccount = accounts.get(i);
									}
							}
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
			}
			System.out.println("Would you like to add an account, make a transaction, or terminate program.");
			userWant = (in.nextLine()).toLowerCase();
			while (!userWant.equals("add an account") && !userWant.equals("make a transaction") && !userWant.equals("terminate program"))
			{
				System.out.println("Would you like to add an account, make a transaction, or terminate program.");
				userWant = (in.nextLine()).toLowerCase();
			}
			if(userWant.equals("terminate program"))
			{
				cont = false;
			}
		}	

		System.out.print("Thank you for visiting our bank");
		
	}
}
