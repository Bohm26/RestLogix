package enums;

import models.User;

/**
 *
 * @author joaopedro
 */
public class UserSession {

		private static UserSession instance;
		private User currentUser;

		private UserSession() {
		} // Construtor privado

		public static synchronized UserSession getInstance() {
			if (instance == null) {
				instance = new UserSession();
			}
			return instance;
		}

		public void login(User user) {
			this.currentUser = user;
		}

		public void logout() {
			this.currentUser = null;
		}

		public User getCurrentUser() {
			return currentUser;
		}

		public boolean isLoggedIn() {
			return currentUser != null;
		}
	}

