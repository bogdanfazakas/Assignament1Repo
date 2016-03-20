package assignament.dataSource;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;


public class BookingsDaoImpl implements BookingDao {
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	public BookingsDaoImpl(){
	}
	
	public BookingsDaoImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	@Transactional
	public void saveOrUpdate(Bookings booking) {
		sessionFactory.getCurrentSession().saveOrUpdate(booking);
		
	}

	@Override
	@Transactional
	public void delete(int id) {
		Bookings booking = new Bookings();
		booking.setIdBooking(id);
		sessionFactory.getCurrentSession().delete(booking);
	}

	@Override
	@Transactional
	public List<Bookings> viewBookings() {
		@SuppressWarnings("unchecked")
		List<Bookings> booking = (List<Bookings>) sessionFactory
				.getCurrentSession().createCriteria(Bookings.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return booking;
	}
	
	
}
