
/**
 * This class represent the Panel
 * extends JPanel
 * 
 * @author Lior Maimon 
 * mmn14 , Question 2
 */
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class PhoneBookPanel extends JPanel {
	private PhoneBook book;
	JPanel bookJPanel;
	JPanel buttonsJPanel;
	JButton addButton;
	JButton removeButton;
	JButton editButton;
	JButton searchButton;
	JButton saveButton;
	JButton loadButton;
	JTextArea textArea;
	JScrollPane scroll;
	/**
	 * construct a new Panel represent the phone book
	 */
	public PhoneBookPanel(){
		book = new PhoneBook();
		setLayout(new BorderLayout());
		buttonsJPanel = new JPanel(new GridLayout(2,3));
		bookJPanel = new JPanel();
		textArea = new JTextArea(book.toString(),15,20);
		textArea.setFont(new Font("Serif",Font.PLAIN,15));
		textArea.setEditable(false);
	    scroll = new JScrollPane(textArea);
	    scroll.setVerticalScrollBarPolicy (ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		bookJPanel.add(scroll);
		add(bookJPanel , BorderLayout.CENTER); //add the book panel 
		addButton = new JButton("Add Number");
		removeButton = new JButton("Remove Number");
		editButton = new JButton("Edit Number");
		searchButton = new JButton("Search Number");
		saveButton = new JButton("Save Phone Book");
		loadButton = new JButton("Load Phone Book");
		addButton.addActionListener(new ButtonListener());
		removeButton.addActionListener(new ButtonListener());
		editButton.addActionListener(new ButtonListener());
		searchButton.addActionListener(new ButtonListener());
		saveButton.addActionListener(new ButtonListener());
		loadButton.addActionListener(new ButtonListener());
		buttonsJPanel.add(addButton);
		buttonsJPanel.add(removeButton);
		buttonsJPanel.add(editButton);
		buttonsJPanel.add(searchButton);
		buttonsJPanel.add(saveButton);
		buttonsJPanel.add(loadButton);
		add(buttonsJPanel , BorderLayout.SOUTH); //add the buttons panel
		
	}
	/**
	 * method paintComponent
	 */
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
	}
	//private method ButtomListener - bottoms performers implements ActionListener
	private class ButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent event) {
			String name;
			String num;
			Object src = event.getSource();
			
			if(src == addButton){//if button pressed is add
				name = JOptionPane.showInputDialog("Please enter the contact name");
				num = JOptionPane.showInputDialog("Please enter the contact phone");
				book.add(name, num);
				textArea.setText(book.toString());
			}
			else if(src == removeButton){//if button pressed is remove
				name = JOptionPane.showInputDialog("Please enter the contact name you want to remove");
				if(book.remove(name)){
					JOptionPane.showMessageDialog(null, name + " Phone Number is removed");
					textArea.setText(book.toString());
				}
				else JOptionPane.showMessageDialog(null, name + " is not in the phone book");
			}
			else if(src == editButton){//if button pressed is edit
				name = JOptionPane.showInputDialog("Please enter the contact name you want to edit");
				num = JOptionPane.showInputDialog("Please enter the contact new phone");
				if(book.edit(name, num)){
					JOptionPane.showMessageDialog(null, name + " Phone Number is now " + num);
					textArea.setText(book.toString());
				}
				else JOptionPane.showMessageDialog(null, name + " is not in the phone book, or the new number is not vallid");
			}
			else if(src == searchButton){//if button pressed is search
				name = JOptionPane.showInputDialog("Please enter the contact name you want to search");
				num = book.search(name);
				if(num != null){
					JOptionPane.showMessageDialog(null, name + " Phone Number is " + num);
				}
				else JOptionPane.showMessageDialog(null, "The contact is not on the Phone Book");
				
			}
			else if(src == saveButton){//if button pressed is save
				if(PhoneBook.saveBook(book)){
					JOptionPane.showMessageDialog(null, "The Phone Book is saved");
				}
				else JOptionPane.showMessageDialog(null, "Error, not saved");
			}
			else{ //if button pressed is load
				PhoneBook.loadBook(book);
				textArea.setText(book.toString());
				
			}
		}
		
	}
}
