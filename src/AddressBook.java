import java.util.*;

public class AddressBook {
	
	private ArrayList<Contact>book;//book of contacts
	private Scanner in= new Scanner(System.in);
	
	AddressBook(){
		this.book=new ArrayList<Contact>();
		mainMenu();
	}
//-----------------------------------MAIN MENU--------------------------------------------------
	
	private void mainMenu() {
		System.out.println("Main Window:\n=============");
		System.out.print("Choose one of the following options:\n"
				+ "(1) Add a new contact\n(2) Search for contact\n"
				+ "(3) Display all contacts\n(4) Quit\n"
				+ "Enter your Choice: ");
		String choice= in.nextLine();
		switch(choice) {
		case "1":
			addNewContact();
			break; 
		case "2":
			searchForContact();
			break;
		case "3":
			displayAllContacts();
			break;
		case "4":
			System.exit(0);
			break;
		default:
			mainMenu();
		}
		
		
	}
	
//----------------------------------ADD NEW CONTACT---------------------------------------------------	
	private void addNewContact() {
		System.out.println("Main Window --> "
				+ "Add a new contact window: (Enter the following information)\n"
				+ "===========================================================================");
		System.out.print("Name: ");
		String n=in.nextLine()+" ";
		System.out.print("Email: ");
		String e=in.nextLine();
		System.out.print("Phone: ");
		String ph=in.nextLine();
		System.out.print("Notes: ");
		String note=in.nextLine();
		//If there is a repeated user
		if(userExists(ph,e)) {
			System.out.println("\nPress Enter to go back to the Main Window");
		}
		else {
		Contact con=new Contact(n,e,ph,note);
		book.add(con);
		System.out.println("---------------------------------------------------------------------------");
		System.out.println("Saved successfully.....Press Enter to go back to the Main Window");}
		goBackToMainWindow();
	}
	
//----------------------------------SEARCH FOR CONTACT-------------------------------------------------
	private void searchForContact() {
		//If the book is empty go back to main window
		if(book.isEmpty()) {
			System.out.println("Main Window --> "
					+ "Search for Contact window:\n=============================================================================="); 
			System.out.println("Cannot Search: Address Book is empty");
			System.out.println("\nPress Enter to go back to the Main Window");
			goBackToMainWindow();
					
		}
		else {
		System.out.println("Main Window --> "
				+ "Search for Contact window: (Choose one of the following options)\n"
				+ "==============================================================================");
		System.out.println("(1) Search by Name\n(2) Search by Email\n"
				+ "(3) Search by Phone");
		System.out.print("\nEnter Your Choice: ");
		int choice= Integer.parseInt(in.nextLine());
		switch(choice) {
			case 1:
				searchByName();
				break;
			case 2:
				searchByEmail();
				break;
			case 3:
				searchByPhone();
				break;
			}
		}
				
	}
//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^Search Methods^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
	private void searchByName() {
		String selection="Main Window --> Search for Contact window --> Search by Name";
		System.out.println(selection+"\n=======================");
		//Will hold the found contacts
		ArrayList<Integer>id=new ArrayList<>();
		System.out.print("(1) Enter Name: ");
		String name=in.nextLine();
		int count=0;//counts number of found contacts
		System.out.println("\nSearch Results: ");
		System.out.println("--------------------------------------------------------------------------------------------------------------------");
		System.out.printf("%-3s | %-25s | %-25s | %-25s | %-25s\n", "ID", "Name ", "Email","Phone", "Notes");
		System.out.println("--------------------------------------------------------------------------------------------------------------------");
		//Turn to lower case to check
		name=name.toLowerCase();
		String check;
		for(Contact c: this.book) {
			check=c.getName().toLowerCase();
			if(check.contains(name+" ")) {
				count++;
				System.out.println(c);//print out details
				//Add the ID
				id.add(c.getID());
			}		
		}
		//Name not found
		if(count==0) {
			System.out.println("Name not found");
			System.out.println("--------------------------------------------------------------------------------------------------------------------\n");
			System.out.println("\nPress Enter to go back to the Main Window");
			goBackToMainWindow();
		}
		System.out.println("--------------------------------------------------------------------------------------------------------------------\n");
		goBackAfterSearch(id,selection);
		
		}
	private void searchByEmail() {
		String selection="Main Window --> Search for Contact window --> Search by Email";
		System.out.println(selection+"\n=======================");
		ArrayList<Integer>id=new ArrayList<>();//holds the contacts found
		int count=0;//count number of contacts found
		System.out.print("(2) Enter Email: ");
		String email=in.nextLine();
		System.out.println("\nSearch Results: ");
		System.out.println("--------------------------------------------------------------------------------------------------------------------");
		System.out.printf("%-3s | %-25s | %-25s | %-25s | %-25s\n", "ID", "Name ", "Email","Phone", "Notes");
		System.out.println("--------------------------------------------------------------------------------------------------------------------");
		//Find matching email
		email=email.toLowerCase();
		String check;
		for(Contact c: this.book) {
			check=c.getEmail().toLowerCase();
			if(check.equals(email)) {
				System.out.println(c);//print out details
				id.add(c.getID());//add to the list
				count++;
			}
		}
		//Email not found
		if(count==0) {
			System.out.println("Email not found");
			System.out.println("--------------------------------------------------------------------------------------------------------------------\n");
			System.out.println("\nPress Enter to go back to the Main Window");
			goBackToMainWindow();
		}
		System.out.println("--------------------------------------------------------------------------------------------------------------------\n");
		goBackAfterSearch(id,selection);
		
	}

	
	private void searchByPhone() {
		String selection="Main Window --> Search for Contact window --> Search by Phone";
		System.out.println(selection+"\n=======================");
		System.out.print("(3) Enter Phone Number: ");
		ArrayList<Integer>id=new ArrayList<>();//holds the contacts found
		String phone=in.nextLine();
		int count=0;//counts number of contacts found
		System.out.println("\nSearch Results: ");
		System.out.println("--------------------------------------------------------------------------------------------------------------------");
		System.out.printf("%-3s | %-25s | %-25s | %-25s | %-25s\n", "ID", "Name ", "Email","Phone", "Notes");
		System.out.println("--------------------------------------------------------------------------------------------------------------------");
		//Find matching phone number
		for(Contact c: this.book) {
			if(c.getPhone().equals(phone)) {
				System.out.println(c);//print out details
				id.add(c.getID());//add to the list
				count++;
			}
		}
		//Phone number not found
		if(count==0) {
			System.out.println("Phone number not found");
			System.out.println("--------------------------------------------------------------------------------------------------------------------\n");
			System.out.println("\nPress Enter to go back to the Main Window");
			goBackToMainWindow();
		}
		System.out.println("--------------------------------------------------------------------------------------------------------------------\n");
		goBackAfterSearch(id,selection);
		
	}
	
	//After searching ask the user to make 2 choices
	private void goBackAfterSearch(ArrayList<Integer>id, String s) {
		System.out.print("Choose one of these options:\n"
				+ "(1) To delete a contact\n(2) Back to main Window\n\n"
				+ "Enter Your Choice: ");
		int choice=Integer.parseInt(in.nextLine());
		switch(choice) {
		case 1:
			deleteContact(id,s);
		case 2:
			mainMenu();
		default:
			mainMenu();
		}
	}
	
//^^^^^^^^^^^^^^^^^^^^^^^DELETE CONTACT^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
	
	private void deleteContact(ArrayList<Integer>id,String s) {
		System.out.println("\n"+s+" --> Delete a Contact\n=======================");
		System.out.print("Enter the Contact ID: ");
		int n= Integer.parseInt(in.nextLine());
		//If the ID inputed is in the list of found contacts
		if(id.contains(n)) {
			for(Contact c:book) {
			    if(c.getID() == n) {
			    	//Remove the contact
			        book.remove(c);
			        break;}
			}
			System.out.println("Deleted.....press Enter to go back to the Main Window");
		
		}
		else {
			System.out.println("Invalid Contact ID");
			System.out.println("Could not be deleted.....press Enter to go back to the Main Window");}
			
		goBackToMainWindow();
		
	}
//-----------------------------------------DISPLAY ALL CONTACTS-----------------------------------------------
	private void displayAllContacts() {
		System.out.println("Main Window --> Display All Contacts\n"
				+ "=============================");
		System.out.println("--------------------------------------------------------------------------------------------------------------------");
		System.out.printf("%-3s | %-25s | %-25s | %-25s | %-25s\n", "ID", "Name ", "Email","Phone", "Notes");
		System.out.println("--------------------------------------------------------------------------------------------------------------------");
		//If the address book is empty
		if(book.isEmpty()) {
			System.out.println("Address Book is empty");
		}
		else {
		//Sort the address book
		Collections.sort(book, new Comparator<Contact>(){
		    public int compare(Contact c1, Contact c2) {
		        return c1.getName().compareToIgnoreCase(c2.getName());
		    }
		});
		//Print the contacts
		for (Contact c: book) {
			System.out.println(c);
		}
		}
		System.out.println("--------------------------------------------------------------------------------------------------------------------\n");
		System.out.println("\nPress Enter to go back to the Main Window");
		goBackToMainWindow();
	}
	
	
	
	
//---------------------------EXTRA HELPER METHODS------------------------------------
	private void goBackToMainWindow() {
		String enter= in.nextLine(); //get user to press enter
		if(enter!=null)
			mainMenu();
	}
	
	//Checks if the email or phone or both are already in the address book
	//Does not determine if email and phone from same user since
		//an existing phone number or email would not be an eligible contact either way
	private boolean userExists(String phone, String email) {
		boolean phoneExist=false;
		boolean emailExist=false;
		for (Contact c: book) {
			//if phone matches
			if(c.getPhone().equals(phone)) {
				//phone number already exists
				phoneExist=true;
				break;
				}
			}
		
		for (Contact c: book) {
			//if email matches
			if(c.getEmail().toLowerCase().equals(email.toLowerCase())) {
				//email already exists
				emailExist=true;
				break;
			}
		}
		
		if(phoneExist||emailExist) {
			System.out.println("---------------------------------------------------------------------------");
			System.out.print("Contact could not be added: ");
			if(phoneExist&&emailExist) {
				System.out.println("Phone number and email already exist in address book");
			}
			else if(phoneExist) {
				System.out.println("Phone number already exists in address book");
			}
			else if(emailExist) {
				System.out.println("Email already exists in address book");
			}
			return true;
		}
		
		return false;
		
		}
}

	
	
