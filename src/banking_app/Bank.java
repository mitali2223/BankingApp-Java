package banking_app;
//import java.sql.*;
import java.util.Scanner;

public class Bank {

	public static void main(String[] args)  {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.getMessage();
		}
		Database_Service db_connection = new Database_Service("jdbc:mysql://localhost:3306/India_Bank","root","slimshady");
		Scanner sc = new Scanner(System.in);
		
		Welcome_Page wc = new Welcome_Page(db_connection);
		
		Welcome_Page.welcome_user();		
		int num = sc.nextInt();
		wc.user(num);

		sc.close();
	}

}
