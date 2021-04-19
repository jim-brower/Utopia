package com.ss.service;

import com.ss.dao.*;
import com.ss.entity.Airport;
import com.ss.entity.Employee;
import com.ss.entity.Flight;
import com.ss.entity.Traveler;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.util.List;

public class AdminService {
    ConnectionUtil connUtil = new ConnectionUtil();

    // wrapper to prevent ever method from being long
    interface DBTransaction {
        Object doTransaction(Connection conn) throws ClassNotFoundException, SQLException;
    }
    private Object transactionWrapper(DBTransaction transaction) {
        Connection conn = null;
        Object result = null;
        try {
            conn = connUtil.getConnection();
            result = transaction.doTransaction(conn);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                conn.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    // Airport methods
    public void addAirport(Airport airport) {
        // No prereqs to add a flight
        DBTransaction transaction = (conn) -> {
            AirportDAO adao = new AirportDAO(conn);
            adao.addAirport(airport);
            conn.commit();
            return null;
        };
        transactionWrapper(transaction);
    }

    public void updateAirport(Airport newAirportInfo, Airport oldAirportInfo) {
        DBTransaction transaction = (conn) -> {
            AirportDAO adao = new AirportDAO(conn);
            adao.updateAirport(newAirportInfo, oldAirportInfo);
            conn.commit();
            return null;
        };
        transactionWrapper(transaction);
    }

    public void deleteAirport(Airport airport) {
        DBTransaction transaction = (conn) -> {
            AirportDAO adao = new AirportDAO(conn);
            adao.deleteAirport(airport);
            conn.commit();
            return null;
        };
        transactionWrapper(transaction);
    }

    public List<Airport> listAirports() {
        DBTransaction transaction = (conn) -> {
            AirportDAO adao = new AirportDAO(conn);
            List<Airport> airports = adao.getAllAirports();
            conn.commit();
            return airports;
        };
        return (List<Airport>) transactionWrapper(transaction);
    }

    public String checkAirport(Airport airport) {
        DBTransaction transaction = (conn) -> {
            AirportDAO adao = new AirportDAO(conn);
            String check = adao.checkAirport(airport);
            conn.commit();
            return check;
        };
        return (String) transactionWrapper(transaction);
    }

    // Flight methods
    public void addFlight(Flight flight) {
        DBTransaction transaction = conn -> {
            Airport origin = flight.getRoute().getOrigin();
            Airport destination = flight.getRoute().getDestination();
            AirportDAO adao = new AirportDAO(conn);
            String checkOrigin = adao.checkAirport(origin);
            String checkDestination = adao.checkAirport(destination);
            if (checkOrigin == null || checkDestination == null) {
                throw new SQLIntegrityConstraintViolationException();
            }
            RouteDAO rdao = new RouteDAO(conn);
            int routeId = rdao.checkRoute(flight.getRoute());
            if (routeId < 0) {
                 routeId = rdao.addRoute(flight.getRoute());
            }
            flight.getRoute().setId(routeId);
            // ASSUME AIRPLANE EXISTS
            // rest of information provided at creation time
            // LocalDateTime departureTime;
            // Integer reservedSeats;
            // double seatPrice;
            FlightDAO fdao = new FlightDAO(conn);
            fdao.addFlight(flight);
            conn.commit();
            return null;
        };
        transactionWrapper(transaction);
    }

    public void updateFlight(Flight flight) {
        DBTransaction transaction = conn -> {
            Airport origin = flight.getRoute().getOrigin();
            Airport destination = flight.getRoute().getDestination();
            AirportDAO adao = new AirportDAO(conn);
            String checkOrigin = adao.checkAirport(origin);
            String checkDestination = adao.checkAirport(destination);
            if (checkOrigin == null || checkDestination == null) {
                throw new SQLIntegrityConstraintViolationException();
            }
            RouteDAO rdao = new RouteDAO(conn);
            int routeId = rdao.checkRoute(flight.getRoute());
            if (routeId < 0) {
                routeId = rdao.addRoute(flight.getRoute());
            }
            flight.getRoute().setId(routeId);
            // ASSUME AIRPLANE EXISTS
            // rest of information provided at creation time
            // LocalDateTime departureTime;
            // Integer reservedSeats;
            // double seatPrice;
            FlightDAO fdao = new FlightDAO(conn);
            fdao.updateFlight(flight);

            conn.commit();
            return null;
        };
        transactionWrapper(transaction);
    }

    public void deleteFlight(Flight flight) {
        DBTransaction transaction = conn -> {
            FlightDAO fdao = new FlightDAO(conn);
            fdao.deleteFlight(flight);
            conn.commit();
            return null;
        };
        transactionWrapper(transaction);
    }

    public List<Flight> listFlights() {
        DBTransaction transaction = (conn) -> {
            FlightDAO fdao = new FlightDAO(conn);
            List<Flight> flights = fdao.getAllFlights();
            for (Flight f : flights) {
                RouteDAO rdao = new RouteDAO(conn);
                f.setRoute(rdao.getCompleteRouteFromId(f.getRoute()));
            }
            conn.commit();
            return flights;
        };
        return (List<Flight>) transactionWrapper(transaction);
    }

    // employee methods
    public void addEmployee(Employee employee) {
        DBTransaction transaction = (conn) -> {
            EmployeeDAO edao = new EmployeeDAO(conn);
            edao.addEmployee(employee);
            conn.commit();
            return null;
        };
        transactionWrapper(transaction);
    }

    public void updateEmployee(Employee e) {
        DBTransaction transaction = (conn) -> {
            EmployeeDAO edao = new EmployeeDAO(conn);
            edao.updateEmployee(e);
            conn.commit();
            return null;
        };
        transactionWrapper(transaction);
    }

    public void deleteEmployee(Employee e) {
        DBTransaction transaction = (conn) -> {
            EmployeeDAO edao = new EmployeeDAO(conn);
            edao.deleteEmployee(e);
            conn.commit();
            return null;
        };
        transactionWrapper(transaction);
    }

    public List<Employee> listEmployees() {
        DBTransaction transaction = (conn) -> {
            EmployeeDAO edao = new EmployeeDAO(conn);
            List<Employee> employees = edao.getAllEmployees();
            conn.commit();
            return employees;
        };
        return (List<Employee>) transactionWrapper(transaction);
    }

    // Traveler methods
    public void addTraveler(Traveler t) {
        DBTransaction transaction = (conn) -> {
            TravelerDAO tdao = new TravelerDAO(conn);
            tdao.addTraveler(t);
            conn.commit();
            return null;
        };
        transactionWrapper(transaction);
    }

    public void updateTraveler(Traveler t) {
        DBTransaction transaction = (conn) -> {
            TravelerDAO tdao = new TravelerDAO(conn);
            tdao.updateTraveler(t);
            conn.commit();
            return null;
        };
        transactionWrapper(transaction);
    }

    public void deleteTraveler(Traveler t) {
        DBTransaction transaction = (conn) -> {
            TravelerDAO tdao = new TravelerDAO(conn);
            tdao.deleteTraveler(t);
            conn.commit();
            return null;
        };
        transactionWrapper(transaction);
    }

    public List<Traveler> listTravelers() {
        DBTransaction transaction = (conn) -> {
            TravelerDAO tdao = new TravelerDAO(conn);
            List<Traveler> travelers = tdao.getAllTravelers();
            conn.commit();
            return travelers;
        };
        return (List<Traveler>) transactionWrapper(transaction);
    }
}
