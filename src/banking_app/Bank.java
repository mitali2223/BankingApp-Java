package banking_app;

public class Bank {

	public static void main(String[] args)  {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.getMessage();
		}
		Database_Service db_connection = new Database_Service("jdbc:mysql://localhost:3306/India_Bank","root","slimshady");
		
		Welcome_Page wc = new Welcome_Page(db_connection);
		
		wc.welcome_user();		
		
	}

}
