package utils;


import org.mindrot.jbcrypt.BCrypt;
/**
 *
 * @author joaopedro
 */
public class PasswordService {
	    public static String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}

