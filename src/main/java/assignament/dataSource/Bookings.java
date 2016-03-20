package assignament.dataSource;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="bookings")
public class Bookings {
	
	@Id
	@Column(name="id_booking")
	private int idBooking;
	
	@Column(name="confirmation")
	private int confirmation;
	
	@Column(name="booking_type")
	private String booking_type;
	
	@Column(name="data")
	private Timestamp data;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_car")
	private Car car;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_user")
	private User user;
	
	public int getIdBooking() {
		return idBooking;
	}

	public void setIdBooking(int idBooking) {
		this.idBooking = idBooking;
	}

	public int getConfirmation() {
		return confirmation;
	}

	public void setConfirmation(int confirmation) {
		this.confirmation = confirmation;
	}

	public String getBooking_type() {
		return booking_type;
	}

	public void setBooking_type(String booking_type) {
		this.booking_type = booking_type;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
