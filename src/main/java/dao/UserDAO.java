package dao;

import enums.UserRole;
import models.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import utils.PasswordService;

/**
 *
 * @author joaopedro
 */
public class UserDAO {

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("RestLogixPU");

	public User createUser(String fullName, String username, String plainPassword, UserRole role) {
		EntityManager em = emf.createEntityManager();
		User user = new User();
		user.setFullName(fullName);
		user.setUsername(username);
		user.setPassword(plainPassword);
		user.setRole(role);

		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		em.close();

		return user;
	}

	public User authenticate(String username, String plainPassword) {
		EntityManager em = emf.createEntityManager();
		try {
			User user = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
					.setParameter("username", username)
					.getSingleResult();

			if (user != null && PasswordService.checkPassword(plainPassword, user.getPasswordHash())) {
				return user;
			}
			return null;
		} catch (NoResultException e) {
			return null;
		}
	}

	public User findByUsername(String username) {
		EntityManager em = emf.createEntityManager();
		try {
			return em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
					.setParameter("username", username)
					.getSingleResult();
		} finally {
			em.close();
		}
	}

	public void save(User user) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		em.close();
	}

}
