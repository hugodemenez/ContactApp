/**
 * 
 */
package isen.contactApp.entities;

import ezvcard.VCard;
import ezvcard.property.*;

import java.sql.Date; //Format AAAA-MM-JJ
import java.time.LocalDate;

/**
 * @author Quentin DUMESGE, Hugo DEMENEZ, Alban DUHAMEL
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
	private String gender;

	// Default constructor
	public Contact(){
		this.lastname = "lastname";
		this.firstname = "firstname";
		this.nickname = "nickname";
		this.phone_number = "phone_number";
		this.address = "address";
		this.email_address = "email_address";
		this.birth_date = Date.valueOf(LocalDate.now());
		this.gender = "Man";
	}

	// Constructor without id
	public Contact(String lastname, String firstname, String nickname, String phone_number,
			String address, String email_address, Date birth_date, String gender) {
		this.lastname = lastname;
		this.firstname = firstname;
		this.nickname = nickname;
		this.phone_number = phone_number;
		this.address = address;
		this.email_address = email_address;
		this.birth_date = birth_date;
		this.gender = gender;
	}

	// Full constructor
	public Contact(int idPerson, String lastname, String firstname, String nickname, String phone_number,
				   String address, String email_address, Date birth_date,String gender) {
		this.idPerson = idPerson;
		this.lastname = lastname;
		this.firstname = firstname;
		this.nickname = nickname;
		this.phone_number = phone_number;
		this.address = address;
		this.email_address = email_address;
		this.birth_date = birth_date;
		this.gender = gender;
	}


	public VCard generateVcard(){
		VCard vcard = new VCard();

		// Setting up name
		StructuredName n = new StructuredName();
		n.setFamily(lastname);
		n.setGiven(firstname);

		// Adding name to vcard
		vcard.setStructuredName(n);

		// Adding gender to vcard
		vcard.setGender(new Gender(gender));

		// Adding nickname to vcard
		vcard.setNickname(nickname);

		// Setting birthdate
		vcard.setBirthday(new Birthday(birth_date));

		// Setting up right format  for address
		Address addressVCard = new Address();
		addressVCard.setStreetAddress(address);
		vcard.addAddress(addressVCard);

		// Adding email address
		vcard.addEmail(email_address);

		// Adding phone number
		vcard.addTelephoneNumber(new Telephone(phone_number));

		return vcard;

	}


	//Getter and Setter
	public String getGender(){
		return gender;
	}

	public int getIdperson() {
		return idPerson;
	}

	public void setIdPerson(int idPerson) {
		this.idPerson = idPerson;
	}

	public String getLastname() {
		return lastname;
	}

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


    public String getAvatarName() {
		return switch (gender) {
			case "man" -> "user_man";
			case "woman" -> "user_woman";
			default -> "user_unset";
		};
    }
}
