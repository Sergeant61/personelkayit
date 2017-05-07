package recep.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import recep.entity.HangiKurs;
import recep.entity.Person;
import recep.entity.SorgulaEntity;
import recep.io.Config;

public class DAO {

	private SessionFactory sessionFactory;
	private Configuration configuration;

	private static DAO uniqueInstance;

	public static DAO getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new DAO();
		}
		return uniqueInstance;
	}

	public static void setInstance() {
		if (uniqueInstance != null) {
			uniqueInstance = null;
		}
	}

	public DAO() {
		try {
			configuration = new Configuration();
			configuration.configure("hibernate.cfg.xml"); // hibernate config
															// xml file name

			String[] urlTxt = Config.configOku();

			configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
			configuration.setProperty("hibernate.connection.url", urlTxt[0]);
			configuration.setProperty("hibernate.connection.username", urlTxt[1]);
			configuration.setProperty("hibernate.connection.password", urlTxt[2]);
			configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");

			sessionFactory = configuration.buildSessionFactory();

		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Veritabaný Hatasý");
			alert.setHeaderText("Veritabaný Konfigürasyon Hatalý");
			alert.setContentText("Ayarlar bölümünden veritabaný konfigürasyon ayarlarýný yapabilirsiniz.");
			alert.showAndWait();
		}
		// sessionFactory = new
		// Configuration().configure("/recep/resource/hibernate.cfg.xml").buildSessionFactory();
	}

	public void addValue(Object obj) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(obj);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
		} finally {
			session.close();
		}
	}

	public void deleteValue(Object obj) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.delete(obj);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
		} finally {
			session.close();
		}
	}

	public List<HangiKurs> getHangiKursList() {
		Session session = sessionFactory.openSession();
		List<HangiKurs> list = null;
		try {

			list = session.createCriteria(HangiKurs.class).list();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return list;
	}

	public List<Person> sorgula(SorgulaEntity sorgulaEntity) {
		Session session = sessionFactory.openSession();
		List<Person> myList = null;
		try {
			Criteria criteria = session.createCriteria(Person.class);
			if (sorgulaEntity.getAd() != null && sorgulaEntity.getAd().length() > 0)
				criteria.add(Restrictions.ilike("ad", sorgulaEntity.getAd()));

			if (sorgulaEntity.getSoyad() != null && sorgulaEntity.getSoyad().length() > 0)
				criteria.add(Restrictions.ilike("soyad", sorgulaEntity.getSoyad()));

			if (sorgulaEntity.getHangiKurs() != null && sorgulaEntity.getHangiKurs().length() > 0)
				criteria.add(Restrictions.ilike("hangiKurs", sorgulaEntity.getHangiKurs()));

			// criteria.add(Restrictions.between("gorusmeZamani",
			// sorgulaEntity.getBasTarihi(), sorgulaEntity.getBitisTarihi()));
			if (sorgulaEntity.getBasTarihi() != null)
				criteria.add(Restrictions.ge("gorusmeZamani", sorgulaEntity.getBasTarihi()));

			if (sorgulaEntity.getSonTarihi() != null)
				criteria.add(Restrictions.lt("gorusmeZamani", sorgulaEntity.getSonTarihi()));

			if (sorgulaEntity.getType() != 0)
				criteria.add(Restrictions.eq("gorusmeTipi", sorgulaEntity.getType()));

			myList = (List<Person>) criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return myList;
	}

	public List<HangiKurs> sorgulaHangiKurs(String value) {
		Session session = sessionFactory.openSession();
		List<HangiKurs> myList = null;
		try {
			Criteria criteria = session.createCriteria(HangiKurs.class);

			if (value.length() > 0)
				criteria.add(Restrictions.ilike("kursAdi", value));

			myList = (List<HangiKurs>) criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return myList;
	}

}
