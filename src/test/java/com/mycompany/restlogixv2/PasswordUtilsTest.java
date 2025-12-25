/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.restlogixv2;

/**
 *
 * @author joaopedro
 */
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import com.mycompany.restlogixv2.util.PasswordUtils;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes da Classe PasswordUtils")
class PasswordUtilsTest {

	@Test
	@DisplayName("Teste 1: Hash de senha não deve retornar nulo")
	void testHashPassword_NotNull() {
		// Arrange
		String password = "MinhaSenha123";

		// Act
		String hashed = PasswordUtils.hashPassword(password);

		// Assert
		assertNotNull(hashed);
		assertFalse(hashed.isEmpty());
	}

	@Test
	@DisplayName("Teste 2: Verificar senha correta")
	void testCheckPassword_Correct() {
		// Arrange
		String password = "SenhaForte123!";
		String hashed = PasswordUtils.hashPassword(password);

		// Act
		boolean result = PasswordUtils.checkPassword(password, hashed);

		// Assert
		assertTrue(result);
	}

	@Test
	@DisplayName("Teste 3: Verificar senha incorreta")
	void testCheckPassword_Incorrect() {
		// Arrange
		String correctPassword = "SenhaForte123!";
		String wrongPassword = "SenhaErrada456!";
		String hashed = PasswordUtils.hashPassword(correctPassword);

		// Act
		boolean result = PasswordUtils.checkPassword(wrongPassword, hashed);

		// Assert
		assertFalse(result);
	}

	@Test
	@DisplayName("Teste 4: Verificar senha nula")
	void testCheckPassword_Null() {
		// Arrange
		String hashed = PasswordUtils.hashPassword("algumaSenha");

		// Act
		boolean result = PasswordUtils.checkPassword(null, hashed);

		// Assert
		assertFalse(result);
	}

	@Test
	@DisplayName("Teste 5: Hash nulo")
	void testCheckPassword_NullHash() {
		// Act
		boolean result = PasswordUtils.checkPassword("senha", null);

		// Assert
		assertFalse(result);
	}

	@ParameterizedTest
	@ValueSource(strings = {
		"Senha123",
		"ABCDefgh123",
		"A1b2C3d4E5f6",
		"Teste@12345"
	})
	@DisplayName("Teste 6: Senhas fortes (parametrizado)")
	void testIsPasswordStrong_Strong(String password) {
		// Act
		boolean result = PasswordUtils.isPasswordStrong(password);

		// Assert
		assertTrue(result, "Senha deve ser forte: " + password);
	}

	@ParameterizedTest
	@ValueSource(strings = {
		"123",
		"senhafraca",
		"SENHAFRACA",
		"12345678",
		"Aa1" // Muito curta
	})
	@DisplayName("Teste 7: Senhas fracas (parametrizado)")
	void testIsPasswordStrong_Weak(String password) {
		// Act
		boolean result = PasswordUtils.isPasswordStrong(password);

		// Assert
		assertFalse(result, "Senha deve ser fraca: " + password);
	}

	@Test
	@DisplayName("Teste 8: Gerar senha aleatória")
	void testGenerateRandomPassword() {
		// Act
		String password = PasswordUtils.generateRandomPassword(10);

		// Assert
		assertNotNull(password);
		assertEquals(10, password.length());
		assertTrue(PasswordUtils.isPasswordStrong(password));
	}

	@Test
	@DisplayName("Teste 9: Fluxo completo de senha")
	void testCompletePasswordFlow() {
		// 1. Gerar senha aleatória
		String password = PasswordUtils.generateRandomPassword(12);
		assertNotNull(password);
		assertTrue(PasswordUtils.isPasswordStrong(password));

		// 2. Fazer hash
		String hashed = PasswordUtils.hashPassword(password);
		assertNotNull(hashed);
		assertNotEquals(password, hashed);

		// 3. Verificar senha correta
		boolean correctCheck = PasswordUtils.checkPassword(password, hashed);
		assertTrue(correctCheck);

		// 4. Verificar senha incorreta
		boolean wrongCheck = PasswordUtils.checkPassword("senhaErrada", hashed);
		assertFalse(wrongCheck);

		System.out.println("Fluxo de senha completo testado com sucesso!");
		System.out.println("Senha original: " + password);
		System.out.println("Hash gerado: " + hashed.substring(0, 20) + "...");
	}
}
