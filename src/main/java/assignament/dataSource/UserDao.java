package assignament.dataSource;

import java.util.List;


public interface UserDao {
	
	public void saveOrUpdate(User user);
	
	public void delete(int id);
	
	public List<User> viewUser();
}
