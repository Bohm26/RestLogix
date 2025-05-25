package dao;

import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;
import models.InventoryMovement;
import utils.JPAUtil;

/**
 *
 * @author joaopedro
 */
public class InventoryMovementDAO {

	public List<InventoryMovement> findByInventoryAndPeriod(Long inventoryId, LocalDate start, LocalDate end) {
		
		EntityManager em = JPAUtil.getEntityManager(); // Abre o EntityManager

		try {
			return em.createQuery(
					"SELECT m FROM InventoryMovement m WHERE m.inventory.id = :inventoryId "
					+ "AND m.movementDate BETWEEN :start AND :end", InventoryMovement.class)
					.setParameter("inventoryId", inventoryId)
					.setParameter("start", start.atStartOfDay())
					.setParameter("end", end.atTime(23, 59, 59))
					.getResultList();
		} finally {
			
			// Fecha o EntityManager
			em.close(); 
		}
	}
}
