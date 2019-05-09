package test;

import java.util.Locale;
import java.util.ResourceBundle;

public class MultilingueBundle {
	
	public static void main(String[] args) {
		 
		try {
			ResourceBundle bundle1 = 
					   ResourceBundle.getBundle("multilingue.Langue", Locale.FRENCH) ;

					ResourceBundle bundle2 = 
					   ResourceBundle.getBundle("multilingue.Langue", 
					                            Locale.ENGLISH) ;

					System.out.println(bundle1.getString("ENI.Encheres")) ;
					System.out.println(bundle2.getString("ENI.Encheres")) ;
			
 
		} catch (Exception e) {
			
			System.out.println("Error retrieving properties file: " + e);
		}
	}

}
