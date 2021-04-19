package com.ss.dao;

import com.ss.entity.Airport;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AirportDAO extends BaseDAO<Airport> {
    public AirportDAO(Connection conn) {
        super(conn);
    }

    @Override
    public List<Airport> extractData(ResultSet rs) throws SQLException {
        List<Airport> airports = new ArrayList<>();
        while (rs.next()) {
            Airport airport = new Airport(rs.getString("iata_id"),
                    rs.getString("city"));
            airports.add(airport);
        }
        return airports;
    }

    public void addAirport(Airport airport) throws ClassNotFoundException, SQLException {
        save("INSERT INTO airport (iata_id, city) VALUES (?, ?)",
                new Object[] { airport.getAirportCode(), airport.getCityName() });
    }

    public void updateAirport(Airport newAirport, Airport oldAirport)
            throws ClassNotFoundException, SQLException {
        save("UPDATE airport set iata_id = ?, city = ? where iata_id = ?",
                new Object[] { newAirport.getAirportCode(), newAirport.getCityName(),
                        oldAirport.getAirportCode()});
    }

    public void deleteAirport(Airport airport) throws ClassNotFoundException, SQLException {
        save("DELETE FROM airport where iata_id = ?",
                new Object[] { airport.getAirportCode() });
    }

    public String checkAirport(Airport airport) throws ClassNotFoundException, SQLException {
        List<Airport> airports = read("SELECT * FROM airport where iata_id = ?",
                new Object[] { airport.getAirportCode() });
        if (airports.size() > 0) {
            return airport.getAirportCode();
        }
        return null;
    }

    public List<Airport> getAllAirports() throws ClassNotFoundException, SQLException {
        return read("select * from airport", null);
    }

}
