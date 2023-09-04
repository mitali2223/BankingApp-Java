package banking_app;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidation {
	
	public boolean nameValidate(String name) {
		

	
		String result = name.trim().replaceAll("\\s{2,}", " ");        
        String regex = "[^a-zA-Z\\s]";
        
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(result);
        boolean containsSpecialCharacterOrDigits = m.find();

          return  !containsSpecialCharacterOrDigits;	
    }	
	
	public boolean usernameValidate(String username) {
		
	String regex=	"^[a-zA-Z0-9][a-zA-Z0-9@_&][A-Za-z0-9_]+{7,21}$";
		

		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(username);
		Boolean res = m.find();	
		return res;		
    }	
	public boolean passwordValidate(String password) {
		
		
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,20}$";
					
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(password);
		Boolean res = m.find();	
		return res;		
    }	

	public boolean mobileNoValidate(long mobileNo ) {
		String str = Long.toString(mobileNo);
		String regex = "([0|91])?[6-9][0-9]{9}$";
		
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);
		Boolean res = m.find();	
		return res;		
    }	


}
