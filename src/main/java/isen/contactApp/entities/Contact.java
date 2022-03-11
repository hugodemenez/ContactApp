/**
 * 
 */
package isen.contactApp.entities;

import java.sql.Date; //Format AAAA-MM-JJ
import java.time.LocalDate;

/**
 * @author dumes
 *
 */
public class Contact {

	private int idPerson;
	private String lastname;
	private String firstname;
	private String nickname;
	private String phone_number;
	private String address;
	private String email_address;
	private Date birth_date;
	private Boolean selected;
	private String avatarName;

	// Default constructor
	public Contact(){
		this.lastname = "lastname";
		this.firstname = "firstname";
		this.nickname = "nickname";
		this.phone_number = "phone_number";
		this.address = "address";
		this.email_address = "email_address";
		this.birth_date = Date.valueOf(LocalDate.now());
		this.selected = false;
	}

	// Constructor without id
	public Contact(String lastname, String firstname, String nickname, String phone_number,
			String address, String email_address, Date birth_date) {
		this.lastname = lastname;
		this.firstname = firstname;
		this.nickname = nickname;
		this.phone_number = phone_number;
		this.address = address;
		this.email_address = email_address;
		this.birth_date = birth_date;
		this.selected = false;
	}

	// Full constructor
	public Contact(int idPerson, String lastname, String firstname, String nickname, String phone_number,
				   String address, String email_address, Date birth_date) {
		this.idPerson = idPerson;
		this.lastname = lastname;
		this.firstname = firstname;
		this.nickname = nickname;
		this.phone_number = phone_number;
		this.address = address;
		this.email_address = email_address;
		this.birth_date = birth_date;
		this.selected = false;
	}



	//Getter and Setter
	public int getIdperson() {
		return idPerson;
	}
	public String getLastname() {return lastname;}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail_address() {
		return email_address;
	}
	public void setEmail_address(String email_address) {
		this.email_address = email_address;
	}
	public Date getBirth_date() {
		return birth_date;
	}
	public void setBirth_date(Date birth_date) {
		this.birth_date = birth_date;
	}

	public Boolean getSelected() {
		return selected;
	}

    public String getAvatarName() {
		return "user_male";
    }
}
