package banking_app;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.Scanner;

public class Welcome_Page {
	Database_Service db_service;
	UserValidation validate_user;
	String uname;
	String password;
	public Welcome_Page(Database_Service db_Service){
		this.db_service = db_Service;
		
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
				// TODO Auto-generated catch block
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
	    			 
	    			   db_service.DBexecuteQuery(name,uname,password,mobileNo);
	                   success = true;

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
		    Banking banking = new Banking();
		     		  
		     if(res.next()) {
					System.out.println("account no : "+ res.getInt(1));

		    	 System.out.println("Press 1 to Deposit Money\nPress 2 to Transfer Money\nPress 3 to Withdraw Money\nPress 4 to check Account Balance ");
		    	 	 
		    	 int num  = sc.nextInt();
		    	 switch(num) {
		    	 case 1 : banking.addMoney(2500);
		    	 break;
		    	 }
				 
			 }else {
				 System.out.println("Incorrect username or password\nPlease Re-enter usename and password");
				 this.signIn();
			 }
		     
		 sc.close();    
	 }

}
