package model;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import entities.UsuarioCompraEvento;

public class UsuarioCompraEventoDAO {
	
	public boolean insert(UsuarioCompraEvento uce) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session sess = sf.openSession();
		sess.beginTransaction();
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<UsuarioCompraEvento> l = sess.createCriteria(UsuarioCompraEvento.class).add(Restrictions.idEq(uce.getId())).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(!l.isEmpty()) {
			sess.close();
			return false;
		}
		uce.setLastModification(new Date());
		sess.save(uce);
		sess.getTransaction().commit();
		sess.close();
		return true;
	}
	public boolean delete(UsuarioCompraEvento uce) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session sess = sf.openSession();
		sess.beginTransaction();
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<UsuarioCompraEvento> l = sess.createCriteria(UsuarioCompraEvento.class).add(Restrictions.idEq(uce.getId())).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(l.isEmpty()) {
			sess.close();
			return false;
		}
		sess.delete(l.get(0));
		sess.getTransaction().commit();
		sess.close();
		return true;
	}

}
