package com.mosh.trbox.model.response;

import com.google.gson.annotations.SerializedName;

public class LoginResponse{

	@SerializedName("access_token")
	private String accessToken;

	@SerializedName("scope")
	private String scope;

	@SerializedName("token_type")
	private String tokenType;

	@SerializedName("expires_in")
	private int expiresIn;


	@SerializedName("error_description")
	private String errorDescription;

	public LoginResponse(String errorDescription) {
		this.errorDescription = errorDescription;
	}


	public String getAccessToken(){
		return accessToken;
	}

	public String getScope(){
		return scope;
	}

	public String getTokenType(){
		return tokenType;
	}

	public int getExpiresIn(){
		return expiresIn;
	}

	public String getErrorDescription() {
		return errorDescription;
	}
}