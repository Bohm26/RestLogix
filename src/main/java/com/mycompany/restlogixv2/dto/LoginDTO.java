/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.restlogixv2.dto;

/**
 *
 * @author joaopedro
 */
public class LoginDTO {

	private String username;
	private String password;

	public LoginDTO() {
	}

	public LoginDTO(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isValid() {
		return username != null && !username.trim().isEmpty()
				&& password != null && !password.trim().isEmpty();
	}
}
