package models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User {
	private StringProperty name;
	private StringProperty mail;
	public User(String name, String mail) {
		this.name=new SimpleStringProperty(name);
		this.mail=new SimpleStringProperty(mail);	
	}
	public void setName(String name) {
		this.name.set(name);
	}
	public String getName() {
		return this.name.get();
	}
	public StringProperty nameProperty() {
		return this.name;
	}
	public void setMail(String mail) {
		this.mail.set(mail);
	}
	public String getMail() {
		return this.mail.get();
	}
	public StringProperty mailProperty() {
		return this.mail;
	}
}
