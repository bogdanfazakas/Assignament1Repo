package assignament.dataSource;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

public class UserDaoImpl implements UserDao{
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	public UserDaoImpl(){
	}
	
	public UserDaoImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional
	public void saveOrUpdate(User user) {
		sessionFactory.getCurrentSession().saveOrUpdate(user);
	}

	@Override
	@Transactional
	public void delete(int id) {
		User user = new User();
		user.setIdUser(id);
		sessionFactory.getCurrentSession().delete(user);
	}

	@Override
	@Transactional
	public List<User> viewUser() {
		
		@SuppressWarnings("unchecked")
		List<User> users = (List<User>) sessionFactory
				.getCurrentSession().createCriteria(User.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return users;
	}

}
