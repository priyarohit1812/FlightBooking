package org.simplilearn.fms.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "fms_airports")
public class Airport {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String city;
	private String state;
	private String country;
	private String pincode;
	
	@OneToMany(mappedBy = "source", cascade = CascadeType.PERSIST)
	private Set<FlightSchedule> sourceAirports= new HashSet<>();
	
	@OneToMany(mappedBy = "destination", cascade = CascadeType.PERSIST)
	private Set<FlightSchedule> destinationAirports= new HashSet<>();
	
	public Airport() {
		this(0,"","","","","");
	}
	public Airport(int id, String name, String city, String state, String country, String pincode) {
		super();
		this.id = id;
		this.name = name;
		this.city = city;
		this.state = state;
		this.country = country;
		this.pincode = pincode;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public Set<FlightSchedule> getSourceAirports() {
		return sourceAirports;
	}
	public void setSourceAirports(Set<FlightSchedule> sourceAirports) {
		this.sourceAirports = sourceAirports;
	}
	public Set<FlightSchedule> getDestinationAirports() {
		return destinationAirports;
	}
	public void setDestinationAirports(Set<FlightSchedule> destinationAirports) {
		this.destinationAirports = destinationAirports;
	}
	
	
}
