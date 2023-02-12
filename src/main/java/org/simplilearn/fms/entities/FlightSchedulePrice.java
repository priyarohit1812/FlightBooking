package org.simplilearn.fms.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "fms_flight_schedule_price")
public class FlightSchedulePrice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private double price;
	private int availableSeat;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fms_flight_schedule_id")
	private FlightSchedule flightSchedule;

	@OneToMany(cascade = CascadeType.ALL)
	private Set<SeatType> seatType = new HashSet<>();
	
	@OneToMany(cascade = CascadeType.ALL)
	private Set<Ticket> tickets = new HashSet<>();

	public FlightSchedulePrice() {
		this(0, 0.0, 0, null, new HashSet<>(), new HashSet<>());
	}

	public FlightSchedulePrice(int id, double price, int availableSeat, FlightSchedule flightSchedule,
			Set<SeatType> seatType, Set<Ticket> tickets) {
		this.id = id;
		this.price = price;
		this.availableSeat = availableSeat;
		this.flightSchedule = flightSchedule;
		this.seatType = seatType;
		this.tickets = tickets;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getAvailableSeat() {
		return availableSeat;
	}

	public void setAvailableSeat(int availableSeat) {
		this.availableSeat = availableSeat;
	}

	public FlightSchedule getFlightSchedule() {
		return flightSchedule;
	}

	public void setFlightSchedule(FlightSchedule flightSchedule) {
		this.flightSchedule = flightSchedule;
	}

	public Set<SeatType> getSeatType() {
		return seatType;
	}

	public void setSeatType(Set<SeatType> seatType) {
		this.seatType = seatType;
	}
}
