import java.util.*;
public class Contact {
	private String name;
	private String email;
	private String phone;
	private String notes;
	private int ID;
	private static int count=0;
	
	Contact(String name, String email,
			String phone, String notes){
		this.name=name;
		this.email=email;
		this.phone=phone;
		this.notes=notes;
		this.ID=++count;
	}
	
	//---------------Getters and Setters---------------

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	public int getID() {
		return this.ID;
	}
	

	public String toString() {
		return String.format("%-3s | %-25s | %-25s | %-25s | %-25s", this.ID,this.name, this.email,this.phone,this.notes);
		
	}
	
	
}
