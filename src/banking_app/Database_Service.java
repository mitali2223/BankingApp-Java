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
	
	public void DBInsertQuery(String name,String uname , String password,long mobileNo) throws Exception {
		
		 st = this.connect().createStatement();
		
		if(!!!doesUsernameExists(uname)) { 
		    st.executeUpdate("Insert into userData(u_name,userId,u_password,mobileNo) values ('" + name + "', '"+ uname + "', '"+password +"','"+mobileNo +"')");
		    String str = "insert into useracdetails(userId) select userId from userdata where userId = '" + uname +"';";
		    st.executeUpdate(str);
			System.out.println("user registered successfully");
		}else{
		 System.out.println("username already exists\nPlease sign in to your account");
		}
		
    }
	public ResultSet dbUserValidation(String username ,String password) throws Exception {
		
		String query = "select account_no , u_name  from userData where userId = '"+username + "' and u_password = '"+password+"';";
		PreparedStatement pst = this.connect().prepareStatement(query);
		ResultSet rs = pst.executeQuery();
		 return rs;
	}
	   private boolean doesUsernameExists(String username) throws SQLException {
		String query = "select account_no , u_name from userData where userId = ?"; 
		PreparedStatement pst = this.connect().prepareStatement(query);
		pst.setString(1, username);
		ResultSet res = pst.executeQuery();	
		if(res.next()) {
			return true;
		}
		return false;
	}
	public void dbAddMoney(int amount, String username) throws SQLException {
		CallableStatement cStatement ;
		cStatement = this.connect().prepareCall("{call spAddAmount(?,?)}");
		cStatement.setString(1,username);
		cStatement.setInt(2, amount);
		cStatement.execute();
    } 
     public  ResultSet fetchData(String uname) throws SQLException{
		String query = " select account_no , u_name , balance from vWfetchData where userId = ?";
	    PreparedStatement pst = this.connect().prepareStatement(query);
		pst.setString(1, uname);
		ResultSet rs = pst.executeQuery();
		return rs;
    }
     public void dbWithdrawMoney(int amount, String username ,String password) throws SQLException {
		CallableStatement cStatement ;
		cStatement = this.connect().prepareCall("{call spWithdrawMoney(?,?,?)}");
		cStatement.setInt(1, amount);
		cStatement.setString(2,username);
		cStatement.setString(3,password);
		cStatement.execute();
    } 
     public void dbTransferMoney(int account_no , int amount, String username ,String password) throws SQLException{
		CallableStatement cStatement ;
		cStatement = this.connect().prepareCall("{call spTransferMoney(?,?,?,?)}");
		cStatement.setInt(1,account_no);
		cStatement.setInt(2, amount);
		cStatement.setString(3,username);
		cStatement.setString(4,password);
		cStatement.execute();
	}
	
}



