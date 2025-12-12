package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import models.RecipeIngredients;
import utils.JPAUtil;

/**
 *
 * @author joaopedro
 */
public class RecipeIngredientsDAO {

	public void save(RecipeIngredients recipeIngredient) {
		EntityManager em = JPAUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(recipeIngredient);
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

	public Optional<RecipeIngredients> findByRecipeAndIngredient(Long recipeId, Long ingredientId) {
		EntityManager em = JPAUtil.getEntityManager();
		try {
			TypedQuery<RecipeIngredients> query = em.createQuery(
					"SELECT ri FROM RecipeIngredients ri "
					+ "WHERE ri.recipe.id = :recipeId AND ri.ingredient.id = :ingredientId",
					RecipeIngredients.class);
			query.setParameter("recipeId", recipeId);
			query.setParameter("ingredientId", ingredientId);
			return Optional.ofNullable(query.getSingleResult());
		} catch (NoResultException e) {
			return Optional.empty();
		} finally {
			em.close();
		}
	}

	public List<RecipeIngredients> findAllByRecipe(Long recipeId) {
		EntityManager em = JPAUtil.getEntityManager();
		try {
			TypedQuery<RecipeIngredients> query = em.createQuery(
					"SELECT ri FROM RecipeIngredients ri "
					+ "WHERE ri.recipe.id = :recipeId",
					RecipeIngredients.class);
			query.setParameter("recipeId", recipeId);
			return query.getResultList();
		} finally {
			em.close();
		}
	}

	public RecipeIngredients update(RecipeIngredients recipeIngredient) {
		EntityManager em = JPAUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			RecipeIngredients updated = em.merge(recipeIngredient);
			em.getTransaction().commit();
			return updated;
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			throw e;
		} finally {
			em.close();
		}
	}

	public void delete(Long id) {
		EntityManager em = JPAUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			RecipeIngredients ri = em.find(RecipeIngredients.class, id);
			if (ri != null) {
				em.remove(ri);
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

}
