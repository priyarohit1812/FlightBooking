package org.simplilearn.fms.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "fms_ticket")
public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fms_users_id")
	private User user;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fms_flight_schedule_price_id")
	private FlightSchedulePrice price;

	private int noOfSeat;
	private double totalPrice;
	
	public Ticket() {
		this(0, null, null, 0, 0.0);
	}

	public Ticket(int id, User user, FlightSchedulePrice price, int noOfSeat, double totalPrice) {
		this.id = id;
		this.user = user;
		this.price = price;
		this.noOfSeat = noOfSeat;
		this.totalPrice = totalPrice;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public FlightSchedulePrice getPrice() {
		return price;
	}

	public void setPrice(FlightSchedulePrice price) {
		this.price = price;
	}

	public int getNoOfSeat() {
		return noOfSeat;
	}

	public void setNoOfSeat(int noOfSeat) {
		this.noOfSeat = noOfSeat;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
}
