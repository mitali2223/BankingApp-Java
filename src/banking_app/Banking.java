package banking_app;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Banking {
	Database_Service db_service;
	ResultSet res;
	public Banking(Database_Service db_Service ){
		this.db_service = db_Service;
	}
	public void addMoney(int amount, String username) throws SQLException {
		db_service.dbAddMoney(amount,username);	
		res = db_service.fetchData(username);
		while(res.next()){
			System.out.println(res.getInt(1) + " " + res.getString(2)+ " " + res.getFloat(3));;
		}	
	}
	public void withdrawMoney(int amount, String username , String password) throws SQLException {
		res = db_service.fetchData(username);
	    while(res.next()){
			System.out.println(res.getInt(1) + " " + res.getString(2)+ " " + res.getFloat(3));
			if(res.getInt(3) >= amount){
				db_service.dbWithdrawMoney(amount, username, password);
			}else{
				System.out.println("insufficient balance");
			}
		}
	}
	public void transferMoney(int account_no , int amount, String username , String password) throws SQLException {
		res = db_service.fetchData(username);
	    while(res.next()){
			
			if(res.getInt(3) >= amount){
				db_service.dbTransferMoney(account_no, amount, username, password);
		}else{
			System.out.println("insufficient balance");
		}
	}
			System.out.println("transaction successfully completed");
	}
	public void checkAccountBalance(String username) throws SQLException {
	    res =  db_service.fetchData(username);
		while(res.next()){
			System.out.println( "Account balance : " + res.getFloat(3));;
		}
	} 	
}
