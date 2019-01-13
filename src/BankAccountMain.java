/**
 * @author Avleen Kaur
 * Period 7
 */

import java.util.ArrayList;
import java.util.Scanner;

public class BankAccountMain 
{
	/**
	 * Helps check whether the input value is a double
	 * @param str string input
	 * @return true or false depending on whether a double or not
	 */
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
	
	/**
	 * Helps check whether the input value is an integer
	 * @param str string input
	 * @return true or false depending on whether an integer or not
	 */
	private static boolean isInt(String str) 
	{
		try 
		{
			Integer.parseInt(str);
			return true;
		} catch(IllegalArgumentException e) 
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
					
					while (!userTransaction.equals("withdraw") && !userTransaction.equals("deposit") && !userTransaction.equals("transfer") && !userTransaction.equals("get account numbers") )
					{
						System.out.println("Would you like to withdraw, deposit, transfer, or get account numbers?");
						userTransaction = (in.nextLine()).toLowerCase();
					}
					
					BankAccount foundAccount = null;
					int accNum = -1;
					
					if(userTransaction.equals("withdraw") || userTransaction.equals("deposit") || userTransaction.equals("transfer"))
					{
						System.out.println("What is your account number?");
						String accNum1 = in.nextLine();
						while (!isInt(accNum1))
						{
							System.out.println("What is your account number?");
							accNum1 = in.nextLine();
						}
						
						accNum = Integer.parseInt(accNum1);
						
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
							String userAcctWant = in.nextLine();
							while (!userAcctWant.equals("yes") && !userAcctWant.equals("no"))
							{
								System.out.println(" Would you like to enter another number?");
								userAcctWant = (in.nextLine()).toLowerCase();
							}
							
							if (userAcctWant.equals("yes"))
							{
								accNum1 = in.nextLine();
								while (!isInt(accNum1))
								{
									System.out.println("What is your account number?");
									accNum1 = in.nextLine();
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
					}
					
					switch (userTransaction)
					{
					case "withdraw": 
					{
						if(foundAccount.getBalance() >= 0)
						{	
							boolean wrong = true;
							while(wrong)
							{
								try
								{
									System.out.println("How much money would you like to withdraw?");
									String withdrawalAmt;
									withdrawalAmt = in.nextLine();
									while (!isNumeric(withdrawalAmt))
									{
										System.out.println("How much money would you like to withdraw?");
										withdrawalAmt = in.nextLine();
									}
									foundAccount.withdraw(Double.parseDouble(withdrawalAmt));
									wrong = false;
								}
								catch(IllegalArgumentException e)
								{
									System.out.println("transaction not authorized. ");	
									System.out.println("Would you like to end your transaction?");
									String userAnswer = in.nextLine();
									while (userAnswer.equals("yes") && userAnswer.equals("no"))
									{
										System.out.println("Would you like to end your transaction?");
										userAnswer = in.nextLine();
									}
									if (userAnswer.equals("yes"))
									{
										wrong = false;
									}
								}
							}
						
						}
						else
						{
							System.out.print("Transaction not authorized. Negative balance. ");	
						}
						
						break;
						
					}
					case "deposit": 
					{
						boolean wrong = true;
						while(wrong)
						{
							try
							{
								System.out.println("How much money would you like to deposit?");
								String depositAmt;
								depositAmt = in.nextLine();
								while (!isNumeric(depositAmt))
								{
									System.out.println("How much money would you like to deposit?");
									depositAmt = in.nextLine();
								}
								foundAccount.deposit(Double.parseDouble(depositAmt));
								wrong = false;
							}
							catch(IllegalArgumentException e)
							{
								System.out.print("transaction not authorized. ");	
							}
						}
						
						break;
					}
					case "transfer": 
					{
						
						if (accounts.size() > 1)
						{
							System.out.println("Which account would you like to transfer to? Please enter their account number");
							String accNum2 = in.nextLine();
							
							while (!isInt(accNum2))
							{
								System.out.println("Which account would you like to transfer to? Please enter their account number");
								accNum2 = in.nextLine();
							}
							
							int accNum02 = Integer.parseInt(accNum2);
							BankAccount foundAccount2 = null;
							for (int i = 0; i < accounts.size(); i++)
							{
									if(accounts.get(i).getAccountNumber() == accNum02)
									{
										foundAccount2 = accounts.get(i);
									}
							}
							
							while (foundAccount2 == null)
							{
								System.out.println("No accounts were found with this account number. Would you like to enter another number?");
								String userAcctWant = in.nextLine();
								while (!userAcctWant.equals("yes") && !userAcctWant.equals("no"))
								{
									System.out.println(" Would you like to enter another number?");
									userAcctWant = (in.nextLine()).toLowerCase();
								}
								
								if (userAcctWant.equals("yes"))
								{
									accNum2 = in.nextLine();
									while (!isInt(accNum2))
									{
										System.out.println("What is account number you want to transfer to?");
										accNum2 = in.nextLine();
									}
									
									accNum02 = Integer.parseInt(accNum2);
									foundAccount2 = null;
									for (int i = 0; i < accounts.size(); i++)
									{
											if(accounts.get(i).getAccountNumber() == accNum)
											{
												foundAccount2 = accounts.get(i);
											}
									}
								
								}
							}
								
								boolean wrong = true;
								while(wrong)
								{
									try
									{
										System.out.println("How much money would you like to transfer?");
										String transferAmt;
										transferAmt = in.nextLine();
										while (!isNumeric(transferAmt))
										{
											System.out.println("How much money would you like to transfer?");
											transferAmt = in.nextLine();
										}
										foundAccount.transfer(foundAccount2, Double.parseDouble(transferAmt));
										wrong = false;
									}
									catch(IllegalArgumentException e)
									{
										System.out.print("transaction not authorized. ");	
										System.out.println("Would you like to end your transaction?");
										String userAnswer = in.nextLine();
										while (userAnswer.equals("yes") && userAnswer.equals("no"))
										{
											System.out.println("Would you like to end your transaction?");
											userAnswer = in.nextLine();
										}
										if (userAnswer.equals("yes"))
										{
											wrong = false;
										}
									}
								}
						
							
						}
					else
					{
						System.out.println("There are not enough accounts in the system to make a transfer. Either choose to make another account or terminate program.");
					}
							
					break;
					}
					case "get account numbers": 
					{
						System.out.println("What is your name?");
						String name = (in.nextLine()).toLowerCase();
				
						for (int i = 0; i < accounts.size(); i++)
						{
							if(accounts.get(i).getName().equals(name))
							{
								if (accounts.get(i) instanceof CheckingAccount)
								{
									System.out.println(accounts.get(i).toString() + "\t Checking Account"); 
								}
								
								else if(accounts.get(i) instanceof SavingsAccount)
								{
									System.out.println(accounts.get(i).toString() + "\t Savings Account");
								}
								
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
