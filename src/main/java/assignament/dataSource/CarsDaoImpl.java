package assignament.dataSource;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;


public class CarsDaoImpl implements CarDao{
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	public CarsDaoImpl(){
	}
	
	public CarsDaoImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional
	public void saveOrUpdate(Car car) {
		sessionFactory.getCurrentSession().saveOrUpdate(car);
	}

	@Override
	@Transactional
	public void delete(int id) {
		Car car = new Car();
		car.setIdCar(id);
		sessionFactory.getCurrentSession().delete(car);
	}

	@Override
	@Transactional
	public List<Car> viewCars() {
		@SuppressWarnings("unchecked")
		List<Car> car = (List<Car>) sessionFactory
				.getCurrentSession().createCriteria(Car.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return car;
	}
	
}
