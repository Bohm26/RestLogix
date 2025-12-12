package dao;

import jakarta.persistence.EntityManager;
import java.util.List;
import models.Supplier;
import utils.JPAUtil;

/**
 *
 * @author joaopedro
 */
public class SupplierDAO {

	public void save(Supplier supplier) {
		EntityManager em = JPAUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(supplier);
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

	public List<Supplier> findAll() {
		EntityManager em = JPAUtil.getEntityManager();
		try {
			return em.createQuery("SELECT s FROM Supplier s", Supplier.class).getResultList();
		} finally {
			em.close();
		}
	}
}
