package services;

import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JOptionPane;
import models.InventoryMovement;
import utils.JPAUtil;

/**
 *
 * @author joaopedro
 */
public class ReportService {

	public class MonthlyReport {

		public static void generateMonthlyReport() {
			
			try (EntityManager em = JPAUtil.getEntityManager()) {
				
				LocalDate startDate = LocalDate.now().withDayOfMonth(1);
				LocalDate endDate = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth());

				List<InventoryMovement> movements = em.createQuery(
					"SELECT m FROM InventoryMovement m WHERE m.movementDate BETWEEN :start AND :end "
					+ "ORDER BY m.movementDate", InventoryMovement.class)
					.setParameter("start", startDate)
					.setParameter("end", endDate)
					.getResultList();

				if (!movements.isEmpty()) {
					
					StringBuilder report = new StringBuilder("Relatório Mensal:\n\n");
					double totalInbound = 0;
					double totalOutbound = 0;

					for (InventoryMovement mov : movements) {
						
						if (mov.getType().equals("INBOUND")) {
							totalInbound += mov.getQuantity();
						}
						else {
							totalOutbound += mov.getQuantity();
						}

						report.append(mov.getMovementDate())
							.append(" - ")
							.append(mov.getInventory().getName())
							.append(" - ")
							.append(mov.getType())
							.append(": ")
							.append(mov.getQuantity())
							.append("\n");
					}

					report.append("\nTotal Entrada: ").append(totalInbound)
						.append("\nTotal Saída: ").append(totalOutbound)
						.append("\nSaldo Final: ").append(totalInbound - totalOutbound);

					JOptionPane.showMessageDialog(null, report.toString(), "Relatório Mensal", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(null, "Nenhuma movimentação encontrada este mês.", "Relatório Mensal", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
	}

}
