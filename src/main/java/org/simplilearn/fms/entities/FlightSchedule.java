package org.simplilearn.fms.entities;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
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
@Table(name = "fms_flight_schedule")
public class FlightSchedule {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private Timestamp departure;
	private Timestamp arrival;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "source_id")
	private Airport source;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "destination_id")
	private Airport destination;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "flight_id")
	private Flight flight;

	@OneToMany(mappedBy = "flightSchedule", cascade = CascadeType.ALL)
	private Set<FlightSchedulePrice> flightSchedulePrice = new HashSet<>();

	public FlightSchedule() {
		this(0, null, null, null, null, null);
	}

	public FlightSchedule(int id, Timestamp departure, Timestamp arrival, Airport source, Airport destination, Flight flight) {
		this.id = id;
		this.departure = departure;
		this.arrival = arrival;
		this.source = source;
		this.destination = destination;
		this.flight = flight;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getDeparture() {
		return departure;
	}

	public void setDeparture(Timestamp departure) {
		this.departure = departure;
	}

	public Timestamp getArrival() {
		return arrival;
	}

	public void setArrival(Timestamp arrival) {
		this.arrival = arrival;
	}

	public Airport getSource() {
		return source;
	}

	public void setSource(Airport source) {
		this.source = source;
	}

	public Airport getDestination() {
		return destination;
	}

	public void setDestination(Airport destination) {
		this.destination = destination;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public Set<FlightSchedulePrice> getFlightSchedulePrice() {
		return flightSchedulePrice;
	}

	public void setFlightSchedulePrice(Set<FlightSchedulePrice> flightSchedulePrice) {
		this.flightSchedulePrice = flightSchedulePrice;
	}
	
	public String getLable() {
		String lable = "";
		if (this.id > 0) {
			String airlineName = this.flight.getAirline().getName();
			String sourceCity = this.source.getCity();
			String destinationCity = this.destination.getCity();
			String departure = this.getDeparture().toLocalDateTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

			lable = String.format("%s (%s to %s) on %s", airlineName, sourceCity, destinationCity, departure);
		}
		return lable;
	}
}
