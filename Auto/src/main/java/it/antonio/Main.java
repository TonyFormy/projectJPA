package it.antonio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {

	private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
			.createEntityManagerFactory("antonioMysql");

	public static void main(String[] args) {

		create("trf", "audi","a4" );
		//update(, "", ); 	
		//delete(1);


		ENTITY_MANAGER_FACTORY.close();
	}



	public static void create(String targa, String marca, String modello) {
		EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction transaction = null;

		try {

			transaction = manager.getTransaction();

			transaction.begin();

			Auto auto = new Auto();
			auto.setTarga(targa);
			auto.setMarca(marca);
			auto.setModello(modello);

			manager.persist(auto);
			transaction.commit();
			
		} catch (Exception ex) {

			if (transaction != null) {
				transaction.rollback();
			}

			ex.printStackTrace();
		} finally {

			manager.close();
		}
	}


	public static List<Auto> readAll() {

		List<Auto> autos = null;

		// Create an EntityManager
		EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction transaction = null;

		try {

			transaction = manager.getTransaction();

			transaction.begin();
			
			autos = manager.createNativeQuery("SELECT * FROM Auto").getResultList();
			

			transaction.commit();
		} catch (Exception ex) {

			if (transaction != null) {
				transaction.rollback();
			}

			ex.printStackTrace();
		} finally {

			manager.close();
		}
		return autos;
	}


	public static void delete(String targa) {

		EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction transaction = null;

		try {

			transaction = manager.getTransaction();

			transaction.begin();

			Auto autos = manager.find(Auto.class, targa);

			manager.remove(autos);

			transaction.commit();
		} catch (Exception ex) {

			if (transaction != null) {
				transaction.rollback();
			}

			ex.printStackTrace();
		} finally {

			manager.close();
		}
	}


	public static void update(String targa, String marca, String modello) {

		EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction transaction = null;

		try {

			transaction = manager.getTransaction();
			transaction.begin();

			Auto autos = manager.find(Auto.class, targa);
			autos.setMarca(marca);
			autos.setModello(modello);
			manager.persist(autos);
			transaction.commit();
		} catch (Exception ex) {

			if (transaction != null) {
				transaction.rollback();
			}

			ex.printStackTrace();
		} finally {
			manager.close();
		}
	}
}
