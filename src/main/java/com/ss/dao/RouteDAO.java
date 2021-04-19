package com.ss.dao;

import com.ss.entity.Airport;
import com.ss.entity.Route;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RouteDAO extends BaseDAO<Route> {
    public RouteDAO(Connection conn) {
        super(conn);
    }

    @Override
    public List<Route> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
        List<Route> routes = new ArrayList<>();
        while (rs.next()) {
            Route route = new Route();
            route.setId(rs.getInt("id"));
            route.getOrigin().setAirportCode(rs.getString("origin_id"));
            route.getDestination().setAirportCode(rs.getString("destination_id"));
            routes.add(route);
        }
        return routes;
    }

    public Integer addRoute(Route route) throws ClassNotFoundException, SQLException {
        return saveWithPK("INSERT INTO route (origin_id, destination_id) VALUES (?, ?)",
                new Object[] { route.getOrigin().getAirportCode(),
                        route.getDestination().getAirportCode() });
    }

    public Integer checkRoute(Route route) throws ClassNotFoundException, SQLException {
        List<Route> routes = read("SELECT * FROM route where origin_id = ? AND destination_id = ?",
                new Object[] { route.getOrigin().getAirportCode(),
                        route.getDestination().getAirportCode() });
        if (routes.size() > 0) {
            return routes.get(0).getId();
        }
        return -1;
    }

    public Route getCompleteRouteFromId(Route route) throws ClassNotFoundException, SQLException {
        List<Route> routes = read("SELECT * FROM route where id = ?",
                new Object[] { route.getId() });
        return routes.get(0);
    }
}
