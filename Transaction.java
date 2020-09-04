/*
	Program Objective  - BankApplicattion
	Created By - Kalp Shah
	DOC -   --NA--
*/
package file_royal;

import java.util.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.*;
public class Transaction implements Serializable
{
	static Scanner sc=new Scanner(System.in);
	

	public static void main(String args[]) throws IOException,ClassNotFoundException
	{
		
		Bank obj=new Bank();
		File objFile = new File("d:\\transaction\\info");//Object of Customer Info File
		File balFile = new File("d:\\transaction\\balance");
		String name[] = objFile.list();
		FileInputStream fis[] = new FileInputStream[name.length];//Declaration of Input Stream
		ObjectInputStream objIs[] = new ObjectInputStream[name.length];//Declaration of ObjectInput Stream
		Customer cust[] = new Customer[50];//Declaration of class Customer

		while(true)
		{
			System.out.println("\n1-----Admin");
			System.out.println("\n2-----Customer");
			System.out.println("\n3-----Exit\n\n");
		
			System.out.print("\n\nEnter Your Choice:");
			int choice =sc.nextInt();
			
		switch(choice)
			{
			case 1:
						System.out.print("\n\t\tEnter Username : ");
						String userName=sc.next();
						System.out.print("\n\t\tEnter Password : ");
						String password=sc.next();
							if(obj.adminLogin(userName,password)==1)//checks validity of credentials
							{
								boolean a=true;
								while(a)
								{
									System.out.println("\n\n1-----Create User");
									System.out.println("\n2-----View User");
									System.out.println("\n3-----Delete");
									System.out.println("\n4-----Go To Main");
									
									System.out.print("\n\nEnter Your Choice:");
									
									int innerChoice=sc.nextInt();
									switch(innerChoice)
									{
										case 1 :
												
											cust[name.length]=new Customer();//initialize
											cust[name.length].scanCustomerDetails();
											System.out.println("\n\n\t\tScanned Successfully");
											name =objFile.list();//re - listing total files
											break;
										case 2:
											
											System.out.println("\n\n1-----Ascending");
											System.out.println("\n2-----Descending");

											System.out.print("\n\nEnter Your Choice:");

											int sort =sc.nextInt();
											Customer custCopy=new Customer();//temporary Object
											switch(sort)
											{
												case 1:
													System.out.println(name.length);
													for(int i=0; i<name.length; i++) 
													{
														
														for(int j=0; j<name.length-1; j++) 
														{
															cust[j]=new Customer();//temporary Object
															
															fis[j] = new FileInputStream("D:\\transaction\\info\\"+name[j]);
															objIs[j] = new ObjectInputStream(fis[j]);
															fis[j+1] = new FileInputStream("D:\\transaction\\info\\"+name[j+1]);
															objIs[j+1] = new ObjectInputStream(fis[j+1]);

															cust[j] = (Customer)objIs[j].readObject();
															cust[j+1] = (Customer)objIs[j+1].readObject();
															

															if((cust[j].custId)>(cust[j+1].custId)) 
																{
																		
																		String change;
																		change = name[j];
																		name[j]=name[j+1];
																		name[j+1]=change;
																																				
																	
																}
															objIs[j+1].close();//closing ObjectStream
															objIs[j].close();//closing ObjectStream
															fis[j+1].close();//closing FileStream
															fis[j].close();//closing FileStream
														}
													}
													custCopy.displayTitle();
													
													for(int k=0;k<name.length;k++) 
													{
														cust[k]=new Customer();
														cust[k].displayCustomerDetails(name[k]);			
										
													}
													break;
												case 2:
													for(int i=0; i<name.length; i++) 
													{
														
														for(int j=0; j<name.length-1; j++) 
														{
															cust[j]=new Customer();//temporary Object
															cust[j+1]=new Customer();
															fis[j] = new FileInputStream("D:\\transaction\\info\\"+name[j]);
															objIs[j] = new ObjectInputStream(fis[j]);

															fis[j+1] = new FileInputStream("D:\\transaction\\info\\"+name[j+1]);
															objIs[j+1] = new ObjectInputStream(fis[j+1]);
															
															cust[j] = (Customer)objIs[j].readObject();
															cust[j+1] = (Customer)objIs[j+1].readObject();
															
																if((cust[j].custId)<(cust[j+1].custId)) 
																{
																		
																		String change;
																		change = name[j];
																		name[j]=name[j+1];
																		name[j+1]=change;
																																				
																	
																}
																objIs[j+1].close();//closing ObjectStream
																objIs[j].close();//closing ObjectStream
																fis[j+1].close();//closing FileStream
																fis[j].close();//closing FileStream
														}
													}
													custCopy.displayTitle();
													
													for(int k=0;k<name.length;k++) 
													{
														cust[k]=new Customer();//temporary Object
														cust[k].displayCustomerDetails(name[k]);
														
													}
													break;
											}
												
											break;
											
										case 3:
												System.out.println("\n1-----Full Delete");
												System.out.println("2-----Reference Delete");
												
												System.out.print("Enter Choice : ");
												int choiceDel = sc.nextInt();
												String delName , repName;
												switch(choiceDel)
												{
												case 1:
													System.out.print("\n\n\t\tEnter Customer Name :");
													delName = sc.next();
													
													
													new Customer().deleteUser(delName);
													name =objFile.list();//re - listing total files
													break;
												case 2:
													System.out.print("\n\n\t\tEnter Customer Name :");
													delName = sc.next();
													
													System.out.print("\n\n\t\tEnter Name for Replacement :");
													repName = sc.next();
													
													new Customer().replaceUser(delName,repName);
													name =objFile.list();//re - listing total files
													break;
												}
											break;
										case 4:
											a=false;
											break;
											
										}
								}
							}

				break;
			case 2:
					System.out.print("\n\t\tEnter Username : ");
					int custUserName=sc.nextInt();
					System.out.print("\n\t\tEnter Password : ");
					String custPassword=sc.next();
					
					for(int i=0 ; i<name.length; i++)
					{
				
						cust[i]=new Customer();
						if(cust[i].custLogin(custUserName,custPassword,name[i])==1)//checks validity of credentials
						{
								System.out.println("\n\n\t\tLogged In");
								boolean b=true;
								while(b)
								{
									 
									System.out.println("\n1-----Deposit");
									System.out.println("\n2-----Withdrawal");
									System.out.println("\n3-----Display Balance");
									System.out.println("\n4-----Transfer Money");
									System.out.println("\n5-----Loan");
									System.out.println("\n6-----Go To Main");
			
									System.out.print("\n\nEnter Your Choice:");

									int innerChoice=sc.nextInt();
									switch(innerChoice)
									{
										case 1 :
											
											cust[i].depositAmount(name[i]);
											break;
										case 2:
											
											cust[i].withdrawAmount(name[i]);
											break;
										case 3: 
											
											cust[i].displayBalance(name[i]);
											break;
										case 4:
												System.out.print("\n\t\tEnter CustID for Transfer: ");
												int custId  =sc.nextInt();
												int flag=0;
												
												for(int j=0; j<name.length; j++)
												{
														flag=1;
														cust[j]=new Customer();//temporary object
														if(name[i]!=name[j] )
														{	
																if(cust[j].transferMoney(name[i],name[j],custId) )
																{
																	flag=0;
																	break;
																}
														}													
												}
												
												if(flag==1)
												{
													System.out.println("\n\t\tWrong ID");
												}
									
											break;
										case 5:
												cust[i].loanCalc(name[i]);
											break;
										case 6:
											b=false;
											break;
											
										}
								}
						}
					}
										
				break;
			case 3: System.exit(0);
			}
		}
	}
}
class Bank
{
	int bankid;
	String bankName;
	String bankAddress;
	Scanner sc=new Scanner(System.in);
	String userName;
	String password;
	
	Bank()
	{
		userName ="admin";
		password ="admin";
	}
	void scanBankDetails()
	{
		bankid=sc.nextInt();
		bankName=sc.nextLine();
		sc.nextLine();
		bankAddress=sc.nextLine();
	}
	void displayBankDetails()
	{
		System.out.println("\t\t\t\n\nBank ID :"+bankid);
		System.out.println("\t\t\tBank Name :"+bankName);
		System.out.println("\t\t\tBank Address :"+bankAddress);
		System.out.println("\n\n");
	}
	int adminLogin(String userName,String password)
	{
		int flag;
		if(this.userName.equals(userName)&&this.password.equals(password))
		{		
			flag=1;
		}
		else
		{
			flag=0;
			System.out.println("\n\n\t\tWrong Input");
		}
		return flag;	
	}
}
class Account extends Bank
{
	String typeAccount;
	
	Scanner sc=new Scanner(System.in);

	boolean scanAccountTypeDetails()
	{
		System.out.print("\n\t\tEnter Account Type : ");
		typeAccount=sc.nextLine();
				
			if(typeAccount.equalsIgnoreCase("Savings") || typeAccount.equalsIgnoreCase("Fixed") || typeAccount.equalsIgnoreCase("Current"))
			{
					System.out.println("\n\n\t\tAccount Created Sucessfully");
					return true;
			}
			else
			{
					System.out.println("\n\t\tAccountType Unavailable");
					return false;
			}
	}
	void displayAccountTypeDetails()
	{
		System.out.println("\n\t\tAccount Type :"+typeAccount);
	
	}
}

class Customer extends Account implements Serializable
{
	static int custCount=0;
	int custId;
	String custName;
	String custAddress;
	String password;
	int userName;
	int bal;
	

	private static final long serialVersionUID = 2914394886892714728L;
	
	
	void scanCustomerDetails() throws IOException
	{
		if(scanAccountTypeDetails()==true)
		{
			Scanner sc=new Scanner(System.in);
			System.out.print("\n\n\t\tCustomer ID : ");
			custId=sc.nextInt();
			this.userName=custId;
			sc.nextLine();
			System.out.print("\n\t\tCustomer Name : ");
			custName=sc.nextLine();
			System.out.print("\n\t\tCustomer Address : ");
			custAddress=sc.nextLine();
			System.out.print("\n\n\t\tCustomer Password : ");
			this.password=sc.next();
			
			
			FileOutputStream fos = new FileOutputStream("D:\\transaction\\info\\"+custName);
			ObjectOutputStream objOs = new ObjectOutputStream(fos);
			
			objOs.writeObject(this);
			
			
			objOs.close();//closing of ObjectStream
	 		fos.close();//closing of FileStream
			
			FileOutputStream fos1 = new FileOutputStream("D:\\transaction\\balance\\"+custName+"bal");
			ObjectOutputStream objOs1 = new ObjectOutputStream(fos1);
			
			Customer objBal = new Customer();//temporary Object
			objBal.bal=10000;
			objOs1.writeObject(objBal);
			
			objOs1.close();//closing of ObjectStream
	 		fos1.close();//closing of FileStream
		}
	}
	void displayCustomerDetails(String name) throws IOException,ClassNotFoundException
	{
	
		
		FileInputStream fis = new FileInputStream("D:\\transaction\\info\\"+name);
		ObjectInputStream objIs = new ObjectInputStream(fis);
		Customer newObject = (Customer)objIs.readObject();//reading Object

		System.out.printf("\n\t%-6d  %8s  %13s  \n",newObject.custId,newObject.custName,newObject.custAddress);
		System.out.print("\t------------------------------------");
		/*		System.out.println("\n\t\tCustomer ID :"+custId);
					System.out.println("\n\t\tCustomer Name:"+custName);
					System.out.println("\n\t\tCustomer Address :"+custAddress);		
					System.out.println("\n\n");
			*/
		
		objIs.close();//closing of ObjectStream
 		fis.close();//closing of FileStream
		
		
	}
	void displayTitle()
	{
		System.out.println("\n\t|custId|  |custName|\t|custAddress|");
		System.out.println("\t------------------------------------");	
	}
	void depositAmount(String name)throws IOException,ClassNotFoundException 
	{
		
		System.out.print("\n\t\tEnter Amount of Deposit : ");
		int deposit=sc.nextInt();
		
		if(deposit>0)
		{
			Customer objBal = new Customer();//temporary Object

			FileInputStream fis = new FileInputStream("D:\\transaction\\balance\\"+name+"bal");//Opening of file
			ObjectInputStream objIs = new ObjectInputStream(fis);
			Customer newObject = (Customer)objIs.readObject();//reading Object
			
				objBal.bal = newObject.bal;
				
				objIs.close();//closing of ObjectStream
		 		fis.close();//closing of FileStream
			FileOutputStream fos = new FileOutputStream("D:\\transaction\\balance\\"+name+"bal");//Opening of file
			ObjectOutputStream objOs = new ObjectOutputStream(fos);			
			
			objBal.bal = objBal.bal + deposit;
			objOs.writeObject(objBal);//Updated Balance
			
			
			System.out.println("\t\tSuccessfull!");
			
			objOs.close();//closing of ObjectStream
	 		fos.close();//closing of FileStream
		
		}
		else
		{
			System.out.println("\n\t\tAmount Misleading");
			System.out.println("\n\t\tDeposit Unsuccessful");
		}
		
		
	}
	void withdrawAmount(String name)throws IOException,ClassNotFoundException
	{
		Customer objBal = new Customer();//temporary Object

		FileInputStream fis = new FileInputStream("D:\\transaction\\balance\\"+name+"bal");//Opening of file
		ObjectInputStream objIs = new ObjectInputStream(fis);
		Customer newObject = (Customer)objIs.readObject();//reading Object
		
			objBal.bal = newObject.bal;
			
			objIs.close();//closing of ObjectStream
	 		fis.close();//closing of FileStream
	
		System.out.print("\n\n\t\tEnter Amount of Withdrawal : ");
		int withdraw=sc.nextInt();
			if(objBal.bal-withdraw>=10000&&withdraw>0)
			{
				FileOutputStream fos = new FileOutputStream("D:\\transaction\\balance\\"+name+"bal");//Opening of file
				ObjectOutputStream objOs = new ObjectOutputStream(fos);	
				objBal.bal = objBal.bal - withdraw;
				objOs.writeObject(objBal);//Updated Balance
				
				
				System.out.println("\t\tSuccessfull!");
				
				objOs.close();//closing of ObjectStream
		 		fos.close();//closing of FileStream
					
					
			}
			else
			{
			System.out.println("\n\t\tAmount Misleading");
			System.out.println("\n\t\tWithdrawal Unsuccessful");
			}
			
		
			
			
	}
	void displayBalance(String name)throws IOException,ClassNotFoundException
	{
		FileInputStream fis = new FileInputStream("D:\\transaction\\balance\\"+name+"bal");//Opening of file
		ObjectInputStream objIs = new ObjectInputStream(fis);
		Customer newObject = (Customer)objIs.readObject();//reading Object
		
		System.out.print("Current Balance :  ");
		System.out.println(newObject.bal);//Updated Balance
		
		objIs.close();//closing of ObjectStream
 		fis.close();//closing of FileStream
	}
	
	int custLogin(int userName , String password , String name)throws IOException,ClassNotFoundException
	{
		int flag = 0;
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd");
		Date date=new Date();
	 	int currentDate=Integer.parseInt(dateFormat.format(date));
		
		
	 	FileInputStream fis = new FileInputStream("D:\\transaction\\info\\"+name);//Opening of file
	 	ObjectInputStream objIs = new ObjectInputStream(fis);
	 	Customer newObject = (Customer) objIs.readObject();//reading Object
		
		
	 	try
		{
			int a=date.getDay();
			if(a==6)
			{
				throw new Exception("\n\t\tLogin on Sunday is not possible");
			}
			else if(currentDate>=7 && currentDate <= 14 && a==6)
			{	
				throw new Exception("\n\t\tLogin on 2ND Sat is not possible");
			}
			else if(currentDate >= 21 && currentDate <= 28 && a==6)
			{		
				throw new Exception("\n\t\tLogin on 4TH Sat is not possible");
			}
			else
			{
				
				if(newObject.userName==userName&&newObject.password.equals(password) )
				{		
					System.out.println("LoggedIn");
					flag= 1;
				}
				else
				{
					flag = 0;
					
				}
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
				
	 	objIs.close();//closing of ObjectStream
 		fis.close();//closing of FileStream
		return flag;
	}
	


	
	boolean transferMoney(String name1,String name , int custId)throws IOException,ClassNotFoundException
	{
		boolean flag = false;
		int trans;
		
		Customer obj1 = new Customer();//temporary Object
		Customer obj2 = new Customer();//temporary Object
		
		FileInputStream fis = new FileInputStream("D:\\transaction\\balance\\"+name+"bal");//Opening of file
	 	ObjectInputStream objIs = new ObjectInputStream(fis);
	 	Customer newObject = (Customer) objIs.readObject();//Reading File
	 		
	 		obj1.bal = newObject.bal; 
	 		
	 		objIs.close();//closing of ObjectStream
	 		fis.close();//closing of FileStream
	 	
	 	FileInputStream fis1 = new FileInputStream("D:\\transaction\\balance\\"+name1+"bal");//Opening of file
	 	ObjectInputStream objIs1 = new ObjectInputStream(fis1);
	 	Customer newObject1 = (Customer) objIs1.readObject();//Reading File
	 	
	 		obj2.bal = newObject1.bal;
	 		
	 		objIs1.close();//closing of ObjectStream
	 		fis1.close();//closing of FileStream
	 		
 		FileInputStream fis2 = new FileInputStream("D:\\transaction\\info\\"+name);//Opening of file
 		ObjectInputStream objIs2 = new ObjectInputStream(fis2);
 		Customer newObject2 = (Customer) objIs2.readObject();//Reading File
 		
	 		obj2.custId = newObject2.custId;
	 		
	 		objIs2.close();//closing of ObjectStream
	 		fis2.close();//closing of FileStream
 		
	 	
		
			if(obj2.custId==custId)
			{
				
				System.out.print("\n\t\tEnter Amount :" );
				trans=sc.nextInt();
					if(obj2.bal-trans>=10000&&trans>0)
					{
						
						
						FileOutputStream fos = new FileOutputStream("D:\\transaction\\balance\\"+name+"bal");//Opening of File
						ObjectOutputStream objOs = new ObjectOutputStream(fos);	
						
						obj1.bal= obj1.bal + trans;//Updated Balance
						objOs.writeObject(obj1);
						
						objOs.close();//closing of ObjectStream
						fos.close();//closing of FileStream

						FileOutputStream fos1 = new FileOutputStream("D:\\transaction\\balance\\"+name1+"bal");//Opening of File
						ObjectOutputStream objOs1 = new ObjectOutputStream(fos1);
						
						obj2.bal = obj2.bal - trans;//Updated Balance
						objOs1.writeObject(obj2);
						
						objOs1.close();//closing of ObjectStream
						fos1.close();//closing of FileStream
						System.out.println("\n\t\tTransfer Successful");		
						///with.displayBalance();
					}
					else
					{
						System.out.println("\n\t\tAmount Misleading");
						System.out.println("\n\t\tTransfer Unsuccessful");
					}
				flag=true;
			}
			else
			{
				flag=false;
			}
			
			return flag;
	}
		
	void loanCalc(String name)throws IOException , ClassNotFoundException
	{
		int loan=0;
		int month=0;
		float interest=9f;
		float emi ;
		
		Customer objBal = new Customer();//temporary Object
		
		FileInputStream fis = new FileInputStream("D:\\transaction\\balance\\"+name+"bal");//Opening of file
	 	ObjectInputStream objIs = new ObjectInputStream(fis);
	 	Customer newObject = (Customer) objIs.readObject();//Reading File
	 		
	 		objBal.bal = newObject.bal; 
	 		
	 	objIs.close();//closing of ObjectStream
	 	fis.close();//closing of FileStream
		
	 			System.out.println("\n\t\tLoan Amount Limit :"+objBal.bal*10);
				System.out.println("\n\t\tInterest:"+interest);
				System.out.print("\n\t\t\tEnter Amount of Loan:");		
				loan = sc.nextInt();
				System.out.print("\n\t\t\tEnter Loan Duration(in months):");		
				month = sc.nextInt();
				
				if(loan<=(objBal.bal*10))
				{
					if(month>0&&month<=24)
					{
						emi=((loan*interest)/100)/month;
						System.out.println("\n\t\t\tEMI in Rs:"+emi);
						System.out.print("\n\n\t\tEnter Your Choice (Y/N):");
						char choice =sc.next().charAt(0);
							if(choice=='Y'||choice=='y')
							{
								FileOutputStream fos = new FileOutputStream("D:\\transaction\\balance\\"+name+"bal");
								ObjectOutputStream objOs = new ObjectOutputStream(fos);	
								
								objBal.bal+=loan;//updated balance
								
								objOs.writeObject(objBal);//writing object
								
								objOs.close();//closing of ObjectStream
								fos.close();//closing of FileStream
								System.out.println("\n\t\tLoan Permitted Successfully");
								
							}
							else if(choice=='N'||choice=='n')
							{
								System.out.println("\n\t\tThanks For Visiting Us");								
							}
							else
							{
								System.out.println("\n\t\tWrong Input");
							}
					}
					else
					{
						System.out.println("\n\t\tMonth Limit Exceeded");
					}
				}
				else
				{
					System.out.println("\n\t\t!!!Exceeding Loan Limit!!!");
				}
	}
	
	void deleteUser(String delName )
	{
		File infoObj = new File("d:\\BankDel\\"+delName);
		File balObj = new File("d:\\BankDel\\"+delName+"bal");
		
		if(new File("d:\\transaction\\info\\"+delName).renameTo(infoObj) && new File("d:\\transaction\\balance\\"+delName+"bal").renameTo(balObj))
		{
			System.out.println("\n\t\tReplaced Successfully");
			
		}
		else
		{
			System.out.println("Replacing Unscessfull");
		}
		
		/*if(new File("d:\\transaction\\info\\"+delName).delete()&&new File("d:\\transaction\\balance\\"+delName+"bal").delete())
			System.out.println("\n\nCustomer : "+delName+" removed successfully");
		else
			System.out.println("\nCustomer :"+delName+" does not exist");
			*/
	}
	
	void replaceUser(String delName , String repName)
	{
		File infoObj = new File("d:\\transaction\\del\\"+repName);
		File balObj = new File("d:\\transaction\\del\\"+repName+"bal");
		
		if(new File("d:\\transaction\\info\\"+delName).renameTo(infoObj) && new File("d:\\transaction\\balance\\"+delName+"bal").renameTo(balObj))
		{
			System.out.println("\n\t\tReplaced Successfully");
			
		}
		else
		{
			System.out.println("Replacing Unscessfull");
		}
		
	}
	

}


