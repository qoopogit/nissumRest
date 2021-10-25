package net.aigarcia.rest.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Phone {
	@Id
	private String id;
	@Column
	private String number;
	@Column
	private String cityCode;
	@Column
	private String countryCode;

	
	@JoinColumn(name = "idUser", referencedColumnName = "id")
	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	@JsonIgnore
	private User user;

	public Phone() {

	}

	public Phone(String id, String number, String cityCode, String countryCode, User user) {
		super();
		this.id = id;
		this.number = number;
		this.cityCode = cityCode;
		this.countryCode = countryCode;
		this.user = user;
	}

	public Phone(String number, String cityCode, String countryCode) {
		super();
		this.number = number;
		this.cityCode = cityCode;
		this.countryCode = countryCode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
