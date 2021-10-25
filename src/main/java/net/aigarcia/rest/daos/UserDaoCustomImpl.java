package net.aigarcia.rest.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Component;

import net.aigarcia.rest.models.User;

@Component
public class UserDaoCustomImpl implements UserDaoCustom {

	@PersistenceContext
	private EntityManager em;
	 
	@Override
	public List<User> findByEmail(String email) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<User> cQuery = builder.createQuery(User.class);
		Root<User> root = cQuery.from(User.class);
		cQuery.select(root).where(builder.like(root.<String>get("email"), "%" + email + "%"));
		TypedQuery<User> query = em.createQuery(cQuery);
		return query.getResultList();
//		return null;
	}

}
