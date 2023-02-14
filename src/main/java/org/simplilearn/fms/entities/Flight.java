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
@Table(name = "fms_flights")
public class Flight {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String model;

	@OneToMany(mappedBy = "flight", cascade = CascadeType.PERSIST)
	private Set<FlightSchedule> flightSchedule = new HashSet<>();

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "airline_id")
	private Airline airline;

	public Flight() {
		this(0, null, null);
	}
	
	

	public Flight(int id, String model, Airline airline) {
		this.id = id;
		this.model = model;
		this.airline = airline;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Set<FlightSchedule> getFlightSchedule() {
		return flightSchedule;
	}

	public void setFlightSchedule(Set<FlightSchedule> flightSchedule) {
		this.flightSchedule = flightSchedule;
	}

	public Airline getAirline() {
		return airline;
	}

	public void setAirline(Airline airline) {
		this.airline = airline;
	}
}
