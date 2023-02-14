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

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "fms_flight_schedule_id")
	private FlightSchedule flightSchedule;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "fms_seat_type_id")
	private SeatType seatType;

	@OneToMany(cascade = CascadeType.ALL)
	private Set<Ticket> tickets = new HashSet<>();

	public FlightSchedulePrice() {
		this(0, 0.0, 0, null, null);
	}

	public FlightSchedulePrice(int id, double price, int availableSeat, FlightSchedule flightSchedule,
			SeatType seatType) {
		this.id = id;
		this.price = price;
		this.availableSeat = availableSeat;
		this.flightSchedule = flightSchedule;
		this.seatType = seatType;
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

	public SeatType getSeatType() {
		return seatType;
	}

	public void setSeatType(SeatType seatType) {
		this.seatType = seatType;
	}
}
