package com.mosh.trbox.model.request;

import com.google.gson.annotations.SerializedName;

public class RegisterRequest{

	@SerializedName("country")
	private String country;

	@SerializedName("password")
	private String password;

	@SerializedName("confirmpassword")
	private String confirmpassword;

	@SerializedName("email")
	private String email;

	@SerializedName("username")
	private String username;

	public void setCountry(String country){
		this.country = country;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public void setConfirmpassword(String confirmpassword){
		this.confirmpassword = confirmpassword;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public void setUsername(String username){
		this.username = username;
	}
}