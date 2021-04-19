package com.ss.dao;

import com.ss.entity.Booking;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BookingDAO extends BaseDAO<Booking> {
    public BookingDAO(Connection conn) {
        super(conn);
    }

    @Override
    public List<Booking> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
        return null;
    }
}
