package model;

import java.util.Date;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import entities.Usuario;

public class UsuarioDAO {
	
	static final Logger uLogger = LogManager.getLogger(UsuarioDAO.class.getName());
	
	private static HibernateUtil hU;
	
	public UsuarioDAO() {
		UsuarioDAO.hU = new HibernateUtil();
	}
	
	public List<Usuario> findAllUsuarios(){
		SessionFactory sf = hU.getSessionFactory();
		Session sess = sf.openSession();
		sess.beginTransaction();
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<Usuario> l = (List<Usuario>)sess.createCriteria(Usuario.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		sess.close();
		return l;
	}
	
	public List<Usuario> getUsuarioByEmail(String email) {
		SessionFactory sf = hU.getSessionFactory();
		Session sess = sf.openSession();
		sess.beginTransaction();
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<Usuario> l = sess.createCriteria(Usuario.class).add(Restrictions.idEq(email)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		sess.close();
		return l;
	}
	
	public boolean insertUsuario(Usuario u) {
		uLogger.info("Entering insertUsuario");
		SessionFactory sf = hU.getSessionFactory();
		Session sess = sf.openSession();
		sess.beginTransaction();
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<Usuario> l = sess.createCriteria(Usuario.class).add(Restrictions.idEq(u.getEmail())).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(!l.isEmpty()) {
			sess.close();
			uLogger.error("The user with email = " + u.getEmail() + " already exists.");
			uLogger.info("Exiting insertUsuario.");
			return false;
		}
		u.setLastModification(new Date());
		sess.save(u);
		sess.getTransaction().commit();
		sess.close();
		uLogger.info("User with email = " + u.getEmail() + " inserted correctly.");
		uLogger.info("Exiting insertUsuario");
		return true;
	}
	
	public boolean updateUsuario(Usuario u) {
		SessionFactory sf = hU.getSessionFactory();
		Session sess = sf.openSession();
		sess.beginTransaction();
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<Usuario> l = sess.createCriteria(Usuario.class).add(Restrictions.idEq(u.getEmail())).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(l.isEmpty()) {
			sess.close();
			return false;
		}
		u.setLastModification(new Date());
		sess.clear();
		sess.update(u);
		sess.getTransaction().commit();
		sess.close();
		return true;
	}
	
	public boolean deleteUsuario(Usuario u) {
		SessionFactory sf = hU.getSessionFactory();
		Session sess = sf.openSession();
		sess.beginTransaction();
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<Usuario> l = sess.createCriteria(Usuario.class).add(Restrictions.idEq(u.getEmail())).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
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
