
/**
 * This class represent Phone Book
 * 
 * @author Lior Maimon 
 * mmn14 , Question 2
 */
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

public class PhoneBook {
	//instance variables
	Map<String , String> book;
	/**
	 * construct a new empty PhoneBook represent the phone book
	 */
	public PhoneBook(){
		book = new HashMap<>();
	}
	/**
	 * method add - add a new contact to the phone book
	 * @param name - The name of the new contact
	 * @param phone - The contact phone
	 */
	public void add(String name, String phone){ 
		String member = name.toLowerCase(); //make all letters small
		if(book.containsKey(member) || name == null || phone == null || phone.length() == 0){
			return;
		}
		else{
			book.put(member, phone);
		}
	}
	/**
	 * method remove - remove a contact from the phone book
	 * @param name - The name of the contact to remove
	 * @return boolean value - true if remove success false otherwise
	 */
	public boolean remove(String name){  //maybe need to add Integer phone
		String member = name.toLowerCase();
		if(book.containsKey(member) ){
			book.remove(member);
			return true;
		}
		return false;
	}
	/**
	 * method edit - edit the phone of the contact from the phone book
	 * @param name - The name of the contact 
	 * @param phone - The new phone number to edit
	 * @return boolean value - true if edit success false otherwise
	 */
	public boolean edit(String name, String phone){ //add a private method check phone contain only digit name only char
		String member = name.toLowerCase();
		if(book.containsKey(member) && phone != null && phone.length() != 0){
			book.put(member, phone);
			return true;
		}
		return false;
	}
	/**
	 * method search - search the contact name in the phone book if find return his phone number
	 * @param name - The name of the contact 
	 * @return String represent the contact phone number or null if failed
	 */
	public String search(String name){
		String member = name.toLowerCase();
		String res = null;
		if(book.containsKey(member)){
			res = book.get(member);
		}
		return res; //phone number on success null on fail
	}
	/**
	 * method saveBook - save the phone book in a file name "myBook.dat"
	 * @param myBook - a PhoneBook object represent the phone book to save
	 * @return boolean value - true if save success false otherwise
	 */
	public static boolean saveBook(PhoneBook myBook){
		Properties table = new Properties();
		table.putAll(myBook.getBook());
		
		try{
			FileOutputStream output = new FileOutputStream("myBook.dat");
			table.store(output, "save phone book");
			output.close();
			return true;
			
		}
		catch(IOException e){
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * method loadBook - load a phone book from file "myBook.dat" into a given PhoneBook object
	 * @param myBook - a PhoneBook object represent the phone book to load to
	 */
	public static void loadBook(PhoneBook myBook){
		try{
			Properties props = new Properties();
			FileInputStream input = new FileInputStream("myBook.dat");
			Set<Object> keys;
			props.load(input);
			input.close();
			keys = props.keySet();
			for(Object key : keys){
				myBook.add((String)key, (props.getProperty((String)key)));
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	/**
	 * method getBook
	 * @return a pointer to the phone book map
	 */
	public Map<String , String> getBook(){
		return this.book;
	}
	/**
	 * method toString
	 * @return String
	 */
	@Override
	public String toString(){
		String str;
		Set<String> keys = this.book.keySet();
		TreeSet<String> sortedKeys = new TreeSet<>(keys);
		str = String.format("%-20s%15s%n", "Name","Phone");
		for(String key : sortedKeys){
			str = String.format("%s"+"%-20s%15s%n", str, key, this.book.get(key));
		}
		return str;		
	}
}
