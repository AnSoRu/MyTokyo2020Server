package model;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import entities.Deportista;

public class DeportistaDAO {
	
	public List<Deportista> findAllDeportistas(){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session sess = sf.openSession();
		sess.beginTransaction();
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<Deportista> l = (List<Deportista>)sess.createCriteria(Deportista.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		sess.close();
		return l;
	}
	
	public List<Deportista> getDeportistaByID(int idDeportista) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session sess = sf.openSession();
		sess.beginTransaction();
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<Deportista> l = sess.createCriteria(Deportista.class).add(Restrictions.idEq(idDeportista)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		sess.close();
		return l;
	}
	
	public List<Deportista> getDeportistaByName(String name) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session sess = sf.openSession();
		sess.beginTransaction();
		@SuppressWarnings({ "deprecation", "unchecked" })
		List<Deportista> l = sess.createCriteria(Deportista.class).add(Restrictions.ilike("nombre", name, MatchMode.EXACT)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		sess.close();
		return l;
	}
}
