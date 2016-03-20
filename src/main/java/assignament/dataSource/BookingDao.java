package assignament.dataSource;

import java.util.List;


public interface BookingDao {
	
	public void saveOrUpdate(Bookings booking);
	
	public void delete(int id);
	
	public List<Bookings> viewBookings();
}
