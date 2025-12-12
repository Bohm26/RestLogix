package services;

import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import models.Inventory;
import models.IngredientRecipe;
import utils.JPAUtil;

/**
 *
 * @author joaopedro
 */
public class AuthService {

	public class ValidityAlert {

		public static void checkExpiredItems() {

			try (EntityManager em = JPAUtil.getEntityManager()) {
				LocalDate today = LocalDate.now();

				List<Inventory> items = em.createQuery(
						"SELECT i FROM Inventory i WHERE i.expiryDate < :today", Inventory.class)
						.setParameter("today", today)
						.getResultList();

				if (!items.isEmpty()) {

					StringBuilder message = new StringBuilder("Itens vencidos:\n");

					for (Inventory item : items) {
						message.append(item.getName())
								.append(" - Venceu em: ")
								.append(item.getExpiryDate())
								.append("\n");
					}

					JOptionPane.showMessageDialog(null, message.toString(), "Alerta de Validade", JOptionPane.WARNING_MESSAGE);
				}
			}
		}
	}
	

	public class LowStockAlert {

		public static void checkLowStock() {

			try (EntityManager em = JPAUtil.getEntityManager()) {
				
				List<Inventory> items = em.createQuery(
						"SELECT i FROM Inventory i WHERE i.quantity < i.minStock", Inventory.class)
						.getResultList();

				if (!items.isEmpty()) {
					StringBuilder message = new StringBuilder("Itens com estoque baixo:\n");
					for (Inventory item : items) {
						message.append(item.getName())
								.append(" - Quantidade: ")
								.append(item.getQuantity())
								.append(" (Mínimo: ")
								.append(item.getMin_stock())
								.append(")\n");
					}
					JOptionPane.showMessageDialog(null, message.toString(), "Alerta de Estoque Baixo", JOptionPane.WARNING_MESSAGE);
				}
			}
		}
	}

	
	public class RecipeCalculator {

		public static Map<String, Double> calculateIngredients(int recipeId) {

			try (EntityManager em = JPAUtil.getEntityManager()) {
				Map<String, Double> ingredients = new HashMap<>();

				List<IngredientRecipe> IngredientRecipe = em.createQuery(
						"SELECT ri FROM RecipeIngredient ri WHERE ri.recipe.id = :recipeId", IngredientRecipe.class)
						.setParameter("recipeId", recipeId)
						.getResultList();

				for (IngredientRecipe ri : IngredientRecipe) {
					ingredients.put(ri.getIngredient().getName(), ri.getQuantity());
				}

				return ingredients;

			}
		}

		// Método para mostrar os ingredientes em uma JOptionPane
		public static void showRecipeIngredients(int recipeId) {

			Map<String, Double> ingredients = calculateIngredients(recipeId);
			StringBuilder message = new StringBuilder("Ingredientes necessários:\n");

			for (Map.Entry<String, Double> entry : ingredients.entrySet()) {
				message.append(entry.getKey())
						.append(": ")
						.append(entry.getValue())
						.append("\n");
			}

			JOptionPane.showMessageDialog(null, message.toString(), "Ingredientes da Receita", JOptionPane.INFORMATION_MESSAGE);
		}

		
		public class ValidityAlert {

			public static void checkExpiredItems() {
				
				try (EntityManager em = JPAUtil.getEntityManager()) {
					LocalDate today = LocalDate.now();

					List<Inventory> items = em.createQuery(
						"SELECT i FROM Inventory i WHERE i.expiryDate < :today", Inventory.class)
						.setParameter("today", today)
						.getResultList();

					if (!items.isEmpty()) {
						
						StringBuilder message = new StringBuilder("Itens vencidos:\n");
						
						for (Inventory item : items) {
							message.append(item.getName())
								.append(" - Venceu em: ")
								.append(item.getExpiryDate())
								.append("\n");
						}
						
						JOptionPane.showMessageDialog(null, message.toString(), "Alerta de Validade", JOptionPane.WARNING_MESSAGE);
					}
					
				}
			}
		}

	}
}
