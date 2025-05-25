
package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import java.util.List;
import models.Recipe;
import utils.JPAUtil;

/**
 *
 * @author joaopedro
 */

public class RecipeDAO {

	// Salva uma receita e seus ingredientes
	public void save(Recipe recipe) {
		EntityManager em = JPAUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(recipe);		// Passa a receita e os ingredientes (método cascade)
			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			throw e;
		} finally {
			em.close();
		}
	}

	// Busca a receita pelo ID
	public Recipe findById(Long id) {
		
		EntityManager em = JPAUtil.getEntityManager();
		try {
			return em.find(Recipe.class, id);
		} finally {
			em.close();
		}
	}

	// Lista todas as receitas
	public List<Recipe> findAll() {
		EntityManager em = JPAUtil.getEntityManager();
		try {
			TypedQuery<Recipe> query = em.createQuery("SELECT r FROM Recipe r", Recipe.class);
			return query.getResultList();
		} finally {
			em.close();
		}
	}

	//Procura as receitas pelo nome
	public List<Recipe> findByName(String name) {
		EntityManager em = JPAUtil.getEntityManager();
		try {
			
			TypedQuery<Recipe> query = em.createQuery(
			"SELECT r FROM Recipe r WHERE LOWER(r.name) LIKE LOWER(:name)", Recipe.class);
			query.setParameter("name", "%" + name + "%");
			return query.getResultList();
		} finally {
			em.close();
		}
	}
	

	// Atualiza uma receita já cadastrada
	public void update(Recipe recipe) {
		EntityManager em = JPAUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(recipe);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

	//Exclui uma receita existente procurando pelo id dela
	public void delete(Long id) {
		EntityManager em = JPAUtil.getEntityManager();

		try {
			em.getTransaction().begin();
			Recipe recipe = em.find(Recipe.class, id);
			if (recipe != null) {
				em.remove(recipe);
			}
			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			throw e;
		} finally {
			em.close();
		}

	}

	public Recipe findByIdWithIngredients(Long id) {
		EntityManager em = JPAUtil.getEntityManager();
		try {
			TypedQuery<Recipe> query = em.createQuery(
					"SELECT DISTINCT r FROM Recipe r "
					+ "LEFT JOIN FETCH r.recipeIngredients ri "
					+ "LEFT JOIN FETCH ri.ingredient "
					+ "WHERE r.id = :id", Recipe.class);
			query.setParameter("id", id);
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} finally {
			em.close();
		}
	}

}
