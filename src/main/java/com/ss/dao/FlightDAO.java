package com.ss.dao;

import com.ss.entity.Airport;
import com.ss.entity.Flight;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FlightDAO extends BaseDAO<Flight> {
    public FlightDAO(Connection conn) {
        super(conn);
    }

    @Override
    public List<Flight> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
        List<Flight> flights = new ArrayList<>();
        while (rs.next()) {
            Flight flight = new Flight();
            flight.setId(rs.getInt("id"));
            flight.getRoute().setId(rs.getInt("route_id"));
            flight.setDepartureTime(rs.getObject(4, LocalDateTime.class));
            flights.add(flight);
        }
        return flights;
    }

    public Integer addFlight(Flight flight) throws SQLException, ClassNotFoundException {
        return saveWithPK("INSERT INTO flight (route_id, airplane_id, departure_time," +
                        " reserved_seats, seat_price) VALUES (?, ?, ?, ?, ?)",
                new Object[] { flight.getRoute().getId(),
                               flight.getAirplane().getId(),
                               flight.getDepartureTime(),
                               flight.getReservedSeats(),
                               flight.getSeatPrice()});
    }

    public Integer updateFlight(Flight flight) throws SQLException, ClassNotFoundException {
        return saveWithPK("UPDATE flight set route_id = ?, airplane_id = ?, departure_time = ?," +
                        " reserved_seats = ? , seat_price = ? where id = ?",
                new Object[] { flight.getRoute().getId(),
                        flight.getAirplane().getId(),
                        flight.getDepartureTime(),
                        flight.getReservedSeats(),
                        flight.getSeatPrice(),
                        flight.getId()});
    }

    public void deleteFlight(Flight flight) throws ClassNotFoundException, SQLException {
        save("DELETE FROM flight where id = ?",
                new Object[] { flight.getId() });
    }

    public List<Flight> getAllFlights() throws ClassNotFoundException, SQLException {
        return read("select * from flight", null);
    }
}
