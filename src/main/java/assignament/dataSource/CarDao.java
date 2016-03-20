package assignament.dataSource;

import java.util.List;

public interface CarDao {
	
	public void saveOrUpdate(Car car);
	
	public void delete(int id);
	
	public List<Car> viewCars();
}
