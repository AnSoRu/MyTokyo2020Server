package model;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import entities.Pais;

public class PaisDAO {
	
	public List<Pais> findAllPaises(){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session sess = sf.openSession();
		sess.beginTransaction();
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<Pais> l = (List<Pais>)sess.createCriteria(Pais.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		sess.close();
		return l;
	}
	
	public List<Pais> getPaisByID(int idPais) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session sess = sf.openSession();
		sess.beginTransaction();
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<Pais> l = sess.createCriteria(Pais.class).add(Restrictions.idEq(idPais)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		sess.close();
		return l;
	}
	
	public List<Pais> getDeportistaByName(String name) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session sess = sf.openSession();
		sess.beginTransaction();
		@SuppressWarnings({ "deprecation", "unchecked" })
		List<Pais> l = sess.createCriteria(Pais.class).add(Restrictions.ilike("nombre", name, MatchMode.EXACT)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		sess.close();
		return l;
	}

}
