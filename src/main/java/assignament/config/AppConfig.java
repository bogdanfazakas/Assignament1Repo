package assignament.config;

import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import assignament.dataSource.BookingDao;
import assignament.dataSource.Bookings;
import assignament.dataSource.BookingsDaoImpl;
import assignament.dataSource.Car;
import assignament.dataSource.CarDao;
import assignament.dataSource.CarsDaoImpl;
import assignament.dataSource.User;
import assignament.dataSource.UserDao;
import assignament.dataSource.UserDaoImpl;
import assignament.domainLogic.CarServiceInterface;
import assignament.domainLogic.ConcreteCarService;



@Configuration
@EnableAutoConfiguration
public class AppConfig extends WebMvcConfigurerAdapter {
	
	/* DB Constants*/
	private final String dbURL = "jdbc:mysql://localhost:3306/assignament1";
	private final String dbUsername = "root";
	private final String dbPassword = "";
	
	@Bean(name = "viewResolver")
	public InternalResourceViewResolver getViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".html");
		return viewResolver;
	}
	
	@Bean(name = "dataSource")
	public DataSource getDataSource() throws InterruptedException {

		try {
			BasicDataSource dataSource = new BasicDataSource();
			dataSource.setDriverClassName("com.mysql.jdbc.Driver");
			dataSource.setUrl(dbURL);
			dataSource.setUsername(dbUsername);
			dataSource.setPassword(dbPassword);

			System.out.println("Connected to Database : " + dbURL);
			
			return dataSource;
		} catch (Exception e) {
			System.err.println("A aparut o eroare in AppConfig la conexiunea cu baza de date");
		}
		return null;
	}
	
	private Properties getHibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.show_sql", "false");
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		properties.put("hibernate.enable_lazy_load_no_trans", "true");
		return properties;
	}
	
	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) {
		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
		sessionBuilder.addProperties(getHibernateProperties());
		sessionBuilder.addAnnotatedClasses(User.class,Car.class,Bookings.class);
		return sessionBuilder.buildSessionFactory();
	}
	
	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
		return transactionManager;
	}
	
	@Autowired
	@Bean(name = "UserDao")
	public UserDao getUserDao(SessionFactory sessionFactory) {
		return new UserDaoImpl(sessionFactory);
	}
	
	@Autowired
	@Bean(name = "CarDao")
	public CarDao getCarDao(SessionFactory sessionFactory) {
		return new CarsDaoImpl(sessionFactory);
	}
	
	@Autowired
	@Bean(name = "BookingDao")
	public BookingDao getBookingDao(SessionFactory sessionFactory) {
		return new BookingsDaoImpl(sessionFactory);
	}
	
	@Bean
	public CarServiceInterface carServices(){
		return new ConcreteCarService();
	}
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	public static void main(String[] args) throws Exception {
		   //SpringApplication.run(AppConfig.class, args);
		   
		   ApplicationContext context =
				   new AnnotationConfigApplicationContext(AppConfig.class);
		   
		   ConcreteCarService car = context.getBean(ConcreteCarService.class);
		   List<Car> test ;
		   test = car.listCars();
		   for (int i = 0; i < test.size(); i++) {
			 Car c = new Car();
			 c = test.get(i);
			 System.out.println(c.getManufacturer());
		   }
		   
		 }
	
	
}
