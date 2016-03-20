package assignament.domainLogic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import assignament.dataSource.Car;
import assignament.dataSource.CarDao;


@Component
public class ConcreteCarService implements CarServiceInterface {
	
	@Autowired 
	private CarDao carDao;
	
	@Override
	public List<Car> listCars() {
		System.out.println(carDao.viewCars());
		return carDao.viewCars();
	}

}
