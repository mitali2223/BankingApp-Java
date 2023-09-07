package banking_app;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.Scanner;

public class Welcome_Page {
	Database_Service db_service;
	UserValidation validate_user;
	Banking bank;
	String uname;
	String password;
	public Welcome_Page(Database_Service db_Service){
		this.db_service = db_Service;
		bank = new Banking(db_Service);
	}
	Scanner sc;
	
	public static void welcome_user() {
		System.out.println("Welcome to INDIA BANK");
		
		System.out.println("Press 1 to sign in to your account\nPress 2 to sign up(create new account)");
		
	}
	
	
	public void user(int param)   {
		
		if(param == 1) {
			try {
				this.signIn();
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		}else if(param == 2) {
			this.registerUser();
				
		}else {
			System.out.println(" Invalid choice");
		}
	}
	
	
	
	 public void registerUser()  {
	        boolean success = false;
	        while (!success) {
	            try {
	                UserValidation ans = new UserValidation();
	    			System.out.println("Name : " );
	    			sc = new Scanner(System.in);
	                String name = sc.nextLine();
	                
	           	                
	                boolean res;
	                
	                res = ans.nameValidate(name);
	                          
	               if(res == false) {
	            	   throw new IOException("name field cannot contain numbers or special characters");
	               }  
	               
	    			System.out.println("Username : ");
	    			uname = sc.nextLine();
	    			res = ans.usernameValidate(uname);
	    			
	    			 if(res == false) {
	    	        	   throw new IOException("username must be at least 8 characters long and it is desired to contain at least one digit , one uppercase character and atleast one special character(@ or _ )");
	    		   }
	    			 
	    			 System.out.println("Password : ");
	    		     password = sc.nextLine();
	    			 res = ans.passwordValidate(password);
	    				
	    			 if(res == false) {
	    	        	   throw new IOException("password must contain at least 8 characters , one uppercase character and atleast one special character(allowed @ ,#,*,%,_,$)");
	    		   }
	    			System.out.println("Mobile No: ");
	    		    long mobileNo = sc.nextLong() ;	
	    		    res = ans.mobileNoValidate(mobileNo);
	    	
	    			 if(res == false) {
	    	        	   throw new IOException("Invalid Mobile Number");
	    		   }
	    			 
	    			   db_service.DBInsertQuery(name,uname,password,mobileNo);
	                   success = true;
				   	   System.out.println("sign in to your account");
	    			   this.signIn();
	    				    
	    		 
	            } catch (Exception e) {
	               
	                System.out.println(e.getMessage());
	            }                    
	            
	        }

	        // Close the scanner when done
	        sc.close();

	 }
	 
	 
	 public void signIn() throws Exception  {
		    Scanner sc = new Scanner(System.in);
			System.out.println("Username : ");
			String uname = sc.next();
			
			System.out.println("Password : ");
		    String user_password = sc.next(); 
		    ResultSet res =  db_service.dbUserValidation(uname, user_password);
		     		  
		     if(res.next()) {
					System.out.println("account no : "+ res.getInt(1));
				int num;
		    	 do{
				System.out.println("\nPress 1 to Deposit Money\nPress 2 to Transfer Money\nPress 3 to Withdraw Money\nPress 4 to check Account Balance\nPress 0 to exit ");
		    	 num = sc.nextInt();
				 int amount;
		    	 switch(num) {
				 case 0 : break;
		    	 case 1 : {
					System.out.println("Enter amount to add to account : ");
					amount = sc.nextInt();
					bank.addMoney(amount,uname);
				 }
		    	 break;
				 case 2 :{
				  System.out.println("enter beneficiary's ac no : ");
			      int account_no = sc.nextInt();
				  System.out.println("Enter amount to transfer : ");
 				  amount = sc.nextInt();
				  System.out.println("Enter your username :  ");
				  uname = sc.next();
				  System.out.println("Enter your password : ");
				  user_password = sc.next(); 
				  bank.transferMoney(account_no, amount, uname, user_password);
				  break;
		    	 }
				case 3 : {
				  System.out.println("Enter amount to withdraw : ");
 				  amount = sc.nextInt();
				  System.out.println("Enter your username :  ");
				  uname = sc.next();
				  System.out.println("Enter your password : ");
				  user_password = sc.next(); 
				  bank.withdrawMoney(amount,uname,user_password);
				  break;
				 }
				case 4 : {
				  
				  bank.checkAccountBalance(uname);
				break;
				 }
				 default : {
					System.out.println("Invalid choice");
				 }
				}
				}while(num != 0);
			
				 
			 }else {
				 System.out.println("Incorrect username or password\nPlease Re-enter usename and password");
				 this.signIn();
			 }
		     
		 sc.close();    
	 }

}
