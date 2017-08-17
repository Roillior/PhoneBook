
/**
 * This class represent the main method
 * 
 * @author Lior Maimon 
 * mmn14 , Question 2
 */

import javax.swing.JFrame;
import javax.swing.UIManager;

public class PhoneBookPanelTest {
	//main method of the program
	public static void main(String[] args) {
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e){}
		
		JFrame frame = new JFrame("Phone Book");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400,450);
		frame.add(new PhoneBookPanel());
		frame.setVisible(true);
	}

}


