package banking_app;
import java.sql.*;

public class Database_Service  {
	String url;
	String username;
	String password;
	Connection connection;
	Statement st;
	Database_Service(String url,String username, String password){
		this.url = url;
		this.username = username;
		this.password = password;
	}
	
	public Connection connect() throws SQLException   {
		 connection = DriverManager.getConnection(url, username, password);
		 return connection;	
	}
	
	public void DBexecuteQuery(String name,String uname , String password,long mobileNo) throws Exception {
		 st = this.connect().createStatement();
		 st.executeUpdate("Insert into userData(u_name,userId,u_password,mobileNo) values ('" + name + "', '"+ uname + "', '"+password +"','"+mobileNo +"')");

		 ResultSet rs = this.dbUserValidation(uname, password);
		 while(rs.next()) {
			System.out.println("account no : " + rs.getInt(1) + "  name : " + rs.getString(2)  );
		}		
    }
	
	public ResultSet dbUserValidation(String username ,String password) throws Exception {
		st = this.connect().createStatement();
				
		 String query = "select account_no , u_name  from userData where userId = '"+username + "' and u_password = '"+password+"';";

		 return st.executeQuery(query);		
	}
}


