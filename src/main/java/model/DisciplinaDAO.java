package model;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import entities.Disciplina;

public class DisciplinaDAO {
	
	private static HibernateUtil hU;
	
	
	public DisciplinaDAO() {
		DisciplinaDAO.hU = new HibernateUtil();
	}

	public List<Disciplina> findAllDisciplinas(){
		SessionFactory sf = hU.getSessionFactory();
		Session sess = sf.openSession();
		sess.beginTransaction();
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<Disciplina> l = (List<Disciplina>)sess.createCriteria(Disciplina.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		sess.close();
		return l;
	}
	
	public List<Disciplina> getDisciplinaByID(int idDisciplina) {
		SessionFactory sf = hU.getSessionFactory();
		Session sess = sf.openSession();
		sess.beginTransaction();
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<Disciplina> l = sess.createCriteria(Disciplina.class).add(Restrictions.idEq(idDisciplina)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		sess.close();
		return l;
	}
	
	public List<Disciplina> getDisciplinaByName(String name) {
		SessionFactory sf = hU.getSessionFactory();
		Session sess = sf.openSession();
		sess.beginTransaction();
		@SuppressWarnings({ "deprecation", "unchecked" })
		List<Disciplina> l = sess.createCriteria(Disciplina.class).add(Restrictions.ilike("nombre", name, MatchMode.EXACT)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		sess.close();
		return l;
	}
	
	public boolean updateDisciplina(Disciplina d) {
		SessionFactory sf = hU.getSessionFactory();
		Session sess = sf.openSession();
		sess.beginTransaction();
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<Disciplina> l = sess.createCriteria(Disciplina.class).add(Restrictions.idEq(d.getIdDisciplina())).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
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
