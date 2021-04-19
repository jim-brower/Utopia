package com.ss.service;

import com.ss.entity.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AdminServiceTest {

    @org.junit.jupiter.api.Test
    void addAirport() {
        Airport ap = new Airport("zzz", "sleep city");
        AdminService admin = new AdminService();
        admin.addAirport(ap);
    }

    @Test
    void updateAirport() {
        Airport newAp = new Airport("zzz", "awake city");
        Airport oldAp = new Airport("zzz", "sleep city");
        AdminService admin = new AdminService();
        admin.updateAirport(newAp, oldAp);
    }

    @Test
    void deleteAirport() {
        Airport ap = new Airport("zzz", "PLACEHOLDER");
        AdminService admin = new AdminService();
        admin.deleteAirport(ap);
    }

    @Test
    void listAirports() {
        AdminService admin = new AdminService();
        List<Airport> airports = admin.listAirports();
        for (Airport ap : airports) {
            System.out.println(ap.getAirportCode() + "   " + ap.getCityName());
        }
    }

    @Test
    void checkAirport() {
        Airport ap = new Airport("zzz", "sleep city");
        AdminService admin = new AdminService();
        assertNotNull(admin.checkAirport(ap));
        Airport ap2 = new Airport("fff", "fake city");
        admin = new AdminService();
        assertNull(admin.checkAirport(ap2));
    }

    @Test
    void addFlight() {
        Flight f = new Flight();
        f.setAirplane(new Airplane(1, new AirplaneType()));
        f.setRoute(new Route(new Airport("JFK", "abc"), new Airport("PDX", "def")));
        f.setDepartureTime(LocalDateTime.now());
        f.setReservedSeats(10);
        f.setSeatPrice(200.00);

        AdminService admin = new AdminService();
        admin.addFlight(f);
    }

    @Test
    void updateFlight() {
        Flight f = new Flight();
        f.setId(3);
        f.setAirplane(new Airplane(1, new AirplaneType()));
        f.setRoute(new Route(new Airport("JFK", "abc"), new Airport("PDX", "def")));
        f.setDepartureTime(LocalDateTime.now());
        f.setReservedSeats(50);
        f.setSeatPrice(1000.00);

        AdminService admin = new AdminService();
        admin.updateFlight(f);
    }

    @Test
    void deleteFlight() {
        Flight f = new Flight();
        f.setId(3);
        AdminService admin = new AdminService();
        admin.deleteFlight(f);
    }

    @Test
    void listFlights() {
        AdminService admin = new AdminService();
        List<Flight> flights = admin.listFlights();
        for (Flight f : flights) {
            System.out.println(f.getId() + "   " + f.getRoute().getOrigin().getAirportCode() +
                    " --> " + f.getRoute().getDestination().getAirportCode() + "   " +
                    f.getDepartureTime());
        }
    }

    @Test
    void addEmployee() {
        AdminService admin = new AdminService();
        Employee e = new Employee();
        e.setGivenName("Jim");
        e.setFamilyName("Brower");
        e.setUserName("jbrower");
        e.setEmail("james.brower@smoothstack.com");
        e.setPassword("greatpassword");
        e.setPhone("12345678901");
        admin.addEmployee(e);
    }

    @Test
    void updateEmployee() {
        AdminService admin = new AdminService();
        Employee e = new Employee();
        e.setId(8);
        e.setGivenName("James");
        e.setFamilyName("Brower");
        e.setUserName("jrbrower");
        e.setEmail("jim.brower@smoothstack.com");
        e.setPassword("badpassword");
        e.setPhone("19446525671");
        admin.updateEmployee(e);
    }

    @Test
    void deleteEmployee() {
        AdminService admin = new AdminService();
        Employee e = new Employee();
        e.setId(8);
        admin.deleteEmployee(e);
    }

    @Test
    void listEmployees() {
        AdminService admin = new AdminService();
        List<Employee> employees = admin.listEmployees();
        for (Employee e : employees) {
            System.out.println(e.getGivenName() + " " + e.getFamilyName() + " " + e.getUserName());
        }
    }

    @Test
    void addTraveler() {
        AdminService admin = new AdminService();
        Traveler t = new Traveler();
        t.setGivenName("Jim");
        t.setFamilyName("Brower");
        t.setUserName("newname");
        t.setEmail("fake@fake.com");
        t.setPassword("greatpassword");
        t.setPhone("234243342");
        admin.addTraveler(t);
    }

    @Test
    void updateTraveler() {
        AdminService admin = new AdminService();
        Employee e = new Employee();
        e.setId(13);
        e.setGivenName("James");
        e.setFamilyName("Brower");
        e.setUserName("brandnew");
        e.setEmail("supernew@new");
        e.setPassword("badpassword");
        e.setPhone("194465");
        admin.updateEmployee(e);
    }

    @Test
    void deleteTraveler() {
        AdminService admin = new AdminService();
        Traveler t = new Traveler();
        t.setId(13);
        admin.deleteTraveler(t);
    }

    @Test
    void listTravelers() {
        AdminService admin = new AdminService();
        List<Traveler> travelers = admin.listTravelers();
        for (Traveler t : travelers) {
            System.out.println(t.getGivenName() + " " + t.getFamilyName() + " " + t.getUserName());
        }
    }
}