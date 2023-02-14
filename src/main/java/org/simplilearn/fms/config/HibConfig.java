package org.simplilearn.fms.config;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.simplilearn.fms.entities.Airline;
import org.simplilearn.fms.entities.Airport;
import org.simplilearn.fms.entities.Flight;
import org.simplilearn.fms.entities.FlightSchedule;
import org.simplilearn.fms.entities.FlightSchedulePrice;
import org.simplilearn.fms.entities.SeatType;
import org.simplilearn.fms.entities.Ticket;
import org.simplilearn.fms.entities.User;


public class HibConfig {
	public static SessionFactory getSessionFactory() {
		Configuration configuration = new Configuration();
		Properties properties = new Properties();
		properties.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
		properties.put(Environment.URL, "jdbc:mysql://localhost:3306/fmsdb");
		properties.put(Environment.USER, "root");
		properties.put(Environment.PASS, "Namish@1602");
		properties.put(Environment.SHOW_SQL, true);
		properties.put(Environment.FORMAT_SQL, true);
		properties.put(Environment.HBM2DDL_AUTO, "update");
		properties.put(Environment.C3P0_MIN_SIZE, 10);
		properties.put(Environment.C3P0_MAX_SIZE, 50);
		properties.put(Environment.C3P0_MAX_STATEMENTS, 100);
		properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
		configuration.setProperties(properties);
		configuration.addAnnotatedClass(User.class);
		configuration.addAnnotatedClass(SeatType.class);
		configuration.addAnnotatedClass(Airport.class);
		configuration.addAnnotatedClass(Airline.class);
		configuration.addAnnotatedClass(Flight.class);
		configuration.addAnnotatedClass(FlightSchedule.class);
		configuration.addAnnotatedClass(FlightSchedulePrice.class);
		configuration.addAnnotatedClass(Ticket.class);
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		return sessionFactory;
		}
	}
		