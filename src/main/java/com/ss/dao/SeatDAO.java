package com.ss.dao;

import com.ss.entity.Seat;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SeatDAO extends BaseDAO<Seat> {
    public SeatDAO(Connection conn) {
        super(conn);
    }

    @Override
    public List<Seat> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
        return null;
    }
}
