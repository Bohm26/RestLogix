/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author joaopedro
 */
import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtils {

	private static final int BCRYPT_ROUNDS = 12;

	public static String hashPassword(String plainPassword) {
		if (plainPassword == null || plainPassword.trim().isEmpty()) {
			throw new IllegalArgumentException("Senha não pode ser vazia");
		}
		return BCrypt.hashpw(plainPassword, BCrypt.gensalt(BCRYPT_ROUNDS));
	}

	public static boolean checkPassword(String plainPassword, String hashedPassword) {
		if (plainPassword == null || hashedPassword == null) {
			return false;
		}
		try {
			return BCrypt.checkpw(plainPassword, hashedPassword);
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean isPasswordStrong(String password) {
		if (password == null || password.length() < 8) {
			return false;
		}

		boolean hasUpper = false;
		boolean hasLower = false;
		boolean hasDigit = false;

		for (char c : password.toCharArray()) {
			if (Character.isUpperCase(c)) {
				hasUpper = true;
			}
			if (Character.isLowerCase(c)) {
				hasLower = true;
			}
			if (Character.isDigit(c)) {
				hasDigit = true;
			}
		}

		return hasUpper && hasLower && hasDigit;
	}

	public static String generateRandomPassword(int length) {
		if (length < 8) {
			length = 8;
		}

		String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String lower = "abcdefghijklmnopqrstuvwxyz";
		String digits = "0123456789";
		String all = upper + lower + digits;

		StringBuilder password = new StringBuilder();

		// Garante pelo menos um de cada tipo
		password.append(upper.charAt((int) (Math.random() * upper.length())));
		password.append(lower.charAt((int) (Math.random() * lower.length())));
		password.append(digits.charAt((int) (Math.random() * digits.length())));

		// Completa com caracteres aleatórios
		for (int i = 3; i < length; i++) {
			password.append(all.charAt((int) (Math.random() * all.length())));
		}

		// Embaralha a senha
		char[] array = password.toString().toCharArray();
		for (int i = array.length - 1; i > 0; i--) {
			int j = (int) (Math.random() * (i + 1));
			char temp = array[i];
			array[i] = array[j];
			array[j] = temp;
		}

		return new String(array);
	}
}
