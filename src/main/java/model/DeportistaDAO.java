package model;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import entities.Deportista;

public class DeportistaDAO {
	
	private static HibernateUtil hU;
	
	public DeportistaDAO() {
		DeportistaDAO.hU = new HibernateUtil();
	}
	
	public List<Deportista> findAllDeportistas(){
		SessionFactory sf = hU.getSessionFactory();
		Session sess = sf.openSession();
		sess.beginTransaction();
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<Deportista> l = (List<Deportista>)sess.createCriteria(Deportista.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		sess.close();
		return l;
	}
	
	public List<Deportista> getDeportistaByID(int idDeportista) {
		SessionFactory sf = hU.getSessionFactory();
		Session sess = sf.openSession();
		sess.beginTransaction();
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<Deportista> l = sess.createCriteria(Deportista.class).add(Restrictions.idEq(idDeportista)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		sess.close();
		return l;
	}
	
	public List<Deportista> getDeportistaByName(String name) {
		SessionFactory sf = hU.getSessionFactory();
		Session sess = sf.openSession();
		sess.beginTransaction();
		@SuppressWarnings({ "deprecation", "unchecked" })
		List<Deportista> l = sess.createCriteria(Deportista.class).add(Restrictions.ilike("nombre", name, MatchMode.EXACT)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		sess.close();
		return l;
	}
	
	public boolean updateDeportista(Deportista d) {
		SessionFactory sf = hU.getSessionFactory();
		Session sess = sf.openSession();
		sess.beginTransaction();
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<Deportista> l = sess.createCriteria(Deportista.class).add(Restrictions.idEq(d.getIdDeportista())).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(l.isEmpty()) {
			sess.close();
			return false;
		}
		d.setLastModification(new Date());
		sess.clear();
		sess.update(d);
		sess.getTransaction().commit();
		sess.close();
		return true;
	}
}
