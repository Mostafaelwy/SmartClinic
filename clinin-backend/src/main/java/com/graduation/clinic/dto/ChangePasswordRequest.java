package com.graduation.clinic.dto;

import jakarta.validation.constraints.NotNull;

public class ChangePasswordRequest {

	@NotNull
	private String oldPassword;
	@NotNull
	private String newPassword;
	
	public ChangePasswordRequest(@NotNull String oldPassword, @NotNull String newPassword) {
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}
	
	
}
