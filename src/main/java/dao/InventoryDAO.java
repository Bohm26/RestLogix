package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import java.util.List;
import javax.swing.JOptionPane;
import models.Inventory;
import utils.JPAUtil;

/**
 *
 * @author joaopedro
 */
public class InventoryDAO {

	public void create(Inventory item) {

		EntityManager em = JPAUtil.getEntityManager();

		try {
			em.getTransaction().begin();
			em.persist(item);
			em.getTransaction().commit();

		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}

			JOptionPane.showMessageDialog(null, "Erro ao criar item no inventário: " + e);

		} finally {
			em.close();
		}
	}

	public Inventory read(Long id) {

		EntityManager em = JPAUtil.getEntityManager();

		try {
			return em.find(Inventory.class, id);
		} finally {
			em.close();
		}
	}

	public List<Inventory> readALL() {

		EntityManager em = JPAUtil.getEntityManager();
		try {
			TypedQuery<Inventory> query = em.createQuery("SELECT i FROM Inventory i", Inventory.class);
			return query.getResultList();
		} finally {
			em.close();
		}
	}

	public Inventory update(Inventory item) {

		EntityManager em = JPAUtil.getEntityManager();

		try {
			em.getTransaction().begin();
			Inventory updatedItem = em.merge(item);
			em.getTransaction().commit();
			return updatedItem;

		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}

			throw new PersistenceException("Erro ao atualizar item no inventário: " + e);
		} finally {
			em.close();
		}
	}

	public void delete(Long id) {

		EntityManager em = JPAUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			Inventory item = em.find(Inventory.class, id);
			if (item != null) {
				em.remove(item);
			}
			em.getTransaction().commit();

		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}

		} finally {
			em.close();
		}
	}

	// Verifica se o ingrediente está sendo usado em alguma receita
	public boolean isUsedInRecipes(Long ingredientId) {
		EntityManager em = JPAUtil.getEntityManager();
		try {
			Long count = em.createQuery(
					"SELECT COUNT(ri) FROM recipe_ingredients ri WHERE ri.ingredient.id = :id",
					Long.class)
					.setParameter("id", ingredientId)
					.getSingleResult();
			return count > 0;
		} finally {
			em.close();
		}
	}

	public Inventory findByName(String name) {
		EntityManager em = JPAUtil.getEntityManager();
		try {
			TypedQuery<Inventory> query = em.createQuery(
					"SELECT i FROM Inventory i WHERE i.name = :name", Inventory.class);
			query.setParameter("name", name);
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} finally {
			em.close();
		}
	}
}
